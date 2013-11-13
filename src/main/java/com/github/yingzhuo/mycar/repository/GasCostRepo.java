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

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.yingzhuo.mycar.domain.GasCost;
import com.github.yingzhuo.mycar.domain.plus.GasType;

public interface GasCostRepo extends JpaRepository<GasCost, Integer>, TypedCostRepo<GasCost> {

	@Query("select c.gasType from GasCost c where (c.car.ownerId = ?1) group by c.gasType order by count(c.gasType) desc")
	public List<GasType> findGasTypeByUserIdOrderByCountDesc(Integer userId);

	@Query("select sum(c.sum) from GasCost c where c.carId = ?1")
	public Double calculateTotalSum(Integer carId);

	@Query("select sum(c.cubage) from GasCost c where c.carId = ?1")
	public Double calculateTotalCubage(Integer carId);
	
}
