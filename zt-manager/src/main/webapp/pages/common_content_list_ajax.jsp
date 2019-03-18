<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<script>
	$(function() {
		layui.use('table', function() {
			table = layui.table;
			//展示已知数据
			table.render({
				id : 'common_content_list',
				elem : '#common_content_list_ajax',
				cols : [ [ //标题栏
				{
					checkbox : true,
				} //默认全选
				,{
					field : 'title',
					title : '页面标题',
					width : 120,
				},{
					field : 'categoryName',
					title : '所属分类',
					width : 200,
				},{
					field : 'ctime',
					title : '发布时间',
					width : 200,
				}, {
					width : 200,
					align : 'left',
					title : '操作',
					toolbar : '#productOperator'
				} ] ],
				page : true,//是否显示分页
				skin : 'line',
				limits : [ 10,20 , 50 ],
				limit : 10,
				url : "${ctx}/commonContent/commonContentListAjax.action",
				where:{
					categoryId:'-1',
				},
				method : "post",
			});
			//监听工具条
			table.on('tool(product-table)', function(obj) {
				//注：tool是工具条事件名，info_list_ajax是table原始容器的属性 lay-filter="对应的值"
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值
				if (layEvent === 'detail') { //查看
					//do somehing
				} else if (layEvent === 'del') { //删除
					//向服务端发送删除指令
					$.ajax({
						url : "${ctx}/commonContent/delCommonContent.action?id="+data["id"],
						type : "POST",
						success : function(msg) {
							table.reload('common_content_list');
							prompt(3,JSON.parse(msg).result);
						}
				    });
				} else if (layEvent === 'edit') { //编辑
					//计算新增商品弹窗宽度(减去左导航200)
					var width=$(window).width()-200;
					var height=$(window).height()-80;
				    var index = layer.open({
						title:'编辑页面内容',
					  	type: 2,
						content: "${ctx}/commonContent/preEditCommonContent.action?id="+data["id"],
						shade: 0,
						resize:false,
						move: false,
						offset: ['84px', '200px'],
						area: [width+"px", height+"px"],
						skin: 'defalut/layer.css',
						btn : [ '保存', '取消' ],
						btnAlign: 'c',
						yes : function(index, layero) {
							var childFrame = window.frames["layui-layer-iframe"
									+ index]; //得到加载层页面window对象
							var result = childFrame
									.checkData(); //掉用其全局函数,校验并获取数据
							if (result.status) {
								layer.close(index); //关闭加载页面
								$.ajax({
										url : "${ctx}/commonContent/updateCommonContent.action",
										data : result.data,
										type : "POST",
										dataType:"json",
										success : function(data) {
											table.reload('common_content_list');
											prompt(2,data.result);
										}
								});
							}
						},
					});		
				}
			});
		});
	});
</script>
<table id="common_content_list_ajax" lay-filter="product-table"></table>
<script type="text/html" id="productOperator">
  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>

