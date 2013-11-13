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
package com.github.yingzhuo.mycar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.yingzhuo.mycar.form.LoginForm;
import com.github.yingzhuo.mycar.security.SecurityUtils;

@Controller
@RequestMapping("/security")
public class SecurityController {

	
	public static final String EMAIL_COOKIE_ATTRIBUTE_NAME = "email";
	public static final String PASSWORD_COOKIE_ATTRIBUTE_NAME = "password";

	@RequestMapping(value = "/login", method = {RequestMethod.GET}, params = {"!error", "!locked"})
	public String login(ModelMap modelMap, @CookieValue(value = "email", required = false) String email) {
		if (SecurityUtils.isAuthenticated()) {
			return "redirect:/car/list";
		}
		
		LoginForm form = new LoginForm();
		modelMap.put("loginForm", form);
		form.setUsername(email);
		return "security/login";
	}

	@RequestMapping(value = "/login", method = {RequestMethod.GET}, params = {"error", "!locked"})
	public String login(
			ModelMap modelMap,
			@RequestParam(value = "error", required = false) String error,
			@CookieValue(value = "email", required = false) String email
		)
	{
		if (SecurityUtils.isAuthenticated()) {
			return "redirect:/car/list";
		}
		
		LoginForm form = new LoginForm();
		form.setUsername(email);

		modelMap.put("loginForm", form);
		modelMap.put("errorMessage", "用户名或密码错误");
		return "security/login";
	}

	@RequestMapping(value = "/login", method = {RequestMethod.GET}, params = {"!error", "locked"})
	public String login(
			@CookieValue(value = "email", required = false) String email,
			ModelMap modelMap
			)
	{
		if (SecurityUtils.isAuthenticated()) {
			return "redirect:/car/list";
		}
		
		LoginForm form = new LoginForm();
		form.setUsername(email);

		modelMap.put("loginForm", form);
		modelMap.put("errorMessage", "你的帐户已经被管理员锁定");
		return "security/login";
	}

}
