package com.github.yingzhuo.mycar.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

public class SignUpPasswordForm implements Serializable {

	private static final long serialVersionUID = -8241518858469418523L;

	@Length(min = 6, max = 6, message = "{flow.signup.password.length}") 		private String password;
	@Length(min = 6, max = 6, message = "{flow.signup.confirmPassword.length}") private String confirmPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
