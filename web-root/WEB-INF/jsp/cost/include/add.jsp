<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="tab-pane fade in" id="add" data-type="ADD">
	<div class="form-horizontal">
		<div class="control-group">
			<label class="control-label">类型</label>
			<div class="controls">
				<select name="costType">
					<option value="GAS" <c:if test="${mostCostType == 'GAS'}">selected=selected</c:if>>燃油</option>
					<option value="PARKING" <c:if test="${mostCostType == 'PARKING'}">selected=selected</c:if>>停车</option>
					<option value="WASHING" <c:if test="${mostCostType == 'WASHING'}">selected=selected</c:if>>洗车</option>
					<option value="TOLL" <c:if test="${mostCostType == 'TOLL'}">selected=selected</c:if>>路桥</option>
					<option value="FINE" <c:if test="${mostCostType == 'FINE'}">selected=selected</c:if>>罚款</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">日期</label>
			<div class="controls">
				<input type="text" name="date" readonly="readonly" style="cursor: pointer; background-color: #FFF;" value="${today}" />
			</div>
		</div>
		<div class="control-group <c:if test="${fn:length(carList) == 1}">hide</c:if>">
			<label class="control-label">车辆</label>
			<div class="controls">
				<select name="carId">
					<c:forEach items="${carList}" var="c">
					<option value="${c.id}">${c.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">花费(元)</label>
			<div class="controls">
				<input type="text" name="sum" placeholder="花费" />
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付类型</label>
			<div class="controls">
				<select name="paymentType">
					<option value="CASH">现金</option>
					<option value="PREPAYMENT_CARD">预付费卡</option>
					<option value="CREDIT_CARD">信用卡</option>
					<option value="DEBIT_CARD">借记卡</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注</label>
			<div class="controls">
				<textarea rows="" cols="" name="comment" style="resize: none;" placeholder="备注"></textarea>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group" data-type="GAS">
			<label class="control-label">燃油种类</label>
			<div class="controls">
				<select name="gasType">
					<option value="_0">0号柴油</option>
					<option value="_93">93号汽油</option>
					<option value="_97">97号汽油</option>
					<option value="_98">98号汽油</option>
					<option value="_OTHER">其他</option>
				</select>
			</div>
		</div>
		<div class="control-group" data-type="GAS">
			<label class="control-label">燃油单价(元/升)</label>
			<div class="controls">
				<input type="text" name="price" placeholder="燃油单价" />
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group" data-type="GAS">
			<label class="control-label">加油量(升)</label>
			<div class="controls">
				<input type="text" name="cubage" placeholder="加油量" />
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group" data-type="GAS">
			<label class="control-label">加油类型</label>
			<div class="controls">
				<select name="fillingType">
					<option value="FIXED_MONEY">固定金额</option>
					<option value="FIXED_CUBAGE">固定容积</option>
					<option value="FULL">加满</option>
				</select>
			</div>
		</div>
		<div class="control-group" data-type="TOLL">
			<label class="control-label">里程(千米)</label>
			<div class="controls">
				<input type="text" name="mileage" placeholder="里程" />
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group" data-type="FINE">
			<label class="control-label">扣分</label>
			<div class="controls">
				<select name="point">
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="6">6</option>
					<option value="12">12</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button type="button" id="addSubmit" class="btn btn-primary">提交</button>
				<button type="button" id="reset" class="btn">重新填写</button>
			</div>
		</div>
	</div>
</div>
