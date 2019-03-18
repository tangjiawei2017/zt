<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!--ZTree样式调整 -->
<style>
.shop-tree-header {
	margin-top: 10px;
	width: 100%;
	background: url(images/table-header.gif) repeat-x scroll 0 bottom
		#F9F9F9;
	height: 28px;
	cursor: default;
	border-bottom: 1px solid #D1D1D1;
	cursor: default;
	width: 100%;
}

.shop-tree-header .shop-tree-hd {
	width: 33%;
	border-right: 1px solid #D0D0D0;
	float: left;
	overflow: hidden;
	height: 28px;
	border-left: 1px solid #EEEEEE;
}

.shop-tree-header .shop-tree-hd-text {
	font: 13px;
	overflow: hidden;
	padding: 4px 3px 4px 5px;
	white-space: nowrap;
	overflow: hidden;
	text-align: center;
}

/** ZTree样式修改**/
.ztree {
	padding: 0px;
	margin: 0px;
}
.ztree *{
 font-size:14px;
}

.ztree li a {
	vertical-align: middle;
	height: 30px;
}

.ztree li>a {
	width: 100%;
}

.ztree li>a, .ztree li a.curSelectedNode {
	padding-top: 0px;
	background: none;
	height: auto;
	border: none;
	cursor: default;
	opacity: 1;
}

.ztree li ul {
	padding-left: 0px
}

.ztree div.diy span {
	line-height: 30px;
	vertical-align: middle;
}

.ztree div.diy {
	height: 100%;
	width: 33%;
	line-height: 30px;
	border-bottom: 1px solid #EDEDED;
	text-align: center;
	display: inline-block;
	box-sizing: border-box;
	color: #6c6c6c;
	font-family: "SimSun";
	font-size: 14px;
	overflow: hidden;
}

.ztree div.diy:first-child {
	text-align: left;
	text-indent: 10px;
	border-left: none;
}

.ztree li span.button.ico_close {
	vertical-align: middle;
}

.ztree li span.button.ico_open {
	vertical-align: middle;
}

.ztree li span.button.ico_docu {
	vertical-align: middle;
}

.ztree .add_img {
	cursor: pointer;
}

.ztree  .opeartor a {
	text-decoration: underline;
	color: #4D75A5;
}

.ztree  .opeartor a:hover {
	color: red;
}

.center {
	text-align: center;
}
/**修改子节点图标**/
.ztree li span.button.ico_docu {
	background-position: -110px 0;
}
/** ZTree样式修改结束**/
.icon-move-up-disable {
	background: url(images/ico_paixu.png) repeat scroll 0 13px transparent;
	margin: 6px 3px 0 2px;
	cursor: pointer;
	float: left;
	border: 0 none;
	height: 13px;
	width: 13px;
	overflow: hidden;
}

.icon-move-down-disable {
	background: url(images/ico_paixu.png) repeat scroll 13px 13px
		transparent;
	margin: 6px 3px 0 2px;
	cursor: pointer;
	float: left;
	border: 0 none;
	height: 13px;
	width: 13px;
	overflow: hidden;
}

.icon-move-down {
	background: url(images/ico_paixu.png) repeat scroll 13px 0 transparent;
	margin: 6px 3px 0 2px;
	cursor: pointer;
	float: left;
	border: 0 none;
	height: 13px;
	width: 13px;
	overflow: hidden;
}

