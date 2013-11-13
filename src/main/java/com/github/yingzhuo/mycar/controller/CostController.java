/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.yingzhuo.mycar.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.Range;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.yingzhuo.mycar.annotation.Ajax;
import com.github.yingzhuo.mycar.domain.Car;
import com.github.yingzhuo.mycar.domain.Cost;
import com.github.yingzhuo.mycar.domain.plus.CostType;
import com.github.yingzhuo.mycar.domain.plus.DateRangeFormat;
import com.github.yingzhuo.mycar.domain.plus.GasType;
import com.github.yingzhuo.mycar.domain.plus.PaymentType;
import com.github.yingzhuo.mycar.security.SecurityUtils;
import com.github.yingzhuo.mycar.service.BusinessValidator;
import com.github.yingzhuo.mycar.service.CarService;
import com.github.yingzhuo.mycar.service.CostService;
import com.github.yingzhuo.mycar.service.UserHabitService;

@Controller
@RequestMapping("/cost")
public class CostController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CostController.class);

	@Resource private CostService costService;
	@Resource private CarService carService;
	@Resource private UserHabitService userHabitService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap modelMap) {
		
		BusinessValidator.assertCarOwner();
		
		Integer userId = SecurityUtils.getPrincipalId();
		List<Car> carList = carService.findCarByOwnerId(userId);

		modelMap.put("mostCostType", costService.findMostCostTypeByUserId(userId, CostType.GAS));
		modelMap.put("mostPaymentType", costService.findMostPaymentTypeByUserId(userId, PaymentType.CASH));
		modelMap.put("mostGasType", costService.findMostGasTypeByUserId(userId, GasType._97));
		modelMap.put("lastTabIndex", userHabitService.findLastTabIndexForCostList(userId, 0, true));
		modelMap.put("pageInfoMap", userHabitService.findPageInfoForCostList(userId, 50, "date", "asc", true));
		modelMap.put("today", new DateTime().toString("yyyy/MM/dd"));
		modelMap.put("carList", carList);
		return "cost/list";
	}

	@Ajax
	@RequestMapping(value = "/ajax/delete")
	public @ResponseBody Object deleteCost(@RequestParam("id") Integer id) {
		LOGGER.debug("cost id: {}", id);
		costService.deleteCostById(id);
		return ControllerSupport.SUCCESS;
	}

	@Ajax
	@RequestMapping(value = "/ajax/{type}", method = {RequestMethod.GET, RequestMethod.POST})
	public String loadCost(
			@PathVariable(value = "type") String type,
			@RequestParam(value = "carId") Integer carId,
			Pageable pageable,
			@RequestParam("dateRange") @DateRangeFormat(pattern = "yyyy-MM-dd", delimiter = "|") Range<Date> dateRange,
			ModelMap modelMap
		)
	{
		BusinessValidator.assertCarOwner(carId);
		
		Integer userId = SecurityUtils.getPrincipalId();

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("userId: {}", userId);
			LOGGER.debug("carId: {}", carId);
			LOGGER.debug("dateRange: {}", dateRange.toString());
			
			PageRequest pr = (PageRequest) pageable;
			LOGGER.debug("pageNumber2 : {}", pr.getPageNumber());
			LOGGER.debug("pageSize2: {}", pr.getPageSize());
			LOGGER.debug("pageSort2: {}", pr.getSort());
		}

		Page<Cost> page = null;
		if (carId.intValue() == -1) {
			page = costService.findCostPageByUserId(userId, CostType.valueOf(type.toUpperCase()), dateRange.getMinimum(), dateRange.getMaximum(), pageable);
		} else {
			page = costService.findCostPageByCarId(carId, CostType.valueOf(type.toUpperCase()), dateRange.getMinimum(), dateRange.getMaximum(), pageable);
		}

		modelMap.put("pageSize", pageable.getPageSize());
		modelMap.put("pageNumber", pageable.getPageNumber() + 1);
		modelMap.put("page", page);
		return "cost/ajax/" + type + "-page";
	}

}
