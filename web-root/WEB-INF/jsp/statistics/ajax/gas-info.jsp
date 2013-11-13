<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<table class="table">
	<tr class="success">
		<td><b>里程</b></td>
		<td>${car.mileage}</td>
		<td><b>总耗油量(升)</b></td>
		<td><fmt:formatNumber value="${totalCubage}" pattern="#,###.##" /></td>
		<td><b>平均油耗(升/百千米)</b></td>
		<td><fmt:formatNumber value="${totalCubage / car.mileage * 100}" pattern="#,###.##" /></td>
	</tr>
	<tr class="info">
		<td><b>里程</b></td>
		<td>${car.mileage}</td>
		<td><b>总燃油花费(元)</b></td>
		<td><fmt:formatNumber value="${totalMoney}" pattern="#,###.##" /></td>
		<td><b>平均油耗(元/千米)</b></td>
		<td><fmt:formatNumber value="${totalMoney / car.mileage}" pattern="#,###.##" /></td>
	</tr>
</table>