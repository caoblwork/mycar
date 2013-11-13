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

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.github.yingzhuo.mycar.domain.plus.CostType;
import com.github.yingzhuo.mycar.domain.plus.Location;
import com.github.yingzhuo.mycar.domain.plus.PaymentType;

/**
 * 支出
 * 
 * @author yingzhuo
 *
 */
@Entity
@Table(name = "TBL_COST")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "COST_TYPE")
public abstract class Cost implements Serializable {

	private static final long serialVersionUID = 5684606342078121527L;

	private Integer id;
	private Long version = 0L;
	private Date date;
	private Date recordingDate = new Date();
	private Car car;
	private Integer carId;
	private Double sum;
	private CostType costType;
	private Location location;
	private PaymentType paymentType;
	private String comment;
	
	@LastModifiedBy   private User lastModifier;
	@LastModifiedDate private Date lastModifiedTime;

	// -------------------------------------------------------------------------------------------

	public Cost() {
		super();
	}

	// -------------------------------------------------------------------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cost other = (Cost) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	// -------------------------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COST_ID", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "COST_DATE", nullable = false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COST_CAR_ID", insertable = false, updatable = false, nullable = false)
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Column(name = "COST_CAR_ID", insertable = true, updatable = true, nullable = false)
	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	@Version
	@Column(name = "COST_VERSION", nullable = false)
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Column(name = "COST_SUM", nullable = false)
	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "COST_RECORDING_DATE", nullable = false)
	public Date getRecordingDate() {
		return recordingDate;
	}

	public void setRecordingDate(Date recordingDate) {
		this.recordingDate = recordingDate;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "COST_TYPE", length = 31, updatable = false, insertable = false, nullable = false)
	public CostType getCostType() {
		return costType;
	}

	public void setCostType(CostType costType) {
		this.costType = costType;
	}

	@Column(name = "COST_COMMENT", length = 50, nullable = false)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "city", column = @Column(name = "COST_LOC_CITY", length = 30)),
		@AttributeOverride(name = "street", column = @Column(name = "COST_LOC_STREET", length = 200)),
		@AttributeOverride(name = "zip", column = @Column(name = "COST_LOC_ZIP", length = 20))
	})
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "COST_PAYMENT_TYPE", length = 20)
	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COST_LAST_MODIFIED_BY")
	public User getLastModifier() {
		return lastModifier;
	}

	public void setLastModifier(User lastModifier) {
		this.lastModifier = lastModifier;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COST_LAST_MODIFIED_TIME")
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	
}
