<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<script>
	var layer;
	$(function() {
		layui.use([ 'table', 'layer' ], function() {
			table = layui.table;
			layer = layui.layer;
			//展示已知数据
			table.render({
				id : 'custom_from_list',
				elem : '#custom_from_list',
				cols : [ [ {
					field : 'name',
					title : '分类名称',
					width : 200,
				},{
					field : 'ctime',
					title : '创建时间',
					width : 200,
				},{
					align : 'center',
					title : '操作',
					toolbar : '#customFormOperator'
				} ] ],
				page : false,//是否显示分页
				skin : 'line',
				limit : 10,
				url : "${ctx}/commonCategory/listAjax.action",
				method : "post",
			});
			//监听工具条
			table.on('tool(custom_from_filter)', function(obj) {
				//注：tool是工具条事件名，customer_list_ajax是table原始容器的属性 lay-filter="对应的值"
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值
			     if (layEvent === 'del') { //删除
					layer.confirm('真的删除该该分类么?', {
						'title' : '提示'
					}, function(index) {
						layer.close(index);
						//向服务端发送删除指令
						$.ajax({
							url : "${ctx}/commonCategory/delCommonCategory.action?id="
									+ data["id"],
							type : "POST",
							dataType:"json",
							success : function(data) {
							    if(data.success){
							    	layer.msg("删除成功!",{
										time : 1500
									});
							    }else if(data.success==false && data.code==1){
							    	layer.msg("该分类下有内容,不可删除!",{
										time : 1500
									});
							    }else{
							    	layer.msg("系统错误!",{
										time : 1500
									});
							    }
							    load("common_category.jsp");
							}
						});
					});
				} else if (layEvent === 'edit') { //编辑
					//计算新增商品弹窗宽度(减去左导航200)
					var width=($(window).width()-800)/2+20;
					var height=($(window).height()-540)/2;
					layer.open({
						type : 2,
						title : '编辑分类',
						content :"${ctx}/commonCategory/preEditcommonCategory.action?id="+data.id,
						offset: [height+'px', width+'px'],
						area : [ '650px', '450px' ],
						btn : [ '保存', '取消' ],
						btnAlign : 'c',
						yes : function(index, layero) {
							var childFrame = window.frames["layui-layer-iframe" + index];    //得到加载层页面window对象
							var result=childFrame.checkData(); //掉用其全局函数,校验并获取数据
							console.log("data====>"+JSON.stringify(result.data));
							if(result.status){
								layer.close(index);   //关闭加载页面
							 	$.ajax({
									url:"${ctx}/commonCategory/editcommonCategory.action",
									data:result.data,
									type:"POST",
									success:function(data){
										if (data== "success") {
											layer.close(index);
											load("common_category.jsp");
											layer.msg("修改成功!", {
												time : 1500
											});
										}
									}
								});
							}
						}
					});
				}
			});
		});
		$(".add-customFrom-btn").click(function() {
			//计算新增商品弹窗宽度(减去左导航200)
			var width=($(window).width()-800)/2+50;
			var height=($(window).height()-540)/2;
			layer.open({
				type : 2,
				title : '新增分类',
				content :"${ctx}/commonCategory/preEditcommonCategory.action",
				offset: [height+'px', width+'px'],
				area : [ '650px', '450px' ],
				btn : [ '保存', '取消' ],
				btnAlign : 'c',
				yes : function(index, layero) {
					var childFrame = window.frames["layui-layer-iframe" + index];    //得到加载层页面window对象
					var result=childFrame.checkData(); //掉用其全局函数,校验并获取数据
					console.log("data====>"+JSON.stringify(result.data));
					if(result.status){
						layer.close(index);   //关闭加载页面
					 	$.ajax({
							url:"${ctx}/commonCategory/editcommonCategory.action",
							data:result.data,
							type:"POST",
							success:function(data){
								if (data == "success") {
									layer.close(index);
									load("common_category.jsp");
									layer.msg("添加成功!", {
										time : 1500
									});
								}
							}
						});
					}
				}
			});
		});
	});
</script>
<div class="sitemake-nav">当前位置 : 首页 &nbsp;&nbsp;>&nbsp;&nbsp;内容页面分类</div>
<button
	class="layui-btn layui-btn-normal layui-btn-sm add-customFrom-btn"
	type="button" style="margin-left: 8px;">新增分类</button>
<table id="custom_from_list" lay-filter="custom_from_filter" ></table>
<script type="text/html" id="customFormOperator">
  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>

