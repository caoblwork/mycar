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
package com.github.yingzhuo.mycar.batch;

import org.springframework.stereotype.Component;

import com.github.yingzhuo.mycar.domain.User;

@Component
public class DeveloperBadgeGrantorItemProcessor extends AbstractBadgeGrantorItemProcessor {

	private static final String BADGE_NAME = "开发者";

	@Override
	public String getBadgeName() {
		return BADGE_NAME;
	}

	@Override
	public boolean needToGrant(User user) {
		return user.isDeveloper();
	}

}
