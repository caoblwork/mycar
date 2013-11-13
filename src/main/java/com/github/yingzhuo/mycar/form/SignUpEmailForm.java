package com.github.yingzhuo.mycar.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class SignUpEmailForm implements Serializable {

	private static final long serialVersionUID = -824158858469418523L;

	@NotBlank
	@Email
	private String email;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
