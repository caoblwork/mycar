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

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.yingzhuo.mycar.domain.FineCost;
import com.github.yingzhuo.mycar.exception.DataBindingException;
import com.github.yingzhuo.mycar.form.FineCostForm;
import com.github.yingzhuo.mycar.service.FineCostService;

@Controller
@RequestMapping("/fine-cost")
public class FineCostController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FineCostController.class);
	
	@Resource private MessageSource messageSource;
	@Resource private FineCostService fineCostService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody String add(
			@Valid FineCostForm form,
			BindingResult bindingResult
		) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		if (bindingResult.hasErrors()) {
			LOGGER.debug("数据绑定错误");
			LOGGER.debug("{}", bindingResult.getAllErrors());
			throw new DataBindingException(messageSource, bindingResult);
		}
		FineCost fineCost = new FineCost();
		PropertyUtils.copyProperties(fineCost, form);
		fineCostService.addFineCost(fineCost);
		
		return "SUCCESS";
	}
}
