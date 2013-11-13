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

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.yingzhuo.mycar.domain.Cost;
import com.github.yingzhuo.mycar.domain.plus.CostType;
import com.github.yingzhuo.mycar.domain.plus.GasType;
import com.github.yingzhuo.mycar.domain.plus.PaymentType;
import com.github.yingzhuo.mycar.repository.CostRepo;
import com.github.yingzhuo.mycar.repository.GasCostRepo;
import com.github.yingzhuo.mycar.service.CostService;

@Service("costService")
@SuppressWarnings("deprecation")
public class CostServiceImpl implements CostService {

	@Resource private CostRepo costRepo;
	@Resource private GasCostRepo gasCostRepo;
//	@Resource private ParkingCostRepo parkingCostRepo;
//	@Resource private WashingCostRepo washingCostRepo;
//	@Resource private TollCostRepo tollCostRepo;
//	@Resource private FineCostRepo fineCostRepo;

	public void deleteCostById(Integer id) {
		Validate.notNull(id);
		costRepo.delete(id);
	}
	
	public Page<Cost> findCostPageByUserId(Integer userId, CostType type, Date startDate, Date endDate, Pageable pageable) {
		Validate.notNull(userId);
		Validate.notNull(type);
		Validate.notNull(startDate);
		Validate.notNull(endDate);
		Validate.notNull(pageable);
		
		return costRepo.findPageByUserId(userId, type, startDate, endDate, pageable);

//		switch (type) {
//		case GAS: 		return CostUtils.cast(gasCostRepo.findPageByUserId(userId, startDate, endDate, pageable), pageable);
//		case PARKING: 	return CostUtils.cast(parkingCostRepo.findPageByUserId(userId, startDate, endDate, pageable), pageable);
//		case WASHING:	return CostUtils.cast(washingCostRepo.findPageByUserId(userId, startDate, endDate, pageable), pageable);
//		case TOLL:		return CostUtils.cast(tollCostRepo.findPageByUserId(userId, startDate, endDate, pageable), pageable);
//		case FINE:		return CostUtils.cast(fineCostRepo.findPageByUserId(userId, startDate, endDate, pageable), pageable);
//		default:
//			return null; // won't happen.
//		}
	}

	public Page<Cost> findCostPageByCarId(Integer carId, CostType type, Date startDate, Date endDate, Pageable pageable) {
		Validate.notNull(carId);
		Validate.notNull(type);
		Validate.notNull(startDate);
		Validate.notNull(endDate);
		Validate.notNull(pageable);
		return costRepo.findPageByCarId(carId, type, startDate, endDate, pageable);
		
//		switch (type) {
//		case GAS: 		return CostUtils.cast(gasCostRepo.findPageByCarId(carId, startDate, endDate, pageable), pageable);
//		case PARKING: 	return CostUtils.cast(parkingCostRepo.findPageByCarId(carId, startDate, endDate, pageable), pageable);
//		case WASHING:	return CostUtils.cast(washingCostRepo.findPageByCarId(carId, startDate, endDate, pageable), pageable);
//		case TOLL:		return CostUtils.cast(tollCostRepo.findPageByCarId(carId, startDate, endDate, pageable), pageable);
//		case FINE:		return CostUtils.cast(fineCostRepo.findPageByCarId(carId, startDate, endDate, pageable), pageable);
//		default:
//			System.out.println("-----------------------------------------------");
//			return null; // won't happen.
//		}
	}

	public PaymentType findMostPaymentTypeByUserId(Integer userId, PaymentType defaultIfNotFound) {
		Validate.notNull(userId);
		Validate.notNull(defaultIfNotFound);
		
		if (userId.intValue() < 1) {
			return defaultIfNotFound;
		}
		List<PaymentType> list = costRepo.findPaymentTypeByUserIdOrderByCountDesc(userId);
		if (list == null || list.isEmpty()) {
			return defaultIfNotFound;
		}
		return list.get(0);
	}

	public CostType findMostCostTypeByUserId(Integer userId, CostType defaultIfNotFound) {
		Validate.notNull(userId);
		Validate.notNull(defaultIfNotFound);
		
		if (userId.intValue() < 1) {
			return defaultIfNotFound;
		}
		List<CostType> list = costRepo.findCostTypeByUserIdOrderByCountDesc(userId);
		if (list == null || list.isEmpty()) {
			return defaultIfNotFound;
		}
		return list.get(0);
	}

	public GasType findMostGasTypeByUserId(Integer userId, GasType defaultIfNotFound) {
		Validate.notNull(userId);
		Validate.notNull(defaultIfNotFound);
		
		if (userId.intValue() < 1) {
			return defaultIfNotFound;
		}
		
		List<GasType> list = gasCostRepo.findGasTypeByUserIdOrderByCountDesc(userId);
		if (list == null || list.isEmpty()) {
			return defaultIfNotFound;
		}
		return list.get(0);
	}

	public Double findCostTotalByCarId(Integer carId, Range<Date> dateRange) {
		Validate.notNull(carId);
		Validate.notNull(dateRange);
		Double d = costRepo.findCostTotalByCarId(carId, dateRange.getMinimum(), dateRange.getMaximum());
		return d != null ? d : 0D;
	}

	public Double findCostTotalByUserId(Integer userId, Range<Date> dateRange) {
		Validate.notNull(userId);
		Validate.notNull(dateRange);
		Double d = costRepo.findCostTotalByUserId(userId, dateRange.getMinimum(), dateRange.getMaximum());
		return d != null ? d : 0D;
	}

	public Map<String, Double> findCostByCarIdGroupByCostType(Integer carId, Range<Date> dateRange) {
		Validate.notNull(carId);
		Validate.notNull(dateRange);

		Map<String, Double> result = new Hashtable<String, Double>(CostType.values().length);
		for (CostType type : CostType.values()) {
			Double d = costRepo.findCostTotalByCarId(carId, type, dateRange.getMinimum(), dateRange.getMaximum());
			if (d == null) {
				d = 0D;
			}
			result.put(type.toString(), d);
		}
		return result;
	}

	public Map<String, Double> findCostByUserIdGroupByCostType(Integer userId, Range<Date> dateRange) {
		Validate.notNull(userId);
		Validate.notNull(dateRange);

		Map<String, Double> result = new Hashtable<String, Double>(CostType.values().length);
		for (CostType type : CostType.values()) {
			Double d = this.costRepo.findCostTotalByUserId(userId, type, dateRange.getMinimum(), dateRange.getMaximum());
			if (d == null) {
				d = 0D;
			}
			result.put(type.toString(), d);
		}
		return result;
	}

	public List<Cost> findCostByCarId(Integer carId, Date startDate, Date endDate) {
		Validate.notNull(carId);
		Validate.notNull(startDate);
		Validate.notNull(endDate);
		return costRepo.findAllByCarId(carId, startDate, endDate);
	}

}
