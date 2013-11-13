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

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.yingzhuo.mycar.form.SignUpCarForm;

@Component
public class SignUpFlowAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignUpFlowAction.class);

	public boolean preconditionCheck() {
		LOGGER.debug("注册账户流先决条件检查");
		// 注册账户无要检测的先觉条件
		return true;
	}

	public void enableSignUpCarForm(SignUpCarForm form) {
		Validate.notNull(form);
		form.setEnabled(true);
	}

}
