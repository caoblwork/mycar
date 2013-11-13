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


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.yingzhuo.mycar.repository.BadgeRepo;
import com.github.yingzhuo.mycar.service.BadgeService;

@Service("badgeService")
public class BadgeServiceImpl implements BadgeService {

	@Resource private BadgeRepo badgeRepo;

	// -----------------------------------------------------------------------------
	
	@Override
	public long countBadgeByUserId(Integer userId) {
		if (userId == null || userId.intValue() < 1) return 0;
		return badgeRepo.countByUserId(userId).longValue();
	}

}
