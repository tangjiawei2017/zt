<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>添加会员</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<script src="${ctx}/js/jquery-2.1.0.js"></script>
<script src="${ctx}/layui/layui.js"></script>
<script src="${ctx}/js/formValidation.js"></script>
<script>
	$(function() {
		layui.use('form', function() {
			var form = layui.form;
			form.render(); //更新渲染全部组件
		});
		//表单初始化校验
		$("#form").formValidation();
	});
	//提交表单时,校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : false,
			"data" : {}
		};
		//表单提交校验
		result.status = $("#form").formValidation("submit");
		result.data = $("#form").serialize();
		return result;
	}
</script>
<body style="height:320px;">
	<div class="container" style="margin-top:30px;">
		<form class="layui-form" action="" id="form">
			<div class="layui-form-item">
				<label class="layui-form-label">用户名:*</label>
				<div class="layui-input-inline">
					<input type="text" name="account" class="layui-input"
						data-rule='{"ajax":"${ctx}/customer/checkCustomerAccountExist.action","errorMsg":"该用户名已存在!"}'>
				</div>
				<div class="layui-form-mid shop-check-msg"></div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">客户姓名:</label>
				<div class="layui-input-inline">
					<input type="text" name="name" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码:*</label>
				<div class="layui-input-inline">
					<input type="password" name="password" class="layui-input"
						data-rule='{"required":true}'>
				</div>
				<div class="layui-form-mid shop-check-msg"></div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">性别:</label>
				<div class="layui-input-block">
					<input name="sex" value="0" title="保密" checked type="radio">
					<input name="sex" value="2" title="男" type="radio"> <input
						name="sex" value="1" title="女" type="radio">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">手机号码:*</label>
				<div class="layui-input-inline">
					<input type="text" name="cellphone" class="layui-input"
						data-rule='{"ajax":"${ctx}/customer/checkCustomerPhoneExist.action","errorMsg":"该手机号已存在!"}'>
				</div>
				<div class="layui-form-mid shop-check-msg"></div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">邮箱:</label>
				<div class="layui-input-inline">
					<input type="text" name="email" class="layui-input">
				</div>
			</div>
		</form>
	</div>
</body>
</html>