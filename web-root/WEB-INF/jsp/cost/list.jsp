<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<title>MyCar</title>
		<base href="<%= basePath %>" />
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<jsp:include page="../layout/reference.jsp"></jsp:include>
		<script type="text/javascript" src="js-app/cost/list.js"></script>
	</head>

	<body>
		<div id="wrap">
			<jsp:include page="../layout/header.jsp"></jsp:include>

			<div class="container">
				<div class="row">
					<jsp:include page="../layout/nav.jsp"></jsp:include>
					<div class="span10">
						<fieldset>
						<legend>消费</legend>
							<div>
								<div class="input-prepend">
									<span class="add-on"><i class="icon-star"></i></span>
									<select class="input-medium" id="carId" <c:if test="${fn:length(carList) == 1}">disabled="disabled"</c:if>>
										<option value="-1">全部</option>
										<c:forEach items="${carList}" var="c">
										<option value="${c.id}" <c:if test="${fn:length(carList) == 1}">selected="selected"</c:if>>${c.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="input-prepend">
									<span class="add-on"><i class="icon-calendar"></i></span>
									<input type="hidden" name="daterange" value="1970-01-01|2099-12-31" />
									<input type="text" id="daterange" readonly="readonly" style="background-color: #FFF; cursor: pointer;" value="1970/01/01 - 2099/12/31" />
								</div>
							</div>
							<div>
								<a href="#" id="download-excel" target="_blank">下载</a>
							</div>
							<br/>
							<div>
								<ul id="tab" class="nav nav-tabs">
									<li><a href="#gas">燃油</a></li>
									<li><a href="#parking">停车</a></li>
									<li><a href="#washing">洗车</a></li>
									<li><a href="#toll">路桥</a></li>
									<li><a href="#fine">罚款</a></li>
									<li><a href="#add">+</a></li>
									<li class="pull-right"><a href="#setting"><i class="icon-eye-open"></i></a></li>
								</ul>
								<div id="tabContent" class="tab-content">
									<jsp:include page="./include/gas.jsp"></jsp:include>
									<jsp:include page="./include/parking.jsp"></jsp:include>
									<jsp:include page="./include/washing.jsp"></jsp:include>
									<jsp:include page="./include/toll.jsp"></jsp:include>
									<jsp:include page="./include/fine.jsp"></jsp:include>
									<jsp:include page="./include/add.jsp"></jsp:include>
									<jsp:include page="./include/setting.jsp"></jsp:include>
								</div>
							</div>
						</fieldset>
					</div>
				</div>
			</div>
		</div>

		<input type="hidden" name="defaultEditMode" value="${defaultEditMode}" />
		<input type="hidden" name="lastTabIndex" value="${lastTabIndex}" />
		<input type="hidden" name="mostPaymentType" value="${mostPaymentType}" />
		<input type="hidden" name="mostGasType" value="${mostGasType}" />
		<input type="hidden" name="pageSize" value="${pageInfoMap['pageSize']}" />
		<input type="hidden" name="pageSort" value="${pageInfoMap['pageSort']}" />
		<input type="hidden" name="pageSortDir" value="${pageInfoMap['pageSortDir']}" />
		<jsp:include page="../layout/footer.jsp"></jsp:include>
	</body>
</html>