<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改密码</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<!-- 定义body 高度,只有body高度 > layer弹窗内容高度才会出滚动条 -->
<style>
body{
  height:250px;
}
</style>
<script type="text/javascript" src="${ctx}/js/jquery-2.1.0.js"></script>
<script src="${ctx}/layui/layui.js"></script>
<script>
	layui.config({
			version : false
	});
	layui.use('form', function() {
		var form = layui.form;
		//form.render(); //更新渲染全部组件
	});
	//提交表单时,校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : false,
			"data" : {}
		};
		//表单提交校验
		result.status = $("#form").formValidation("submit");
		var password = $("input[name='pwd']").val();
		var password2 = $("input[name='newPwdAgain']").val();
		if (password != password2) {
			result.status = false;
		}
		result.data = $("#form").serialize();
		return result;
	}
</script>
<script src="${ctx}/js/formValidation.js"></script>
<script>
	$(function() {
		//表单初始化校验
		$("#form").formValidation();
	});
</script>
</head>
<body>
	<div class="container">
		<div class="content-r">
			<div class="layui-field-box">
				<form class="layui-form" action="" id="form" method="post">
					<div class="layui-form-item">
						<label class="layui-form-label">旧密码:*</label>
						<div class="layui-input-inline">
							<input type="password" name="password" class="layui-input"
								data-rule='{"ajax":"${ctx}/admin/checkAccountPwd.action","errorMsg":"旧密码不正确!"}'
								value="">
						</div>
						<div class="layui-form-mid shop-check-msg"></div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">新密码:*</label>
						<div class="layui-input-inline">
							<input type="password" name="pwd" class="layui-input"
								data-rule='{"required":true}' value="">
						</div>
						<div class="layui-form-mid shop-check-msg"></div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">确认新密码:*</label>
						<div class="layui-input-inline">
							<input type="password" name="newPwdAgain" class="layui-input"
								data-rule='{"required":true}' value="">
						</div>
						<div class="layui-form-mid shop-check-msg"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>