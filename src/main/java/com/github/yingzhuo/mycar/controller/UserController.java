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
package com.github.yingzhuo.mycar.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.yingzhuo.mycar.domain.User;
import com.github.yingzhuo.mycar.domain.plus.Gender;
import com.github.yingzhuo.mycar.exception.EvilAccessException;
import com.github.yingzhuo.mycar.form.PasswordForm;
import com.github.yingzhuo.mycar.security.SecurityUtils;
import com.github.yingzhuo.mycar.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Resource private UserService userService;
	
//	@Autowired @Key(group = KeyGroup.A, type = KeyType.PUBLIC)
//	private PublicKey publicKey;
	
	// --------------------------------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/password", method = {RequestMethod.GET}, params = "!success")
	public String password(ModelMap modelMap) {
		User user = userService.findUserById(SecurityUtils.getPrincipalId());
		PasswordForm form = new PasswordForm();
		form.setStandAlonePassword(user.isStandAlonePassword());
		modelMap.put("passwordForm", form);
		return "user/password";
	}

	@RequestMapping(value = "/password", method = {RequestMethod.GET}, params = "success")
	public String passwordSuccess(ModelMap modelMap, HttpSession session) {
		User user = userService.findUserById(SecurityUtils.getPrincipalId());
		PasswordForm form = new PasswordForm();
		form.setStandAlonePassword(user.isStandAlonePassword());
		modelMap.put("passwordForm", form);
		modelMap.put("message", "密码修改成功");
		return "user/password";
	}

	@RequestMapping(value = "/password", method = {RequestMethod.POST})
	public String changePassword(
			@Valid @ModelAttribute("passwordForm") PasswordForm form,
			BindingResult bindingResult,
			ModelMap modelMap,
			HttpServletResponse response,
			HttpSession session
		)
	{
		Integer userId = SecurityUtils.getPrincipalId();
		
		if (bindingResult.hasErrors()) {
			LOGGER.debug("数据绑定失败");
			return "user/password";
		}
		
		boolean passwordCorrect = !form.isStandAlonePassword() ? userService.isPasswordCorrect(userId, form.getOldPassword()) : true;
		String storedCaptcha     = (String) session.getAttribute("CAPTCHA_KEY");
		boolean captchaCorrect  = StringUtils.equalsIgnoreCase(storedCaptcha, form.getCaptcha());
		
		if (! passwordCorrect) {
			LOGGER.debug("旧密码不正确");
			bindingResult.rejectValue("oldPassword", null, "旧密码不正确");
		}
		
		if (! captchaCorrect ) {
			LOGGER.debug("验证码不正确");
			bindingResult.rejectValue("captcha", null, "验证码不正确");
		}
		
		if (bindingResult.hasErrors()) {
			return "user/password";
		}

		userService.setPasswordForUser(userId, form.getNewPassword());
		modelMap.put("passwordForm", new PasswordForm());

		return "redirect:/user/password?success";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String infomation(ModelMap modelMap)
	{
		Integer userId = SecurityUtils.getPrincipalId();
		modelMap.put("user", userService.findUserById(userId));
		return "user/profile";
	}

	@RequestMapping("/edit-nickname")
	public @ResponseBody String editNickname(
			@RequestParam("pk") Integer userId,
			@RequestParam("value") String nickname,
			HttpServletResponse response
		)
	{
		if (userId.intValue() != SecurityUtils.getPrincipalId().intValue()) {
			throw new EvilAccessException();
		}
		
		int length = StringUtils.length(nickname);
		if (length < 2 || length > 10) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "名号长度必须在2和10之间";
		}
		
		userService.setNicknameForUser(userId, nickname);
		SecurityUtils.getPrincipal(User.class).setNickname(nickname);
		return "SUCCESS";
	}

	@RequestMapping("/edit-gender")
	public @ResponseBody String editGender(
			@RequestParam("pk") Integer userId,
			@RequestParam("value") String sex,
			HttpServletResponse response
		)
	{
		if (userId.intValue() != SecurityUtils.getPrincipalId().intValue()) {
			throw new EvilAccessException();
		}
		
		Gender gender = Gender.valueOf(sex);
		userService.setGenderForUser(userId, gender);
		SecurityUtils.getPrincipal(User.class).setGender(gender);
		return "SUCCESS";
	}
}
