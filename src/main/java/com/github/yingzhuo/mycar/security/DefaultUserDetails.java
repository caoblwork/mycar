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
package com.github.yingzhuo.mycar.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.github.yingzhuo.mycar.domain.User;
import com.google.common.collect.Sets;

public final class DefaultUserDetails implements UserDetails, Serializable {

	private static final long serialVersionUID = 7704795243529875684L;
	
	private User user;
	
	public static UserDetails decorate(User user) {
		return user == null ? null : new DefaultUserDetails(user);
	}
	
	private DefaultUserDetails(User user) {
		this.user = Validate.notNull(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = Sets.newHashSet();
		if (user.isUser())		authorities.add(DefaultGrantedAuthority.USER);
		if (user.isAdmin()) 	authorities.add(DefaultGrantedAuthority.ADMIN);
		if (user.isDeveloper()) authorities.add(DefaultGrantedAuthority.DEVELOPER);
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	public User getUser() {
		return this.user;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DefaultUserDetails))
			return false;
		DefaultUserDetails other = (DefaultUserDetails) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.user == null ? "null" : this.user.toString();
	}

}

enum DefaultGrantedAuthority implements GrantedAuthority, Serializable {

	ADMIN {
		@Override
		public String getAuthority() {
			return "ROLE_ADMIN";
		}
	},

	DEVELOPER {
		@Override
		public String getAuthority() {
			return "ROLE_DEVELOPER";
		}
	},
	
	USER {
		@Override
		public String getAuthority() {
			return "ROLE_USER";
		}
	};

}