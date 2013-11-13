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
package com.github.yingzhuo.mycar.service;

import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface UserHabitService {

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean findDefaultEditModeForCarList(Integer userId, boolean defaultValue, boolean createIfNotFound);

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean toggleDefaultEditModeForCarList(Integer userId);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean findDefaultEditModeForContactList(Integer userId, boolean defaultValue, boolean createIfNotFound);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean toggleDefaultEditModeForContactList(Integer userId);

	@Transactional(propagation = Propagation.REQUIRED)
	public int findLastTabIndexForCostList(Integer userId, int defaultValue, boolean createIfNotFound);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void setLastTabIndexForCostList(Integer userId, int index);

	@Transactional(propagation = Propagation.REQUIRED)
	public int findLastTabIndexForChart1(Integer userId, int defaultValue, boolean createIfNotFound);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void setLastTabIndexForChart1(Integer userId, int index);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> findPageInfoForCostList(Integer userId, Integer defaultPageSize, String defaultSort, String defaultSortDir, boolean createIfNotFount);

	@Transactional(propagation = Propagation.REQUIRED)
	public void setPageInfoForCostList(Integer userId, Integer pageSize, String sort, String sortDir);

}
