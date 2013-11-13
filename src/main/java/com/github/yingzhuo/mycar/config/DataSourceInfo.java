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
package com.github.yingzhuo.mycar.config;

import java.util.List;
import java.util.Properties;

public abstract class DataSourceInfo {
	
	public static DataSourceInfo PRODUCTION = null;

	public static DataSourceInfo getInstance(List<Profile> profiles, final Properties jdbcProperties) {
		
		if (PRODUCTION == null) {
			PRODUCTION = new DataSourceInfo() {
				public String getType() {
					return "MySQL 5";
				}
				public String getDriverClass() {
					return jdbcProperties.getProperty("jdbc.driverClass");
				}
				public String getUrl() {
					return jdbcProperties.getProperty("jdbc.url");
				}
				public String getUser() {
					return jdbcProperties.getProperty("jdbc.username");
				}
				public String getOrmDialect() {
					return jdbcProperties.getProperty("hibernate.dialect");
				}
			};
		}
		return PRODUCTION;
	}
	
	// ----------------------------------------------------------------------------------------
	
	public abstract String getType();

	public abstract String getDriverClass();
	
	public abstract String getUrl();
	
	public abstract String getUser();
	
	public abstract String getOrmDialect();

}
