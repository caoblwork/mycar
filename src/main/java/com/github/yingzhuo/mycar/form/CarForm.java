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
package com.github.yingzhuo.mycar.form;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.github.yingzhuo.mycar.domain.plus.CarType;

@SuppressWarnings("serial")
public class CarForm implements Serializable  {

	@Length(min = 2, max = 20) private String name;
	private CarType type;
	@Length(min = 0, max = 20) private String brand;
	@Length(min = 0, max = 20) private String modelType;
	@Length(min = 0, max = 20) private String numberOfEngine;
	@Length(min = 0, max = 20) private String numberOfFrame;
	@Length(min = 0, max = 20) private String license;

	// -----------------------------------------------------------------------------------------

	public CarForm() {
		super();
	}

	// -----------------------------------------------------------------------------------------

	public String getName() {
		if (StringUtils.isBlank(name)) return null;
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	public String getBrand() {
		if (StringUtils.isBlank(brand)) return null;
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModelType() {
		if (StringUtils.isBlank(modelType)) return null;
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getNumberOfEngine() {
		if (StringUtils.isBlank(numberOfEngine)) return null;
		return numberOfEngine;
	}

	public void setNumberOfEngine(String numberOfEngine) {
		this.numberOfEngine = numberOfEngine;
	}

	public String getNumberOfFrame() {
		if (StringUtils.isBlank(numberOfFrame)) return null;
		return numberOfFrame;
	}

	public void setNumberOfFrame(String numberOfFrame) {
		this.numberOfFrame = numberOfFrame;
	}

	public String getLicense() {
		if (StringUtils.isBlank(license)) return null;
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}
}
