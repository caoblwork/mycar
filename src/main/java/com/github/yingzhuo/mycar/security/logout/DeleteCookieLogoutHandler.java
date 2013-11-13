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
package com.github.yingzhuo.mycar.security.logout;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.Assert;

import com.github.yingzhuo.mycar.util.CookieUtils;

public class DeleteCookieLogoutHandler implements LogoutHandler, InitializingBean {

	private String[] cookieNames = null;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		for (String cookieName : cookieNames) {
			Cookie cookieForDelete = CookieUtils.getInstanceForRemove(cookieName);
			response.addCookie(cookieForDelete);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cookieNames);
		Assert.noNullElements(cookieNames);
	}

	public void setCookieNames(String[] cookieNames) {
		this.cookieNames = cookieNames;
	}
	
}
