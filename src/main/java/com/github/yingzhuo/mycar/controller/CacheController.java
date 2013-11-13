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

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.yingzhuo.mycar.annotation.Ajax;
import com.github.yingzhuo.mycar.domain.plus.UserSnapshot;
import com.github.yingzhuo.mycar.service.CacheService;

@Controller
@RequestMapping("/cache")
public class CacheController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);
	
	@Resource
	private CacheService cacheService;

	@Ajax
	@RequestMapping(value = "/user-snapshot", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String findUserDetailsProperty(
			@RequestParam("id") Integer userId,
			@RequestParam("property") String propertyName
		)
	{
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("userId: {}", userId);
			LOGGER.debug("propertyName: {}", propertyName);
		}
		
		Map<Integer, UserSnapshot> map = cacheService.findAllUserSnapshot();
		UserSnapshot ud = map.get(userId);
		
		if (ud == null) {
			return StringUtils.EMPTY;
		}
		
		String strPropertyValue = null;
		try {
			strPropertyValue = BeanUtils.getProperty(ud, propertyName);
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
		}
		
		return StringUtils.defaultString(strPropertyValue, StringUtils.EMPTY);
	}

}
