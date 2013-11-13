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
package com.github.yingzhuo.mycar.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.github.yingzhuo.mycar.domain.plus.FillingType;
import com.github.yingzhuo.mycar.domain.plus.GasType;

@Entity
@DiscriminatorValue("GAS")
@SuppressWarnings("serial")
public class GasCost extends Cost {

	private Double price;
	private Double cubage;
	private GasType gasType;
	private FillingType fillingType;
	
	// ------------------------------------------------------------------------------
	
	public GasCost() {
		super();
	}

	// ------------------------------------------------------------------------------

	@Column(name = "COST_GAS_PRICE")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "COST_GAS_TYPE", length = 15)
	public GasType getGasType() {
		return gasType;
	}

	public void setGasType(GasType gasType) {
		this.gasType = gasType;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "COST_GAS_FILLINGTYPE", length = 15)
	public FillingType getFillingType() {
		return fillingType;
	}

	public void setFillingType(FillingType fillingType) {
		this.fillingType = fillingType;
	}

	@Column(name = "COST_GAS_CUBAGE")
	public Double getCubage() {
		return cubage;
	}

	public void setCubage(Double cubage) {
		this.cubage = cubage;
	}

}
