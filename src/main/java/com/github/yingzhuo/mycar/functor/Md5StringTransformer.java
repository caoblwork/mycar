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
package com.github.yingzhuo.mycar.functor;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;

@Component("md5StringTransformer")
public class Md5StringTransformer implements Function<String, String> {

	@Override
	public String apply(String input) {
		if (input == null) return null;
		return DigestUtils.md5Hex(input);
	}

}
