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
		<script type="text/javascript" src="js-app/admin/environment.js"></script>
	</head>

	<body>
		<div id="wrap">
			<jsp:include page="../layout/header.jsp"></jsp:include>

			<div class="container">
				<div class="row">
					<jsp:include page="../layout/nav.jsp"></jsp:include>
					<div class="span10">
						<fieldset>
						<legend>工作环境</legend>
							<div>
								<blockquote class="pull-left" style="padding-right: 12px;">
									<h2 class="text-error">PRODUCTION</h2>
									<small>开发环境数据库</small>
								</blockquote>
								
								<c:if test="${fn:contains(profiles, 'DEV')}">
								<blockquote class="pull-left" style="padding-right: 12px;">
									<h2 class="text-info">DEBUG</h2>
									<small>部分配置为调试模式</small>
								</blockquote>
								</c:if>
							</div>
							
							<table class="table table-striped table-hover">
								<tbody>
									<tr>
										<td><b>数据库类型</b></td>
										<td>${dataSourceInfo.type}</td>
									</tr>
									<tr>
										<td><b>驱动类</b></td>
										<td>${dataSourceInfo.driverClass}</td>
									</tr>
									<tr>
										<td><b>URL</b></td>
										<td>${dataSourceInfo.url}</td>
									</tr>
									<tr>
										<td><b>用户</b></td>
										<td>${dataSourceInfo.user}</td>
									</tr>
									<tr>
										<td><b>ORM方言</b></td>
										<td>${dataSourceInfo.ormDialect}</td>
									</tr>
								</tbody>
							</table>
						</fieldset>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="../layout/footer.jsp"></jsp:include>
	</body>
</html>