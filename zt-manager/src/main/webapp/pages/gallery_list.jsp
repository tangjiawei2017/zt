<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style>
.opeartor a {
	text-decoration: underline;
	color: #4D75A5;
}

.opeartor a:hover {
	color: red;
}
</style>
<title>每页大图</title>
<script>
$(function(){
	$(".add-btn").click(function() {
		layer.open({
			type : 2,
			title:'新增广告',
			content : "${ctx}/gallery/preEditgallery.action",
			area : [ '680px', '500px' ],
			scrollbar :false,
			btn : [ '保存', '取消' ],
			yes : function(index, layero) {
				var childFrame = window.frames["layui-layer-iframe" + index];    //得到加载层页面window对象
				var result=childFrame.checkData(); //掉用其全局函数,校验并获取数据
				if(result.status){
					layer.close(index);   //关闭加载页面
					$.ajax({
						url:"${ctx}/gallery/addGallery.action",
						data:result.data,
						type:"POST",
						dataType:"json",
						success:function(data){
							refresh("${ctx}/gallery/list.action",1,data.result);
						}
					});
				}
			},
		});
	});
});
function editGallery(id){
	layer.open({
		type : 2,
		title : '编辑广告',
		content : "${ctx}/gallery/preEditgallery.action?id="+id,
		area : [ '680px', '500px' ],
		btn : [ '保存', '取消' ],
		yes : function(index, layero) {
			var childFrame = window.frames["layui-layer-iframe" + index];    //得到加载层页面window对象
			var result=childFrame.checkData();     //掉用其全局函数,校验并获取数据
			if(result.status){
				layer.close(index);   //关闭加载页面
				$.ajax({
					url:"${ctx}/gallery/updateGallery.action",
					data:result.data,
					type:"POST",
					dataType:"json",
					success:function(data){
					    refresh("${ctx}/gallery/list.action",2,data.result);
					}
				});
			}
		}
	});
}
function delGallery(id){
	$.ajax({
		url:"${ctx}/gallery/delGallery.action?id="+id,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="success"){
				layer.msg('删除成功!', {
					time : 1500,
					skin : 'success-class',
					isOutAnim : true,
				});
				//重新加载数据
				$.ajax({
					url : "${ctx}/gallery/list.action",
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
			<div class="tilte">其他广告位列表</div>
		</div>
		<div class="main-panel-btn">
			<button
				class="layui-btn layui-btn-primary shop-operator-btn layui-btn-small add-btn">新增</button>
		</div>
		<table class="layui-table gallery-table" style="margin-top: 0px;"
			lay-skin="line">
			<colgroup>
				<col width="25%">
				<col width="25%">
				<col width="25%">
				<col>
			</colgroup>
			<thead>
				<tr>
					<th>广告位名称</th>
					<th>缩略图</th>
					<th>更新时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="gallery">
					<tr>
						<td>${gallery.name}</td>
						<td><img src="${gallery.path}" /></td>
						<td><fmt:formatDate value="${gallery.modify}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><span class="opeartor"><a
								href="javaScript:void(0)" onclick="editGallery('${gallery.id}')">维护</a>&nbsp;<a
								href="javaScript:void(0)" onclick="delGallery('${gallery.id}')">删除</a></span></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>