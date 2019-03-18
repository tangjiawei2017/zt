<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<script>
	$(function() {
        var cols=[];
        var colsArray=[];
		$.ajax({
			url : "${ctx}/customForm/customFormItemList.action?id=${param.id}",
			type : "post",
			dataType : "json",
			success : function(result) {
				if (result.code == 200 && result.data != null) {
					var data = result.data;
					cols.push({
						checkbox : true,
					});
					for(var i=0;i<data.length;i++){
						cols.push({
							field : data[i].code,
							title : data[i].itemName,
							width : 150,
						});
					}
					cols.push({
						width : 200,
						align : 'left',
						title : '操作',
						toolbar : '#orderOperator'
					});
					colsArray.push(cols);
					layui.use('table', function() {
						table = layui.table;
						//展示已知数据
						table.render({
							id : 'order_list',
							elem : '#order_list_ajax',
							cols : colsArray,
							page : true,//是否显示分页
							skin : 'line',
							limits : [ 10, 20, 50 ],
							limit : 10,
							url : "${ctx}/customForm/customFormContentListAjax.action",
							where : {
								formId : "${param.id}",
								status : $(".condition.status .content .tt")
										.data("status"),
								ctime : $(".condition.ctime .content .tt").data(
										"ctime")
							},
							method : "post",
						});
					});
					//监听工具条
					table.on('tool(order-table)', function(obj) {
						//注：tool是工具条事件名，order_list_ajax是table原始容器的属性 lay-filter="对应的值"
						var data = obj.data; //获得当前行数据
						var layEvent = obj.event; //获得 lay-event 对应的值
						 if (layEvent === 'edit') { //编辑
							layer.open({
								type : 2,
								title : '查看与处理',
								content : "${ctx}/customForm/customFormContentDetail.action?formId=${param.id}&contentId="+data["id"],
								area : [ '700px', '480px' ],
								btn : ["确认","关闭"],
								yes : function(index, layero) {
									var childFrame = window.frames["layui-layer-iframe"
											+ index]; //得到加载层页面window对象
									var result = childFrame.checkData(); //掉用其全局函数,校验并获取数据
									if(result.status){
										layer.closeAll();
										$.ajax({
											url:"${ctx}/customForm/updatecustomFormContent.action",
										    type:"post",
										    data:{
										    	id:data["id"],
										    	result:result.data.result
										    },
										    success:function(result){
										    	if(result=="success"){
										    		table.reload('order_list', searchCondition());
										    		layer.msg("处理成功!", {
														time : 1500,
														skin : 'success-class',
														anim : 5,
														isOutAnim : true,
													});
										    	}else{
										    		layer.msg("处理失败!", {
														time : 1500,
														skin : 'error-class',
														anim : 5,
														isOutAnim : true,
													});
										    	}
										    }
										});
									}
								},
								btn2 : function(index, layero) {
									layer.close(index);
								}
							});
					     }else  if (layEvent === 'detail') { //编辑
								layer.open({
									type : 2,
									title : '查看',
									content : "${ctx}/customForm/customFormContentDetail.action?formId=${param.id}&contentId="+data["id"],
									area : [ '700px', '480px' ],
									btn : ["关闭"],
									yes : function(index,layero){
										layer.close(index);
									}
								});
					     }
					});
				}
			}
		});
	});
</script>
<table id="order_list_ajax" lay-filter="order-table"></table>
<script type="text/html" id="orderOperator">
   <!-- 这里同样支持 laytpl 语法 -->
  {{#  if(d.status==0){ }}
    <a class="layui-btn layui-btn-mini" lay-event="edit">查看&处理</a>
  {{# }else if(d.status==1){ }}
    <a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>
  {{# }}}
</script>



