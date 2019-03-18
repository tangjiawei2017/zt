<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>批量修改展示分类</title>
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
	});
	//校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : true,
			"data" : {}
		};
		//展示分类和管理分类是否选择校验
		if(typeof $("input[name='category']:checked").val() =="undefined"){
			layer.msg("商品至少要选择一个展示分类!", {
				time : 2000,
				skin : 'error-class',
				anim :  6,
				isOutAnim : true,
			});
			result.status=false;
		}
		result.data = $("#form").serialize();
		return result;
	}
</script>
</head>
<body>
	<div class="container">
		<div class="layui-field-box">
			<form class="layui-form" action="" id="form">
				<div class="layui-form-item">
					<label class="layui-form-label">展示分类:*</label>
					<div class="layui-input-block">
						<c:forEach items="${productCategoryList}" var="productCategory"
							varStatus="status">
							<input name="category" lay-skin="primary"
								title="${productCategory.name}" value="${productCategory.name}"
								type="checkbox">
						</c:forEach>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>