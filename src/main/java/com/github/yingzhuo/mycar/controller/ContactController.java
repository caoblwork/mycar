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
package com.github.yingzhuo.mycar.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.yingzhuo.mycar.annotation.Ajax;
import com.github.yingzhuo.mycar.domain.Contact;
import com.github.yingzhuo.mycar.exception.DataBindingException;
import com.github.yingzhuo.mycar.form.ContactForm;
import com.github.yingzhuo.mycar.security.SecurityUtils;
import com.github.yingzhuo.mycar.service.ContactService;
import com.github.yingzhuo.mycar.service.UserHabitService;

@Controller
@RequestMapping(value = "/contact")
public class ContactController {
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
	
	private static final int DEFAULT_PAGE_SIZE = 50;
	private static final Sort DEFAULT_SORT = new Sort(Sort.Direction.ASC, "id");
	
	@Resource private ContactService contactService;
	@Resource private UserHabitService userHabitService;
	@Resource private MessageSource messageSource;
	
	// --------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/list/{page}", method = {RequestMethod.GET})
	public String list(
			@PathVariable("page") int p,
			ModelMap modelMap
		)
	{
		Integer userId = SecurityUtils.getPrincipalId();
		Page<Contact> page = contactService.findByUserId(userId, new PageRequest(p - 1, DEFAULT_PAGE_SIZE, DEFAULT_SORT));
		modelMap.put("page", page);
		modelMap.put("pageNumber", new Integer(p));
		modelMap.put("defaultEditMode", userHabitService.findDefaultEditModeForContactList(userId, false, true));
		return "contact/list";
	}

	// --------------------------------------------------------------------------------------------------
	@Ajax
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
	public @ResponseBody Object add(@Valid ContactForm form, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			throw new DataBindingException(bindingResult);
		}
		
		Contact contact = new Contact();
		try {
			PropertyUtils.copyProperties(contact, form);
		} catch (Exception e) {
			return ControllerSupport.FAILED;
		}
		
		contactService.save(contact);
		
		return ControllerSupport.SUCCESS;
	}
	
	// --------------------------------------------------------------------------------------------------
	@Ajax
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	public @ResponseBody String delete(@RequestParam("id") Integer id) {
		contactService.delete(id);
		return "SUCCESS";
	}

	// --------------------------------------------------------------------------------------------------
	@Ajax
	@RequestMapping(value = "/edit-name", method = {RequestMethod.POST})
	public @ResponseBody String editName(
			@RequestParam("pk") Integer contactId,
			@RequestParam("value") String newName,
			HttpServletResponse response
		)
	{
		int length = StringUtils.length(newName);
		if (length < 2 || length > 255) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "长度需要在2和255之间";
		}
		contactService.editName(contactId, newName);
		return "SUCCESS";
	}

	// --------------------------------------------------------------------------------------------------
	@Ajax
	@RequestMapping(value = "/edit-phones", method = {RequestMethod.POST})
	public @ResponseBody String editPhones(
			@RequestParam("pk") Integer contactId,
			@RequestParam("value") String newPhones,
			HttpServletResponse response
			)
	{
		int length = StringUtils.length(newPhones);
		if (length > 255) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "长度需要在0和255之间";
		}
		contactService.editPhoneNumbers(contactId, newPhones);
		return "SUCCESS";
	}
	
	// --------------------------------------------------------------------------------------------------
	@Ajax
	@RequestMapping(value = "/edit-address", method = {RequestMethod.POST})
	public @ResponseBody String editAddress(
			@RequestParam("pk") Integer contactId,
			@RequestParam("value") String newAddress,
			HttpServletResponse response
			)
	{
		int length = StringUtils.length(newAddress);
		if (length > 255) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "长度需要在0和255之间";
		}
		contactService.editAddress(contactId, newAddress);
		return "SUCCESS";
	}
	
	// --------------------------------------------------------------------------------------------------
	@Ajax
	@RequestMapping(value = "/edit-title", method = {RequestMethod.POST})
	public @ResponseBody String editTitle(
			@RequestParam("pk") Integer contactId,
			@RequestParam("value") String newTitle,
			HttpServletResponse response
			)
	{
		int length = StringUtils.length(newTitle);
		if (length > 255) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "长度需要在0和255之间";
		}
		contactService.editTitle(contactId, newTitle);
		return "SUCCESS";
	}
}
