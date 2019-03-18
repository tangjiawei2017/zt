<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css"
	type="text/css" />
<script src="${ctx}/js/jquery-2.1.0.js"></script>
<script src="${ctx}/layui/layui.js"></script>
<script src="${ctx}/js/formValidation.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.ztree.all.min.js"></script>
<title>商品关联</title>
<style>
.layui-layer-border {
	border: 0px;
}
.relate-content{
    margin:20px;
}
.ces-input-select {
	background: url(../images/down-btn.png);
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
	$(function() {
		$(".product-relate-btn").click(function() {
			table.reload('product_list', searchCondition());
		});
		$.ajax({
			url : "${ctx}/product/productCateogryListAjax.action",
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
	
	//获取查询条件
	function searchCondition() {
		var params = {
			url : "${ctx}/product/productlistAjax.action",
		}
		var where = {};
		where.search = $("input[name='search']").val();
		where.csequence = $(".condition.category .content .tt")
				.data("category");
		where.sale = $(".condition.sale .content .tt").data("sale");
		where.brandId = $(".condition.brand .content .tt").data("brand");
		where.categoryId = $("input[name='parentCategory']").data("value");
		params.where = where;
		return params;
	}
	
	function onClick(e, treeId, treeNode) {
		var nodes = $.fn.zTree.getZTreeObj("categoryTree").getSelectedNodes();
		$("input[name='parentCategory']").val(nodes[0].name);
		$("input[name='parentCategory']").data("value",nodes[0].id);
		$("#menuContent").slideUp("fast");
		table.reload('product_list', searchCondition());
	}

	function showMenu() {
		if($("#menuContent").is(":visible")){
			$("#menuContent").slideUp("fast");
		}else{
			var $input = $("input[name='parentCategory']");
			var $offset = $input.offset();
			$("#menuContent").css({ 
				"left":$offset.left + "px",
				"top": $offset.top + $input.outerHeight() + "px"
			}).slideDown("fast");
		}
	}
	//提交表单时,校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : true,
			"data" : {}
		};
		var checkStatus = table.checkStatus('product_list');
        result.data = checkStatus.data;
        return result;
	};
</script>
</head>
<body style="height:430px;">
	<div class="container">
		<div class="relate-content">
			<div class="layui-input-inline">
				<input type="text" name="parentCategory" value="全部分类"
					data-value="-1" class="layui-input shop-select" readonly
					onclick="showMenu(); return false;"> <i
					class="ces-input-select" id="showcateControl"
					onclick="showMenu(); return false;"></i>
			</div>
			<div id="menuContent" class="menuContent">
				<ul id="categoryTree" class="ztree"></ul>
			</div>
			<div class="layui-input-inline" style="margin-left:20px;margin-right:20px;"><input type="text"  class="layui-input" name="search" placeholder="请输入商品编号/名称"></div>
			<button class="layui-btn  layui-btn-normal product-relate-btn"  type="button">搜索</button>
		</div>
		<div class="product-content-ajax">
		 <jsp:include page="product_relate_ajax.jsp?id=${param.id}"></jsp:include>
		</div>
	</div>
</body>
</html>