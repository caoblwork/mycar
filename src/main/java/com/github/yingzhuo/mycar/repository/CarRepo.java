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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.github.yingzhuo.mycar.domain.Car;
import com.github.yingzhuo.mycar.domain.plus.CarType;

public interface CarRepo extends JpaRepository<Car, Integer> {

	@Query("from Car c left join fetch c.owner as o where o.id = ?1 order by c.id")
	public List<Car> findByOwnerId(Integer ownerId);

	@Query("select count(c.id) from Car c where c.id = ?1 and c.name = ?2")
	public Long countByIdAndName(Integer id, String name);

	@Query("select c.owner.id from Car c where c.id = ?1")
	public Integer findOwnerIdByCarId(Integer carId);

	@Query("select count(c.id) from Car c where c.ownerId = ?1 and c.name = ?2")
	public Long countByOwnerIdAndName(Integer ownerId, String name);

	@Query("select count(c.id) from Car c where c.ownerId = ?1")
	public Long countByOwnerId(Integer ownerId);
	
	// 不再使用批量更新/批量删除操作
	/* ************************************************************************/
	@Deprecated
	@Modifying
	@Query("update Car as c set c.mileage = ?2 where c.id = ?1")
	public void setMileageForCar(Integer carId, Integer mileage);
	
	@Deprecated
	@Modifying
	@Query("update Car as c set c.name = ?2 where c.id = ?1")
	public void setNameForCar(Integer carId, String name);

	@Deprecated
	@Modifying
	@Query("update Car as c set c.type = ?2 where c.id = ?1")
	public void setTypeForCar(Integer carId, CarType type);
	
	@Deprecated
	@Modifying
	@Query("update Car as c set c.brand = ?2 where c.id = ?1")
	public void setBrandForCar(Integer carId, String brand);
	
	@Deprecated
	@Modifying
	@Query("update Car as c set c.modelType = ?2 where c.id = ?1")
	public void setModelTypeForCar(Integer carId, String modelType);
	
	@Deprecated
	@Modifying
	@Query("update Car as c set c.license = ?2 where c.id = ?1")
	public void setLicenseForCar(Integer carId, String license);
	
	@Deprecated
	@Modifying
	@Query("update Car as c set c.numberOfFrame = ?2 where c.id = ?1")
	public void setNumberOfFrameForCar(Integer carId, String numberOfFrame);
	
	@Deprecated
	@Modifying
	@Query("update Car as c set c.numberOfEngine = ?2 where c.id = ?1")
	public void setNumberOfEngineForCar(Integer carId, String numberOfEngine);
	/* ************************************************************************/

}
