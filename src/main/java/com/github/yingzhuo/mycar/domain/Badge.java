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
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 奖章 
 * 
 * @author YING Zhuo
 *
 */
@Entity
@Table(name = "TBL_BADGE")
public class Badge implements Serializable {

	private static final long serialVersionUID = -2567244658885950347L;

	private String id = UUID.randomUUID().toString().replaceAll("-", StringUtils.EMPTY).toUpperCase();
	private String name;
	private String description;
	
	// -----------------------------------------------------------------------------

	public Badge() {
		super();
	}

	public Badge(String name) {
		super();
		this.name = name;
	}
	
	public Badge(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	// -----------------------------------------------------------------------------

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
		if (!(obj instanceof Badge))
			return false;
		Badge other = (Badge) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
				.append(this.name)
				.build();
	}
	
	// -----------------------------------------------------------------------------

	@Id
	@Column(name = "BADGE_ID", length = 32, unique = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "BADGE_NAME", length = 100, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "BADGE_DESC", length = 255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
