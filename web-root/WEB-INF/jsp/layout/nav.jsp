<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="avatar" uri="https://github.com/yingzhuo/gravatar4j/taglibs"%>


<div class="span2">
	<div class="well well-small sidebar-nav">
		<ul class="nav nav-list">
			<li>
				<ul class="thumbnails">
					<li>
						<a class="thumbnail" href="/user/profile"><avatar:img email="${CURRENT_USER_EMAIL}" size="120" /></a>
					</li>
				</ul>
			</li>
			<li class="nav-header">data</li>
			<li <c:if test="${fn:contains(PAGE_NAME, 'CAR_LIST')}">class="active"</c:if>><a href="car/list">车辆</a></li>
			<li <c:if test="${fn:contains(PAGE_NAME, 'CONTACT_LIST')}">class="active"</c:if>><a href="contact/list/1">联系人</a></li>
			<li <c:if test="${fn:contains(PAGE_NAME, 'COST_LIST')}">class="active"</c:if>><a href="cost/list">消费</a></li>
			<li class="nav-header">statistics</li>
			<li <c:if test="${fn:contains(PAGE_NAME, 'STATISTICS_COST')}">class="active"</c:if>><a href="statistics/cost">图表</a></li>
		</ul>
	</div>
</div>