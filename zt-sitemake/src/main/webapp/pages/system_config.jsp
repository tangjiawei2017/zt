<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<style>
.layui-form-label {
	width: 150px;
}
</style>
<script>
	var layer;
	var form;
	$(function() {
		layui.use([ 'form', 'layer' ], function() {
			form = layui.form;
			layer = layui.layer;
			//监听提交
			form.on('submit(formDemo)', function(data) {
				updateSystemSetting();
				return false;
			});
			form.render(); //更新全部
		});
	});

	//更新系统设置
	function updateSystemSetting() {
		var data = {

		};
		$(".layui-form-item input[type='radio']:checked").each(function() {
			var name = $(this).attr("name");
			data[name] = $(this).val();
		});
		$.ajax({
			url : "${ctx}/systemConfig/updateSystemSetting.action",
			type : "post",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.result == "success") {
					layer.msg("设置成功!", {
						"time" : 1500
					});
					load("${ctx}/systemConfig/list.action");
				}
			}
		});
	}
</script>
<div class="sitemake-nav">当前位置 : 首页 &nbsp;&nbsp;>&nbsp;&nbsp;系统设置</div>
<form class="layui-form" action="">
	<div class="layui-form-item">
		<label class="layui-form-label">闭店模式:</label>
		<div class="layui-input-block">
			<input type="radio" name="sysSiteRunState" title="开启"
				<c:if test="${sysSiteRunState==1}">checked</c:if> value="1">
			<input type="radio" name="sysSiteRunState" title="关闭"
				<c:if test="${sysSiteRunState==0}">checked</c:if> value="0">
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
		</div>
	</div>
</form>

