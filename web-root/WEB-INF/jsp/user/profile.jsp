<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %> 

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
<html lang="zh">
	<head>
		<title>MyCar</title>
		<base href="<%=basePath %>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<jsp:include page="../layout/reference.jsp"></jsp:include>
		<script type="text/javascript" src="js-app/user/profile.js"></script>
	</head>

	<body>
		<div id="wrap">
			<%-- import header start --%>
			<jsp:include page="../layout/header.jsp"></jsp:include>
			<%-- import header end --%>

			<div class="container">
				<div class="row">
					<!-- 导航List -->
					<%-- import main-nav start --%>
					<jsp:include page="../layout/nav.jsp"></jsp:include>
					<%-- import main-nav end --%>

					<!-- 主要内容 -->
					<div class="span10">
						<div class="form-horizontal">
							<fieldset>
								<legend>个人信息</legend>
								<table class="table table-bordered table-hover">
									<tr>
										<td><b>电子邮件</b></td>
										<td><span>${user.email}</span></td>
									</tr>
									<tr>
										<td><b>名号</b></td>
										<td><a id="nickname" data-pk="${user.id}" data-type="text" href="#" class="editable" data-url="user/edit-nickname">${user.nickname}</a></td>
									</tr>
									<tr>
										<td><b>性别</b></td>
										<td>
											<a id="gender" data-pk="${user.id}" data-type="select" href="#" class="editable" data-url="user/edit-gender">
												<c:if test="${user.gender == 'MALE'}">男</c:if>
												<c:if test="${user.gender == 'FEMALE'}">女</c:if>
											</a>
										</td>
									</tr>
								</table>
								<table class="table well well-small">
									<tr>
										<td><b>获得的徽章</b></td>
										<td></td>
									</tr>
									<c:forEach items="${user.badgeSet}" var="b">
									<tr>
										<td><img alt="" src="images/badge.png"> "${b.name}"</td>
										<td>${b.description}</td>
									</tr>
									</c:forEach>
								</table>
							</fieldset>
						</div>
					</div>
				</div>
			</div>
		</div>

		<%-- import footer start --%>
		<jsp:include page="../layout/footer.jsp"></jsp:include>
		<%-- import footer start --%>
	</body>
</html>