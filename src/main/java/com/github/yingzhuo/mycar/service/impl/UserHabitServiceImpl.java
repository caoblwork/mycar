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
package com.github.yingzhuo.mycar.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.yingzhuo.mycar.domain.UserHabit;
import com.github.yingzhuo.mycar.repository.UserHabitRepo;
import com.github.yingzhuo.mycar.service.UserHabitService;

@Service("userHabitServiceImpl")
public class UserHabitServiceImpl implements UserHabitService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserHabitServiceImpl.class);
	
	@Resource private UserHabitRepo userHabitRepo;

	@Override
	public boolean findDefaultEditModeForCarList(Integer userId, boolean defaultValue, boolean createIfNotFound) {
		Integer habitId = userId;
		Validate.notNull(habitId);
		
		UserHabit habit = userHabitRepo.findOne(habitId);
		
		if (habit != null) {
			return habit.getDefaultEditModeForCarList() != null ? habit.getDefaultEditModeForCarList() : defaultValue;
		}
		
		if (createIfNotFound) {
			LOGGER.debug("创建用户行为实体 userId: {}", userId);
			habit = new UserHabit();
			habit.setId(habitId);
			habit.setDefaultEditModeForCarList(defaultValue);
			userHabitRepo.save(habit);
		}
		return defaultValue;
	}

	@Override
	public boolean toggleDefaultEditModeForCarList(Integer userId) {
		Integer habitId = userId;
		Validate.notNull(habitId);
		
		UserHabit habit = userHabitRepo.findOne(userId);
		if (habit == null) {
			throw new IllegalArgumentException("没有找到相对应的用户习惯信息");
		}
		
		boolean b = habit.getDefaultEditModeForCarList();
		habit.setDefaultEditModeForCarList(! b);
		userHabitRepo.saveAndFlush(habit);
		return !b;
	}

	@Override
	public int findLastTabIndexForCostList(Integer userId, int defaultIndex, boolean createIfNotFound) {
		Integer habitId = userId;
		Validate.notNull(habitId);
		
		UserHabit habit = userHabitRepo.findOne(habitId);
		if (habit != null) {
			return habit.getLastTabIndexForCostList() == null ? new Integer(defaultIndex) : habit.getLastTabIndexForCostList();
		}
		
		if (createIfNotFound) {
			LOGGER.debug("创建用户行为实体 userId: {}", userId);
			habit = new UserHabit();
			habit.setId(habitId);
			habit.setLastTabIndexForCostList(new Integer(defaultIndex));
			userHabitRepo.save(habit);
		}
		return defaultIndex;
	}

	@Override
	public void setLastTabIndexForCostList(Integer userId, int index) {
		Integer habitId = userId;
		Validate.notNull(habitId);
		
		UserHabit habit = userHabitRepo.findOne(habitId);
		habit.setLastTabIndexForCostList(index);
		userHabitRepo.saveAndFlush(habit);
	}

	@Override
	public int findLastTabIndexForChart1(Integer userId, int defaultValue, boolean createIfNotFound) {
		Integer habitId = userId;

		UserHabit habit = userHabitRepo.findOne(habitId);
		if (habit != null) {
			return habit.getLastTabIndexForChart1() == null ? new Integer(defaultValue) : habit.getLastTabIndexForChart1();
		}
		
		if (createIfNotFound) {
			LOGGER.debug("创建用户行为实体 userId: {}", userId);
			habit = new UserHabit();
			habit.setId(habitId);
			habit.setLastTabIndexForChart1(defaultValue);
			userHabitRepo.save(habit);
		}
		return defaultValue;
	}

	@Override
	public void setLastTabIndexForChart1(Integer userId, int index) {
		Integer habitId = userId;
		Validate.notNull(habitId);
		
		UserHabit habit = userHabitRepo.findOne(habitId);
		habit.setLastTabIndexForChart1(index);
		userHabitRepo.saveAndFlush(habit);
	}
	
	@Override
	public Map<String, Object> findPageInfoForCostList(Integer userId, Integer defaultPageSize, String defaultSort, String defaultSortDir, boolean createIfNotFount
		)
	{
		Integer habitId = userId;
		Map<String, Object> map = new HashMap<String, Object>();
		
		Validate.notNull(habitId);
		UserHabit habit = userHabitRepo.findOne(habitId);
		
		if (habit == null) {
			if (createIfNotFount) {
				habit = new UserHabit();
				habit.setPageSizeForCostList(defaultPageSize);
				habit.setPageSortForCostList(defaultSort);
				habit.setPageSortDirForCostList(defaultSortDir);
				userHabitRepo.save(habit);
			}
			map.put("pageSize", defaultPageSize);
			map.put("pageSort", defaultSort);
			map.put("pageSortDir", defaultSortDir);
			return map;
		}
		
		map.put("pageSize", habit.getPageSizeForCostList());
		map.put("pageSort", habit.getPageSortForCostList());
		map.put("pageSortDir", habit.getPageSortDirForCostList());
		return map;
	}

	@Override
	public void setPageInfoForCostList(Integer userId, Integer pageSize, String sort, String sortDir) {
		Integer habitId = userId;
		Validate.notNull(habitId);

		UserHabit habit = userHabitRepo.findOne(habitId);
		habit.setPageSizeForCostList(pageSize);
		habit.setPageSortForCostList(sort);
		habit.setPageSortDirForCostList(sortDir);
		userHabitRepo.saveAndFlush(habit);
	}

	@Override
	public boolean findDefaultEditModeForContactList(Integer userId, boolean defaultValue, boolean createIfNotFound) {
		Integer habitId = userId;
		Validate.notNull(habitId);
		
		UserHabit habit = userHabitRepo.findOne(habitId);
		
		if (habit == null) {
			if (createIfNotFound) {
				habit = new UserHabit();
				habit.setDefaultEditModeForContactList(defaultValue);
				userHabitRepo.save(habit);
			}
			return defaultValue;
		}
		
		return habit.getDefaultEditModeForContactList().booleanValue();
	}

	@Override
	public boolean toggleDefaultEditModeForContactList(Integer userId) {
		Integer habitId = userId;
		Validate.notNull(habitId);
		
		UserHabit habit = userHabitRepo.findOne(userId);
		if (habit == null) {
			throw new IllegalArgumentException("没有找到相对应的用户习惯信息");
		}
		
		boolean b = habit.getDefaultEditModeForContactList();
		habit.setDefaultEditModeForContactList(! b);
		userHabitRepo.saveAndFlush(habit);
		return !b;
	}

}
