<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<script src="${ctx}/js/jquery-2.1.0.js"></script>
<script src="${ctx}/layui/layui.js"></script>
<title>留言详情</title>
<style>
.order-infor-left {
	padding: 5px;
	float: left;
}

.order-infor-left .title-txt {
	padding-left: 10px;
	display: inline;
	clear: none;
	float: left;
}

.order-infor-left .title-txt div {
	height: 80px;
	line-height: 80px;
	font-size: 18px;
}

.order-infor-left .order-line {
	width: 100%;
	height: 30px;
	line-height: 30px;
	margin: 0px;
	padding: 0px;
	clear: both;
	margin: 0px;
}

.order-infor-left .left {
	float: left;
	text-align: right;
	width: 125px;
}

.order-infor-left .order-line img {
	width: 15px;
	margin-left: 8px;
}

.order-infor-left .right {
	word-break: break-all;
	word-wrap: break-word;
	margin-left: 10px;
	float: left;
}

.layui-textarea {
	width: 300px;
	height: 100px;
	float: left;
}

.answer-btn {
	float: left;
	margin-left: 20px;
}

.review-content {
	line-height: 30px;
}

.review-content .rig {
	padding-left: 130px;
	padding-top: 5px;
	line-height: 20px;
}

.answer {
	clear: both;
}
</style>
<script>
	$(function() {
		var layer;
		layui.use([ 'element', 'layer' ], function() {
			var element = layui.element;
			layer = layui.layer;
		});
	});
	function checkData(){
		var result = {
				"status" : true,
				"data" : {}
			};
		result.data.id="${message.id}";
		result.data.status=1;
		result.data.answer=$.trim($("textarea[name='answer']").val());
		return result;
	}
</script>
</head>
<body>
	<div class="ces-infor-order">
		<div class="order-infor-left">
			<div class="order-line">
				<span class="left">留言人:</span> <span class="right">${message.account }</span>
			</div>
			<div class="order-line">
				<span class="left">手机:</span> <span class="right">${message.phone }</span>
			</div>
			<div class="order-line">
				<span class="left">邮箱:</span> <span class="right">${message.email }</span>
			</div>
			<div class="review-content">
				<div class="left">留言内容:</div>
				<div class="rig" style="color: #999">${message.content}</div>
			</div>
			<c:if test="type=='detail'">
				<div class="order-line">
					<span class="left">处理状态:</span> <span class="right"> <c:choose>
						<c:when test="${message.status==0}">
					                      未处理
					   </c:when>
						<c:when test="${message.status==1}">
					                    已处理
					   </c:when>
					</c:choose>
					</span>
				</div>
			</c:if>
			<c:if test="${message.answer!=null}">
				<div class="review-content answer">
					<div class="left">回复内容:</div>
					<div class="rig" style="color: #999">${message.answer}</div>
				</div>
			</c:if>
			<c:if test="${message.status==0  && type=='edit'}">
				<div class="add-answer-div" style="margin-top:20px;">
					<form class="layui-form" action="" id="form">
						<div class="layui-form-item layui-form-text">
							<label class="left">回复:</label>
							<textarea name="answer" class="layui-textarea" style="width:500px;height:150px;margin-left:5px;"></textarea>
						</div>
					</form>
					</div>
			</c:if>
		</div>
	</div>
</body>
</html>