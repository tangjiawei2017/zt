<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<script>
	$(function() {
		layui.use('table', function() {
			table = layui.table;
			//展示已知数据
			table.render({
				id : 'product_list',
				elem : '#product_list_ajax',
				cols : [ [ //标题栏
				{
					checkbox : true,
				} //默认全选
				,{
					field : 'code',
					title : '商品编码',
					width : 120,
				},{
					field : 'name',
					title : '商品名称',
					width : 230,
				}, {
					field : 'image',
					title : '缩略图',
					width : 100,
					templet: '#imgTpl',
				}, {
					field : 'category',
					title : '所属类型',
					width : 140
				}, {
					field : 'brand',
					title : '所属品牌',
					width : 100
				},{
					field : 'created',
					title : '添加日期',
					width : 120,
				}, {
					field : 'sale',
					title : '上架',
					width : 80,
					templet: '#saleTpl',
				},{
					width : 200,
					align : 'center',
					title : '操作',
					toolbar : '#productOperator'
				} ] ],
				page : true,//是否显示分页
				skin : 'line',
				limits : [ 10,20 , 50 ],
				limit : 10,
				url : "${ctx}/product/productlistAjax.action",
				where : {
					search : $("input[name='search']").val(), //搜索条件
					csequence : $(".condition.category .content .tt").data("category"),
					sale : $(".condition.sale .content .tt").data("sale"),
					brandId : $(".condition.brand .content .tt").data("brand"),
					categoryId : $("input[name='parentCategory']").data("value")
				},
				method : "post",
			});
			//监听工具条
			table.on('tool(product-table)', function(obj) {
				//注：tool是工具条事件名，product_list_ajax是table原始容器的属性 lay-filter="对应的值"
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值
				if (layEvent === 'del') { //删除
					layer.confirm('真的删除该商品么?',{'title':'提示'}, function(index) {
						layer.close(index);
						//向服务端发送删除指令
						$.ajax({
							url : "${ctx}/product/delProduct.action?id="+data["id"],
							type : "POST",
							success : function(msg) {
								table.reload('product_list',searchCondition());
								prompt(3,JSON.parse(msg).result);
							}
						});
					});
				} else if (layEvent === 'edit') { //编辑
					//计算新增商品弹窗宽度(减去左导航200)
					var width=$(window).width()-200;
					var height=$(window).height()-80;
				    var index = layer.open({
						title:'编辑商品',
					  	type: 2,
						content: "${ctx}/product/preEditProduct.action?id="+data["id"],
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
										url : "${ctx}/product/updateProduct.action",
										data : result.data,
										type : "POST",
										dataType:"json",
										success : function(data) {
											table.reload('product_list',searchCondition());
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
	//商品上,下架 type 0下架, 1上架
	function updateSaleStauts(id,obj){
		$.ajax({
			url:"${ctx}/product/updateSaleStatus.action",
			data:{"id":id,"sale":$(obj).data("sale")},
			type:"post",
			success:function(msg){
				if(JSON.parse(msg).result=="success"){
					if($(obj).data("sale")==1){
						$("#img_"+id).attr("src","${ctx}/images/saleon.png");
						$(obj).data("sale",0);
					}else if($(obj).data("sale")==0){
						$("#img_"+id).attr("src","${ctx}/images/saleoff.png");
						$(obj).data("sale",1);
					}
				}
			}
		});
	}
</script>
<table id="product_list_ajax" lay-filter="product-table"></table>
<script type="text/html" id="productOperator">
  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
<script type="text/html" id="imgTpl">
    <img src="{{d.image}}" width=25 height=25/>
</script>
<script type="text/html" id="saleTpl">
    {{#  if(d.sale ==1 ){ }}
       <img src="${ctx}/images/saleon.png" onclick="updateSaleStauts({{d.id}},this)" id="img_{{d.id}}" data-sale="0"/>
    {{#  } else { }}
      <img src="${ctx}/images/saleoff.png" onclick="updateSaleStauts({{d.id}},this)" id="img_{{d.id}}" data-sale="1"/>
    {{#  } }}
</script>

