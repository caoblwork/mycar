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
package com.github.yingzhuo.mycar.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.github.yingzhuo.mycar.domain.GasCost;
import com.github.yingzhuo.mycar.repository.CostRepo;
import com.github.yingzhuo.mycar.repository.GasCostRepo;
import com.github.yingzhuo.mycar.service.GasCostService;

@Service("gasCostService")
public class GasCostServiceImpl implements GasCostService {

	@Resource private CostRepo costRepo;
	@Resource private GasCostRepo gasCostRepo;
	
	// -----------------------------------------------------------------------------------

	public void addGasCost(GasCost gasCost) {
		Validate.notNull(gasCost);
		costRepo.save(gasCost);
	}

	public Double calculateTotalSum(Integer carId) {
		Validate.notNull(carId);
		return gasCostRepo.calculateTotalSum(carId);
	}

	public Double calculateTotalCubage(Integer carId) {
		Validate.notNull(carId);
		return gasCostRepo.calculateTotalCubage(carId);
	}

}
