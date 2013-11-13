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
		<script type="text/javascript" src="js-app/user/sign-up.js"></script>
	</head>

	<body>
		<div id="wrap">
			<jsp:include page="../layout/header.jsp"></jsp:include>

			<div class="container">
				<div class="row">
					<div class="span10">
						<fieldset>
							<legend>注册 <small> - 邮件</small></legend>
							<form:form cssClass="form-horizontal" modelAttribute="signUpEmailForm">
								<div class="control-group">
									<label class="control-label">电子邮件</label>
									<div class="controls">
										<form:input path="email" placeholder="电子邮件" />
										<form:errors path="email" cssClass="help-inline" />
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<button type="submit" name="_eventId" value="next" class="btn btn-primary">下一步</button>
										<button type="submit" name="_eventId" value="cancel" class="btn">取 消</button>
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