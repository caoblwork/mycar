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

import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.yingzhuo.logbackext.service.StatusService;
import com.github.yingzhuo.mycar.annotation.Ajax;
import com.github.yingzhuo.mycar.config.DataSourceInfo;
import com.github.yingzhuo.mycar.config.Profile;
import com.github.yingzhuo.mycar.config.SpringUtils;
import com.github.yingzhuo.mycar.domain.User;
import com.github.yingzhuo.mycar.service.AccountService;
import com.github.yingzhuo.mycar.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource private AccountService accountService;
	@Resource private UserService    userService;
	
	private StatusService statusService = StatusService.getDefaultService();

	// ------------------------------------------------------------------------
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(
			ModelMap modelMap
		)
	{
		Set<User> userSet = accountService.getOnlineAccount();
		modelMap.put("userSet", userSet);
		return "admin/account";
	}
	
	@Ajax
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/evit", method = RequestMethod.POST)
	public @ResponseBody String evit(@RequestParam("userId") Integer userId) {
		userService.lockUser(userId);
		boolean success = accountService.evitAccount(userId);
		return success ? "OK" : "NG";
	}

	@RequestMapping(value = "/log-context", method = RequestMethod.GET)
	public String logContext(ModelMap modelMap) {
		modelMap.put("statusList", statusService.getStatusList());
		return "admin/log-context";
	}
	
	@RequestMapping(value = "/environment", method = RequestMethod.GET)
	public String environment(ModelMap modelMap) {
		List<Profile> profiles = SpringUtils.getActiveProfiles();
		modelMap.put("profiles", profiles);
		modelMap.put("dataSourceInfo", DataSourceInfo.getInstance(profiles, SpringUtils.getBean("jdbcProperties", Properties.class)));
		return "admin/environment";
	}

}
