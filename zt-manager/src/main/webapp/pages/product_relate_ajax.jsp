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
					width : 200,
				}, {
					field : 'image',
					title : '图片',
					width : 100,
					templet: '#imgTpl',
				},{
					field : 'price',
					title : '销售价格',
					width : 100,
				},{
					field : 'stock',
					title : '商品库存',
					width : 100,
				} ] ],
				page : true,//是否显示分页
				skin : 'line',
				limits : [ 10,20 , 50 ],
				limit : 10,
				url : "${ctx}/product/productRelateListAjax.action?id=${param.id}",
				where : {
					search : $("input[name='search']").val(), //搜索条件
					csequence : $(".condition.category .content .tt").data("category"),
					sale : $(".condition.sale .content .tt").data("sale"),
					brandId : $(".condition.brand .content .tt").data("brand"),
				    categoryId : $("input[name='parentCategory']").data("value")
				},
				method : "post",
			});
	     });
     });
</script>
<table id="product_list_ajax" lay-filter="product-table"></table>
<script type="text/html" id="imgTpl">
    <img src="{{d.image}}" width=25 height=25/>
</script>