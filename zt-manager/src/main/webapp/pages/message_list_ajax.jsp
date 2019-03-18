<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<script>
	$(function() {
		layui.use('table', function() {
			table = layui.table;
			//展示已知数据
			table.render({
						id : 'order_list',
						elem : '#order_list_ajax',
						cols : [ [ //标题栏
						{
							field : 'account',
							title : '留言人',
							width : 120,
						}, {
							field : 'content',
							title : '留言内容',
							width : 350,
						}, {
							field : 'ctime',
							title : '留言时间',
							width : 200,
						}, {
							field : 'phone',
							title : '手机',
							width : 150,
						}, {
							field : 'email',
							title : '邮箱',
							width : 200,
						}, {
							width : 150,
							title : '操作',
							toolbar : '#orderOperator'
						} ] ],
						page : true,//是否显示分页
						skin : 'line',
						limits : [ 10, 20, 50 ],
						limit : 10,
						url : "${ctx}/message/listAjax.action",
						where : {
							status : $(".condition.advise .content .tt").data(
									"advise"),
						},
						method : "post",
					});
			//监听工具条
			table.on('tool(order-table)', function(obj) {
				//注：tool是工具条事件名，order_list_ajax是table原始容器的属性 lay-filter="对应的值"
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值
				if (layEvent === 'detail') { //查看
					layer.open({
						type : 2,
						title : '查看详情',
						content : "${ctx}/message/detail.action?id="
								+ data["id"],
						area : [ '750px', '500px' ],
						btn : [ '关闭' ],
						yes : function(index, layero) {
							layer.close(index);
						}
					});
				} else if (layEvent === 'answer') {
					layer.open({
						type : 2,
						title : '留言处理',
						content : "${ctx}/message/dealwith.action?id="
								+ data["id"],
						area : [ '750px', '500px' ],
						btn : [ '确定', '关闭' ],
						yes : function(index, layero) {
							var childFrame = window.frames["layui-layer-iframe"
									+ index]; //得到加载层页面window对象
							var result = childFrame.checkData(); //掉用其全局函数,校验并获取数据
							if (result.status) {
								updateMessageAjax(result.data);
								layer.close(index); //关闭加载页面
							}
						},
						btn2 : function(index, layero) {
							layer.close(index);
						}
					});
				}else if(layEvent==='del'){
					$.ajax({
						url : "${ctx}/message/delMessage.action?id="+data["id"],
						type : "post",
						data : data,
						success : function(result) {
							if (result == "success") {
								table.reload('order_list', searchCondition());
								dialog("删除成功!");
							}
						}
					})
				}
			});
		});
	});
	function updateMessageAjax(data) {
		$.ajax({
			url : "${ctx}/message/updateMessage.action",
			type : "post",
			data : data,
			success : function(result) {
				if (result == "success") {
					table.reload('order_list', searchCondition());
					dialog("操作成功!");
				}
			}
		})
	}
</script>
<table id="order_list_ajax" lay-filter="order-table"></table>
<script type="text/html" id="orderOperator">
   <!-- 这里同样支持 laytpl 语法 -->
  {{#  if(d.status==0){ }}
    <a class="layui-btn layui-btn-mini" lay-event="answer">处理</a>
    <a class="layui-btn layui-btn-mini" lay-event="del">删除</a>
  {{# }else if(d.status==1){ }}
    <a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-mini" lay-event="del">删除</a>
  {{# }  }}
</script>