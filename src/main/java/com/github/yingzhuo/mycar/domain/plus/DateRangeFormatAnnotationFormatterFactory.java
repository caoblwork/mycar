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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang3.Range;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.util.StringUtils;


public class DateRangeFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<DateRangeFormat> {

	@Override
	public Set<Class<?>> getFieldTypes() {
		Set<Class<?>> result = new HashSet<Class<?>>();
		result.add(Range.class);
		return result;
	}

	@Override
	public Printer<?> getPrinter(DateRangeFormat annotation, Class<?> fieldType) {
		return new DateRangeFormatter(annotation.pattern(), annotation.delimiter());
	}

	@Override
	public Parser<?> getParser(DateRangeFormat annotation, Class<?> fieldType) {
		return new DateRangeFormatter(annotation.pattern(), annotation.delimiter());
	}

	//~ ================================================================================================================

	private static class DateRangeFormatter implements Formatter<Range<Date>> {

		private DateFormat dateFormat;
		private String delimiter;
		
		public DateRangeFormatter(String pattern, String delimiter) {
			this.delimiter  = delimiter == null ? "" : delimiter;
			this.dateFormat = new SimpleDateFormat(pattern);
		}

		@Override
		public String print(Range<Date> object, Locale locale) {
			String s = this.dateFormat.format(object.getMinimum());
			String e = this.dateFormat.format(object.getMaximum());
			return s + this.delimiter + e;
		}

		@Override
		public Range<Date> parse(String text, Locale locale) throws ParseException {
			String[] arr = StringUtils.split(text, delimiter);
			if (arr.length != 2) {
				throw new ParseException("无法正常区分开始日期和结束日期", -1);
			}
			return Range.between(dateFormat.parse(arr[0]), dateFormat.parse(arr[1]));
		}
	}

}
