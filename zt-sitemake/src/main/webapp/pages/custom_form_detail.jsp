
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>配置系统</title>
<link rel="stylesheet" href="${ctx}/res/layui/css/layui.css">
<style>
.shop-form-item {
	display: block;
	height: 40px;
	line-height: 40px;
}
</style>
<script src="${ctx}/res/js/jquery-2.1.0.js"></script>
<script src="${ctx}/res/layui/layui.js"></script>
<script>
	var layer;
	$(function() {
		layui.use('form', function() {
			layer = layui.layer;
		});
	});
	function checkSubmit() {
		//必填项进行判断
		$(".shop-form-item .isrequired").each(function() {
			// if($(this).next())
		});
	}
</script>
</head>
<body>
	<div class="container">
		<form id="form" action="/customForm/addCustomFormContent.action"
			method="post">
			<input type="hidden" name="formId" value="${customForm.id}" />
			<c:forEach items="${customForm.itemList}" var="item"
				varStatus="status">
				<div class="shop-form-item">
					<label>${item.name}</label> <span class="isrequired"><c:if
							test="${item.isRequried}">*</c:if></span> :
					<c:choose>
						<c:when test="${item.type==0}">
							<input type="text" name="ext${status.index+1}"
								data-dataType="${item.dataType}"
								placeholder="${item.itemPrompt}">
						</c:when>
						<c:when test="${item.type==1}">
							<textarea name="ext${status.index+1}"
								data-dataType="${item.dataType}"
								placeholder="${item.itemPrompt}"></textarea>
						</c:when>
						<c:when test="${item.type==2 && item.dataType==5}">
							<c:set var="dataValue" value="${ fn:split(item.dataValue, ';') }" />
							<c:forEach items="${dataValue}" var="value" varStatus="shopradio">
								<input type="radio" name="ext${status.index+1}"
									<c:if test="${shopradio.first}">checked</c:if>>${value}
						    </c:forEach>
						</c:when>
						<c:when test="${item.type==3 && item.dataType==5}">
							<c:set var="dataValue" value="${ fn:split(item.dataValue, ';') }" />
							<c:forEach items="${dataValue}" var="value" varStatus="shopradio">
								<input type="checkbox" name="ext${status.index+1}">${value}
						    </c:forEach>
						</c:when>
						<c:when test="${item.type==4 && item.dataType==5}">
							<c:set var="dataValue" value="${ fn:split(item.dataValue, ';') }" />
							<select type="checkbox" name="ext${status.index+1}">
								<c:forEach items="${dataValue}" var="value">
									<option value="${value}">${value}</option>
								</c:forEach>
							</select>
						</c:when>
					</c:choose>
				</div>
			</c:forEach>
			<input type="submit" value="提交" onclick="return checkSubmit();" />
		</form>
	</div>
</body>
</html>