<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>分类</title>
<link rel="stylesheet" href="${ctx}/res/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/res/css/common.css">
<style>
.layui-tab-content {
	padding: 0px;
}

.layui-table tbody tr:hover {
	background-color: transparent !important;
}

.layui-table {
	margin: 0px;
}

.add-extend-btn {
	margin: 10px;
}

.product-template-extendattr tbody tr td a {
	text-decoration: underline;
	color: blue;
}

img {
	cursor: pointer;
}

table input {
	max-width: 100px;
}
</style>
<script src="${ctx}/res/js/jquery-2.1.0.js"></script>
<script src="${ctx}/res/layui/layui.js"></script>
<script src="${ctx}/res/js/formValidation.js"></script>
<script>
	var layer;
	var element;
	var form;
	$(function() {
		layui.use('form', function() {
			form = layui.form;
			layer = layui.layer;
			//form.render(); //更新渲染全部组件
		});
	});
	//提交表单时,校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : true,
			"data" : {}
		};
		//表单初始化校验
		result.status = $("#form").formValidation("submit");
		result.data=$("#form").serialize();
		return result;
	}
</script>
</head>
<body>
	<div class="container">
		<div class="content-r">
			<div class="layui-field-box">
				<form action="" id="form">
					<c:choose>
						<c:when test="${commonCategory!=null}">
							<input type="hidden" name="id" value="${commonCategory.id}" />
							<div class="layui-form-item">
								<label class="layui-form-label">分类名称:*</label>
								<div class="layui-input-inline">
									<input type="text" name="name" class="layui-input"
										value="${commonCategory.name}"
										data-rule='{"required":true,"ajax":"${ctx}/commonCategory/checkCommonCategoryExist.action?id=${commonCategory.id}","errorMsg":"该分类名称已存在!"}'>
								</div>
								<div class="layui-form-mid shop-check-msg"></div>
							</div>
							<div class="layui-form-item layui-form-text">
								<label class="layui-form-label">描述</label>
								<div class="layui-input-block">
									<textarea name="description" placeholder="请输入内容"
										class="layui-textarea">${commonCategory.description}</textarea>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="layui-form-item">
								<label class="layui-form-label">分类名称:*</label>
								<div class="layui-input-inline">
									<input type="text" name="name" class="layui-input" value=""
										data-rule='{"required":true,"ajax":"${ctx}/commonCategory/checkCommonCategoryExist.action","errorMsg":"该分类名称已存在!"}'>
								</div>
								<div class="layui-form-mid shop-check-msg"></div>
							</div>
							<div class="layui-form-item layui-form-text">
								<label class="layui-form-label">描述</label>
								<div class="layui-input-block">
									<textarea name="description" placeholder="请输入内容"
										class="layui-textarea"></textarea>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</form>
			</div>
		</div>
	</div>
</body>
</html>