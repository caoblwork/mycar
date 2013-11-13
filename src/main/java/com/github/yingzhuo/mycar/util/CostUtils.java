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
package com.github.yingzhuo.mycar.util;

import java.util.ArrayList;

import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.github.yingzhuo.mycar.domain.Cost;
import com.github.yingzhuo.mycar.domain.FineCost;
import com.github.yingzhuo.mycar.domain.GasCost;
import com.github.yingzhuo.mycar.domain.ParkingCost;
import com.github.yingzhuo.mycar.domain.TollCost;
import com.github.yingzhuo.mycar.domain.WashingCost;
import com.github.yingzhuo.mycar.domain.plus.PaymentType;

public class CostUtils {

	public static String getCostTypeString(Cost cost) {
		if (cost == null) return "";
		if ( cost instanceof GasCost ) return "燃油";
		if ( cost instanceof ParkingCost ) return "停车";
		if ( cost instanceof WashingCost ) return "洗车";
		if ( cost instanceof TollCost ) return "路桥";
		if ( cost instanceof FineCost ) return "罚款";
		throw new IllegalStateException();
	}

	public static String getPaymentTypeString(PaymentType type) {
		if (type == null) return "";
		if (type == PaymentType.CASH) return "现金";
		if (type == PaymentType.CREDIT_CARD) return "信用卡";
		if (type == PaymentType.DEBIT_CARD) return "借记卡";
		if (type == PaymentType.PREPAYMENT_CARD) return "预付费卡";
		throw new IllegalStateException();
	}

	public static Page<Cost> cast(Page<? extends Cost> page, Pageable pageable) {
		Validate.notNull(page);
		Validate.notNull(pageable);
		return new PageImpl<Cost>(new ArrayList<Cost>(page.getContent()), pageable, page.getTotalElements());
	}
}
