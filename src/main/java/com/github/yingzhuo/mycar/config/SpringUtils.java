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
package com.github.yingzhuo.mycar.config;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.github.yingzhuo.mycar.service.CacheService;
import com.google.common.collect.Lists;

@Component
public final class SpringUtils implements ApplicationContextAware {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringUtils.class);
	
	private static ApplicationContext HOLDER;
	
	@Resource
	protected CacheService cacheService;
	
	public static ApplicationContext get() {
		return HOLDER;
	}

	public static <T> T getBean(Class<T> beanType) {
		return HOLDER.getBean(beanType);
	}
	
	public static <T> T getBean(String beanName, Class<T> beanType) {
		return HOLDER.getBean(beanName, beanType);
	}
	
	public static MessageSource getMessageSource() {
		return getBean(MessageSource.class);
	}
	
	@Deprecated
	public static Profile getActiveProfile() {
		String[] names = HOLDER.getEnvironment().getActiveProfiles();
		if (ArrayUtils.contains(names, "dev")) {
			return Profile.DEV;
		}
		if (ArrayUtils.contains(names, "production")) {
			return Profile.PRODUCTION;
		}
		return null;
	}
	
	public static List<Profile> getActiveProfiles() {
		String[] array = HOLDER.getEnvironment().getActiveProfiles();
		
		List<Profile> list = Lists.newArrayList();
		for (String s : array) {
			Profile p = null;
			try {
				p = Profile.valueOf(s.toUpperCase());
			} catch (Exception e) {
			}
			if (p != null) list.add(p);
		}
		return list;
	}
	
	// --------------------------------------------------------------------------------
	
	@PostConstruct
	public void init() {
		
		// 日志展示当前工作环境
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(StringUtils.repeat('*', 50));
			LOGGER.debug(StringUtils.center("ActiveProfile: {}", 50), getActiveProfiles());
			LOGGER.debug(StringUtils.repeat('*', 50));
		}
		
		// 初始化用户缓存
		cacheService.findAllUserSnapshot();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringUtils.HOLDER = applicationContext;
	}

}

