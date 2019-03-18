<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="${ctx}/js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="${ctx}/layui/layui.js"></script>
<script src="${ctx}/js/formValidation.js"></script>
<script src="${ctx}/wangEditor/wangEditor.min.js"></script>
<meta charset="utf-8">
<style>
.shop-select {
	cursor: pointer;
}

.up-section {
	margin-top: 10px;
	width: 120px;
}

.close-upimg {
	width: 15px;
}

.layui-form-label {
	width: 100px;
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

.layui-table tbody tr:hover {
	background-color: transparent !important;
}

.product-img-div {
	width: 100%;
	height: 150px;
}

.product-img-div .img-div {
	position: relative;
	width: 150px;
	height: 150px;
	float: left;
	border: 1px dashed #999;
	text-align: center;
	margin-right: 10px;
}

.product-img-div .img-div img {
	width: 150px;
	height: 150px;
}

.product-pic-instruction {
	width: 100%;
	color: #999;
}

.product-img-delete {
	position: absolute;
	top: 0px;
	right: 0px;
	background-image: url("${ctx}/images/img-del.png");
	width: 18px;
	height: 18px;
	cursor: pointer;
}

.shop-spec-label {
	float: left;
	display: block;
	padding: 9px 10px 9px 5px;
	font-weight: 400;
}

h1 {
	font-size: 2em;
	margin: .67em 0
}

h2 {
	font-size: 1.5em;
	margin: .75em 0
}

h3 {
	font-size: 1.17em;
	margin: .83em 0
}
.layui-form-select dl{
  z-index: 10050;
}
</style>
<script>
	var table;
	var editor;
	var form;
	var layer;
	$(function() {
		layui.use(['form', 'layer'],function() {
			form = layui.form;
		    layer = layui.layer;
			layui.use('upload',function() {
				upload = layui.upload;
				layui.use('upload',function() {
					upload = layui.upload;
					//执行实例(缩略图)
					var uploadInst = upload.render({
							elem : '#logo',
							url : '${ctx}/upload/commonUpload.action',
							accept : 'images',
							size : 120,
							done : function(res) {
								if (res.code == 0) {
									$(".logo-img").remove();
									//上传成功,存储上传图片路径
									$("input[name='image']").val(res.data.src);
									$div = '<section class="up-section logo-img"><span class="up-span"></span>'
											+ '<img class="close-upimg" src="${ctx}/images/del.png"><img class="up-img" src="'
                                                                                + res.data.src+'" data-src="'+ res.data.src+'" ></section>';
									$("#logo").parent().append($div);
								}
							},
							error : function() {
							}
					});
					$(".img-div").delegate(".close-upimg","click",function() {
							$(".logo-img").remove();
							$("input[name='image']").val('');
					});
				});
			});
			$(".img-div").delegate(".close-upimg", "click",function() {
						$(".logo-img").remove();
			});
			//创建富文本编辑器
			var E = window.wangEditor;
			//创建富文本编辑器
			var E = window.wangEditor;
			editor = new E('#editor');
			 // 自定义菜单配置
		   /*  editor.customConfig.menus = [
		    	   'head',  // 标题
		    	    'bold',  // 粗体
		    	    'fontSize',  // 字号
		    	    'fontName',  // 字体
		    	    'italic',  // 斜体
		    	    'underline',  // 下划线
		    	    'strikeThrough',  // 删除线
		    	    'foreColor',  // 文字颜色
		    	    'backColor',  // 背景颜色
		    	    'link',  // 插入链接
		    	    'list',  // 列表
		    	    'justify',  // 对齐方式
		    	    'emoticon',  // 表情
		    	    'image',  // 插入图片
		    	    'table',  // 表格
		    	    'video',  // 插入视频
		    	    'undo',  // 撤销
		    	    'redo'  // 重复
		    ] */
		    // 自定义字体
		    editor.customConfig.fontNames = [
		        '宋体',
		        '微软雅黑',
		        '楷体',
		        '黑体',
		        'Arial',
		        'Tahoma',
		        'Verdana'
		    ]
		    editor.customConfig.pasteFilterStyle = false;
		    editor.customConfig.uploadImgServer = '${ctx}/upload/productDescriptionImage.action';
			editor.customConfig.uploadFileName = 'file';
			// 将图片大小限制为 1M
			editor.customConfig.uploadImgMaxSize = 1 * 1024 * 1024;
			editor.create();
			//初始化数据
			editor.txt.html($("textarea[name='content']").val());
			//表单初始化校验
			$("#form").formValidation();
		});
	});

	//提交表单时,校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : false,
			"data" : {}
		};
		//表单提交校验
		result.status = $("#form").formValidation("submit");
		//表单校验失败,直接返回错误
		if (!result.status) {
			return result;
		}
		//类型处理
		$("input[name='categoryId']").val(
				$("input[name='category_id']").data("value"));
		//商品缩略图不能为空
		var image = $("input[name='image']").val();
		if (image == '') {
			layer.msg("商品缩略图不能为空!");
			result.status = false;
			return result;
		}
		//商品描述赋值
		$("textarea[name='content']").val(editor.txt.html());
		//手动将 图片路径以及规格值传递过去
		result.data = $("#form").serialize();
		return result;
	}
</script>
</head>
<body>
	<div class="container">
		<div class="content-r">
			<div class="layui-field-box">
				<c:choose>
					<c:when test="${commonContent!=null}">
						<form class="layui-form" action="" id="form">
							<input type="hidden" name="id" value="${commonContent.id}" />
							<div class="layui-form-item name-module">
								<label class="layui-form-label"><span class="showName">页面标题</span>:<span
									class="need">*</span></label>
								<div class="layui-input-inline">
									<input type="text" name="title" class="layui-input" value="${commonContent.title}"
										data-rule='{"required":true}'>
								</div>
								<div class="layui-form-mid shop-check-msg"></div>
							</div>
							<div class="layui-form-item category-module">
								<label class="layui-form-label"><span class="showName">所属分类</span>:<span
									class="need">*</span></label>
								<div class="layui-input-inline">
									<select name="categoryid">
									   <c:forEach items="${categoryList}" var="item"> 
									       <option value="${item.id}" <c:if test="${item.id==commonContent.categoryid}">selected</c:if>>${item.name}</option>
									   </c:forEach>
									</select>
								</div>
								<div id="menuContent" class="menuContent">
									<ul id="categoryTree" class="ztree"></ul>
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">页面内容:</label>
								<div class="layui-input-inline">
									<div id="editor" style="width: 900px;"></div>
									<textarea name="content" style="display: none;">${commonContent.content}</textarea>
								</div>
							</div>
						</form>
					</c:when>
					<c:otherwise>
						<form class="layui-form" action="" id="form">
							<div class="layui-form-item name-module">
								<label class="layui-form-label"><span class="showName">页面标题</span>:<span
									class="need">*</span></label>
								<div class="layui-input-inline">
									<input type="text" name="title" class="layui-input"
										data-rule='{"required":true}'>
								</div>
								<div class="layui-form-mid shop-check-msg"></div>
							</div>
							<div class="layui-form-item category-module">
								<label class="layui-form-label"><span class="showName">所属分类</span>:<span
									class="need">*</span></label>
									<div class="layui-input-inline">
									<select name="categoryid">
									   <c:forEach items="${categoryList}" var="item"> 
									       <option value="${item.id}">${item.name}</option>
									   </c:forEach>
									</select>
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">页面内容:</label>
								<div class="layui-input-inline">
									<div id="editor" style="width: 900px;"></div>
									<textarea name="content" style="display: none;"></textarea>
								</div>
							</div>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>