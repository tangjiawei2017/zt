<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>属性模板扩展属性项的选项值</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<script src="${ctx}/js/jquery-2.1.0.js"></script>
<script src="${ctx}/layui/layui.js"></script>
<script src="${ctx}/js/formValidation.js"></script>
<style>
.layui-table tbody tr:hover {
	background-color: transparent !important;
}

a {
	color: blue;
}
</style>
<script>
	var layer;
	$(function() {
		layui.use('form', function() {
			layer = layui.layer;
		});
		$(".addSpec-btn").click(
			function() {
				$(".layui-table tbody").append('<tr><td><div class="layui-input-inline"><input type="text" name="extendAttrValue" class="layui-input"></div></td><td><a href="javascript:void(0)" onclick="delSpecValueTR(this)">删除</a></td></tr>');
			});
	});
	//提交表单时,校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : true,
			"data" : []
		};
		$(".layui-table tbody tr input[name='extendAttrValue']").each(function() {
			if ($.trim($(this).val()) === '') {
				layer.msg('属性值不能为空!', {
					time : 1500
				});
				result.status = false;
			}else{
				result.data.push($(this).val());
			}
		});
		return result;
	}
	
	//删除规格属性
	function delSpecValueTR(obj) {
			$(obj).parent().parent().remove();
	}
</script>
</head>
<body>
	<div class="container">
		<div class="content-r">
			<div class="layui-field-box">
				<button
					class="layui-btn layui-btn-primary shop-operator-btn layui-btn-small addSpec-btn">新增</button>
				<form class="layui-form" action="" id="form">
					<div class="layui-form-item">
						<table class="layui-table">
							<colgroup>
								<col width="50%">
								<col width="50%">
							</colgroup>
							<thead>
								<tr>
									<th>属性名称</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${extendAttrValueArray}" var="attrValue">
									<tr>
										<td><div class="layui-input-inline">
												<input type="text" name="extendAttrValue" class="layui-input"
													value="${attrValue}">
											</div></td>
										<td><a href="javascript:void(0)"
											onclick="delSpecValueTR(this)">删除</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>