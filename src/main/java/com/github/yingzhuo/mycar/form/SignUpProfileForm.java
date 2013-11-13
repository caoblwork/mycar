package com.github.yingzhuo.mycar.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.github.yingzhuo.mycar.domain.plus.Gender;

public class SignUpProfileForm implements Serializable {

	private static final long serialVersionUID = -2415885184694185223L;

	
	@NotNull 					private Gender gender = Gender.MALE;
	@Length(min = 2, max = 15) 	private String name;

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
