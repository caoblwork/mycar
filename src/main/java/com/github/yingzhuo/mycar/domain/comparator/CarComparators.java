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

import com.github.yingzhuo.mycar.domain.Car;

@SuppressWarnings("unchecked")
public final class CarComparators {

	@Deprecated
	public static final Comparator<Car> DEFAULT = ComparatorUtils.nullLowComparator(new DefaultCarComparator());
	
	public static final Comparator<Car> BY_ID   = DEFAULT;
	
	public static final Comparator<Car> BY_NAME = ComparatorUtils.nullLowComparator(new ByNameCarComparator());

}

class DefaultCarComparator implements Comparator<Car> {

	public int compare(Car c1, Car c2) {
		return c1.getId().intValue() - c2.getId().intValue();
	}
	
}

class ByNameCarComparator implements Comparator<Car> {

	public int compare(Car c1, Car c2) {
		return c1.getName().compareTo(c2.getName());
	}
	
}
