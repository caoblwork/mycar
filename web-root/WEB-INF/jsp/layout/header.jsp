<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<span class="brand">MyCar</span>
			<div class="nav-collapse collapse">
	            <ul class="nav">
					<security:authorize access="isAuthenticated()">
					<li <c:if test="${PAGE_NAME == 'CAR_LIST'}">class="active"</c:if>><a href="car/list">首页</a></li>
					</security:authorize>
					<li <c:if test="${PAGE_NAME == 'CONTACT-ME'}">class="active"</c:if>><a href="contact-me">联系我</a></li>
	            </ul>

	            <ul class="nav pull-right">
					<li class="dropdown">
						<security:authorize access="isAuthenticated()">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><security:authentication property="principal.user.nickname"/>(<security:authentication property="principal.user.email"/>)<i class="caret"></i></a>
						</security:authorize>
						<ul class="dropdown-menu">
							<li><a target="_blank" href="http://gravatar.com/emails/"><i class="icon-share-alt"></i> 设置头像</a></li>
							<li><a href="user/password"><i class="icon-white"></i> 更改密码</a></li>
							<li><a href="user/profile"><i class="icon-white"></i> 个人信息</a></li>
							<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_DEVELOPER')">
							<li class="divider"></li>
							<li class="dropdown-submenu">
								<a tabindex="-1" href="#"><i class="icon-wrench"></i> 系统管理</a>
								<ul class="dropdown-menu">
									<li><a href="admin/account">在线用户</a></li>
									<li><a href="admin/environment">工作环境</a></li>
									<li><a href="admin/log-context">日志系统</a></li>
								</ul>
							</li>
							</security:authorize>
							<li class="divider"></li>
							<li><a href="security/logout"><i class="icon-off"></i> 退出系统</a></li>
						</ul>
					</li>
					<li>
						<a href="user/profile"><img alt="" src="images/badge.png"> <b>${CURRENT_USER_BADGE_COUNT}</b></a>
					</li>
				</ul>
          	</div>
		</div>
	</div>
</div>
<br/>
<br/>
<br/>
