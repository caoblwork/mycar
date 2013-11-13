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

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

@SuppressWarnings({"unchecked", "rawtypes"})
public class AspectUtils {

	private AspectUtils() {
	}

	// methods
	// -------------------------------------------------------------------------------

	public static Method getMethod(JoinPoint joinPoint) {
		if (joinPoint == null) return null;
		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		return ms.getMethod();
	}

	public static boolean isClassNameEndWithAny(Class targetClass, String... strings) {
		String className = targetClass.getSimpleName();
		return StringUtils.endsWithAny(className, strings);
	}

	public static boolean isAnnotated(Class targetClass, Class annotationClass) {
		if (targetClass == null) return false;
		return targetClass.getAnnotation(annotationClass) != null;
	}

	public static boolean isAnnotated(Method method, Class annotationClass) {
		if (method == null) return false;
		return method.getAnnotation(annotationClass) != null;
	}
	
}
