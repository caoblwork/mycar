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
		<script type="text/javascript" src="js-app/admin/account.js"></script>
	</head>

	<body>
		<div id="wrap">
			<jsp:include page="../layout/header.jsp"></jsp:include>

			<div class="container">
				<div class="row">
					<jsp:include page="../layout/nav.jsp"></jsp:include>
					<div class="span10">
						<fieldset>
						<legend>在线用户</legend>
							<table class="table">
								<thead>
									<tr>
										<td><b>ID</b></td>
										<td><b>名号</b></td>
										<td><b>注册Email</b></td>
										<td><b>性别</b></td>
										<td><b>最后登录时间</b></td>
										<td><b>最后登录IP</b></td>
										<td></td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${userSet}" var="user">
									<tr>
										<td>${user.id}</td>
										<td>${user.nickname}</td>
										<td>${user.email}</td>
										<td>
											<c:if test="${user.gender == 'MALE'}">男</c:if>
											<c:if test="${user.gender == 'FEMALE'}">女</c:if>
										</td>
										<td><fmt:formatDate value="${user.lastLoginDate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
										<td>${user.lastLoginIpAddress}</td>
										<td>
											<c:if test="${!user.admin}">
											<a href="#" data-type="evit" data-id="${user.id}">强制下线</a>
											</c:if>
										</td>
									</tr>
									</c:forEach>
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