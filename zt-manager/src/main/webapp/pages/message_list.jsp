<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>留言列表</title>
<script>
	var table;
	var layer;
	$(function() {
		layui.use('laydate', function() {
			layui.use([ 'element', 'layer' ], function() {
				layer = layui.layer;
			});
		});
		$(".condition .content a").bind({
			"mouseover" : function() {
				$(this).parent().css("background-color", "#00b38a");
				$(this).css("color", "#fff").css("text-decoration", "none");
			},
			"mouseout" : function() {
				if (!$(this).hasClass("tt")) {
					$(this).parent().css("background-color", "#e4e4e4");
					$(this).css("color", "#6f6ffd");
				}
			}
		});
		$(".condition .content a").bind("click", function() {
			$(this).parent().siblings().children("a").each(function() {
				if ($(this).hasClass("tt")) {
					$(this).parent().css("background-color", "#e4e4e4");
					$(this).css("color", "#6f6ffd");
					$(this).removeClass("tt");
				}
			});
			$(this).addClass("tt");
			$(this).parent().css("background-color", "#00b38a");
			$(this).css("color", "#fff").css("text-decoration", "none");
			table.reload('order_list', searchCondition());
		});
		$(".content .tt").parent().css("background-color", "#00b38a");
		$(".condition .tt").css("color", "#fff");
	});
	//获取查询条件
	function searchCondition() {
		var params = {
			url : "${ctx}/message/listAjax.action",
		}
		var where = {};
		where.status = $(".condition.advise .content .tt").data("advise");
		params.where = where;
		return params;
	}
	function dialog(msg) {
		layer.msg(msg, {
			time : 1500,
			skin : 'success-class',
			anim : 5,
			isOutAnim : true,
		});
	}
</script>
</head>
<body>
	<div class="container">
		<div class="main-panel-head">
			<div class="tilte">在线留言</div>
		</div>
		<div class="main-panel-filter">
			<div class="condition advise" style="border-bottom: 0">
				<span class="title">状态:</span> <span class="content"><a
					href="#" data-advise="2" class="tt">所有</a></span><span class="content"><a
					href="#" data-advise="0">未处理</a></span> <span class="content"><a
					href="#" data-advise="1">已处理</a></span>
			</div>
		</div>
		<div class="order-content-ajax">
			<jsp:include page="message_list_ajax.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>