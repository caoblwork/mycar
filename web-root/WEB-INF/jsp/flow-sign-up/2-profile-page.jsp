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
							<legend>注册 <small> - 个人信息</small></legend>
							<form:form cssClass="form-horizontal" modelAttribute="signUpProfileForm">
								<div class="control-group">
									<label class="control-label">姓名</label>
									<div class="controls">
										<form:input path="name" placeholder="姓名" />
										<form:errors path="name" cssClass="help-inline" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">性别</label>
									<div class="controls">
										<label class="radio inline"><form:radiobutton path="gender" value="MALE" />男</label>
										<label class="radio inline"><form:radiobutton path="gender" value="FEMALE" />女</label>
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<button type="submit" name="_eventId" value="prev" class="btn">上一步</button>
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