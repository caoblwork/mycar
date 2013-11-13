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
package com.github.yingzhuo.mycar.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class BindingResultUtils {

	public static Object getErrorMessages(MessageSource messageSource, BindingResult bindingResult, Locale locale) {
		if (bindingResult == null) {
			return null;
		}

		Map<String, Object> root = new HashMap<String, Object>();
		if (bindingResult.hasErrors()) {
			root.put("success", false);
			root.put("globalErrors", getGlobalErrors(messageSource, bindingResult, locale));
			root.put("fieldErrors",  getFieldErros(messageSource, bindingResult, locale));
		} else {
			root.put("success", true);
		}
		
		return root;
	}
	
	private static boolean isEmpty(String str) {
		if (str == null) return true;
		return str.length() == 0;
	}
	
	private static boolean isNotEmpty(String str) {
		return ! isEmpty(str);
	}
	
	public static String getMessage(MessageSource messageSource, DefaultMessageSourceResolvable error, Locale locale) {
		if (messageSource == null) {
			return error.getDefaultMessage();
		}
		
		String[] codes    = error.getCodes();
		Object[] args     = error.getArguments();
		String defaultMsg = error.getDefaultMessage();
		
		String msg = null;
		for (String code : codes) {
			try {
				msg = messageSource.getMessage(code, args, locale);
			} catch (NoSuchMessageException e) {
			}
			if (isNotEmpty(msg)) {
				break;
			}
		}
		
		if (isEmpty(msg)) {
			return defaultMsg;
		}
		
		return msg;
	}

	public static List<String> getGlobalErrors(MessageSource messageSource, BindingResult bindingResult, Locale locale) {
		List<String> globalErrors = new ArrayList<String>();
		for (ObjectError oe : bindingResult.getGlobalErrors()) {
			String message = getMessage(messageSource, oe, locale);
			globalErrors.add(message);
		}
		return globalErrors;
	}
	
	public static Map<String, List<String>> getFieldErros(MessageSource messageSource, BindingResult bindingResult, Locale locale) {
		Map<String, List<String>> fieldErrors = new HashMap<String, List<String>>();
		for (FieldError fe : bindingResult.getFieldErrors()) {
			String field   = fe.getField();
			
			String message = getMessage(messageSource, fe, locale);
			if (fieldErrors.get(field) != null) {
				fieldErrors.get(field).add(message);
			} else {
				List<String> list = new LinkedList<String>();
				list.add(message);
				fieldErrors.put(field, list);
			}
		}
		return fieldErrors;
	}
}

