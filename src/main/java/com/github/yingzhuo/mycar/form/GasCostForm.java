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

import com.github.yingzhuo.mycar.domain.plus.FillingType;
import com.github.yingzhuo.mycar.domain.plus.GasType;

@SuppressWarnings("serial")
public class GasCostForm extends CostForm {

	private Double price;
	private Double cubage;
	private GasType gasType;
	private FillingType fillingType;

	@Override
	public String toString() {
		return "GasCostForm [price=" + price + ", cubage=" + cubage
				+ ", gasType=" + gasType + ", fillingType=" + fillingType
				+ ", getDate()=" + getDate() + ", getCarId()=" + getCarId()
				+ ", getSum()=" + getSum() + ", getLocation()=" + getLocation()
				+ ", getPaymentType()=" + getPaymentType() + ", getComment()="
				+ getComment() + "]";
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCubage() {
		return cubage;
	}

	public void setCubage(Double cubage) {
		this.cubage = cubage;
	}

	public GasType getGasType() {
		return gasType;
	}

	public void setGasType(GasType gasType) {
		this.gasType = gasType;
	}

	public FillingType getFillingType() {
		return fillingType;
	}

	public void setFillingType(FillingType fillingType) {
		this.fillingType = fillingType;
	}
	
}
