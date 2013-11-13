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
package com.github.yingzhuo.mycar.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.github.yingzhuo.mycar.domain.Cost;
import com.github.yingzhuo.mycar.domain.plus.CostType;
import com.github.yingzhuo.mycar.domain.plus.PaymentType;

public interface CostRepo extends JpaRepository<Cost, Integer> {

	@Deprecated
	@Query("from Cost c where (c.carId = ?1) and (c.date between ?3 and ?4) and (c.costType = ?2)")
	public Page<Cost> findPageByCarId(Integer carId, CostType type, Date startDate, Date endDate, Pageable pageable);
	
	@Deprecated
	@Query("from Cost c where (c.car.ownerId = ?1) and (c.date between ?3 and ?4) and (c.costType = ?2)")
	public Page<Cost> findPageByUserId(Integer userId, CostType type, Date startDate, Date endDate, Pageable pageable);

	@Query("select c.costType from Cost c where (c.car.ownerId = ?1) group by c.costType order by count(c.costType) desc")
	public List<CostType> findCostTypeByUserIdOrderByCountDesc(Integer userId);

	@Query("select c.paymentType from Cost c where (c.car.ownerId = ?1) group by c.paymentType order by count(c.paymentType) desc")
	public List<PaymentType> findPaymentTypeByUserIdOrderByCountDesc(Integer userId);

	@Query("select sum(c.sum) from Cost c where (c.carId = ?1) and (c.date between ?2 and ?3)")
	public Double findCostTotalByCarId(Integer carId, Date start, Date end);
	
	@Query("select sum(c.sum) from Cost c where (c.car.ownerId = ?1) and (c.date between ?2 and ?3)")
	public Double findCostTotalByUserId(Integer userId, Date start, Date end);

	@Query("select sum(c.sum) from Cost c where (c.carId = ?1) and (c.costType = ?2) and (c.date between ?3 and ?4)")
	public Double findCostTotalByCarId(Integer carId, CostType type, Date start, Date end);
	
	@Query("select sum(c.sum) from Cost c where (c.car.ownerId = ?1) and (c.costType = ?2) and (c.date between ?3 and ?4)")
	public Double findCostTotalByUserId(Integer userId, CostType type, Date start, Date end);
	
	@Query("from Cost c where (c.carId = ?1) and c.date between ?2 and ?3")
	public List<Cost> findAllByCarId(Integer carId, Date start, Date end);

	@Modifying
	@Query("delete Cost as c where c.carId = ?1")
	public void deleteByCarId(Integer carId);

}
