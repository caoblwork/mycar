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
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.yingzhuo.mycar.annotation.Ajax;
import com.github.yingzhuo.mycar.domain.Car;
import com.github.yingzhuo.mycar.domain.plus.DateRangeFormat;
import com.github.yingzhuo.mycar.security.SecurityUtils;
import com.github.yingzhuo.mycar.service.BusinessValidator;
import com.github.yingzhuo.mycar.service.CarService;
import com.github.yingzhuo.mycar.service.CostService;
import com.github.yingzhuo.mycar.service.GasCostService;
import com.github.yingzhuo.mycar.service.UserHabitService;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsController.class);
	
	@Resource private CarService carService;
	@Resource private CostService costService;
	@Resource private GasCostService gasCostService;
	@Resource private UserHabitService userHabitService;

	@RequestMapping(value = "/cost", method = RequestMethod.GET)
	public String cost(ModelMap modelMap)
	{
		BusinessValidator.assertCarOwner();
		
		Integer userId = SecurityUtils.getPrincipalId();
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("userId: {}", userId);
		}

		List<Car> carList = carService.findCarByOwnerId(userId);
		modelMap.put("carList", carList);
		modelMap.put("lastTabIndex", userHabitService.findLastTabIndexForChart1(userId, 0, true));
		return "statistics/cost";
	}

	@Ajax
	@RequestMapping(value = "/ajax/gas-info", method = RequestMethod.POST)
	public String gasInfo(
			@RequestParam("carId") Integer carId,
			ModelMap modelMap
		)
	{
		BusinessValidator.assertCarOwner(carId);
		
		Car car = carService.findCarById(carId);
		Integer totalMileage = car.getMileage();
		
		if (totalMileage == null) {
			return "statistics/ajax/mileage-not-set";
		}

		Double totalCubage = gasCostService.calculateTotalCubage(carId);
		Double totalMoney  = gasCostService.calculateTotalSum(carId);

		modelMap.put("car", car);
		modelMap.put("totalCubage", totalCubage);
		modelMap.put("totalMoney",  totalMoney);
		return "statistics/ajax/gas-info";
	}
	
	@Ajax
	@RequestMapping(value = "/ajax/cost-pie", method = RequestMethod.POST)
	public String costPie(
			@RequestParam("carId") Integer carId,
			@DateRangeFormat(pattern = "yyyy-MM-dd", delimiter = "|") @RequestParam("daterange") Range<Date> dateRange,
			ModelMap modelMap
		)
	{
		BusinessValidator.assertCarOwner(carId);
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("carId: {}", carId);
			LOGGER.debug("dateRange: {}", dateRange.toString());
		}
		
		double sum = 0D;
		Map<String, Double> map = null;
		if (carId.intValue() == -1) {
			sum = costService.findCostTotalByUserId(SecurityUtils.getPrincipalId(), dateRange);
			modelMap.put("sum", sum);
			if (sum == 0D) return "statistics/ajax/cost-pie";
			map = costService.findCostByUserIdGroupByCostType(SecurityUtils.getPrincipalId(), dateRange);
			modelMap.put("map", map);
		} else {
			sum = costService.findCostTotalByCarId(carId, dateRange);
			modelMap.put("sum", sum);
			if (sum == 0D) return "statistics/ajax/cost-pie";
			map = costService.findCostByCarIdGroupByCostType(carId, dateRange);
			modelMap.put("map", map);
		}

		return "statistics/ajax/cost-pie";
	}

}
