 <%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>首页轮播图</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<style>
.logo-img {
	width: 400px;
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
					size : 1*1024,
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
			var uploadInst2 = upload.render({
				elem : '#logo2',
				url : '${ctx}/upload/commonUpload.action',
				accept : 'images',
				size : 1*1024,
				done : function(res) {
					if (res.code == 0) {
						$(".logo-img2").remove();
						$div = '<section class="up-section logo-img2"><span class="up-span"></span>'
								+ '<img class="close-upimg" src="${ctx}/images/del.png"><img class="up-img" src="'
                        + res.data.src+'" data-src="'+ res.data.src+'" ></section>';
						$("#logo2").parent().append($div);
					}
				},
				error : function() {
				}
		});
		});
		$(".img-div").delegate(".close-upimg", "click", function() {
			$(".logo-img").remove();
		});
		$(".img-div2").delegate(".close-upimg", "click", function() {
			$(".logo-img2").remove();
		});
		//表单初始化校验
		$("#form").formValidation();
	});
	//校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : true,
			"data" : {}
		};
		//表单提交校验
		result.status = $("#form").formValidation("submit");
		result.data = $("#form").serialize();
		var image = $.trim($(".img-div .up-section .up-img").data("src"));
		if (typeof image != "undefined" && image != "") {
			result.data = result.data + "&path=" + image;
		}else{
			if(result.status){
				result.status=false;
				layer.msg("图片不能为空!");
			}
		}
		var mimage = $.trim($(".img-div2 .up-section .up-img").data("src"));
		if (typeof mimage != "undefined" && mimage != "") {
			result.data = result.data + "&mpath=" + mimage;
		}
		return result;
	}
</script>
</head>
<body style="height: 360px;">
	<div class="container">
		<div class="layui-field-box shop-category-box">
			<c:choose>
				<c:when test="${sitePic!=null}">
					<form class="layui-form" action="" id="form">
						<input type="hidden" name="id" value="${sitePic.id}" />
						<div class="layui-form-item">
							<label class="layui-form-label">序号:*</label>
							<div class="layui-input-inline">
								<input type="text" name="number" class="layui-input"
									value="${sitePic.number}" data-rule='{"required":true,"number":true}'>
							</div>
							<div class="layui-form-mid shop-check-msg"></div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">链接:</label>
							<div class="layui-input-inline">
								<input type="text" name="link" class="layui-input" style="width:300px;"
									value="${sitePic.link}">
							</div>
						</div>
						<div class="layui-form-item img-div">
							<label class="layui-form-label">图片:</label>
							<div class="layui-input-inline image">
								<button type="button" class="layui-btn layui-btn-normal"
									id="logo">选择</button>
								<c:if test="${sitePic.path!=null}">
									<section class="up-section logo-img">
										<span class="up-span"></span> <img class="close-upimg"
											src="${ctx}/images/del.png"> <img class="up-img"
											src="${sitePic.path}" data-src="${sitePic.path}" />
									</section>
								</c:if>
							</div>
						</div>
						<div class="layui-form-item img-div2">
							<label class="layui-form-label">手机图片:</label>
							<div class="layui-input-inline image">
								<button type="button" class="layui-btn layui-btn-normal"
									id="logo2">选择</button>
								<c:if test="${sitePic.mpath!=null}">
									<section class="up-section logo-img2">
										<span class="up-span"></span> <img class="close-upimg"
											src="${ctx}/images/del.png"> <img class="up-img"
											src="${sitePic.mpath}" data-src="${sitePic.mpath}" />
									</section>
								</c:if>
							</div>
						</div>
					</form>
				</c:when>
				<c:otherwise>
					<form class="layui-form" action="" id="form">
						<div class="layui-form-item">
							<label class="layui-form-label">序号:*</label>
							<div class="layui-input-inline">
								<input type="text" name="number" class="layui-input"
									data-rule='{"required":true,"number":true}'>
							</div>
							<div class="layui-form-mid shop-check-msg"></div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">链接:</label>
							<div class="layui-input-inline">
								<input type="text" name="link" class="layui-input" style="width:300px;"
									value="">
							</div>
						</div>
						<div class="layui-form-item img-div">
							<label class="layui-form-label">图片:</label>
							<div class="layui-input-inline image">
								<button type="button" class="layui-btn layui-btn-normal"
									id="logo">选择</button>
							</div>
						</div>
						<div class="layui-form-item img-div2">
							<label class="layui-form-label">手机图片:</label>
							<div class="layui-input-inline image">
								<button type="button" class="layui-btn layui-btn-normal"
									id="logo2">选择</button>
							</div>
						</div>
					</form>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>