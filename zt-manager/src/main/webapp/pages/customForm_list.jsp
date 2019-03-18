<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>自定义表单</title>
<style>
#startTime,#endTime {
	width: 140px;
	height: 25px;
	border: 1px solid #c1c1c1;
}
.mleft {
	margin-left: 20px;
}

.layui-btn-small {
	height: 25px;
	line-height: 25px;
}
</style>
<script>
	var table;
	var laydate;
	var layer;
	$(function() {
		layui.use('laydate', function() {
			laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#startTime',
				type : 'datetime',
				position : 'fixed'
			});
			laydate.render({
				elem : '#endTime',
				type : 'datetime',
				position : 'abolute',
			});
			layui.use([ 'element', 'layer' ], function() {
				layer = layui.layer;
			});
		});
		$(".condition .content a").bind({
			"mouseover" : function() {
				$(this).parent().css("background-color", "#00b38a");
				$(this).css("color", "#fff").css("text-decoration", "none");
				$(this).children().css("color", "#fff");
			},
			"mouseout" : function() {
				if (!$(this).hasClass("tt")) {
					$(this).parent().css("background-color", "#e4e4e4");
					$(this).css("color", "#6f6ffd");
					$(this).children().css("color", "#6f6ffd");
				}
			}
		});
		$(".condition .content a").bind("click", function() {
			$(this).parent().siblings().children("a").each(function() {
				if ($(this).hasClass("tt")) {
					$(this).parent().css("background-color", "#e4e4e4");
					$(this).css("color", "#6f6ffd");
					$(this).children().css("color", "#6f6ffd");
					$(this).removeClass("tt");
				}
			});
			$(this).addClass("tt");
			$(this).parent().css("background-color", "#00b38a");
			$(this).css("color", "#fff").css("text-decoration", "none");
			$(this).children().css("color", "#fff");
			if ($(this).parents(".condition").is(".ctime")) {
				$("#startTime").val('');
				$("#endTime").val('');
			}
			table.reload('order_list', searchCondition());
		});
		$(".content .tt").parent().css("background-color", "#00b38a");
		$(".condition .content a span").css("color", "#6f6ffd");
		$(".condition .tt").css("color", "#fff");
		$(".condition .tt").children().css("color", "#fff");
		$("button[name='search-btn']").click(function() {
			table.reload('order_list', searchCondition());
		});
		$(".filter-btn").click(function() {
			var startTime = $.trim($("#startTime").val());
			var endTime = $.trim($("#endTime").val());
			if (startTime == '' || endTime == '') {
				layer.msg('请先选择日期', {
					time : 1500,
					skin : 'error-class',
					anim : 5,
					isOutAnim : true,
				});
			} else {
				var $this = $(".condition.ctime .content .tt");
				$this.parent().css("background-color", "#e4e4e4");
				$this.css("color", "#6f6ffd");
				$this.removeClass("tt");
				table.reload('order_list', searchCondition());
			}
		});
	});
	//获取查询条件
	function searchCondition() {
		var params = {
			url : "${ctx}/customForm/customFormContentListAjax.action",
		}
		var where = {};
		where.formId = "${customForm.id}";
		where.status =  $(".condition.status .content .tt").data("status");
		if ($(".condition.ctime .content .tt").length > 0) {
			where.ctime = $(".condition.ctime .content .tt").data("ctime");
		} else {
			var startTime = $.trim($("#startTime").val());
			var endTime = $.trim($("#endTime").val());
			where.ctime = startTime + "~" + endTime;
		}
		params.where = where;
		return params;
	}
</script>
</head>
<body>
	<div class="container">
		<div class="main-panel-head">
			<div class="tilte">${customForm.name}</div>
		</div>
		<div class="main-panel-filter">
			<div class="condition status">
				<span class="title">处理状态:</span> <span class="content"><a
					href="javaScript:void(0)" class="tt" data-status="2">全部</a></span> <span class="content"><a
					href="javaScript:void(0)" data-status="0">未处理</a></span> <span class="content"><a
					href="javaScript:void(0)" data-status="1">已处理</a></span>
			</div>
			<div class="condition ctime" style="border-bottom: 0">
				<span class="title">提交时间:</span> <span class="content"><a
					href="javaScript:void(0)" class="tt" data-ctime="0">全部</a></span> <span class="content"><a
					href="javaScript:void(0)" data-ctime="1">今天</a></span><span class="content"><a
					href="javaScript:void(0)" data-ctime="2">昨天</a></span> <span class="content"><a
					href="javaScript:void(0)" data-ctime="3">本周</a></span><span class="content"><a
					href="javaScript:void(0)" data-ctime="4">本月</a></span> <span class="content"><a
					href="javaScript:void(0)" data-ctime="5">本季度</a></span> <span class="mleft"> <input
					type="text" id="startTime">
				</span>-<span> <input type="text" id="endTime">
				</span>
				<button
					class="layui-btn layui-btn-primary layui-btn-small filter-btn shop-operator-btn">筛选</button>
			</div>
		</div>
		<div class="order-content-ajax">
			<jsp:include page="customForm_list_ajax.jsp?id=${customForm.id}"></jsp:include>
		</div>
	</div>
</body>
</html>