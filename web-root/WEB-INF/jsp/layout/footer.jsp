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

<spring:eval expression="@applicationProperties['application.version']" var="applicationVersion" />
<spring:eval expression="@applicationProperties['application.author']"  var="applicationAuthor" />
<spring:eval expression="@applicationProperties['application.author.email']"  var="applicationAuthorEmail" />
<spring:eval expression="@applicationProperties['application.author.address']"  var="applicationAuthorAddress" />
<spring:eval expression="@applicationProperties['application.source.url']"  var="applicationSourceUrl" />
<spring:eval expression="@applicationProperties['license.name']"  var="licenseName" />
<spring:eval expression="@applicationProperties['license.url']"   var="licenseUrl" />

<div id="footer" class="navbar navbar-fixed-bottom">
	<div class="navbar-inner">
		<div class="container" style="margin-top: 5px;">
			<div class="pull-left">
				<p class="muted">
					Designed and built by <a href="mailto:${applicationAuthorEmail}" class="muted">${applicationAuthor}</a>.
					This software is licensed under <a href="${licenseUrl}" class="muted">${licenseName}</a>
				</p>
			</div>
			<div class="pull-right">
				<p class="muted">${applicationVersion}</p>
			</div>
			<div class="clearfix"></div>
      	</div>
	</div>
</div>