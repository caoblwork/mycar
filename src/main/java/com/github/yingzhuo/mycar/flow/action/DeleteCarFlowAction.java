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
package com.github.yingzhuo.mycar.flow.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.message.MessageResolver;
import org.springframework.stereotype.Component;

import com.github.yingzhuo.mycar.security.SecurityUtils;
import com.github.yingzhuo.mycar.service.UserService;

@Component
public class DeleteCarFlowAction {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCarFlowAction.class);

	@Resource
	private UserService userService;

	public boolean preconditionCheck(Integer carId, MessageContext messageContext) {
		LOGGER.debug("删除车辆流先决条件检查");

		boolean hasError = false;

		// 没有登录，强制结束流程
		if (SecurityUtils.isUnauthenticated()) {
			LOGGER.debug("没有登录");
			
			MessageResolver mr = new MessageBuilder().error().code("flow.deletecar.precondition.unauthenticated").build();
			messageContext.addMessage(mr);
			hasError = true;
		}

		// 要删除的车辆根本不属于本用户
		if (userService.isCarOwner(SecurityUtils.getPrincipalId(), carId) == false) {
			LOGGER.debug("车辆不存在或车辆不属于当前用户");
			
			
			MessageResolver mr = new MessageBuilder().error().code("flow.deletecar.precondition.not.car.owner").build();
			messageContext.addMessage(mr);
			hasError = true;
		}

		return (hasError == false);
	}

}
