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
		<script type="text/javascript" src="js-app/car/delete.js"></script>
	</head>

	<body>
		<div id="wrap">
			<jsp:include page="../layout/header.jsp"></jsp:include>

			<div class="container">
				<div class="row">
					<jsp:include page="../layout/nav.jsp"></jsp:include>
					<div class="span10">
						<fieldset>
						<legend>删除车辆</legend>
							<!-- 警告 -->
							
							<form:form modelAttribute="deleteCarReviewForm" cssClass="form-inline alert alert-block alert-error fade in">
								<%--<button type="button" class="close" data-dismiss="alert">&times;</button>--%>
								<h4 class="alert-heading">你正在试图删除一个车辆！</h4>
								<p></p>
								<p>删除车辆的同时会删除其所有相关数据，这个操作是不可恢复的。如果你确定要删除，请输入车辆名称后点击删除按钮。</p>
								<p></p>
								<input type="hidden" name="carId" value="${carId}" />
								<div class="control-group">
									<form:input path="carName" placeholder="车辆名称" />
									<form:errors path="carName" cssClass="help-inline" />
								</div>
								<div class="control-group">
									<div class="controls">
										<button type="submit" name="_eventId" value="submit" class="btn btn-danger">删 除</button>
										<button type="submit" name="_eventId" value="cancel" class="btn">放 弃</button>
									</div>
								</div>
							</form:form>
						</fieldset>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="../layout/footer.jsp"></jsp:include>
	</body>
</html>