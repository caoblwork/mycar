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
		<title>500</title>
		<base href="<%=basePath%>" />
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<link type="image/x-icon" rel="shortcut icon" href="icon/favicon.ico" />
		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />
		
		<script type="text/javascript" src="js-lib/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="js-lib/bootstrap.min.js"></script>
		<script type="text/javascript" src="js-lib/common.js"></script>
	</head>

	<body>
		<div id="wrap">
			<div class="container">
				<div class="row">
					<div class="span12">
						<div id="wrap">
							<br />
							<div class="container">
								<div class="row">
									<div class="span12">
										<div class="hero-unit">
											<div class="pull-left">
												<h1>500 <small>服务器发生错误</small></h1>
												<br/>
												<div>
													<ul class="unstyled">
														<li><i class="icon-envelope" style="margin-top: 8px;"></i> <a href="mailto:${applicationAuthorEmail}">${applicationAuthorEmail}</a></li>
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