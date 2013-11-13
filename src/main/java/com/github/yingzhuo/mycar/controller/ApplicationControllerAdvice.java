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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.github.yingzhuo.mycar.exception.DataBindingException;
import com.github.yingzhuo.mycar.exception.EvilAccessException;
import com.github.yingzhuo.mycar.exception.NotCarOwnerException;
import com.github.yingzhuo.mycar.exception.OpenIdLoginException;
import com.github.yingzhuo.mycar.exception.PageNotFoundException;


@ControllerAdvice
public class ApplicationControllerAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationControllerAdvice.class);
	
	// -------------------------------------------------------------------------------------------------

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(EvilAccessException.class)
	public ModelAndView handleEvilAccessException(EvilAccessException ex) {
		LOGGER.warn(ex.getMessage(), ex);
		return new ModelAndView("redirect:/security/logout");
	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(NotCarOwnerException.class)
	public ModelAndView handleNotCarOwnerException(NotCarOwnerException ex) {
		return new ModelAndView("redirect:/car/list", "error", "not-car-owner");
	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(DataBindingException.class)
	public @ResponseBody Object handleDataBindingException(DataBindingException ex) {
		LOGGER.debug(ex.getMessage(), ex);
		return ex;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(PageNotFoundException.class)
	public String handlePageNotFoundException(PageNotFoundException ex) {
		return "error/404";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(OpenIdLoginException.class)
	public String handleOpenIdLoginException() {
		return "error/open-id";
	}

	@ExceptionHandler(Throwable.class)
	public String all(Throwable throwable) {
		LOGGER.error(throwable.getMessage(), throwable);
		return "error/500";
	}
}
