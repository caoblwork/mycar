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
package com.github.yingzhuo.mycar.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Range;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.yingzhuo.mycar.domain.Cost;
import com.github.yingzhuo.mycar.domain.plus.CostType;
import com.github.yingzhuo.mycar.domain.plus.GasType;
import com.github.yingzhuo.mycar.domain.plus.PaymentType;

public interface CostService {

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCostById(Integer id);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<Cost> findCostPageByUserId(Integer userId, CostType type, Date startDate, Date endDate, Pageable pageable);
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Cost> findCostByCarId(Integer carId, Date startDate, Date endDate);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<Cost> findCostPageByCarId(Integer carId, CostType type, Date startDate, Date endDate, Pageable pageable);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PaymentType findMostPaymentTypeByUserId(Integer userId, PaymentType defaultIfNotFound);
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CostType findMostCostTypeByUserId(Integer userId, CostType defaultIfNotFound);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public GasType findMostGasTypeByUserId(Integer userId, GasType defaultIfNotFound);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Double findCostTotalByCarId(Integer carId, Range<Date> dateRange);
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Double findCostTotalByUserId(Integer userId, Range<Date> dateRange);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<String, Double> findCostByCarIdGroupByCostType(Integer carId, Range<Date> dateRange);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<String, Double> findCostByUserIdGroupByCostType(Integer userId, Range<Date> dateRange);

}
