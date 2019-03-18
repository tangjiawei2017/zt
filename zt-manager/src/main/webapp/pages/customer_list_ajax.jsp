<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<script>
	$(function() {
		layui.use('table', function() {
			table = layui.table;
			//展示已知数据
			table.render({
				id : 'customer_list',
				elem : '#customer_list_ajax',
				cols : [ [ //标题栏
				{
					checkbox : true,
				} //默认全选
				, {
					field : 'account',
					title : '用户名',
					width : 200,
				}, {
					field : 'name',
					title : '姓名',
					width : 100,
				}, {
					field : 'regtime',
					title : '注册日期',
					width : 250,
				}, {
					field : 'source',
					title : '来源',
					width : 100,
					templet: '#sourceTpl',
				},{
					field : 'status',
					title : '会员状态',
					width : 100,
					templet: '#statusTpl',
				},{
					width : 200,
					align : 'center',
					title : '操作',
					toolbar : '#customerOperator'
				} ] ],
				page : true,//是否显示分页
				skin : 'line',
				limits : [ 10,20 , 50 ],
				limit : 10,
				url : "${ctx}/customer/customerListAjax.action",
				where : {
					type:$("select[name='findType']").val(),
					search: $("input[name='search']").val(), //搜索条件
				},
				method : "post",
			});
			//监听工具条
			table.on('tool(customer-table)', function(obj) {
				//注：tool是工具条事件名，customer_list_ajax是table原始容器的属性 lay-filter="对应的值"
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值
				if (layEvent === 'detail') { //查看
					//do somehing
				} else if (layEvent === 'del') { //删除
					layer.confirm('真的删除该会员么?',{'title':'提示'}, function(index) {
						layer.close(index);
						//向服务端发送删除指令
						$.ajax({
							url : "${ctx}/customer/delCustomer.action?id="+data["id"],
							type : "POST",
							success : function(msg) {
								table.reload('customer_list',searchCondition());
								prompt(3,JSON.parse(msg).result);
							}
						});
					});
				} else if (layEvent === 'edit') { //编辑
					layer.open({
						type : 2,
						title : '客户信息',
						content : "${ctx}/customer/customerDetail.action?id="+data["id"],
						area : [ '850px', '540px' ],
						btn :['关闭'],
						yes :function(index,layero){
							layer.close(index);
						}
					});					
				}
			});
		});
	});
	//商品上,下架 type 0下架, 1上架
	function updateCustomerStatus(id,obj){
		$.ajax({
			url:"${ctx}/customer/updateCustomerStatus.action",
			data:{"id":id,"status":$(obj).data("status")},
			type:"post",
			success:function(msg){
				if(msg=="success"){
					if($(obj).data("status")==1){
						$("#img_"+id).attr("src","${ctx}/images/saleon.png");
						$(obj).data("status",0);
					}else if($(obj).data("status")==0){
						$("#img_"+id).attr("src","${ctx}/images/saleoff.png");
						$(obj).data("status",1);
					}
				}
			}
		});
	}
</script>
<table id="customer_list_ajax" lay-filter="customer-table"></table>
<script type="text/html" id="customerOperator">
  <a class="layui-btn layui-btn-mini" lay-event="edit">查看</a>
  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
<script type="text/html" id="statusTpl">
    {{#  if(d.status ==1 ){ }}
       <img src="${ctx}/images/saleon.png" onclick="updateCustomerStatus({{d.id}},this)" id="img_{{d.id}}" data-status="0"/>
    {{#  } else { }}
      <img src="${ctx}/images/saleoff.png" onclick="updateCustomerStatus({{d.id}},this)" id="img_{{d.id}}" data-status="1"/>
    {{#  } }}
</script>
<script type="text/html" id="sourceTpl">
  {{#  if(d.source === 0){ }}
                  前台注册
  {{#  } }}
  {{#  if(d.source === 1){ }}
                   后台添加
  {{#  } }}
  {{#  if(d.source === 2){ }}
                   留言
  {{#  } }}
</script>

