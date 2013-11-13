<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<spring:eval expression="@applicationProperties['application.version']" var="applicationVersion" />
<spring:eval expression="@applicationProperties['application.author']"  var="applicationAuthor" />
<spring:eval expression="@applicationProperties['application.author.email']"  var="applicationAuthorEmail" />
<spring:eval expression="@applicationProperties['application.author.address']"  var="applicationAuthorAddress" />
<spring:eval expression="@applicationProperties['application.source.url']"  var="applicationSourceUrl" />
<spring:eval expression="@applicationProperties['license.name']"  var="licenseName" />
<spring:eval expression="@applicationProperties['license.url']"   var="licenseUrl" />

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<title>MyCar</title>
		<base href="<%=basePath%>" />
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<jsp:include page="../layout/reference.jsp"></jsp:include>
	</head>

	<body>
		<div id="wrap">
			<jsp:include page="../layout/header.jsp"></jsp:include>
	
			<div class="container">
				<div class="row">
					<div class="span12">
						<div id="wrap">
							<br />
							<div class="container">
								<div class="row">
									<div class="span12">
										<div class="hero-unit">
											<div class="thumbnail pull-left" style="margin-right: 20px;">
												<img alt="weixin" src="images/ying-weixin.jpg">
												<div class="caption" align="center">
													<h3>微信</h3>
												</div>
											</div>
											<div class="pull-left">
												<h1>
													应卓 <small>巨人网络</small>
												</h1>
												<br />
												<div>
													<ul class="unstyled">
														<li><i class="icon-download" style="margin-top: 8px;"></i> <a href="${applicationSourceUrl}">项目主页</a></li>
														<li><i class="icon-envelope" style="margin-top: 8px;"></i> <a href="${applicationAuthorEmail}">${applicationAuthorEmail}</a></li>
														<li><i class="icon-map-marker" style="margin-top: 8px;"></i> ${applicationAuthorAddress}</li>
													</ul>
												</div>
											</div>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="../layout/footer.jsp"></jsp:include>
	</body>
</html>