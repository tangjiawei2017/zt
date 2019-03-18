<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>基本信息设置</title>
<style>
.content {
	width: 100%;
	padding-left: 20px;
	padding-top: 20px;
}
.layui-form-label{
   width:100px;
}
.logo-img {
	width: 120px;
	margin-top: 10px;
	border: 1px solid #ccc;
}
</style>
<script src="${ctx}/js/formValidation.js"></script>
<script>
$(function(){
		layui.use('upload',function() {
			var upload = layui.upload;
			//执行实例
			var uploadInst = upload.render({
						elem : '#logo-btn',
						url : '${ctx}/upload/commonUpload.action',
						accept : 'images',
						size : 120, 
						done : function(res) {
							if (res.code == 0) {
								$(".logo-img").remove();
								$div = '<section class="up-section logo-img"><span class="up-span"></span>'
									 + '<img class="close-upimg" src="images/del.png"><img class="up-img" src="'
		                             + res.data.src+'" data-src="'+ res.data.src+'" ></section>';
								$("#logo-btn").parent().append($div);
							}
						},
						error : function() {
						}
					});
				});
		$(".save").click(function() {
				var params = {};
				params.siteName = $("input[name='siteName']").val();
				params.logo = $(".logo-img .up-img").data("src");
				if (typeof params.logo == "undefined"
						|| params.logo == "") {
					layer.msg("logo图片不能为空!", {
						time : 1500,
						skin : 'success-class',
						anim : 5,
						isOutAnim : true,
					});
				} else {
					if ($("#form").formValidation("submit")) {
						$.ajax({
							url : "${ctx}/site/updateSite.action",
							data : params,
							type : "post",
							dataType : "json",
							success : function(data) {
								refresh(
										"${ctx}/site/config.action",
										2, data.result);
							}
					   });
					}
				}
		});
		//表单初始化校验
		$("#form").formValidation();
		$(".logo-div").delegate(".close-upimg", "click", function() {
			$(".logo-img").remove();
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="main-panel-head">
			<div class="tilte">基本信息设置</div>
		</div>
		<div class="content">
			<form id="form">
				<div class="layui-form-item">
					<label class="layui-form-label">网店名称:*</label>
					<div class="layui-input-inline">
						<input type="text" name="siteName" class="layui-input" style="width:350px;"
							value="${site.siteName}" data-rule='{"required":true}'>
					</div>
					<div class="layui-form-mid shop-check-msg"></div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">网店logo:*</label>
					<div class="layui-input-inline logo-div">
						<button type="button"
							class="layui-btn layui-btn-normal layui-btn-sm" id="logo-btn">图片上传</button>
						<c:if test="${site.logo!=null && site.logo!=''}">
							<section class="up-section logo-img">
								<span class="up-span"></span> <img class="close-upimg"
									src="images/del.png"><img class="up-img"
									src="${site.logo}" data-src="${site.logo}">
							</section>
						</c:if>
					</div>
				</div>
				<c:if test="${site.ctime!=null}">
				<div class="layui-form-item">
					<label class="layui-form-label">网站开通日期:</label>
					<div class="layui-input-inline">
					<input type="text" name="siteName" class="layui-input"
							value='<fmt:formatDate value="${site.ctime}" pattern="yyyy年MM月dd日 " />' disabled="disabled">
					</div>
				</div>
				</c:if>
				<div class="layui-form-item save-div">
					<div class="layui-input-block">
						<button class="layui-btn save" type="button">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>