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
		<script type="text/javascript" src="js-app/user/sign-up.js"></script>
	</head>

	<body>
		<div id="wrap">
			<%-- import header start --%>
			<jsp:include page="../layout/header.jsp"></jsp:include>
			<%-- import header end --%>

			<div class="container">
				<div class="row">
					<!-- 主要内容 -->
					<div class="span10">
						<form:form class="form-horizontal" modelAttribute="signUpForm" action="user/sign-up">
							<fieldset>
								<legend>注册</legend>
								<div class="control-group">
									<label class="control-label"><span class="text-warning">*</span>电子邮件</label>
									<div class="controls">
										<form:input path="email" placeholder="电子邮件" class="input-xlarge"/>
										<form:errors path="email" cssClass="help-inline" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><span class="text-warning">*</span>性别</label>
									<div class="controls">
										<c:forEach items="${genderBeanList}" var="genderBean">
										<label class="radio"><input type="radio" name="gender" value="${genderBean.value}" <c:if test="${genderBean.default}">checked="checked"</c:if>>${genderBean.label}</label>
										</c:forEach>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><span class="text-warning">*</span>密码</label>
									<div class="controls">
										<form:password path="password" placeholder="密码" class="input-xlarge"/>
										<form:errors path="password" cssClass="help-inline" />
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

		<%-- import footer start --%>
		<jsp:include page="../layout/footer.jsp"></jsp:include>
		<%-- import footer start --%>
	</body>
</html>
