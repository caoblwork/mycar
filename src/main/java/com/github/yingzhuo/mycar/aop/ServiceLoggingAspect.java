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
package com.github.yingzhuo.mycar.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.yingzhuo.mycar.util.AspectUtils;


@Aspect
@Component
public class ServiceLoggingAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceLoggingAspect.class);

	@Around("execution(public * com.github.yingzhuo.mycar.service.*.*(..))")
	public Object hashPassword(ProceedingJoinPoint jp) throws Throwable {
		long start = System.nanoTime();
		Object result = jp.proceed();
		long end   = System.nanoTime();
		LOGGER.info("{}:{}", AspectUtils.getMethod(jp).toGenericString(), (end - start));
		return result;
	}

}
