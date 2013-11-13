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
package com.github.yingzhuo.mycar.flow.handler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.execution.FlowExecutionOutcome;
import org.springframework.webflow.mvc.servlet.AbstractFlowHandler;

import com.github.yingzhuo.mycar.util.CookieUtils;

public class SignUpFlowHandler extends AbstractFlowHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignUpFlowHandler.class);
	
	private static final String FLOW_ID = "flow-sign-up-id";
	private static final String RETURN  = "/security/login";
	
	@Override
	public String getFlowId() {
		return FLOW_ID;
	}

	@Override
	public String handleExecutionOutcome(FlowExecutionOutcome outcome,
			HttpServletRequest request, HttpServletResponse response) {
		
		String endStateId = outcome.getId();
		LOGGER.info("注册用户流结束({})", endStateId);
		
		if (StringUtils.equals("confirmed", endStateId)) {
			Integer id   = (Integer) outcome.getOutput().get("id");
			String email = (String) outcome.getOutput().get("email");

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("注册完成 ID: {}", id);
				LOGGER.debug("注册完成 EMAIL: {}", email);
			}

			Cookie c = CookieUtils.getInstance("email", email);
			response.addCookie(c);
		}

		return RETURN;
	}
	
}
