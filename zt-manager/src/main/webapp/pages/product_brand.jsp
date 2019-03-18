<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>商品品牌</title>
<script>
	$(function() {
		$(".add-btn").click(function() {
			layer.open({
				type : 2,
				title:'新增品牌',
				content : "${ctx}/product/preAddBrand.action",
				area : [ '600px', '400px' ],
				btn : [ '保存', '取消' ],
				yes : function(index, layero) {
					var childFrame = window.frames["layui-layer-iframe" + index];    //得到加载层页面window对象
					var result=childFrame.checkData(); //掉用其全局函数,校验并获取数据
					if(result.status){
						layer.close(index);   //关闭加载页面
						$.ajax({
							url:"${ctx}/product/saveBrand.action",
							data:result.data,
							type:"POST",
							success:function(msg){
								refresh("${ctx}/product/showBrand.action",1,JSON.parse(msg).result);
							}
						});
					}
				},
			});
		});
	});
	function editBrand(id){
		layer.open({
			type : 2,
			title:"编辑品牌",
			content : "${ctx}/product/preEditBrand.action?id="+id,
			area : [ '600px', '400px' ],
			btn : [ '保存', '取消' ],
			yes : function(index, layero) {
				var childFrame = window.frames["layui-layer-iframe" + index];    //得到加载层页面window对象
				var result=childFrame.checkData(); //掉用其全局函数,校验并获取数据
				if(result.status){
					layer.close(index);   //关闭加载页面
					$.ajax({
						url:"${ctx}/product/updateBrand.action",
						data:result.data,
						type:"POST",
						success:function(msg){
						    refresh("${ctx}/product/showBrand.action",2,JSON.parse(msg).result);
						}
					});
				}
			}
		});
	}
	function delBrand(id){
		$.ajax({
			url:"${ctx}/product/delBrand.action?id="+id,
			type:"post",
			success:function(msg){
				var data=JSON.parse(msg);
				if(data.result=="success"){
					layer.msg('删除成功!', {
						time : 1500,
						skin : 'success-class',
						isOutAnim : true,
					});
					//重新加载数据
					$.ajax({
						url : "${ctx}/product/showBrand.action",
						type : "POST",
						async : "true",
						cache : false,
						success : function(data) {
							$(".layui-body").html(data); 
						}
					});
				}
			}
		})
	}
</script>
</head>
<body>
	<div class="container">
		<div class="main-panel-head">
			<div class="tilte">商品品牌</div>
		</div>
		<div class="main-panel-btn">
			<button
				class="layui-btn layui-btn-primary shop-operator-btn layui-btn-small add-btn">新增</button>
		</div>
		<table class="layui-table" style="margin: 0; padding: 0;" lay-skin="line">
			<colgroup>
				<col width="33.3%">
				<col width="33.3%">
				<col>
			</colgroup>
			<thead>
				<tr>
					<th>名称</th>
					<th>图片</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${brandList}" var="brand">
					<tr>
						<td><c:out value="${brand.name}" /></td>
						<td><c:if test="${brand.image!=null && brand.image!=''}"><img src="${brand.image}" width="20" /></c:if></td>
						<td><a href="javascript:void(0)"
							onclick="editBrand(${brand.id});">编辑</a>&nbsp&nbsp<a
							href="javascript:void(0)" onclick="delBrand(${brand.id});">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>