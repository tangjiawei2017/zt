<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>会员列表信息</title>
<style>
.layui-table-view {
	margin-top: 0px;
}
</style>
<script>
    $(function(){
    	$("button[name='search-btn']").click(function(){
			table.reload('customer_list',searchCondition());
		});
	//批量按钮操作事件
	$(".main-panel-btn button").click(function() {
			var checkStatus = table.checkStatus('customer_list');
			var ids=null;
			if($(this).data("btn")!="add"){
				ids=checkboxIds(checkStatus);
			}
			switch ($(this).data("btn")) {
			case "add":
	            layer.open({
							type : 2,
							title : '添加会员',
							content : "${ctx}/customer/preAddCustomer.action",
							area : [ '700px', '460px' ],
							btn : [ '保存', '取消' ],
							yes : function(index, layero) {
								var childFrame = window.frames["layui-layer-iframe"
										+ index]; //得到加载层页面window对象
								var result = childFrame
										.checkData(); //掉用其全局函数,校验并获取数据
								if (result.status) {
									layer.close(index); //关闭加载页面
									$.ajax({
										url : "${ctx}/customer/addCustomer.action",
										data : result.data,
										type : "POST",
										success : function(msg) {
											table.reload('customer_list',searchCondition());
											prompt(1,JSON.parse(msg).result);
										}
									});
								}
							}
					})
				break;
			case "del":
				if(ids){
					$.ajax({
						url : "${ctx}/customer/batchDelCustomer.action",
						data : {"ids":ids},
						type : "POST",
						success : function(msg) {
							table.reload('customer_list',searchCondition());
							prompt(3,JSON.parse(msg).result);
						}
					});
		         }
				break;
			case "puton":
				if(ids){
					$.ajax({
						url : "${ctx}/customer/batchPutOnCustomer.action",
						data : {"ids":ids},
						type : "POST",
						success : function(msg) {
							table.reload('customer_list',searchCondition());
							prompt(4,JSON.parse(msg).result);
						}
					});
				}
				break;
			case "putoff":
				if(ids){
					$.ajax({
						url : "${ctx}/customer/batchPutOffCustomer.action",
						data : {"ids":ids},
						type : "POST",
						success : function(msg) {
							table.reload('customer_list',searchCondition());
							prompt(5,JSON.parse(msg).result);
						}
					});
				}
			break;
		}
	});
    })
	//获取选中行的id集合,
	function checkboxIds(checkStatus,msg){
		if(checkStatus.data.length==0){
			msg=(typeof msg =="undefined" || msg=='')?"请选择操作行!":msg;
			layer.msg(msg, {
				time : 1000,
				skin : 'error-class',
				isOutAnim : true,
			});
			return false;
		}else{
			var params="";
			for(var i in checkStatus.data){
				params=params+checkStatus.data[i].id+",";
			}
			params=params.substring(0, params.length-1);
			return params;
		}
	}
	//结果提示
	function prompt(type, result) {
		if (typeof type !== "undefined" && type != "") {
			var msg = "";
			switch (type) {
			case 1:
				msg = result == "success" ? "添加成功!" : "添加失败";
				break;
			case 2:
				msg = result == "success" ? "修改成功!" : "修改失败";
				break;
			case 3:
				msg = result == "success" ? "删除成功!" : "删除失败";
				break;
			case 4:
				msg = result == "success" ? "启用成功!" : "启用失败";
				break;
			case 5:
				msg = result == "success" ? "停用成功!" : "停用失败";
				break;
			}
			layer.msg(msg, {
				time : 1500,
				skin : result == "success" ? 'success-class' : 'error-class',
				anim : result == "success" ? 5 : 6,
				isOutAnim : true,
			});
		}
	}
	//获取查询条件
	function searchCondition() {
		var params = {
			url : "${ctx}/customer/customerListAjax.action",
		}
		var where = {};
		where.type = $("select[name='findType']").val();
		where.search = $("input[name='search']").val();
		params.where = where;
		return params;
	}
</script>
<body>
	<div class="container">
		<div class="main-panel-head">
			<div class="tilte">会员列表</div>
			<div class="customer-search">
				<select name="findType">
					<option value="account">用户名</option>
					<option value="name">姓名</option>
					<option value="phone">手机号</option>
				</select> <input type="text" name="search" class="shop-search-input">
				<button class="shop-sarch-btn" name="search-btn">搜素</button>
			</div>
		</div>
		<div class="main-panel-btn">
			<button
				class="layui-btn layui-btn-primary shop-operator-btn layui-btn-small add-btn"
				data-btn="add">新增</button>
			<button
				class="layui-btn layui-btn-primary shop-operator-btn layui-btn-small del-btn"
				data-btn="del">删除</button>
			<button
				class="layui-btn layui-btn-primary shop-operator-btn layui-btn-small puton-btn"
				data-btn="puton">启用</button>
			<button
				class="layui-btn layui-btn-primary shop-operator-btn layui-btn-small putoff-btn"
				data-btn="putoff">停用</button>
		</div>
		<div class="customer-content-ajax">
			<jsp:include page="customer_list_ajax.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>