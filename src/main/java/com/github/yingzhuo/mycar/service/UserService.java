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

import java.util.Date;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.yingzhuo.mycar.domain.User;
import com.github.yingzhuo.mycar.domain.plus.Gender;
import com.github.yingzhuo.mycar.form.SignUpEmailForm;
import com.github.yingzhuo.mycar.form.SignUpPasswordForm;
import com.github.yingzhuo.mycar.form.SignUpProfileForm;

public interface UserService {

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isEmailAvailableToSignUp(String email);
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User findUserById(Integer id);
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User findUserByEmail(String emailAddress);

	@Transactional(propagation = Propagation.REQUIRED)
	public void setPasswordForUser(Integer userId, String newPassword);

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean isPasswordCorrect(Integer userId, String password);

	@Transactional(propagation = Propagation.REQUIRED)
	public void setNicknameForUser(Integer userId, String nickname);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void setGenderForUser(Integer userId, Gender gender);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isCarOwner(Integer userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isCarOwner(Integer userId, Integer carId);

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateLastLogin(Integer id, String ipAddress, Date date);

	@Transactional(propagation = Propagation.REQUIRED)
	public void lockUser(Integer id);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void unlockUser(Integer id);

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer createUser(User user);

	@Transactional(propagation = Propagation.REQUIRED)
	public User createUser(SignUpEmailForm emailForm, SignUpProfileForm profileForm, SignUpPasswordForm passwordForm);

}
