<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<script type="text/javascript" src="js-app/security/login.js"></script>
	</head>

	<body>
		<div class="modal">
			<form:form cssClass="modal-form form-horizontal" action="security/login-process" modelAttribute="loginForm" method="post">
				<div class="modal-header">
					<h3>登录</h3>
				</div>
				<div class="modal-body">
					<div class="control-group error">
						<div class="controls">
							<span class="help-block error">${errorMessage}</span>
						</div>
					</div>
					<div class="control-group">
						<div class="control-label">
							<label for="email">电子邮件</label>
						</div>
						<div class="controls">
							<input type="text" value="${loginForm.username}" placeholder="电子邮件" name="j_username" />
						</div>
					</div>
					<div class="control-group">
						<div class="control-label">
							<label for="password">密码</label>
						</div>
						<div class="controls">
							<input type="password" value="" placeholder="密码" name="j_password" />
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<label class="checkbox inline"><input type="checkbox" name="remember-me" checked="checked" /> 记住我</label>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="pull-left">
						<a href="user/sign-up">还没有账户？</a>
						<%--
						<a href="openid/login">用Google帐号登录</a>
						--%>
					</div>
					<div class="pull-right">
						<button type="submit" class="btn btn-primary">登录</button>
					</div>
					<div class="clearfix"></div>
				</div>
			</form:form>
		</div>
	</body>
</html>