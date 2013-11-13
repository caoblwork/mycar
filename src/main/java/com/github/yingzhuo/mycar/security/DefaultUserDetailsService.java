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
package com.github.yingzhuo.mycar.security;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.github.yingzhuo.mycar.domain.User;
import com.github.yingzhuo.mycar.service.UserService;

@Component("databaseUserDetailsService")
public class DefaultUserDetailsService implements UserDetailsService {

	@Resource
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (StringUtils.isBlank(username)) {
			throw new UsernameNotFoundException("username is null or blank.");
		}
		
		String email = Validate.notBlank(username);
		User user = userService.findUserByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("username/email not found. username/email: %s", email));
		}
		
		return DefaultUserDetails.decorate(user);
	}

	// --------------------------------------------------------------------------------------------

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
