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

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.yingzhuo.mycar.domain.Contact;
import com.github.yingzhuo.mycar.repository.ContactRepo;
import com.github.yingzhuo.mycar.service.ContactService;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

	@Resource
	private ContactRepo contactRepo;
	
	// ------------------------------------------------------------------------------

	@Override
	public Page<Contact> findByUserId(Integer userId, Pageable pageable) {
		Validate.notNull(userId);
		Validate.notNull(pageable);
		return contactRepo.findByOwnerId(userId, pageable);
	}

	@Override
	public void save(Contact contact) {
		Validate.notNull(contact);
		contactRepo.save(contact);
	}

	@Override
	public void delete(Integer id) {
		Validate.notNull(id);
		contactRepo.delete(id);
	}

	@Override
	public void editName(Integer id, String newName) {
		Validate.notNull(id);
		Validate.isTrue(StringUtils.isNotBlank(newName));

		Contact c = contactRepo.findOne(id);
		if (c != null) {
			c.setName(newName);
			contactRepo.saveAndFlush(c);
		}
	}

	@Override
	public void editPhoneNumbers(Integer id, String newPhoneNumbers) {
		Validate.notNull(id);
		newPhoneNumbers = StringUtils.trimToNull(newPhoneNumbers);
//		contactRepo.editPhoneNumbers(id, newPhoneNumbers);
		
		Contact c = contactRepo.findOne(id);
		if (c != null) {
			c.setPhoneNumbers(newPhoneNumbers);
			contactRepo.saveAndFlush(c);
		}
	}

	@Override
	public void editAddress(Integer id, String newAddress) {
		Validate.notNull(id);
		newAddress = StringUtils.trimToNull(newAddress);
		
		Contact c = contactRepo.findOne(id);
		if (c != null) {
			c.setAddress(newAddress);
			contactRepo.saveAndFlush(c);
		}
	}

	@Override
	public void editTitle(Integer id, String newTitle) {
		Validate.notNull(id);
		newTitle = StringUtils.trimToNull(newTitle);
		
		Contact c = contactRepo.findOne(id);
		if (c != null) {
			c.setTitle(newTitle);
			contactRepo.saveAndFlush(c);
		}
	}

}
