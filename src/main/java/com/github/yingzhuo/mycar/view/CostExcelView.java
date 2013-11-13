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
package com.github.yingzhuo.mycar.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.github.yingzhuo.mycar.domain.Car;
import com.github.yingzhuo.mycar.domain.Cost;
import com.github.yingzhuo.mycar.util.CostUtils;

public class CostExcelView extends AbstractExcelView {

	private static final Pair<Integer, Integer> C2 = Pair.<Integer, Integer>of(1, 2);
	private static final Pair<Integer, Integer> B5 = Pair.<Integer, Integer>of(4, 1);
	
	@Override
	@SuppressWarnings("unchecked")
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HSSFSheet sheet = workbook.getSheetAt(0);
		Car car             = (Car) model.get("car");
		List<Cost> costList = (List<Cost>) model.get("costList");
		
		fillCarName(sheet, StringUtils.trim(car.getName()));
		fillCost(workbook, sheet, costList);
	}

	private void fillCarName(HSSFSheet sheet, String carName) {
		HSSFCell c2 = super.getCell(sheet, C2.getLeft(), C2.getRight());
		c2.setCellValue(carName);
	}
	
	private void fillCost(HSSFWorkbook workbook, HSSFSheet sheet, List<Cost> costList) {
		Validate.noNullElements(costList);

		int i = B5.getLeft();
		int j = B5.getRight();

		HSSFCellStyle cellStyle = workbook.createCellStyle();
		HSSFDataFormat format   = workbook.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("yyyy/mm/dd"));
		HSSFFont font = workbook.createFont();
		font.setFontName("微软雅黑");
		font.setFontHeightInPoints((short)11);
		cellStyle.setFont(font);
		
		for (Cost cost : costList) {
			HSSFCell cell1 = super.getCell(sheet, i, j);
			HSSFCell cell2 = super.getCell(sheet, i, j + 1);
			HSSFCell cell3 = super.getCell(sheet, i, j + 2);
			HSSFCell cell4 = super.getCell(sheet, i, j + 3);
			HSSFCell cell5 = super.getCell(sheet, i, j + 4);
			
			cell3.setCellStyle(cellStyle);
			
			cell1.setCellValue(CostUtils.getCostTypeString(cost));
			cell2.setCellValue(CostUtils.getPaymentTypeString(cost.getPaymentType()));
			cell3.setCellValue(cost.getDate());
			cell4.setCellValue(cost.getSum());
			cell5.setCellValue(cost.getComment());
			i ++;
		}
		
	}
}
