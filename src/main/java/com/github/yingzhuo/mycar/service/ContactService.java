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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.yingzhuo.mycar.domain.Contact;

public interface ContactService {

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<Contact> findByUserId(Integer userId, Pageable pageable);

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Contact contact);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Integer id);

	@Transactional(propagation = Propagation.REQUIRED)
	public void editName(Integer id, String newName);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void editPhoneNumbers(Integer id, String newPhoneNumbers);

	@Transactional(propagation = Propagation.REQUIRED)
	public void editAddress(Integer id, String newAddress);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void editTitle(Integer id, String newTitle);
	
}
