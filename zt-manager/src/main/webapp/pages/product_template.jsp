<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>商品属性模板</title>
<script>
	$(function() {
		$(".add-btn").click(function() {
			layer.open({
				type : 2,
				title:'新增属性模板',
				content : "${ctx}/productTemplate/preAddTemplate.action",
				area : [ '800px', '540px' ],
				btn : [ '保存', '取消' ],
				yes : function(index, layero) {
					var childFrame = window.frames["layui-layer-iframe" + index];    //得到加载层页面window对象
					var result=childFrame.checkData(); //掉用其全局函数,校验并获取数据
					//console.log(result);
					if(result.status){
						layer.close(index);   //关闭加载页面
						$.ajax({
							url:"${ctx}/productTemplate/addTemplate.action",
							data:{"data":JSON.stringify(result.data)},
							dataType:"json",
							type:"POST",
							success:function(data){
								refresh("${ctx}/productTemplate/list.action",1,data.result);
							}
						});
					}
				},
			});
		});
	});
	function editTemplate(id){
		layer.open({
			type : 2,
			title:'编辑属性模板',
			content : "${ctx}/productTemplate/preEditTemplate.action?id="+id,
			area : [ '800px', '540px' ],
			btn : [ '保存', '取消' ],
			yes : function(index, layero) {
				var childFrame = window.frames["layui-layer-iframe" + index];    //得到加载层页面window对象
				var result=childFrame.checkData(); //掉用其全局函数,校验并获取数据
				if(result.status){
					layer.close(index);   //关闭加载页面
					//若当前模板下已有关联商品,则不允许编辑模板
					$.ajax({
						url:"${ctx}/productTemplate/checkTemplateIsUse.action?id="+id,
						dataType:"json",
						type:"POST",
						success:function(data){
						    if(data.result=="success"){
						    	$.ajax({
									url:"${ctx}/productTemplate/updateTemplate.action",
									data:{"data":JSON.stringify(result.data)},
									dataType:"json",
									type:"POST",
									success:function(data){
									    refresh("${ctx}/productTemplate/list.action",2,data.result);
									}
								});
						    }else{
						    	layer.msg("该模板下已有商品,不可编辑!", {
									time : 1500,
									skin : 'error-class',
									isOutAnim : true,
								});
						    }
						}
					});
				}
			}
		});
	}
	function delTemplate(id){
		//若当前模板下已有关联商品,则不允许编辑模板
		$.ajax({
			url:"${ctx}/productTemplate/checkTemplateIsUse.action?id="+id,
			dataType:"json",
			type:"POST",
			success:function(data){
			    if(data.result=="success"){
			    	$.ajax({
						url:"${ctx}/productTemplate/delTemplate.action?id="+id,
						type:"post",
						dataType:"json",
						success:function(data){
							if(data.result=="success"){
								refresh("${ctx}/productTemplate/list.action",3,data.result);
							}else{
								layer.msg('删除失败!', {
									time : 1500,
									skin : 'error-class',
									anim :6,
									isOutAnim : true,
								});
							}
						}
					});
			    }else{
			    	layer.msg("该模板下已有商品,不可删除!", {
						time : 1500,
						skin : 'error-class',
						isOutAnim : true,
					});
			    }
			}
		});
	}
</script>
</head>
<body>
	<div class="container">
		<div class="main-panel-head">
			<div class="tilte">属性模板设置</div>
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
					<th>产品模板名称</th>
					<th>关联的一级产品分类</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${templateList}" var="template">
					<tr>
						<td><c:out value="${template.name}" /></td>
						<td><c:out value="${template.categorys}"/></td>
						<td><a href="javascript:void(0)"
							onclick="editTemplate(${template.id});">编辑</a>&nbsp&nbsp<c:if
								test="${not template.isdefault}">
								<a href="javascript:void(0)"
									onclick="delTemplate(${template.id});">删除</a>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>