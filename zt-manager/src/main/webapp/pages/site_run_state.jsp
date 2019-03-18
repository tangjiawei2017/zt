<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>网店运营状态</title>
<style>
.layui-form-label {
	width: 120px;
}
.layui-textarea{
  width:580px;
  height:200px;
}
</style>
<script>
	layui.use('form', function() {
		var form = layui.form;
		//监听提交
		form.on('submit(siteform)', function(data) {
			$.ajax({
				url:"${ctx}/site/saveSiteRunState.action",
				type:"post",
				dataType:"json",
				data:{state:$("input[name='state']:checked").val(),content:$("textarea[name='content']:visible").val()},
				success:function(data){
					if(data.result="success"){
						layer.msg("保存成功!");
					}else{
						layer.msg("保存失败!");
					}
				}
			});
			return false;
		});
		//radio事件监听
		form.on("radio(radio)",function(data){
			data.value==0?$(".siteContent").show():$(".siteContent").hide();
		});
		form.render(); //更新渲染全部组件
	});
</script>
<body>
	<div class="container">
		<div class="main-panel-head">
			<div class="tilte">网店运营状态设置</div>
		</div>
		<div class="layui-field-box">
			<form class="layui-form" action="" id="form">
				<div class="layui-form-item">
					<label class="layui-form-label">网店运营状态:</label>
					<div class="layui-input-block">
						<input type="radio" name="state" value="1" title="正常运营" lay-filter="radio"
							<c:if test="${siteConfig.value!=0}">checked</c:if>> <input
							type="radio" name="state" value="0" title="暂时闭店" lay-filter="radio"
							<c:if test="${siteConfig.value==0}">checked</c:if>>
					</div>
				</div>
				<div class="layui-form-item siteContent" <c:if test="${siteConfig.value==1}">style="display:none"</c:if>>
					<label class="layui-form-label">闭店公告:</label>
					<div class="layui-input-inline">
						<textarea name="content" class="layui-textarea"><c:if
								test="${siteConfig.jsonvalue==null}">您好，本店暂停营业，请您稍后再来...</c:if><c:if
								test="${siteConfig.jsonvalue!=null}">${siteConfig.jsonvalue}</c:if></textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="siteform">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>