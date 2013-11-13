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

import java.util.Set;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.github.yingzhuo.mycar.domain.User;
import com.google.common.collect.ImmutableSet;

public class SecurityUtils {

	public static final Set<Class<?>> ACCEPT_TYPES = ImmutableSet.<Class<?>> of(UserDetails.class, User.class);
	
	@SuppressWarnings("unchecked")
	public static <T> T getPrincipal(Class<T> klass) {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			// 只有匿名用户的情形，principal为'anonymousUser'
			if (principal instanceof String) {
				return null;
			}
			
			if (klass == UserDetails.class) {
				return (T) principal;
			}
			
			if (klass == User.class) {
				return (T) ((DefaultUserDetails) principal).getUser();
			}
			
		} catch (NullPointerException e) {
			return null;
		} catch (ClassCastException e) {
			return null;
		}
		
		return null;
	}
	
	public static Integer getPrincipalId() {
		User user = getPrincipal(User.class);
		if (user == null) return null;
		return user.getId();
	}
	
	public static boolean isAuthenticated() {
		try {
			return getPrincipal(UserDetails.class) != null;
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	public static boolean isUnauthenticated() {
		return ! isAuthenticated();
	}

}