.icon-move-up {
	background: url(images/ico_paixu.png) repeat scroll 0 0 transparent;
	margin: 6px 3px 0 2px;
	cursor: pointer;
	float: left;
	border: 0 none;
	height: 13px;
	width: 13px;
	overflow: hidden;
}
</style>
<title>商品类型</title>
<script>
var zTreeSetting = {
		data : {
			simpleData : {
				enable : true,
				idKey : 'id',
				pIdKey : 'parentid',
				rootPId : 0
			}
		},
		view : {
			showLine : false,
			addDiyDom : addDiyDom
		},
	};
	$(function() {
		$.ajax({
			url : "${ctx}/product/productCateogryListAjax.action",
			async : false,
			success : function(nodes) {
				$.fn.zTree.init($("#categoryTree"), zTreeSetting, nodes);
			}
		});
		$(".add-btn").click(function() {
			layer.open({
				type : 2,
				title:'新增商品类型',
				content : "${ctx}/product/preAddCategory.action",
				area : [ '800px', '540px' ],
				scrollbar :false,
				btn : [ '保存', '取消' ],
				yes : function(index, layero) {
					var childFrame = window.frames["layui-layer-iframe" + index];    //得到加载层页面window对象
					var result=childFrame.checkData(); //掉用其全局函数,校验并获取数据
					if(result.status){
						layer.close(index);   //关闭加载页面
						$.ajax({
							url:"${ctx}/product/addCategory.action",
							data:result.data,
							type:"POST",
							dataType:"json",
							success:function(data){
								refresh("${ctx}/product/showCategory.action",1,data.result);
							}
						});
					}
				},
			});
		});
	});
	
	function editCategory(id){
		layer.open({
			type : 2,
			title : '编辑商品类型',
			content : "${ctx}/product/preEditCategory.action?id="+id,
			area : [ '800px', '540px' ],
			btn : [ '保存', '取消' ],
			yes : function(index, layero) {
				var childFrame = window.frames["layui-layer-iframe" + index];    //得到加载层页面window对象
				var result=childFrame.checkData();     //掉用其全局函数,校验并获取数据
				if(result.status){
					layer.close(index);   //关闭加载页面
					$.ajax({
						url:"${ctx}/product/updateCategory.action",
						data:result.data,
						type:"POST",
						dataType:"json",
						success:function(data){
						    refresh("${ctx}/product/showCategory.action",2,data.result);
						}
					});
				}
			}
		});
	}
	function delCategory(id){
		$.ajax({
			url:"${ctx}/product/delCategory.action?id="+id,
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
						url : "${ctx}/product/showCategory.action",
						type : "POST",
						async : "true",
						cache : false,
						success : function(data) {
							$(".layui-body").html(data); 
						}
					});
				}else if(data.result=="fail" && data.code==1){
					layer.msg('该分类下有子分类，无法删除！', {
						time : 2000,
						skin : 'error-class',
						anim :6,
						isOutAnim : true,
					});
				}else if(data.result=="fail" & data.code==2){
					layer.msg('该分类下包含商品,不允许删除!', {
						time : 2000,
						skin : 'error-class',
						anim :6,
						isOutAnim : true,
					});
				}
			}
		})
	}
	
	function addChildCateogry(parentid){
		layer.open({
			type : 2,
			title:'新增商品类型',
			content : "${ctx}/product/preAddCategory.action?parentid="+parentid,
			area : [ '800px', '540px' ],
			btn : [ '保存', '取消' ],
			yes : function(index, layero) {
				var childFrame = window.frames["layui-layer-iframe" + index];    //得到加载层页面window对象
				var result=childFrame.checkData(); //掉用其全局函数,校验并获取数据
				if(result.status){
					layer.close(index);   //关闭加载页面
					$.ajax({
						url:"${ctx}/product/addCategory.action",
						data:result.data,
						type:"POST",
						dataType:"json",
						success:function(data){
							refresh("${ctx}/product/showCategory.action",1,data.result);
						}
					});
				}
			},
		});
	}
	
	/**
	 * 自定义DOM节点
	 */
	function addDiyDom(treeId, treeNode) {
		var spaceWidth = 15;
		var liObj = $("#" + treeNode.tId);
		var aObj = $("#" + treeNode.tId + "_a");
		var switchObj = $("#" + treeNode.tId + "_switch");
		var icoObj = $("#" + treeNode.tId + "_ico");
		var spanObj = $("#" + treeNode.tId + "_span");
		aObj.attr('title', '');
		aObj.append('<div class="diy swich"></div>');
		var div = $(liObj).find('div').eq(0);
		switchObj.remove();
		spanObj.remove();
		icoObj.remove();
		div.append(switchObj);
		div.append(icoObj);
		div.append(spanObj);
		var spaceStr = "<span style='height:1px;display: inline-block;width:"
				+ (spaceWidth * treeNode.level) + "px'></span>";
		switchObj.before(spaceStr);
		var editStr = '';
		var sort ='<div class="ship-content-text sorting"><span class="icon-move-up upsorting"'
		         +'onclick="sortUpOrDown(${ship.id},${ship.orderNum},-1)"></span> <span class="'
		         +'icon-move-down downsorting" onclick="sortUpOrDown(${ship.id},${ship.orderNum},1)"></span>';
	    var child ='<img src="${ctx}/images/add.png" class="add_img" onclick="addChildCateogry(\''
			     + treeNode.id + '\')">';
		var operator = '<span class="opeartor"><a href="javaScript:void(0)" onclick="editCategory(\''
					+ treeNode.id + '\')">编辑</a>&nbsp;<a href="javaScript:void(0)" onclick="delCategory(\''
					+ treeNode.id + '\')">删除</a></span>';
		editStr += '<div class="diy">' + child + '</div>';
		editStr += '<div class="diy">' + operator + '</div>';
		aObj.append(editStr);
	}
	//配送方式排序,direction -1向前进一步,1向后退一步 
	function sortUpOrDown(id, orderNum, direction) {
		$.ajax({
			url : "${ctx}/ship/updateShipOrderNum.action",
			type : "POST",
			data : {
				id : id,
				orderNum : orderNum,
				direction : direction
			},
			success : function(result) {
					refresh("${ctx}/ship/list.action", 2,result);
			}
		});
	}
</script>
</head>
<body>
	<div class="container">
		<div class="main-panel-head">
			<div class="tilte">商品类型</div>
		</div>
		<div class="main-panel-btn">
			<button
				class="layui-btn layui-btn-primary shop-operator-btn layui-btn-small add-btn">新增</button>
		</div>
		<div class="shop-tree-header">
			<div class="shop-tree-hd">
				<div class="shop-tree-hd-text">名称</div>
			</div>
			<div class="shop-tree-hd">
				<div class="shop-tree-hd-text">新增子类</div>
			</div>
			<div class="shop-tree-hd">
				<div class="shop-tree-hd-text center">操作</div>
			</div>
		</div>
		<ul id="categoryTree" class="ztree"></ul>
	</div>
</body>
</html>