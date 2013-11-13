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

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import com.github.yingzhuo.mycar.domain.User;
import com.github.yingzhuo.mycar.domain.comparator.UserComparators;
import com.github.yingzhuo.mycar.security.DefaultUserDetails;
import com.github.yingzhuo.mycar.service.AccountService;
import com.google.common.collect.Sets;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Resource
	private SessionRegistry sessionRegistry;
	
	@Override
	public int countOnlineAccount() {
		return sessionRegistry.getAllPrincipals().size();
	}

	@Override
	public Set<User> getOnlineAccount() {
		List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
		Set<User> users = Sets.<User> newTreeSet(UserComparators.BY_ID);
		for (Object principal : allPrincipals) {
			if (principal instanceof DefaultUserDetails) {
				DefaultUserDetails u = (DefaultUserDetails) principal;
				users.add(u.getUser());
			}
		}
		return users;
	}

	@Override
	public boolean evitAccount(Integer accountId) {
		List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
		
		boolean flag = false;
		for (Object principal : allPrincipals) {
			if (principal instanceof DefaultUserDetails) {
				DefaultUserDetails u = (DefaultUserDetails) principal;
				if (u.getUser().getId().intValue() == accountId.intValue()) {
					List<SessionInformation> sil = sessionRegistry.getAllSessions(principal, true);
					for (SessionInformation si : sil) {
						sessionRegistry.removeSessionInformation(si.getSessionId());
						flag = true;
					}
				}
			}
		}
		return flag;
	}

}
