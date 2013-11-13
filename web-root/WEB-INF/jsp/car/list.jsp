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
		<script type="text/javascript" src="js-app/car/list.js"></script>
	</head>

	<body>
		<div id="wrap">
			<jsp:include page="../layout/header.jsp"></jsp:include>

			<div class="container">
				<div class="row">
					<jsp:include page="../layout/nav.jsp"></jsp:include>
					<div class="span10">
						<fieldset>
						<legend>车辆</legend>
						<div>
							<div class="pull-left">
								<button type="button" class="btn" data-toggle="modal" data-type="add"><i class="icon-plus"></i></button>
								<button type="button" data-type="switch" class="btn"><i class="icon-pencil"></i></button>
							</div>
							<div class="clearfix"></div>
						</div>
						<br/>
						<c:if test="${fn:length(carList) != 0}">
						<div>
							<table class="table table-striped table-hover" id="vehicleTbl">
								<thead>
									<tr>
										<td><b>名称</b></td>
										<td><b>类型</b> <i class="icon-question-sign" rel="tooltip" title="M:载客 N:载货 O:挂车 X:其他"></i></td>
										<td><b>品牌</b></td>
										<td><b>型号</b></td>
										<td><b>牌照号</b></td>
										<td><b>钢架号</b></td>
										<td><b>发动机号</b></td>
										<td><b>里程(千米)</b></td>
										<c:if test="${fn:length(carList) != 0}"><td></td></c:if>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${carList}" var="c">
									<tr>
										<td><a href="#" data-type="text" data-pk="${c.id}" data-url="car/edit-name" class="editable" data-edit-type="editName">${c.name}</a></td>
										<td><a href="#" data-type="select" data-pk="${c.id}" data-url="car/edit-type" class="editable" data-edit-type="editType">${c.type}</a></td>
										<td><a href="#" data-type="text" data-pk="${c.id}" data-url="car/edit-brand" class="editable" data-edit-type="editBrand">${c.brand}</a></td>
										<td><a href="#" data-type="text" data-pk="${c.id}" data-url="car/edit-model-type" class="editable" data-edit-type="editModelType">${c.modelType}</a></td>
										<td><a href="#" data-type="text" data-pk="${c.id}" data-url="car/edit-license" class="editable" data-edit-type="editLicense">${c.license}</a></td>
										<td><a href="#" data-type="text" data-pk="${c.id}" data-url="car/edit-number-of-frame" class="editable" data-edit-type="editNumberOfFrame">${c.numberOfFrame}</a></td>
										<td><a href="#" data-type="text" data-pk="${c.id}" data-url="car/edit-number-of-engine" class="editable" data-edit-type="editNumberOfEngine">${c.numberOfEngine}</a></td>
										<td><a href="#" data-type="text" data-pk="${c.id}" data-url="car/edit-mileage" class="editable" data-edit-type="editMileage">${c.mileage}</a></td>
										<td>
											<a href="car/delete?carId=${c.id}">删除</a>
											<a target="_blank" href="car/qrcode?carId=${c.id}">二维码</a>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						</c:if>
						<c:if test="${fn:length(carList) == 0}">
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
					<label class="control-label"><span class="text-warning">*</span>名称</label>
					<div class="controls">
						<input type="text" placeholder="名称" name="name" />
						<p class="help-block"></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><span class="text-warning">*</span>类型</label>
					<div class="controls">
						<select name="type">
							<option value="M">载客</option>
							<option value="N">载货</option>
							<option value="O">挂车</option>
							<option value="X">其他</option>
						</select>
						<p class="help-block"></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">品牌</label>
					<div class="controls">
						<input type="text" placeholder="品牌" name="brand" />
						<p class="help-block"></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">型号</label>
					<div class="controls">
						<input type="text" placeholder="型号" name="modelType" />
						<p class="help-block"></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">牌照号</label>
					<div class="controls">
						<input type="text" placeholder="牌照号" name="license" />
						<p class="help-block"></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">钢架号</label>
					<div class="controls">
						<input type="text" placeholder="钢架号" name="numberOfFrame" />
						<p class="help-block"></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">发动机号</label>
					<div class="controls">
						<input type="text" placeholder="发动机号" name="numberOfEngine" />
						<p class="help-block"></p>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" name="submit">提交</button>
				<button class="btn" data-dismiss="modal">关闭</button>
			</div>
		</form>

		<input type="hidden" name="error" value="${error}" />
		<input type="hidden" name="defaultEditMode" value="${defaultEditMode}" />
		<jsp:include page="../layout/footer.jsp"></jsp:include>
	</body>
</html>