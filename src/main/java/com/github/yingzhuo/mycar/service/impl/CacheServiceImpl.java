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

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.yingzhuo.mycar.domain.plus.UserSnapshot;
import com.github.yingzhuo.mycar.repository.UserRepo;
import com.github.yingzhuo.mycar.service.CacheService;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

	@Resource private UserRepo userRepo;

	public void giveUpAllUserDetails() {
		// 不做事情
	}

	@Override
	public Map<Integer, UserSnapshot> findAllUserSnapshot() {
		Map<Integer, UserSnapshot> map = new Hashtable<Integer, UserSnapshot>();
		List<UserSnapshot> list = userRepo.findAllUserDetails();
		for (UserSnapshot ud : list) {
			map.put(ud.getId(), ud);
		}
		return map;
	}

}
