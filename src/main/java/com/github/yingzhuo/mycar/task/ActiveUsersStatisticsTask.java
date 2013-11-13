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
package com.github.yingzhuo.mycar.task;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.joda.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

import com.github.yingzhuo.mycar.config.BatchLogs;
import com.github.yingzhuo.mycar.domain.User;
import com.github.yingzhuo.mycar.domain.comparator.UserComparators;
import com.github.yingzhuo.mycar.security.DefaultUserDetails;
import com.google.common.collect.Sets;

@Component
@SuppressWarnings("deprecation")
public class ActiveUsersStatisticsTask {

	@Resource
	private SessionRegistry sessionRegistry;

	// -------------------------------------------------------------------------------------------------------------------

	@Scheduled(cron = "10 0/1 * * * ?")		// 每1分钟启动
	public void work() {
		
		List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
		long countRoleUser      = 0L;
		long countRoleAdmin     = 0L;
		long countRoleDeveloper = 0L;

		Set<User> activeUserSet = Sets.newTreeSet(UserComparators.BY_ID);
		for (Object principal : allPrincipals) {
			if (principal instanceof DefaultUserDetails) {
				DefaultUserDetails ud = (DefaultUserDetails) principal;
				User user = ud.getUser();
				activeUserSet.add(user);
				
				if (user.isUser())		countRoleUser ++;
				if (user.isAdmin()) 	countRoleAdmin ++;
				if (user.isDeveloper()) countRoleDeveloper ++;
			}
		}

		BatchLogs.ACTIVE_USERS.info("{},{},{},{},{}", 
					LocalDateTime.now().toString("yyyyMMddHHmm") + "00", 
					activeUserSet.size(), countRoleUser, countRoleAdmin, countRoleDeveloper);
	}
}

