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
package com.github.yingzhuo.mycar.flow.validator;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.message.MessageResolver;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;

import com.github.yingzhuo.mycar.form.SignUpEmailForm;
import com.github.yingzhuo.mycar.service.UserService;


@Component
public class SignUpEmailFormValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignUpEmailFormValidator.class);
	
	@Resource
	private UserService userService;

	public void validateEmailPage(SignUpEmailForm form, ValidationContext context) {
		MessageContext mc = context.getMessageContext();
		
		if (StringUtils.isBlank(form.getEmail())) {
			// 这个地方由JSR303验证过了
		}
		else
		if (! userService.isEmailAvailableToSignUp(form.getEmail())) {
			LOGGER.debug("email: '{}' 不能注册", form.getEmail());
			
			MessageResolver mr = new MessageBuilder()
									.error()
									.source("email")
									.code("flow.signup.email.not.available")
//									.defaultText("email已经被注册")
									.build();
			mc.addMessage(mr);
		}
	}
}
