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

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.yingzhuo.mycar.service.BusinessValidator;
import com.github.yingzhuo.mycar.service.CarService;
import com.github.yingzhuo.mycar.service.CostService;

@Controller
@RequestMapping("/cost")
public class CostReportController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CostReportController.class);
	
	@Resource private CostService costService;
	@Resource private CarService  carService;
	
	@RequestMapping("/car/{carId}/{start}/{end}/COST.xls")
	public String costExcelReporter(
			@PathVariable("carId") Integer carId,
			@DateTimeFormat(pattern = "yyyyMMdd") @PathVariable("start") Date startDate,
			@DateTimeFormat(pattern = "yyyyMMdd") @PathVariable("end") Date endDate,
			ModelMap modelMap
		)
	{
		BusinessValidator.assertCarOwner(carId);
		
		LOGGER.debug("carId: {}", carId);
		LOGGER.debug("startDate: {}", DateFormatUtils.format(startDate, "yyyy-MM-dd"));
		LOGGER.debug("endDate  : {}", DateFormatUtils.format(endDate,   "yyyy-MM-dd"));
		
		modelMap.put("car", carService.findCarById(carId));
		modelMap.put("costList", costService.findCostByCarId(carId, startDate, endDate));
		
		return "cost-excel-view";
	}
}
