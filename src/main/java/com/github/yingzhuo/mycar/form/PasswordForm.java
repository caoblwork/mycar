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
package com.github.yingzhuo.mycar.form;

import org.hibernate.validator.constraints.Length;

@SuppressWarnings("serial")
public class PasswordForm implements java.io.Serializable {

	@Length(min = 6, max = 6, message = "六位") private String oldPassword;
	@Length(min = 6, max = 6, message = "六位") private String newPassword;
	@Length(min = 6, max = 6, message = "六位") private String confirmPassword;
	@Length(min = 5, max = 5, message = "五位") private String captcha;
	private boolean standAlonePassword = true;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public boolean isStandAlonePassword() {
		return standAlonePassword;
	}

	public void setStandAlonePassword(boolean standAlonePassword) {
		this.standAlonePassword = standAlonePassword;
	}

}
