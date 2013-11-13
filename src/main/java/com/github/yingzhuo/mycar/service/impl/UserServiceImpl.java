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
package com.github.yingzhuo.mycar.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.github.yingzhuo.mycar.domain.Car;
import com.github.yingzhuo.mycar.domain.User;
import com.github.yingzhuo.mycar.domain.plus.Gender;
import com.github.yingzhuo.mycar.form.SignUpEmailForm;
import com.github.yingzhuo.mycar.form.SignUpPasswordForm;
import com.github.yingzhuo.mycar.form.SignUpProfileForm;
import com.github.yingzhuo.mycar.repository.CarRepo;
import com.github.yingzhuo.mycar.repository.UserRepo;
import com.github.yingzhuo.mycar.service.UserService;
import com.google.common.base.Function;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource private UserRepo userRepo;
	@Resource private CarRepo carRepo;

	@Resource(name = "md5StringTransformer")
	private Function<String, String> passwordHashTransformer;
	
	// ------------------------------------------------------------------------------------------

	public User findUserById(Integer id) {
		Validate.notNull(id);
		Validate.isTrue(id.intValue() >= 1);
		return userRepo.findOne(id);
	}
	
	public User findUserByEmail(String emailAddress) {
		Validate.notEmpty(emailAddress);
		return userRepo.findOne(emailAddress);
	}

	public void setPasswordForUser(Integer userId, String newPassword) {
		Validate.notNull(newPassword);
		
		newPassword = passwordHashTransformer.apply(newPassword);

		User user = userRepo.findOne(userId);
		if (user == null) return;

		user.setPassword(newPassword);
		user.setStandAlonePassword(true);
		userRepo.saveAndFlush(user);
	}

	public boolean isPasswordCorrect(Integer userId, String password) {
		Validate.notNull(userId);
		Validate.isTrue(StringUtils.isNotBlank(password));
		
		password = passwordHashTransformer.apply(password);
		return userRepo.countByIdAndHashedPassword(userId, password) == 1;
	}

	public void setNicknameForUser(Integer userId, String nickname) {
		Validate.notNull(userId);
		Validate.isTrue(StringUtils.isNotBlank(nickname));
		userRepo.setNicknameForUser(userId, nickname);
	}

	public void setGenderForUser(Integer userId, Gender gender) {
		Validate.notNull(userId);
		Validate.notNull(gender);
		userRepo.setGenderForUser(userId, gender);
	}

	public boolean isCarOwner(Integer userId) {
		Validate.notNull(userId);
		return carRepo.countByOwnerId(userId) > 0L;
	}

	public boolean isCarOwner(Integer userId, Integer carId) {
		Validate.notNull(userId);
		Validate.notNull(carId);
		
		Car car = carRepo.findOne(carId);
		if (car == null) {
			return false;
		}
		
		return car.getOwnerId().intValue() == userId.intValue();
	}

	public void updateLastLogin(Integer id, String ipAddress, Date date) {
		Validate.notNull(id);
		Validate.notNull(ipAddress);
		Validate.notNull(date);
		userRepo.updateLastLogin(id, ipAddress, date);
	}

	public void lockUser(Integer id) {
		Validate.notNull(id);
		userRepo.updateLock(id, System.currentTimeMillis());
	}

	public void unlockUser(Integer id) {
		Validate.notNull(id);
		userRepo.updateLock(id, null);
	}

	public boolean isEmailAvailableToSignUp(String email) {
		if (StringUtils.isBlank(email)) return false;
		return userRepo.findOne(email) == null;
	}

	public Integer createUser(User user) {
		Validate.notNull(user);
		User u = userRepo.save(user);
		return u.getId();
	}

	@Override
	public User createUser(SignUpEmailForm emailForm, SignUpProfileForm profileForm, SignUpPasswordForm passwordForm) {
		
		Validate.notNull(emailForm);
		Validate.notNull(profileForm);
		Validate.notNull(passwordForm);
		
		User user = new User();
		user.setNickname(profileForm.getName());
		user.setGender(profileForm.getGender());
		user.setEmail(emailForm.getEmail());
		user.setPassword(this.passwordHashTransformer.apply(passwordForm.getPassword()));
		return userRepo.save(user);
	}

}
