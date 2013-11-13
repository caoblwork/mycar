<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>MyCar</title>
	<base href="<%=basePath%>" />
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
						<legend>
							注册 <small> - 车辆</small>
						</legend>
						<form:form cssClass="form-horizontal" modelAttribute="signUpCarForm">
							<div class="control-group">
								<label class="control-label"><span class="text-warning">*</span>名称</label>
								<div class="controls">
									<form:input path="name" placeholder="名称" />
									<form:errors path="name" cssClass="help-inline" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><span class="text-warning">*</span>类型</label>
								<div class="controls">
									<form:select path="type">
										<form:option value="M">载客</form:option>
										<form:option value="N">载货</form:option>
										<form:option value="O">挂车</form:option>
										<form:option value="X">其他</form:option>
									</form:select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">品牌</label>
								<div class="controls">
									<form:input path="brand" placeholder="品牌" />
									<form:errors path="brand" cssClass="help-inline" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">型号</label>
								<div class="controls">
									<form:input path="modelType" placeholder="型号" />
									<form:errors path="modelType" cssClass="help-inline" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">牌照号</label>
								<div class="controls">
									<form:input path="license" placeholder="牌照号" />
									<form:errors path="license" cssClass="help-inline" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">钢架号</label>
								<div class="controls">
									<form:input path="numberOfFrame" placeholder="钢架号" />
									<form:errors path="numberOfFrame" cssClass="help-inline" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">发动机号</label>
								<div class="controls">
									<form:input path="numberOfEngine" placeholder="发动机号" />
									<form:errors path="numberOfEngine" cssClass="help-inline" />
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit" name="_eventId" value="prev" class="btn">上一步</button>
									<button type="submit" name="_eventId" value="submit" class="btn btn-primary">完 成</button>
									<button type="submit" name="_eventId" value="skip" class="btn btn-success">跳 过</button>
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