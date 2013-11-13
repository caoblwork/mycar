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
		<script type="text/javascript" src="js-app/contact/list.js"></script>
	</head>

	<body>
		<div id="wrap">
			<jsp:include page="../layout/header.jsp"></jsp:include>

			<div class="container">
				<div class="row">
					<jsp:include page="../layout/nav.jsp"></jsp:include>
					<div class="span10">
						<fieldset>
						<legend>联系人</legend>
						<div>
							<div class="pull-left">
								<button type="button" class="btn" data-toggle="modal" data-type="add"><i class="icon-plus"></i></button>
								<button type="button" data-type="switch" class="btn"><i class="icon-pencil"></i></button>
							</div>
							<div class="clearfix"></div>
						</div>
						<br/>
						<c:if test="${page.totalPages != 0}">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<td><b>姓名</b></td>
										<td><b>电话</b></td>
										<td><b>地址</b></td>
										<td><b>称谓</b></td>
										<td></td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.content}" var="c" varStatus="s">
									<tr>
										<td><a href="#" data-type="text" data-pk="${c.id}" data-url="contact/edit-name" class="editable" data-edit-type="editName">${c.name}</a></td>
										<td><a href="#" data-type="text" data-pk="${c.id}" data-url="contact/edit-phones" class="editable" data-edit-type="editPhoneNumbers">${c.phoneNumbers}</a></td>
										<td><a href="#" data-type="text" data-pk="${c.id}" data-url="contact/edit-address" class="editable" data-edit-type="editAddress">${c.address}</a></td>
										<td><a href="#" data-type="text" data-pk="${c.id}" data-url="contact/edit-title" class="editable" data-edit-type="editTitle">${c.title}</a></td>
										<td>
											<a href="#" data-id="${c.id}" data-type="delete">删除</a>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<c:if test="${page.totalPages > 1}">
							<div>
								<div class="pagination pagination-right">
									<ul>
										<!-- 第一页 -->
										<c:if test="${page.firstPage}">
											<li class="disabled"><span>&laquo;</span></li>
										</c:if>
										<c:if test="${!page.firstPage}">
											<li><a href="contact/list/${i}" data-page-number="1">&laquo;</a></li>
										</c:if>
										
										<c:forEach begin="1" end="${page.totalPages}" var="i">
										<c:if test="${pageNumber == i}">
											<li class="active"><span>${pageNumber}</span></li>
										</c:if>
										<c:if test="${pageNumber != i}">
											<li><a href="contact/list/${i}" data-page-number="${i}">${i}</a></li>
										</c:if>
										</c:forEach>
										
										<!-- 最后一页 -->
										<c:if test="${page.lastPage}">
											<li class="disabled"><span>&raquo;</span></li>
										</c:if>
										<c:if test="${!page.lastPage}">
											<li><a href="contact/list/${page.totalPages}" data-page-number="${page.totalPages}">&raquo;</a></li>
										</c:if>
									</ul>
								</div>
								<div class="clearfix"></div>
							</div>
							</c:if>
							</c:if>
							<c:if test="${fn:length(page.content) == 0}">
							<div class="alert alert-block alert-success">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								没有数据~
							</div>
							</c:if>
						</fieldset>
					</div>
				</div>
			</div>
		</div>
		
		<form id="addModal" class="modal hide fade modal-form form-horizontal" tabindex="-1">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h3 id="myModalLabel">添加</h3>
			</div>
			<div class="modal-body">
				<div class="control-group">
					<label class="control-label"><span class="text-warning">*</span>姓名</label>
					<div class="controls">
						<input type="text" placeholder="姓名" name="name" />
						<p class="help-block"></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">电话</label>
					<div class="controls">
						<input type="text" placeholder="电话" name="phoneNumbers" />
						<p class="help-block"></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">地址</label>
					<div class="controls">
						<input type="text" placeholder="地址" name="address" />
						<p class="help-block"></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">称谓</label>
					<div class="controls">
						<input type="text" placeholder="称谓" name="title" />
						<p class="help-block"></p>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" name="submit">提交</button>
				<button class="btn" data-dismiss="modal">关闭</button>
			</div>
		</form>

		<input type="hidden" name="defaultEditMode" value="${defaultEditMode}" />
		<jsp:include page="../layout/footer.jsp"></jsp:include>
	</body>
</html>