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

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.yingzhuo.mycar.annotation.Ajax;
import com.github.yingzhuo.mycar.domain.Car;
import com.github.yingzhuo.mycar.domain.plus.CarType;
import com.github.yingzhuo.mycar.exception.DataBindingException;
import com.github.yingzhuo.mycar.form.CarForm;
import com.github.yingzhuo.mycar.security.SecurityUtils;
import com.github.yingzhuo.mycar.service.BusinessValidator;
import com.github.yingzhuo.mycar.service.CarService;
import com.github.yingzhuo.mycar.service.UserHabitService;
import com.github.yingzhuo.mycar.util.QRCodeUtils;
import com.google.common.math.DoubleMath;

@Controller
@RequestMapping("/car")
public class CarController {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

	@Resource private CarService carService;
	@Resource private UserHabitService habitService;

	// ===================================================================================================
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "error", required = false) String error,
			ModelMap modelMap
		)
	{
		Integer ownerId = SecurityUtils.getPrincipalId();
		List<Car> carList = carService.findCarByOwnerId(ownerId);
		boolean defaultEditMode = habitService.findDefaultEditModeForCarList(ownerId, false, true);
		
		modelMap.put("carList", carList);
		modelMap.put("defaultEditMode", defaultEditMode);
		modelMap.put("error", error);
		return "car/list";
	}

	@Ajax
	@ResponseBody
	@RequestMapping(value = "/edit-mileage", method = {RequestMethod.POST})
	public String editMileage(
			@RequestParam("pk") Integer carId,
			@RequestParam("value") String mileageText,
			HttpServletResponse response
		)
	{
		BusinessValidator.assertCarOwner(carId);
		
		Double mileage = null;
		try {
			mileage = Double.parseDouble(mileageText);
		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "非法数据";
		}

		// 数据四舍五入
		carService.setMileageForCar(carId, (int) DoubleMath.roundToInt(mileage, RoundingMode.HALF_UP));
		
		response.setStatus(HttpServletResponse.SC_OK);
		return "SUCCESS";
	}
	
	@Ajax
	@ResponseBody
	@RequestMapping(value = "/edit-name", method = {RequestMethod.POST})
	public String editName(
			HttpServletResponse response,
			@RequestParam("pk") Integer carId,
			@RequestParam("value") String name
		)
	{
		BusinessValidator.assertCarOwner(carId);
		
		int length = StringUtils.length(name);
		if (length < 2 || length > 10) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "长度应在2-10之间";
		}
		
		if (! carService.isCarAbleToRename(carId, name)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "不能使用此名称";
		}
		
		name = StringUtils.trim(name);
		carService.setNameForCar(carId, name);
		return "SUCCESS";
	}
	
	@Ajax
	@ResponseBody
	@RequestMapping(value = "/edit-brand", method = {RequestMethod.POST})
	public String editBrand(
			HttpServletResponse response,
			@RequestParam("pk") Integer carId,
			@RequestParam("value") String brand
		)
	{
		BusinessValidator.assertCarOwner(carId);

		int length = StringUtils.length(brand);
		if (length > 50) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "长度应在0-50之间";
		}

		brand = StringUtils.trim(brand);
		carService.setBrandForCar(carId, brand);
		return "SUCCESS";
	}
	
	@Ajax
	@ResponseBody
	@RequestMapping(value = "/edit-model-type", method = {RequestMethod.POST})
	public String editModelType(
			HttpServletResponse response,
			@RequestParam("pk") Integer carId,
			@RequestParam("value") String modelType
			)
	{
		BusinessValidator.assertCarOwner(carId);
		
		int length = StringUtils.length(modelType);
		if (length > 50) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "长度应在0-50之间";
		}
		
		modelType = StringUtils.trim(modelType);
		carService.setModelTypeForCar(carId, modelType);
		return "SUCCESS";
	}
	
	@Ajax
	@ResponseBody
	@RequestMapping(value = "/edit-license", method = {RequestMethod.POST})
	public String editLicense(
			HttpServletResponse response,
			@RequestParam("pk") Integer carId,
			@RequestParam("value") String license
		)
	{
		BusinessValidator.assertCarOwner(carId);
		
		int length = StringUtils.length(license);
		if (length > 50) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "长度应在0-50之间";
		}
		carService.setLicenseForCar(carId, license);
		return "SUCCESS";
	}

	@Ajax
	@ResponseBody
	@RequestMapping(value = "/edit-number-of-frame", method = {RequestMethod.POST})
	public String editNumberOfFrame(
			HttpServletResponse response,
			@RequestParam("pk") Integer carId,
			@RequestParam("value") String numberOfFrame
			)
	{
		BusinessValidator.assertCarOwner(carId);
		
		int length = StringUtils.length(numberOfFrame);
		if (length > 50) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "长度应在0-50之间";
		}
		carService.setNumberOfFrameForCar(carId, numberOfFrame);
		return "SUCCESS";
	}

	@Ajax
	@ResponseBody
	@RequestMapping(value = "/edit-number-of-engine", method = {RequestMethod.POST})
	public String editNumberOfEngine(
			HttpServletResponse response,
			@RequestParam("pk") Integer carId,
			@RequestParam("value") String numberOfEngine
		)
	{
		BusinessValidator.assertCarOwner(carId);
		
		int length = StringUtils.length(numberOfEngine);
		if (length > 50) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "长度应在0-50之间";
		}
		carService.setNumberOfEngineForCar(carId, numberOfEngine);
		return "SUCCESS";
	}

	@Ajax
	@ResponseBody
	@RequestMapping(value = "/edit-type", method = {RequestMethod.POST})
	public String editType(
			HttpServletResponse response,
			@RequestParam("pk") Integer carId,
			@RequestParam("value") String typeTxt
		)
	{
		BusinessValidator.assertCarOwner(carId);
		
		CarType type = null;

		try {
			type = Enum.valueOf(CarType.class, typeTxt);
		} catch (IllegalArgumentException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "参数错误";
		}
		carService.setTypeForCar(carId, type);
		return "SUCCESS";
	}

	@Ajax
	@RequestMapping(value = "/add-car", method = {RequestMethod.POST})
	public @ResponseBody Object addCar(@Valid CarForm form, BindingResult bindingResult) throws IllegalAccessException, InvocationTargetException {
		
		if (bindingResult.hasErrors()) {
			throw new DataBindingException(bindingResult);
		}
		
		String newName = form.getName();
		if (! carService.isCarAbleToCreate(SecurityUtils.getPrincipalId(), newName)) {
			bindingResult.rejectValue("name", null, "不能使用此名称");
			throw new DataBindingException(bindingResult);
		}
		
		Car newCar = new Car();
		BeanUtils.copyProperties(newCar, form);

		carService.addCar(newCar, SecurityUtils.getPrincipalId());
		return ControllerSupport.SUCCESS;
	}

	@RequestMapping(value = "/qrcode", method = RequestMethod.GET, params = {"carId"})
	public ResponseEntity<byte[]> qrcode(
			@RequestParam("carId") Integer carId
		)
	{
		BusinessValidator.assertCarOwner(carId);
		
		Car car = carService.findCarById(carId);
		
		List<String> lines = new ArrayList<String>();
		lines.add("品牌: " + car.getBrand());
		lines.add("型号: " + car.getModelType());
		lines.add("钢架: " + car.getNumberOfFrame());
		lines.add("引擎: " + car.getNumberOfEngine());
		lines.add("牌照: " + car.getLicense());
		
		ByteArrayOutputStream body = new ByteArrayOutputStream(128);
		QRCodeUtils.writeToOutputStream(body, lines, 300, 300);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Pragma", "No-cache");
		headers.set("Cache-Control", "no-cache");
		headers.set("Content-Type", "image/png");
		headers.set("Content-Disposition", "attachment;filename=" + car.getId() + ".png");
		headers.set("Expires", "0");
		headers.setContentLength(body.size());

		try {
			return new ResponseEntity<byte[]>(body.toByteArray(), headers, HttpStatus.OK);
		} finally {
			IOUtils.closeQuietly(body);		// 数据从内存到内存，其实不需要关闭
		}
	}

}
