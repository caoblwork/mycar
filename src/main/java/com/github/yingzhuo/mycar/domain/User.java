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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.yingzhuo.mycar.domain.comparator.CarComparators;
import com.github.yingzhuo.mycar.domain.plus.Gender;
import com.github.yingzhuo.mycar.domain.plus.Role;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * 用户
 * 
 * @author yingzhuo
 *
 */
@Entity
@Table(name = "TBL_USER")
@NamedQueries({
	@NamedQuery(
		name = "User.findAllUserDetails",
		hints = { @QueryHint(name = "javax.persistence.query.timeout", value = "10000") },
		query = "SELECT NEW com.github.yingzhuo.mycar.domain.plus.UserSnapshot(u.id, u.nickname, u.email, u.gender)" +
				"FROM User u ORDER BY u.id ASC")
})
public class User implements Serializable {

	/** 序列化UID */
	private static final long serialVersionUID = -7350446801916756087L;

	// ~---------------------------------------------------------------------------------------------------

	private Integer id;
	private String nickname;
	private Gender gender = Gender.UNKNOWN;
	private String email;
	private String password;
	private Date lastLoginDate;
	private String lastLoginIpAddress;
	private boolean enabled = true;
	private String memonic;
	private List<Contact> contactList = new ArrayList<Contact>(0);
	private Long lockedTime;
	private long roleBitSet = 1L;
	private Set<Badge> badgeSet = new HashSet<Badge>();
	
	private Collection<Contact> contactCollection;
	private Map<Integer, Car>   carMapById = Maps.newHashMap();
	private boolean standAlonePassword = true;

	// ~---------------------------------------------------------------------------------------------------

	/** 构造方法 */
	public User() {
		super();
	}
	
	// ~---------------------------------------------------------------------------------------------------

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
		User other = (User) obj;
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
			.append(getId())
			.append(getNickname())
			.append(getEmail())
			.toString();
	}
	
	// ~---------------------------------------------------------------------------------------------------

	@Transient
	public boolean isRole(Role role) {
		return (roleBitSet & role.getValue()) != 0;
	}
	
	@Transient
	public boolean isAdmin() {
		return isRole(Role.ADMIN);
	}
	
	@Transient
	public boolean isUser() {
		return isRole(Role.USER);
	}

	@Transient
	public boolean isDeveloper() {
		return isRole(Role.DEVELOPER);
	}

	// ~---------------------------------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "USER_EMAIL", length = 30, nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "USER_NICKNAME", length = 30, nullable = false, unique = false)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "USER_PWD", length = 32, nullable = false, unique = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "USER_SEX")
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USER_LAST_LOGIN_DT")
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Column(name = "USER_LAST_LOGIN_IP")
	public String getLastLoginIpAddress() {
		return lastLoginIpAddress;
	}

	public void setLastLoginIpAddress(String lastLoginIpAddress) {
		this.lastLoginIpAddress = lastLoginIpAddress;
	}

	@Column(name = "USER_ENABLED")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "USER_MEMONIC", length = 30)
	public String getMemonic() {
		return memonic;
	}

	public void setMemonic(String memonic) {
		this.memonic = memonic;
	}

	@Column(name = "USER_LOCKED_TIME", nullable = true)
	public Long getLockedTime() {
		return lockedTime;
	}

	public void setLockedTime(Long lockedTime) {
		this.lockedTime = lockedTime;
	}

	@Column(name = "USER_ROLES", nullable = false)
	public long getRoleBitSet() {
		return roleBitSet;
	}

	public void setRoleBitSet(long roleBitSet) {
		this.roleBitSet = roleBitSet;
	}

	@OneToMany(mappedBy = "owner")
	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "TBL_USER_BADGE",
		joinColumns = { @JoinColumn(name = "USER_ID") },
		inverseJoinColumns = { @JoinColumn(name = "BADGE_ID") }
	)
	public Set<Badge> getBadgeSet() {
		return badgeSet;
	}

	public void setBadgeSet(Set<Badge> badgeSet) {
		this.badgeSet = badgeSet;
	}

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	public Collection<Contact> getContactCollection() {
		return contactCollection;
	}

	public void setContactCollection(Collection<Contact> contactCollection) {
		this.contactCollection = contactCollection;
	}

	@MapKey(name = "id")
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	public Map<Integer, Car> getCarMapById() {
		return carMapById;
	}

	public void setCarMapById(Map<Integer, Car> carMapById) {
		this.carMapById = carMapById;
	}

	@Transient
	public Set<Integer> getCarIdSet() {
		return ImmutableSet.<Integer> copyOf(Sets.newTreeSet(carMapById.keySet()));
	}
	
	@Transient
	public Set<Car> getCarSet() {
		Set<Car> set = Sets.newTreeSet(CarComparators.BY_ID);
		set.addAll(carMapById.values());
		return ImmutableSet.<Car> copyOf(set);
	}

	@Column(name = "USER_STAND_ALONE")
	public boolean isStandAlonePassword() {
		return standAlonePassword;
	}

	public void setStandAlonePassword(boolean standAlonePassword) {
		this.standAlonePassword = standAlonePassword;
	}

}
