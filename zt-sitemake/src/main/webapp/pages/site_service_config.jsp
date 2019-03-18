
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<script>
	var layer;
	var form;
	$(function() {
		layui.use([ 'form', 'layer' ], function() {
			form = layui.form;
			layer = layui.layer;
			//监听提交
			form.on('submit(formDemo)', function(data) {
				updateSiteService();
				return false;
			});
			$(".shop-reset").click(function() {
				$("input[type='checkbox']").each(function() {
					$(this).removeAttr("checked");
					form.render();
				});
				updateSiteService();
			});
			form.render(); //更新全部
		});
	});

	//更新服务
	function updateSiteService() {
		var data = {

		};
		$("input[type='checkbox']").each(function() {

			var name = $(this).attr("name");
			data[name] = $(this).is(":checked") ? 1 : 0;
		});
		$.ajax({
			url : "${ctx}/siteServiceConfig/updateSiteService.action",
			type : "post",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.result == "success") {
					layer.msg("设置成功!", {
						"time" : 1500
					});
					load("${ctx}/siteServiceConfig/list.action");
				}
			}
		});
	}
</script>
<div class="sitemake-nav">当前位置 : 首页
	&nbsp;&nbsp;>&nbsp;&nbsp;网站服务配置</div>
<form class="layui-form" action="">
	<div class="layui-form-item">
		<div class="layui-input-block">
			<input type="checkbox" name="productService" title="启用商品服务" value="1"
				lay-skin="primary"
				<c:if test="${productService=='1'}">checked</c:if>>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<input type="checkbox" name="customerService" title="启用会员服务"
				lay-skin="primary" value="1"
				<c:if test="${customerService=='1'}">checked</c:if>>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<input type="checkbox" name="comContentService" title="启用内容服务"
				lay-skin="primary" value="1"
				<c:if test="${comContentService=='1'}">checked</c:if>>

		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<input type="checkbox" name="newsService" title="启用资讯服务"
				lay-skin="primary" value="1"
				<c:if test="${newsService=='1'}">checked</c:if>>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<input type="checkbox" name="messageService" title="启用留言服务"
				lay-skin="primary" value="1"
				<c:if test="${messageService=='1'}">checked</c:if>>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<input type="checkbox" name="ggService" title="启用广告服务"
				lay-skin="primary" value="1"
				<c:if test="${ggService=='1'}">checked</c:if>>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<input type="checkbox" name="customFormService" title="启用自定义表单服务"
				lay-skin="primary" value="1"
				<c:if test="${customFormService=='1'}">checked</c:if>>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
			<button type="button" class="layui-btn layui-btn-primary shop-reset">重置</button>
		</div>
	</div>
</form>

