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
					field : 'number',
					title : '订单号',
					width : 180,
				}, {
					field : 'cTime',
					title : '订购时间',
					width : 180,
				}, {
					field : 'status',
					title : '订单状态',
					width : 100,
					templet : '#statusTpl',
				}, {
					field : 'paytypecode',
					title : '支付类型',
					width : 120,
					templet : '#paytypeTpl',
				}, {
					field : 'payStatus',
					title : '支付状态',
					width : 120,
					templet : '#paystatusTpl',
				}, {
					field : 'total',
					title : '订单总计',
					width : 100,
					templet : '#totalTpl',
				} ] ],
				page : true,//是否显示分页
				skin : 'line',
				limits : [ 10, 20, 50 ],
				limit : 10,
				url : "${ctx}/customer/customerOrderListAjax.action",
				where : {
					payerId:$("input[name='payerId']").val(),
				},
				method : "post",
			});
		});
	});
</script>
<table id="order_list_ajax" lay-filter="order-table"></table>
<script type="text/html" id="orderOperator">
   <!-- 这里同样支持 laytpl 语法 -->
  {{#  if(d.status==5){ }}
    <a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>
  {{# }else if(d.status==1 || d.status==2 || d.status==3){ }}
    <a class="layui-btn layui-btn-mini" lay-event="edit">处理</a>
  {{# } else if(d.status==4){ }}
    <a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-mini" lay-event="del">删除</a>
  {{# }}}
</script>
<script type="text/html" id="totalTpl">
  ￥{{d.total}}
</script>
<script type="text/html" id="statusTpl">
  {{#  if(d.status === 1){ }}
                  待支付
  {{# } else if(d.status === 2){ }}
                  待发货
  {{# } else if(d.status === 3){ }}
                 已发货
  {{# } else if(d.status === 4){ }}
                 已取消
  {{# } else if(d.status ===5){ }}
                 已完成
  {{#  } }} 
</script>
<script type="text/html" id="paytypeTpl">
  {{#  if(d.paytypecode === 0){ }}
                  在线支付
  {{#  } }}
  {{#  if(d.paytypecode === 1){ }}
                  货到付款
  {{#  } }}
</script>
<script type="text/html" id="paystatusTpl">
  {{#  if(d.payStatus === 1){ }}
           待支付
  {{#  } }}
  {{#  if(d.payStatus === 2){ }}
                  已支付
  {{#  } }}
</script>



