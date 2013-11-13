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
package com.github.yingzhuo.mycar.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.github.yingzhuo.mycar.config.SpringUtils;
import com.github.yingzhuo.mycar.domain.plus.UserSnapshot;
import com.github.yingzhuo.mycar.service.CacheService;

public class UserSnapshotCacheTag extends TagSupport {

	private static final long serialVersionUID = 3335937594490039336L;

	private Integer entityId;
	private String property;
	
	// ----------------------------------------------------------------------------------
	
	public UserSnapshotCacheTag() {
		super();
	}
	
	// ----------------------------------------------------------------------------------
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		CacheService service = SpringUtils.getBean(CacheService.class);
		
		UserSnapshot ud = service.findAllUserSnapshot().get(getEntityId());
		if (ud == null) {
			print(out, StringUtils.EMPTY);
			return SKIP_BODY;
		}
		
		String propertyValue = null;

		try {
			propertyValue = BeanUtils.getProperty(ud, property);
		} catch (Exception e) {
			throw new JspException(e.getMessage(), e);
		}
		
		print(out, propertyValue);
		
		return SKIP_BODY;
	}
	
	private void print(JspWriter out, String str) throws JspException {
		try {
			out.print(str);
		} catch (IOException e) {
			new JspException(e.getMessage(), e);
		}
	}
	
	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}
	
	// ----------------------------------------------------------------------------------

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	
}
