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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_USER_HABIT")
public class UserHabit implements Serializable {

	private static final long serialVersionUID = 4353213116347883774L;

	private Integer id;
	private Boolean defaultEditModeForCarList = false;
	private Boolean defaultEditModeForContactList = false;
	private Integer lastTabIndexForCostList = 0;
	private Integer lastTabIndexForChart1   = 0;
	private Integer pageSizeForCostList     = 50;
	private String  pageSortForCostList	 = "date";
	private String  pageSortDirForCostList  = "ASC";
	
	// ----------------------------------------------------------------------------------------
	
	public UserHabit() {
		super();
	}
	
	// ----------------------------------------------------------------------------------------

	@Id
	@Column(name = "HABIT_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "HABIT_DEFAULT_EDIT_MODE_FOR_CAR_LIST")
	public Boolean getDefaultEditModeForCarList() {
		return defaultEditModeForCarList;
	}

	public void setDefaultEditModeForCarList(Boolean defaultEditModeForCarList) {
		this.defaultEditModeForCarList = defaultEditModeForCarList;
	}
	
	@Column(name = "HABIT_DEFAULT_EDIT_MODE_FOR_CONTACT_LIST")
	public Boolean getDefaultEditModeForContactList() {
		return defaultEditModeForContactList;
	}

	public void setDefaultEditModeForContactList(
			Boolean defaultEditModeForContactList) {
		this.defaultEditModeForContactList = defaultEditModeForContactList;
	}

	@Column(name = "HABIT_LAST_TAB_INDEX_FOR_COST_LIST")
	public Integer getLastTabIndexForCostList() {
		return lastTabIndexForCostList;
	}

	public void setLastTabIndexForCostList(Integer lastTabIndexForCostList) {
		this.lastTabIndexForCostList = lastTabIndexForCostList;
	}

	@Column(name = "HABIT_LAST_TAB_INDEX_FOR_CHART_1")
	public Integer getLastTabIndexForChart1() {
		return lastTabIndexForChart1;
	}

	public void setLastTabIndexForChart1(Integer lastTabIndexForChart1) {
		this.lastTabIndexForChart1 = lastTabIndexForChart1;
	}

	@Column(name = "HABIT_PAGE_SIZE_FOR_COST_LIST")
	public Integer getPageSizeForCostList() {
		return pageSizeForCostList;
	}

	public void setPageSizeForCostList(Integer pageSizeForCostList) {
		this.pageSizeForCostList = pageSizeForCostList;
	}

	@Column(name = "HABIT_PAGE_SORT_FOR_COST_LIST")
	public String getPageSortForCostList() {
		return pageSortForCostList;
	}

	public void setPageSortForCostList(String pageSortForCostList) {
		this.pageSortForCostList = pageSortForCostList;
	}

	@Column(name = "HABIT_PAGE_SORT_DIR_FOR_COST_LIST")
	public String getPageSortDirForCostList() {
		return pageSortDirForCostList;
	}

	public void setPageSortDirForCostList(String pageSortDirForCostList) {
		this.pageSortDirForCostList = pageSortDirForCostList;
	}
	
}
