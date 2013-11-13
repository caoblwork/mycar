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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.github.yingzhuo.mycar.domain.Cost;

@NoRepositoryBean
public interface TypedCostRepo<T extends Cost> extends Repository<T, Integer> {

	@Query("from #{#entityName} c where (c.car.id = ?1) and c.date between ?2 and ?3")
	public Page<T> findPageByCarId(Integer userId, Date startDate, Date endDate, Pageable pageable);

	@Query("from #{#entityName} c where (c.car.ownerId = ?1) and c.date between ?2 and ?3")
	public Page<T> findPageByUserId(Integer userId, Date startDate, Date endDate, Pageable pageable);

}
