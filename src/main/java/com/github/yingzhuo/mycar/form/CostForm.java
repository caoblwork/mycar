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
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.github.yingzhuo.mycar.domain.plus.Location;
import com.github.yingzhuo.mycar.domain.plus.PaymentType;

@SuppressWarnings("serial")
public class CostForm implements Serializable {

	@NotNull private Date date;
	@NotNull private Integer carId;
	@NotNull private Double sum;
	private Location location;
	@NotNull private PaymentType paymentType;
	@Length(max = 30) private String comment;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getComment() {
		if (StringUtils.isBlank(comment)) return null;
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
