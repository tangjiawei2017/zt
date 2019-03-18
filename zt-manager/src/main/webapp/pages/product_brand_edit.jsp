<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>商品品牌编辑</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<style>
body{
  height:250px;
}
.logo-img {
	width: 120px;
	margin-top: 10px;
	border: 1px solid #ccc;
}
.close-upimg{
    width:15px;
}
.layui-field-box{
   margin-top:30px;
}
</style>
<script src="${ctx}/js/jquery-2.1.0.js"></script>
<script src="${ctx}/layui/layui.js"></script>
<script src="${ctx}/js/formValidation.js"></script>
<script>
	$(function() {
		layui.use([ 'form', 'upload' ],
			function() {
				var form = layui.form;
				form.render(); //更新渲染全部组件
				var upload = layui.upload;
				//执行实例
				var uploadInst = upload
						.render({
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
	});
	//校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : false,
			"data" : {}
		};
		//表单提交校验
		result.status = $("#form").formValidation("submit");
		var img=$(".up-img").data("src");
		if(typeof img !='undefined'){
			result.data  = $("#form").serialize()+"&image="+img;
		}else{
			result.data = $("#form").serialize();
		}
		return result;
	}
</script>
</head>
<body>
	<div class="container">
		<div class="layui-field-box">
			<c:choose>
				<c:when test="${brand!=null}">
					<form class="layui-form" action="" id="form" method="post">
						<div class="layui-form-item">
							<label class="layui-form-label">品牌名称:*</label> <input name="id"
								type="hidden" value="${brand.id}">
							<div class="layui-input-inline">
								<input type="text" name="name" class="layui-input"
									value="${brand.name}"
									data-rule='{"ajax":"${ctx}/product/checkbrandExist.action?id=${brand.id}","errorMsg":"该分类名称已存在!"}'>
							</div>
							<div class="layui-form-mid shop-check-msg"></div>
						</div>
						<div class="layui-form-item img-div">
							<label class="layui-form-label">图片:</label>
							<div class="layui-input-inline image">
								<button type="button" class="layui-btn layui-btn-normal"
									id="logo">选择</button>
								<c:if test="${brand.image!=null && brand.image!=''}">
									<section class="up-section logo-img">
										<span class="up-span"></span> <img class="close-upimg"
											src="${ctx}/images/del.png"> <img class="up-img"
											src="${brand.image}" data-src="${brand.image}" />
									</section>
								</c:if>
							</div>
						</div>
					</form>
				</c:when>
				<c:otherwise>
					<form class="layui-form" action="" id="form">
						<div class="layui-form-item">
							<label class="layui-form-label">品牌名称:*</label>
							<div class="layui-input-inline">
								<input type="text" name="name" lay-verify="required"
									class="layui-input"
									data-rule='{"ajax":"${ctx}/product/checkbrandExist.action","errorMsg":"该分类名称已存在!"}'>
							</div>
							<div class="layui-form-mid shop-check-msg"></div>
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