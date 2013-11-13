<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="tab-pane fade in" id="setting" data-type="SETTING">
	<div class="form-horizontal">
		<div class="control-group">
			<label class="control-label">每页条目数</label>
			<div class="controls">
				<select name="pageSize">
					<option value="1" <c:if test="${pageInfoMap['pageSize'] == 1}">selected="selected"</c:if>>1</option>
					<option value="10" <c:if test="${pageInfoMap['pageSize'] == 10}">selected="selected"</c:if>>10</option>
					<option value="20" <c:if test="${pageInfoMap['pageSize'] == 20}">selected="selected"</c:if>>20</option>
					<option value="50" <c:if test="${pageInfoMap['pageSize'] == 50}">selected="selected"</c:if>>50</option>
					<option value="100" <c:if test="${pageInfoMap['pageSize'] == 100}">selected="selected"</c:if>>100</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序</label>
			<div class="controls">
				<select name="pageSort">
					<option value="asc" <c:if test="${pageInfoMap['pageSortDir'] == 'asc'}">selected="selected"</c:if>>日期 从先到后</option>
					<option value="desc" <c:if test="${pageInfoMap['pageSortDir'] == 'desc'}">selected="selected"</c:if>>日期 从后到先</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button type="button" id="settingSubmit" class="btn btn-primary">提交</button>
			</div>
		</div>
	</div>
</div>
