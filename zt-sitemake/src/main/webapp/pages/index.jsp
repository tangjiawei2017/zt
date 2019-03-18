<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>配置系统</title>
<link rel="stylesheet" href="${ctx}/res/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/res/css/global.css">
<script src="${ctx}/res/js/jquery-2.1.0.js"></script>
<script src="${ctx}/res/layui/layui.js"></script>
<script>
	$(function() {
		//初始化
		load("common_category.jsp");
		$(".fly-user-main .layui-nav .layui-nav-item").click(function() {
			$(this).addClass("layui-this").siblings().removeClass(
					"layui-this");
			var url = $(this).data("url");
			if (url != '') {
				load(url);
			}
		});
	});

	function load(url) {
		$.ajax({
			url : url,
			type : 'post',
			success : function(data) {
				$(".fly-panel-user").html(data);
			}
		});
	}
</script>
</head>
<body>
	<div class="layui-container  fly-user-main">
		<ul class="layui-nav layui-nav-tree layui-inline">
		    <li class="layui-nav-item layui-this" data-url="common_category.jsp"><a
				href="javaScript:void(0)"> <i class="layui-icon">&#xe612;</i>
					通用分类设置
			</a></li>
			<li class="layui-nav-item" data-url="custom_form_list.jsp"><a
				href="javaScript:void(0)"> <i class="layui-icon">&#xe612;</i>
					自定义表单
			</a></li>
			<li class="layui-nav-item"
				data-url="${ctx}/siteServiceConfig/list.action"><a
				href="javaScript:void(0)"> <i class="layui-icon">&#xe620;</i>
					服务配置
			</a></li>
			<li class="layui-nav-item" data-url="${ctx}/systemConfig/list.action"><a
				href="javaScript:void(0)"> <i class="layui-icon">&#xe620;</i>
					系统设置
			</a></li>
		</ul>
		<div class="fly-panel fly-panel-user"></div>
	</div>
</body>
</html>