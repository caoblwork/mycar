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

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.yingzhuo.mycar.domain.Car;
import com.github.yingzhuo.mycar.domain.plus.CarType;
import com.github.yingzhuo.mycar.form.SignUpCarForm;

public interface CarService {

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Car findCarById(Integer id);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Car> findCarByOwnerId(Integer id);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean matches(Integer id, String carName);

	@Deprecated
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCarById(Integer carId);

	@Transactional(propagation = Propagation.REQUIRED)
	public void setMileageForCar(Integer carId, Integer mileage);

	@Transactional(propagation = Propagation.REQUIRED)
	public void setNameForCar(Integer carId, String name);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isCarAbleToRename(Integer carId, String newName);
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isCarAbleToCreate(Integer ownerId, String newName);

	@Transactional(propagation = Propagation.REQUIRED)
	public void setTypeForCar(Integer carId, CarType type);

	@Transactional(propagation = Propagation.REQUIRED)
	public void setBrandForCar(Integer carId, String brand);

	@Transactional(propagation = Propagation.REQUIRED)
	public void setModelTypeForCar(Integer carId, String modelType);

	@Transactional(propagation = Propagation.REQUIRED)
	public void setLicenseForCar(Integer carId, String license);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void setNumberOfFrameForCar(Integer carId, String numberOfFrame);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void setNumberOfEngineForCar(Integer carId, String numberOfEngine);

	@Transactional(propagation = Propagation.REQUIRED)
	public void addCar(Car newCar, Integer ownerId);

	@Transactional(propagation = Propagation.REQUIRED)
	public void addCar(SignUpCarForm form, Integer ownerId);

}
