<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="ajax-result">
	<c:if test="${page.totalPages != 0}">
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<td><b>日期</b></td>
				<td><b>车辆</b></td>
				<td><b>支付方式</b></td>
				<td><b>燃油种类</b></td>
				<td><b>燃油单价(元/升)</b></td>
				<td><b>加油方式</b></td>
				<td><b>加油量(升)</b></td>
				<td><b>花费(元)</b></td>
				<td><b>备注</b></td>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="gc" varStatus="s">
			<tr>
				<td><fmt:formatDate value="${gc.date}" pattern="yyyy/MM/dd" /></td>
				<td>${gc.car.name}</td>
				<td>
					<c:if test="${gc.paymentType == 'CASH'}">现金</c:if>
					<c:if test="${gc.paymentType == 'PREPAYMENT_CARD'}">预付费卡</c:if>
					<c:if test="${gc.paymentType == 'CREDIT_CARD'}">信用卡</c:if>
					<c:if test="${gc.paymentType == 'DEBIT_CARD'}">借记卡</c:if>
				</td>
				<td>
					<c:if test="${gc.gasType == '_0'}">0号柴油</c:if>
					<c:if test="${gc.gasType == '_93'}">93号汽油</c:if>
					<c:if test="${gc.gasType == '_97'}">97号汽油</c:if>
					<c:if test="${gc.gasType == '_98'}">98号汽油</c:if>
					<c:if test="${gc.gasType == '_OTHER'}">其他</c:if>
				</td>
				<td>${gc.price}</td>
				<td>
					<c:if test="${gc.fillingType == 'FIXED_MONEY'}">固定金额</c:if>
					<c:if test="${gc.fillingType == 'FIXED_CUBAGE'}">固定容积</c:if>
					<c:if test="${gc.fillingType == 'FULL'}">加满</c:if>
				</td>
				<td><c:if test="${gc.cubage == null}">-</c:if><c:if test="${gc.cubage != null}">${gc.cubage}</c:if></td>
				<td>${gc.sum}</td>
				<td>
					<c:if test="${gc.comment != null}"><i class="icon-comment" style="cursor: pointer;" rel="tooltip" title="${gc.comment}"></i></c:if>
				</td>
				<td>
					<a href="#" data-id="${gc.id}" data-function="delete">删除</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${page.totalPages > 1}">
	<div>
		<div class="pagination pagination-right">
			<ul>
				<!-- 第一页 -->
				<c:if test="${page.firstPage}">
					<li class="disabled"><span>&laquo;</span></li>
				</c:if>
				<c:if test="${!page.firstPage}">
					<li><a href="#" data-page-number="1">&laquo;</a></li>
				</c:if>
				
				<c:forEach begin="1" end="${page.totalPages}" var="i">
				<c:if test="${pageNumber == i}">
					<li class="active"><span>${pageNumber}</span></li>
				</c:if>
				<c:if test="${pageNumber != i}">
					<li><a href="#" data-page-number="${i}">${i}</a></li>
				</c:if>
				</c:forEach>
				
				<!-- 最后一页 -->
				<c:if test="${page.lastPage}">
					<li class="disabled"><span>&raquo;</span></li>
				</c:if>
				<c:if test="${!page.lastPage}">
					<li><a href="#" data-page-number="${page.totalPages}">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
		<div class="clearfix"></div>
	</div>
	</c:if>
	</c:if>
	<c:if test="${page.totalPages == 0}">
		<div class="alert alert-block alert-success">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			没有数据~
		</div>
	</c:if>
</div>
