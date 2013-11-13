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
package com.github.yingzhuo.mycar.domain.comparator;

import java.util.Comparator;

import org.apache.commons.collections.ComparatorUtils;

import com.github.yingzhuo.mycar.domain.User;

@SuppressWarnings("unchecked")
public final class UserComparators {

	private UserComparators() {
	}
	
	// 比较器
	// ---------------------------------------------------------------------------------
	
	@Deprecated
	public static final Comparator<User> DEFAULT = ComparatorUtils.nullLowComparator(new DefaultUserComparator());
	
	public static final Comparator<User> BY_ID   = DEFAULT;
	
	public static final Comparator<User> BY_NICKNAME = ComparatorUtils.nullLowComparator(new ByNicknameUserComparator());

}


// ==========================================================================================

class DefaultUserComparator implements Comparator<User> {
	
	public int compare(User o1, User o2) {
		return o1.getId().intValue() - o2.getId().intValue();
	}

}

class ByNicknameUserComparator implements Comparator<User> {

	public int compare(User o1, User o2) {
		return o1.getNickname().compareTo(o2.getNickname());
	}
	
}