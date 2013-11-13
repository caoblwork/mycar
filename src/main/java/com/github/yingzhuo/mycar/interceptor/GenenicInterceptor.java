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
package com.github.yingzhuo.mycar.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.github.yingzhuo.mycar.domain.User;
import com.github.yingzhuo.mycar.security.SecurityUtils;
import com.github.yingzhuo.mycar.service.BadgeService;

public class GenenicInterceptor extends HandlerInterceptorAdapter {

	private BadgeService badgeService;
	private boolean     sessionScope = false;

	// ---------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		initPageName(request);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		initCurrentUserEmail(request);
		initCurrentUserBadgeCount(request);
	}

	// ---------------------------------------------------------------------------------------------------------------------
	private void initPageName(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String pageName = StringUtils.startsWith(uri, "/") ? StringUtils.substring(uri, 1) : uri;
		pageName = StringUtils.replace(pageName, "/", "_").toUpperCase();
		
		if (sessionScope == false) {
			request.setAttribute("PAGE_NAME", pageName);
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("PAGE_NAME", pageName);
		}
	}

	private void initCurrentUserEmail(HttpServletRequest request) {
		if (sessionScope == false) {
			request.setAttribute("CURRENT_USER_EMAIL", SecurityUtils.isAuthenticated() ? SecurityUtils.getPrincipal(User.class).getEmail() : StringUtils.EMPTY);
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("CURRENT_USER_EMAIL", SecurityUtils.isAuthenticated() ? SecurityUtils.getPrincipal(User.class).getEmail() : StringUtils.EMPTY);
		}
	}
	
	private void initCurrentUserBadgeCount(HttpServletRequest request) {
		Integer currentUserId = SecurityUtils.getPrincipalId();
		Long count = badgeService.countBadgeByUserId(currentUserId);

		if (sessionScope == false) {
			request.setAttribute("CURRENT_USER_BADGE_COUNT", count);
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("CURRENT_USER_BADGE_COUNT", count);
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------
	public void setBadgeService(BadgeService badgeService) {
		this.badgeService = badgeService;
	}

	public void setSessionScope(boolean sessionScope) {
		this.sessionScope = sessionScope;
	}
	
}
