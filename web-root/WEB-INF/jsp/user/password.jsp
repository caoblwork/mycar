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
		<script type="text/javascript" src="js-app/user/password.js"></script>
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
						<form:form class="form-horizontal" modelAttribute="passwordForm" action="user/password">
							<fieldset>
								<legend>更改密码</legend>
								<div class="control-group">
									<c:if test="${passwordForm.standAlonePassword}">
									<label class="control-label"><span class="text-warning">*</span>旧密码</label>
									<div class="controls">
										<form:password path="oldPassword" placeholder="旧密码" class="input-xlarge"/>
										<form:errors path="oldPassword" cssClass="help-inline" />
									</div>
									</c:if>
								</div>
								<div class="control-group">
									<label class="control-label"><span class="text-warning">*</span>新密码</label>
									<div class="controls">
										<form:password path="newPassword" placeholder="新密码" class="input-xlarge"/>
										<form:errors path="newPassword" cssClass="help-inline" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><span class="text-warning">*</span>验证码</label>
									<div class="controls">
										<form:password path="captcha" placeholder="验证码" class="input-xlarge"/>
										<form:errors path="captcha" cssClass="help-inline" /> <br/>
										<a href="#" rel="captcha"><img alt="" src="images/captcha.jpeg" /></a>
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<button type="submit" class="btn btn-primary">提交</button>
									</div>
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		
		<span id="message" class="hide">${message}</span>

		<%-- import footer start --%>
		<jsp:include page="../layout/footer.jsp"></jsp:include>
		<%-- import footer start --%>
	</body>
</html>