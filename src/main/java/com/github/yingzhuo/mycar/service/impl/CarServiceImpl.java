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

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.yingzhuo.mycar.domain.Car;
import com.github.yingzhuo.mycar.domain.User;
import com.github.yingzhuo.mycar.domain.plus.CarType;
import com.github.yingzhuo.mycar.form.SignUpCarForm;
import com.github.yingzhuo.mycar.repository.CarRepo;
import com.github.yingzhuo.mycar.repository.CostRepo;
import com.github.yingzhuo.mycar.repository.UserRepo;
import com.github.yingzhuo.mycar.service.CarService;

@Service("carService")
public class CarServiceImpl implements CarService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);
	
	@Resource private CarRepo carRepo;
	@Resource private UserRepo userRepo;
	@Resource private CostRepo costRepo;
	
	public Car findCarById(Integer id) {
		Validate.notNull(id);
		Validate.isTrue(id.intValue() >= 1);
		return carRepo.findOne(id);
	}
	
	public List<Car> findCarByOwnerId(Integer id) {
		Validate.notNull(id);
		Validate.isTrue(id.intValue() >= 1);
		return carRepo.findByOwnerId(id);
	}

	public boolean matches(Integer id, String carName) {
		Validate.notNull(id);
		Validate.isTrue(id.intValue() >= 1);
		Validate.isTrue(StringUtils.isNotBlank(carName));
		return carRepo.countByIdAndName(id, carName).longValue() != 0L;
	}

	public void deleteCarById(Integer carId) {
		Validate.notNull(carId);
		Validate.isTrue(carId.intValue() >= 1);

		// TODO 删除关联数据
		costRepo.deleteByCarId(carId);
		carRepo.delete(carId);
	}

	public void setMileageForCar(Integer carId, Integer mileage) {
		Validate.notNull(carId);
		Validate.isTrue(carId.intValue() >= 1);
		Validate.notNull(mileage);
		Validate.isTrue(mileage.intValue() >= 0);

		Car car = carRepo.findOne(carId);
		if (car != null) {
			car.setMileage(mileage);
			carRepo.saveAndFlush(car);
		}
	}

	public void setNameForCar(Integer carId, String name) {
		Validate.notNull(carId);
		Validate.isTrue(carId.intValue() >= 1);
		Validate.isTrue(StringUtils.isNotBlank(name));
		
		Car car = carRepo.findOne(carId);
		if (car != null) {
			car.setName(name);
			carRepo.saveAndFlush(car);
		}
	}

	public boolean isCarAbleToRename(Integer carId, String newName) {
		Validate.notNull(carId);
		Validate.isTrue(StringUtils.isNotBlank(newName));

		Integer ownerId = carRepo.findOwnerIdByCarId(carId);
		long c = carRepo.countByOwnerIdAndName(ownerId, newName);
		return c == 0L;
	}

	public void setTypeForCar(Integer carId, CarType type) {
		Validate.notNull(carId);
		Validate.notNull(type);
		Car car = carRepo.findOne(carId);
		if (car != null) {
			car.setType(type);
			carRepo.saveAndFlush(car);
		}
	}

	public void setBrandForCar(Integer carId, String brand) {
		Validate.notNull(carId);
		if (StringUtils.isBlank(brand)) {
			brand = null;
		}
		Car car = carRepo.findOne(carId);
		if (car != null) {
			car.setBrand(brand);
			carRepo.saveAndFlush(car);
		}
	}
	
	public void setModelTypeForCar(Integer carId, String modelType) {
		Validate.notNull(carId);
		if (StringUtils.isBlank(modelType)) {
			modelType = null;
		}
		Car car = carRepo.findOne(carId);
		if (car != null) {
			car.setModelType(modelType);
			carRepo.saveAndFlush(car);
		}
	}

	public void setLicenseForCar(Integer carId, String license) {
		Validate.notNull(carId);
		if (StringUtils.isBlank(license)) {
			license = null;
		}
		Car car = carRepo.findOne(carId);
		if (car != null) {
			car.setLicense(license);
			carRepo.saveAndFlush(car);
		}
	}

	public void setNumberOfFrameForCar(Integer carId, String numberOfFrame) {
		Validate.notNull(carId);
		if (StringUtils.isBlank(numberOfFrame)) {
			numberOfFrame = null;
		}
		Car car = carRepo.findOne(carId);
		if (car != null) {
			car.setNumberOfFrame(numberOfFrame);
			carRepo.saveAndFlush(car);
		}
	}

	public void setNumberOfEngineForCar(Integer carId, String numberOfEngine) {
		Validate.notNull(carId);
		if (StringUtils.isBlank(numberOfEngine)) {
			numberOfEngine = null;
		}
		Car car = carRepo.findOne(carId);
		if (car != null) {
			car.setNumberOfEngine(numberOfEngine);
			carRepo.saveAndFlush(car);
		}
	}

	public boolean isCarAbleToCreate(Integer ownerId, String newName) {
		Validate.notNull(ownerId);
		Validate.isTrue(StringUtils.isNotBlank(newName));
		
		long c = carRepo.countByOwnerIdAndName(ownerId, newName);
		return c == 0L;
	}

	public void addCar(Car newCar, Integer ownerId) {
		Validate.notNull(newCar);
		
		if (newCar.getId() != null) {
			throw new IllegalStateException();
		}
		
		User owner = userRepo.findOne(ownerId);
		newCar.setOwner(owner);
		
		carRepo.save(newCar);
		carRepo.flush();
	}

	public void addCar(SignUpCarForm form, Integer ownerId) {
		Validate.notNull(form);
		
		if (form.isEnabled() == false) {
			LOGGER.debug("form({}) is unenabled", form);
			return;
		}
		
		Car newCar = new Car();
		newCar.setId(null);
		try {
			PropertyUtils.copyProperties(newCar, form);
		} catch (Exception e) {
			newCar.setName(form.getName());
			newCar.setLicense(form.getLicense());
			newCar.setBrand(form.getBrand());
			newCar.setModelType(form.getModelType());
			newCar.setMileage(0);
			newCar.setType(form.getType());
			newCar.setNumberOfEngine(form.getNumberOfEngine());
			newCar.setNumberOfFrame(form.getNumberOfFrame());
		}

		User owner = userRepo.findOne(ownerId);
		newCar.setOwner(owner);
		carRepo.save(newCar);
		carRepo.flush();
	}

}
