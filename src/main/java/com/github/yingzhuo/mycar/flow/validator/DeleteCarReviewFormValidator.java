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
package com.github.yingzhuo.mycar.flow.validator;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.message.MessageResolver;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;

import com.github.yingzhuo.mycar.form.DeleteCarReviewForm;
import com.github.yingzhuo.mycar.service.CarService;

@Component
public class DeleteCarReviewFormValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCarReviewFormValidator.class);
	
	@Resource private CarService carService;
	
	public void validateReviewPage(DeleteCarReviewForm form, ValidationContext context) {
		MessageContext mc = context.getMessageContext();
		String carName = form.getCarName();
		Integer carId  = form.getCarId();
		
		LOGGER.debug("carId: {}",carId);
		LOGGER.debug("carName: {}", carName);
		
		if (StringUtils.isBlank(carName)) {
			LOGGER.debug("carName is blank");
			
			MessageResolver mr = new MessageBuilder()
							.error()
							.source("carName")
							.code("flow.deletecar.carname.blank")
							.build();
			mc.addMessage(mr);
			return;
		}
		
		// 验证车辆ID与车辆是否匹配
		if (carService.matches(carId, carName) == false) {
			LOGGER.debug("车辆ID与Name不匹配");
			
			MessageResolver mr = new MessageBuilder()
							.error()
							.source("carName")
							.code("flow.deletecar.carname.wrong")
							.build();
			mc.addMessage(mr);
		}
	}

}
