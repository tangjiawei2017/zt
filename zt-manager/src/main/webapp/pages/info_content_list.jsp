<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>资讯列表</title>
<style>
.layui-layer-border {
	border: 0px;
}
.ces-input-select {
	background: url("${ctx}/images/down-btn.png");
	position: absolute;
	right: 10px;
	top: 50%;
	margin-top: -3px;
	cursor: pointer;
	width: 13px;
	height: 6px;
}

.menuContent {
	display: none;
	position: absolute;
	z-index: 100;
	border: 1px solid #ccc;
	background-color: #fff;
	width: 190px;
	height: 200px;
	overflow-y: scroll;
}

#categoryTreestyle {
	margin-top: 0;
	width: 190px;
	height: 200px;
	overflow-y: scroll;
}
</style>
<script>
   var setting = {
		view : {
			dblClickExpand : false
		},
		data : {
			simpleData : {
				enable : true,
				idKey : 'id',
				pIdKey : 'parentid',
				rootPId : 0
			}
		},
		callback : {
			onClick : onClick
		}
	};
	var table;
	//计算新增商品弹窗宽度(减去左导航200)
	var width = $(window).width() - 200;
	var height = $(window).height() - 80;
	$(function() {
		//批量按钮操作事件
		$(".main-panel-btn button").click(function() {
			var checkStatus = table.checkStatus('info_content_list');
			var ids = null;
			if ($(this).data("btn") != "add") {
				ids = checkboxIds(checkStatus);
			}
			switch ($(this).data("btn")) {
			case "add":
				var index = layer
						.open({
							title : '添加资讯',
							type : 2,
							content : '${ctx}/info/preAddInfoContent.action',
							shade : 0,
							resize : false,
							move : false,
							offset : [ '84px', '200px' ],
							area : [ width + "px",
									height + "px" ],
							skin : 'defalut/layer.css',
							btn : [ '保存', '取消' ],
							btnAlign : 'c',
							yes : function(index, layero) {
								var childFrame = window.frames["layui-layer-iframe"
										+ index]; //得到加载层页面window对象
								var result = childFrame
										.checkData(); //掉用其全局函数,校验并获取数据
								if (result.status) {
									layer.close(index); //关闭加载页面
									$.ajax({
												url : "${ctx}/info/addInfoContent.action",
												data : result.data,
												type : "POST",
												dataType : "json",
												success : function(data) {
													table.reload('info_content_list');
													prompt(1,data.result);
											}
									});
								}
							},
						});
				break;
			case "del":
				if (ids) {
					$.ajax({
						    url : "${ctx}/info/batchDelInfoContent.action",
							data : {
								"ids" : ids
							},
							type : "POST",
							success : function(msg) {
								table.reload('info_content_list');
								prompt(3,JSON.parse(msg).result);
							}
						});
				}
				break;
			}
	});
		//初始化分类树
		$.ajax({
			url : "${ctx}/info/infoCateogryListAjax.action",
			type : "post",
			dataType : "json",
			success : function(zNodes) {
				zNodes.unshift({
					"id" : -1,
					"parentid" : 0,
					"name" : "全部分类"
				});
				$.fn.zTree.init($("#categoryTree"), setting, zNodes);
				//ZTree 初始化分类 选择节点(选中初始化节点)
				var id = $.trim($("input[name='parentCategory'").data(
						"value"));
				var treeObj = $.fn.zTree.getZTreeObj("categoryTree"); //ztree树的ID
				if (Number(id) == 0) {
					// id =0 则显示 顶级展示分类
					id = -1;
				}
				var node = treeObj.getNodeByParam("id", id);//根据ID找到该节点
				treeObj.selectNode(node); //根据该节点选中
		    }
		});
	});
	//获取选中行的id集合,
	function checkboxIds(checkStatus, msg) {
		if (checkStatus.data.length == 0) {
			msg = (typeof msg == "undefined" || msg == '') ? "请选择操作行!" : msg;
			layer.msg(msg, {
				time : 1000,
				skin : 'error-class',
				isOutAnim : true,
			});
			return false;
		} else {
			var params = "";
			for ( var i in checkStatus.data) {
				params = params + checkStatus.data[i].id + ",";
			}
			params = params.substring(0, params.length - 1);
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
			url : "${ctx}/info/infoContentListAjax.action",
		}
		var where = {};
		where.categoryId = $("input[name='parentCategory']").data("value");
		params.where = where;
		return params;
	}
	function onClick(e, treeId, treeNode) {
		var nodes = $.fn.zTree.getZTreeObj("categoryTree").getSelectedNodes();
		$("input[name='parentCategory']").val(nodes[0].name);
		$("input[name='parentCategory']").data("value",nodes[0].id);
		$("#menuContent").slideUp("fast");
		table.reload('info_content_list', searchCondition());
	}

	function showMenu() {
		if($("#menuContent").is(":visible")){
			$("#menuContent").slideUp("fast");
		}else{
			var $input = $("input[name='parentCategory']");
			var $offset = $input.offset();
			var left = Number($offset.left-200);
			var top  = Number($offset.top-80);
			$("#menuContent").css({ 
				"left":left + "px",
				"top": top + $input.outerHeight() + "px"
			}).slideDown("fast");
		}
	}
</script>
</head>
<body>
	<div class="container">
		<div class="main-panel-head">
			<div class="tilte">资讯列表</div>
			<div class="shop-category" style="width:190px;font-weight: normal;">
			<div class="layui-input-inline">
				<input type="text" name="parentCategory" style="width:190px;" value="全部分类"
					data-value="-1" class="shop-search-input shop-select" readonly
					onclick="showMenu(); return false;"> <i
					class="ces-input-select" id="showcateControl"
					onclick="showMenu(); return false;"></i>
		    </div>
			<div id="menuContent" class="menuContent">
					<ul id="categoryTree" class="ztree"></ul>
			</div>
			</div>
		</div>
		<div class="main-panel-btn">
			<button
				class="layui-btn layui-btn-primary shop-operator-btn layui-btn-small add-btn"
				data-btn="add">新增</button>
			<button
				class="layui-btn layui-btn-primary shop-operator-btn layui-btn-small del-btn"
				data-btn="del">删除</button>
		</div>
		<div class="product-content-ajax">
			<jsp:include page="info_content_list_ajax.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>