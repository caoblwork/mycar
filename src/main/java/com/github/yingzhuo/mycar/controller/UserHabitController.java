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

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.yingzhuo.mycar.annotation.Ajax;
import com.github.yingzhuo.mycar.security.SecurityUtils;
import com.github.yingzhuo.mycar.service.UserHabitService;

@Controller
@RequestMapping("/habit")
public class UserHabitController {

	@Resource private UserHabitService habitService;
	
	// --------------------------------------------------------------------------------------------------------
	
	@Ajax
	@RequestMapping(value = "/car/list/edit-mode", method = RequestMethod.POST)
	public @ResponseBody String method0() {
		Integer userId = SecurityUtils.getPrincipalId();
		habitService.toggleDefaultEditModeForCarList(userId);
		return "SUCCESS";
	}

	@Ajax
	@RequestMapping(value = "/cost/list/last-tab", method = RequestMethod.POST)
	public @ResponseBody String method1(@RequestParam("index") Integer index) {
		habitService.setLastTabIndexForCostList(SecurityUtils.getPrincipalId(), index);
		return "SUCCESS";
	}

	@Ajax
	@RequestMapping(value = "/statistics/cost/last-tab", method = RequestMethod.POST)
	public @ResponseBody String method2(@RequestParam("index") Integer index) {
		habitService.setLastTabIndexForChart1(SecurityUtils.getPrincipalId(), index);
		return "SUCCESS";
	}

	@Ajax
	@RequestMapping(value = "/statistics/cost-list/page-info", method = RequestMethod.POST)
	public @ResponseBody String method3(
			@RequestParam("pageSize") Integer pageSize,
			@RequestParam("pageSort") String  sort,
			@RequestParam("pageSortDir") String sortDir
		)
	{
		habitService.setPageInfoForCostList(SecurityUtils.getPrincipalId(), pageSize, sort, sortDir);
		return "SUCCESS";
	}
	
	@Ajax
	@RequestMapping(value = "/contact/list/edit-mode", method = RequestMethod.POST)
	public @ResponseBody String method4() {
		Integer userId = SecurityUtils.getPrincipalId();
		habitService.toggleDefaultEditModeForContactList(userId);
		return "SUCCESS";
	}
}
