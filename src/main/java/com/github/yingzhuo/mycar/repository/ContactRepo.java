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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.github.yingzhuo.mycar.domain.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer> {

	@Query("FROM Contact AS c WHERE c.owner.id = ?1")
	public Page<Contact> findByOwnerId(Integer ownerId, Pageable pageable);

	// 不再使用批量更新/批量删除操作
	/* ************************************************************************/
	@Deprecated
	@Modifying
	@Query("UPDATE Contact AS c SET c.name = ?2 WHERE c.id = ?1")
	public void editName(Integer id, String newName);
	
	@Deprecated
	@Modifying
	@Query("UPDATE Contact AS c SET c.phoneNumbers = ?2 WHERE c.id = ?1")
	public void editPhoneNumbers(Integer id, String newPhoneNumbers);

	@Deprecated
	@Modifying
	@Query("UPDATE Contact AS c SET c.address = ?2 WHERE c.id = ?1")
	public void editAddress(Integer id, String newAddress);
	
	@Deprecated
	@Modifying
	@Query("UPDATE Contact AS c SET c.title = ?2 WHERE c.id = ?1")
	public void editTitle(Integer id, String newTitle);
	/* ************************************************************************/

}
