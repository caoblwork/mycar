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
package com.github.yingzhuo.mycar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.yingzhuo.mycar.config.SpringUtils;
import com.github.yingzhuo.mycar.exception.NotCarOwnerException;
import com.github.yingzhuo.mycar.security.SecurityUtils;

public final class BusinessValidator {
	
	private static Object LOCK_OBJ = new Object();
	private static Logger LOGGER   = LoggerFactory.getLogger("BUSINESS-VALIDATE");
	
	private static UserService userService;
	

	public static void assertCarOwner() {

		// lazy init
		if (userService == null) {
			synchronized (LOCK_OBJ) {
				userService = SpringUtils.getBean(UserService.class);
			}
		}
		
		if(! userService.isCarOwner(SecurityUtils.getPrincipalId())) {
			throw new NotCarOwnerException();
		}
		
		LOGGER.debug("assertCarOwner() OK");
	}

	public static void assertCarOwner(Integer carId) {
		
		// lazy init
		if (userService == null) {
			synchronized (LOCK_OBJ) {
				userService = SpringUtils.getBean(UserService.class);
			}
		}

		if (! userService.isCarOwner(SecurityUtils.getPrincipalId(), carId)) {
			throw new NotCarOwnerException();
		}
		
		LOGGER.debug("assertCarOwner(Integer carId) OK");
	}
}
