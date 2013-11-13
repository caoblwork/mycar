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
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.github.yingzhuo.mycar.domain.plus.CarType;
import com.google.common.collect.Maps;

@Entity
@Table(name = "TBL_CAR")
public class Car implements Serializable {

	private static final long serialVersionUID = -5046216374892524364L;

	private Integer id;
	private String name;
	private User owner;
	private Integer ownerId;
	private String brand;
	private String modelType;
	private String numberOfEngine;
	private String numberOfFrame;
	private String license;
	private Integer mileage;
	private CarType type;
	private Map<Integer, Cost> costMapById = Maps.newHashMap();
	
	@LastModifiedBy   private User lastModifier;
	@LastModifiedDate private Date lastModifiedTime;

	// ------------------------------------------------------------------------------------------

	public Car() {
		super();
	}

	// ------------------------------------------------------------------------------------------

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
		Car other = (Car) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	// ------------------------------------------------------------------------------------------

	@Id
	@Column(name = "CAR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "CAR_NAME", length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CAR_OWNER_ID")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Column(name = "CAR_OWNER_ID", insertable = false, updatable = false)
	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	@Column(name = "CAR_BRAND")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "CAR_MODEL_TYPE")
	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	@Column(name = "CAR_ENGINE")
	public String getNumberOfEngine() {
		return numberOfEngine;
	}

	public void setNumberOfEngine(String numberOfEngine) {
		this.numberOfEngine = numberOfEngine;
	}

	@Column(name = "CAR_FRAME")
	public String getNumberOfFrame() {
		return numberOfFrame;
	}

	public void setNumberOfFrame(String numberOfFrame) {
		this.numberOfFrame = numberOfFrame;
	}

	@Column(name = "CAR_LICENSE")
	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	@Column(name = "CAR_MILEAGE")
	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "CAR_TYPE", length = 5)
	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAR_LAST_MODIFIED_BY")
	public User getLastModifier() {
		return lastModifier;
	}

	public void setLastModifier(User lastModifier) {
		this.lastModifier = lastModifier;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CAR_LAST_MODIFIED_TIME")
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	@MapKey(name = "id")
	@OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
	public Map<Integer, Cost> getCostMapById() {
		return costMapById;
	}

	public void setCostMapById(Map<Integer, Cost> costMapById) {
		this.costMapById = costMapById;
	}
	
	
}
