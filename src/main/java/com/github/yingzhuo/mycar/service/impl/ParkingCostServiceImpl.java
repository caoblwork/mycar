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

import com.github.yingzhuo.mycar.domain.ParkingCost;
import com.github.yingzhuo.mycar.repository.CostRepo;
import com.github.yingzhuo.mycar.service.ParkingCostService;

@Service("parkingCostService")
public class ParkingCostServiceImpl implements ParkingCostService {

	@Resource CostRepo costRepo;
	
	public void addParkingCost(ParkingCost pc) {
		Validate.notNull(pc);
		costRepo.save(pc);
	}
}
