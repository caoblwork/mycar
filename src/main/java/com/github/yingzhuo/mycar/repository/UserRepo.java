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
package com.github.yingzhuo.mycar.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.github.yingzhuo.mycar.domain.User;
import com.github.yingzhuo.mycar.domain.plus.Gender;

public interface UserRepo extends JpaRepository<User, Integer>, UserCustomerDao {

	@Query("from User u where u.email = ?1")
	public User findOne(String email);

	@Query("select count(u.id) from User u where u.id = ?1 and u.password = ?2")
	public Long countByIdAndHashedPassword(Integer id, String hashedPassword);

	@Modifying
	@Query("update User u set u.lastLoginIpAddress = ?2, u.lastLoginDate = ?3 where u.id = ?1")
	public void updateLastLogin(Integer id, String ipAddress, Date date);

	@Modifying
	@Query("update User u set u.lockedTime = ?2 where u.id = ?1")
	public void updateLock(Integer id, Long timestamp);
	
	@Modifying
	@Query("update User u set u.nickname = ?2 where u.id = ?1")
	public void setNicknameForUser(Integer userId, String nickname);

	@Modifying
	@Query("update User u set u.gender = ?2 where u.id = ?1")
	public void setGenderForUser(Integer userId, Gender gender);

}
