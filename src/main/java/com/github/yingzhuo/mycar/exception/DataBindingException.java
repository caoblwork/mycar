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
package com.github.yingzhuo.mycar.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;

import com.github.yingzhuo.mycar.util.BindingResultUtils;

public class DataBindingException extends RuntimeException {

	private static final long serialVersionUID = 4886698281191876208L;

	private boolean success = true;
	
	private List<String> globalErrors = new ArrayList<String>();
	
	private Map<String, List<String>> fieldErrors = new HashMap<String, List<String>>();

	// --------------------------------------------------------------------------------------------------------------------
	
	public DataBindingException(BindingResult bindingResult) {
		this(null, bindingResult);
	}

	public DataBindingException(MessageSource messageSource, BindingResult bindingResult) {
		this(messageSource, bindingResult, Locale.getDefault());
	}

	public DataBindingException(MessageSource messageSource, BindingResult bindingResult, Locale locale) {
		if (bindingResult.hasErrors()) {
			success = false;
			globalErrors = BindingResultUtils.getGlobalErrors(messageSource, bindingResult, locale);
			fieldErrors  = BindingResultUtils.getFieldErros(messageSource, bindingResult, locale);
		} 
	}
	
	// --------------------------------------------------------------------------------------------------------------------

	// getter & setter
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<String> getGlobalErrors() {
		return globalErrors;
	}

	public void setGlobalErrors(List<String> globalErrors) {
		this.globalErrors = globalErrors;
	}

	public Map<String, List<String>> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(Map<String, List<String>> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	
}