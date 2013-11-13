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

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;

import com.github.yingzhuo.mycar.domain.Badge;
import com.github.yingzhuo.mycar.domain.User;
import com.github.yingzhuo.mycar.repository.BadgeRepo;

public abstract class AbstractBadgeGrantorItemProcessor implements ItemProcessor<User, User> {

	@Resource private BadgeRepo badgeRepo;
	
	public abstract String getBadgeName();

	public Badge getBadge() {
		return badgeRepo.findByName(getBadgeName());
	}
	
	public abstract boolean needToGrant(User user);

	protected final boolean alreadyGranted(User user) {
		for (Badge b : user.getBadgeSet()) {
			if (StringUtils.equals(getBadgeName(), b.getName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public final User process(User user) throws Exception {
		if (needToGrant(user)) {
			if (! alreadyGranted(user)) {
				user.getBadgeSet().add(getBadge());
			}
		}
		return user;
	}

}
