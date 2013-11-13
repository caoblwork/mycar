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
package com.github.yingzhuo.mycar.domain.plus;

import java.io.Serializable;

public class ActiveUsersRecord implements Serializable {

	private static final long serialVersionUID = -4236700069055983427L;

	private String timestamp;
	private Integer activeUserCount;
	private Integer roleUserCount;
	private Integer roleAdminCount;
	private Integer roleDeveloperCount;
	
	public ActiveUsersRecord() {
	}
	
	@Override
	public String toString() {
		return "ActiveUsersRecord [timestamp=" + timestamp
				+ ", activeUserCount=" + activeUserCount + ", roleUserCount="
				+ roleUserCount + ", roleAdminCount=" + roleAdminCount
				+ ", roleDeveloperCount=" + roleDeveloperCount + "]";
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getActiveUserCount() {
		return activeUserCount;
	}

	public void setActiveUserCount(Integer activeUserCount) {
		this.activeUserCount = activeUserCount;
	}

	public Integer getRoleUserCount() {
		return roleUserCount;
	}

	public void setRoleUserCount(Integer roleUserCount) {
		this.roleUserCount = roleUserCount;
	}

	public Integer getRoleAdminCount() {
		return roleAdminCount;
	}

	public void setRoleAdminCount(Integer roleAdminCount) {
		this.roleAdminCount = roleAdminCount;
	}

	public Integer getRoleDeveloperCount() {
		return roleDeveloperCount;
	}

	public void setRoleDeveloperCount(Integer roleDeveloperCount) {
		this.roleDeveloperCount = roleDeveloperCount;
	}

}
