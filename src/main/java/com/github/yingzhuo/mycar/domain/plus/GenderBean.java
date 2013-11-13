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
package com.github.yingzhuo.mycar.domain.plus;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.Validate;

import com.github.yingzhuo.mycar.config.SpringUtils;
import com.google.common.collect.ImmutableList;

public class GenderBean  {

	public static List<GenderBean> getGenderBeanList(Locale locale) {
		return ImmutableList.<GenderBean> of(
				new GenderBean(Gender.MALE, locale, true),
				new GenderBean(Gender.FEMALE, locale, false),
				new GenderBean(Gender.UNKNOWN, locale, false)
			);
	}

	private Gender gender;
	private Locale locale;
	private boolean isDefault;
	
	private GenderBean(Gender gender, Locale locale, boolean isDefault) {
		Validate.notNull(gender);
		this.gender = gender;
		this.locale = locale == null ? Locale.getDefault() : locale;
		this.isDefault = isDefault;
	}
	
	public String getLabel() {
		switch (this.gender) {
		case FEMALE: 	return SpringUtils.getMessageSource().getMessage("gender.female",  new Object[] {}, this.locale);
		case MALE:		return SpringUtils.getMessageSource().getMessage("gender.male",    new Object[] {}, this.locale);
		case UNKNOWN:	return SpringUtils.getMessageSource().getMessage("gender.unknown", new Object[] {}, this.locale);
		}
		return null; // won't happen.
	}

	public String getValue() {
		return this.gender.toString();
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	@Override
	public String toString() {
		return this.gender.toString();
	}
	
}
