  <%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>资讯分类</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css"
	type="text/css" />
<style>
.shop-category-box {
	margin-top: 30px;
}

.shop-select {
	cursor: pointer;
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

.logo-img {
	width: 120px;
	margin-top: 10px;
	border: 1px solid #ccc;
}

.close-upimg {
	width: 15px;
}
</style>
<script src="${ctx}/js/jquery-2.1.0.js"></script>
<script src="${ctx}/layui/layui.js"></script>
<script src="${ctx}/js/formValidation.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.ztree.all.min.js"></script>
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
	$(function() {
		layui.use([ 'form', 'upload' ],function() {
			var form = layui.form;
			form.render(); //更新渲染全部组件
			var upload = layui.upload;
			//执行实例
			var uploadInst = upload.render({
					elem : '#logo',
					url : '${ctx}/upload/commonUpload.action',
					accept : 'images',
					size : 50,
					done : function(res) {
						if (res.code == 0) {
							$(".logo-img").remove();
							$div = '<section class="up-section logo-img"><span class="up-span"></span>'
									+ '<img class="close-upimg" src="${ctx}/images/del.png"><img class="up-img" src="'
	                                + res.data.src+'" data-src="'+ res.data.src+'" ></section>';
							$("#logo").parent().append($div);
						}
					},
					error : function() {
					}
			 });
		});
		$(".img-div").delegate(".close-upimg", "click", function() {
			$(".logo-img").remove();
		});
		//表单初始化校验
		$("#form").formValidation();
		$.ajax({
					url : "${ctx}/info/infoCateogryListAjax.action?id=${category.id}",
					type : "post",
					dataType : "json",
					success : function(zNodes) {
						zNodes.unshift({
							"id" : -1,
							"parentid" : 0,
							"name" : "顶级展示分类"
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
	//校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : false,
			"data" : {}
		};
		//表单提交校验
		result.status = $("#form").formValidation("submit");
		result.data = $("#form").serialize();
		var image = $.trim($(".up-section .up-img").data("src"));
		if (typeof image != "undefined" && image != "") {
			result.data = result.data + "&image=" + image;
		}
		//设置父类模板id
		var parentid = $("input[name='parentCategory']").data("value");
		if (Number(parentid) == -1) {
			parentid = 0;
		}
		result.data = result.data + "&parentid=" + parentid;
		return result;
	}

	function onClick(e, treeId, treeNode) {
		var nodes = $.fn.zTree.getZTreeObj("categoryTree").getSelectedNodes();
		$("input[name='parentCategory']").val(nodes[0].name);
		$("input[name='parentCategory']").data("value", nodes[0].id);
		$("#menuContent").slideUp("fast");
	}

	function showMenu() {
		if ($("#menuContent").is(":visible")) {
			$("#menuContent").slideUp("fast");
		} else {
			var $input = $("input[name='parentCategory']");
			var $offset = $input.offset();
			$("#menuContent").css({
				"left" : $offset.left + "px",
				"top" : $offset.top + $input.outerHeight() + "px"
			}).slideDown("fast");
		}
	}
</script>
</head>
<body style="height:360px;">
	<div class="container">
		<div class="layui-field-box shop-category-box">
			<c:choose>
				<c:when test="${category!=null}">
					<form class="layui-form" action="" id="form">
						<input type="hidden" name="id" value="${category.id}" />
						<div class="layui-form-item">
							<label class="layui-form-label">分类名称:*</label>
							<div class="layui-input-inline">
								<input type="text" name="name" class="layui-input"
									value="${category.name}"
									data-rule='{"ajax":"${ctx}/info/checkInfoCategoryExist.action?id=${category.id}","errorMsg":"该分类名称已存在!"}'>
							</div>
							<div class="layui-form-mid shop-check-msg"></div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">所属分类:*</label>
							<div class="layui-input-inline">
								<c:if test="${category.parent==null}">
									<input type="text" name="parentCategory" value="顶级展示分类"
										data-value="0" class="layui-input shop-select" readonly
										onclick="showMenu(); return false;">
									<i class="ces-input-select" id="showcateControl"
										onclick="showMenu(); return false;"></i>
								</c:if>
								<c:if test="${category.parent!=null}">
									<input type="text" name="parentCategory"
										value="${category.parent.name}"
										data-value="${category.parent.id} "
										class="layui-input shop-select" readonly
										onclick="showMenu(); return false;">
									<i class="ces-input-select" id="showcateControl"
										onclick="showMenu(); return false;"></i>
								</c:if>
							</div>
							<div id="menuContent" class="menuContent">
								<ul id="categoryTree" class="ztree"></ul>
							</div>
						</div>
						<div class="layui-form-item img-div">
							<label class="layui-form-label">图片:</label>
							<div class="layui-input-inline image">
								<button type="button" class="layui-btn layui-btn-normal"
									id="logo">选择</button>
								<c:if test="${category.image!=null}">
									<section class="up-section logo-img">
										<span class="up-span"></span> <img class="close-upimg"
											src="${ctx}/images/del.png"> <img class="up-img"
											src="${category.image}"
											data-src="${category.image}" />
									</section>
								</c:if>
							</div>
						</div>
					</form>
				</c:when>
				<c:otherwise>
					<form class="layui-form" action="" id="form">
						<div class="layui-form-item">
							<label class="layui-form-label">分类名称:*</label>
							<div class="layui-input-inline">
								<input type="text" name="name" lay-verify="required"
									class="layui-input"
									data-rule='{"ajax":"${ctx}/info/checkInfoCategoryExist.action","errorMsg":"该分类名称已存在!"}'>
							</div>
							<div class="layui-form-mid shop-check-msg"></div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">所属分类:*</label>
							<div class="layui-input-inline">
								<c:if test="${parentid==null}">
									<input type="text" name="parentCategory" value="顶级展示分类"
										data-value="0" class="layui-input shop-select" readonly
										onclick="showMenu(); return false;">
									<i class="ces-input-select" id="showcateControl"
										onclick="showMenu(); return false;"></i>
								</c:if>
								<c:if test="${parentid!=null}">
									<input type="text" name="parentCategory" value="${parentName}"
										data-value="${parentid}" class="layui-input shop-select"
										readonly onclick="showMenu(); return false;">
									<i class="ces-input-select" id="showcateControl"
										onclick="showMenu(); return false;"></i>
								</c:if>
							</div>
							<div id="menuContent" class="menuContent">
								<ul id="categoryTree" class="ztree"></ul>
							</div>
						</div>
						<div class="layui-form-item img-div">
							<label class="layui-form-label">图片:</label>
							<div class="layui-input-inline image">
								<button type="button" class="layui-btn layui-btn-normal"
									id="logo">选择</button>
							</div>
						</div>
					</form>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>