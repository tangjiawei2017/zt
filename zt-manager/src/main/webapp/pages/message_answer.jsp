<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<script src="${ctx}/js/jquery-2.1.0.js"></script>
<script src="${ctx}/layui/layui.js"></script>
<title>意见反馈</title>
<style>
.layui-form{
  margin-top:20px;
}
.layui-textarea {
	width: 300px;
	height: 100px;
	float: left;
}
</style>
<script>
	$(function() {
		var layer;
		layui.use([ 'element', 'layer' ], function() {
			var element = layui.element;
			layer = layui.layer;
			layer.config({
				extend : 'myskin/style.css', //加载新皮肤
				skin : 'layer-ext-myskin' //一旦设定，所有弹层风格都采用此主题。
			});
		});
	});
	//提交表单时,校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : false,
			"data" : {}
		};
		var answer=$("textarea[name=answer]").val();
		if(answer==""){
			layer.msg("回复内容不能为空!", {
				time : 1500,
				skin : 'success-class',
				anim : 5,
				isOutAnim : true,
			});
			result.status=false;
		}else{
			result.data.id=$("input[name='id']").val();
			result.data.answer=answer;
			result.status=true;
		}
		return result;
	}
</script>
</head>
<body>
	<form class="layui-form" action="">
	    <input type="hidden" name="id" value="${id}"/>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">回复内容:</label>
			<div class="layui-input-block">
				<textarea name="answer" class="layui-textarea"></textarea>
			</div>
		</div>
	</form>
</body>
</html>