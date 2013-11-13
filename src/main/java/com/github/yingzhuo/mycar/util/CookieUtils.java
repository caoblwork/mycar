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

import javax.servlet.http.Cookie;

public class CookieUtils {

	/** 私有构造方法 */
	private CookieUtils() {}
	
	public static Cookie getInstance(String name, String value) {
		return getInstance(name, value, Integer.MAX_VALUE);
	}
	
	public static Cookie getInstance(String name, String value, int maxAge) {
		return getInstance(name, value, maxAge, "/");
	}
	
	public static Cookie getInstance(String name, String value, int maxAge, String uri) {
		Cookie c = new Cookie(name, value);
		c.setMaxAge(maxAge);
		c.setPath(uri);
		return c;
	}

	public static Cookie getInstanceForRemove(String name) {
		return getInstance(name, "", 0, "/");
	}

}
