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
<title>商品评价详情</title>
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
    var layer;
	$(function() {
		layui.use([ 'element', 'layer' ], function() {
			var element = layui.element;
			layer = layui.layer;
		});
	});
	//提交表单时,校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : false,
			"data" : {}
		};
		if($("textarea[name='result']").val()!=''){
			result.data.result=$("textarea[name='result']").val();
			result.status=true;
		}else{
			  layer.msg("处理记录不能为空!");
			  result.status=false;
		}
		return result;
	}
</script>
</head>
<body>
	<div class="ces-infor-order">
		<div class="order-infor-left">
			<c:forEach items="${list}" var="detail">
				<div class="order-line">
					<span class="left">${detail.name}:</span> <span class="right">${detail.value }</span>
				</div>
			</c:forEach>
			<div class="order-line">
				<span class="left">处理状态:</span> <span class="right"> <c:choose>
						<c:when test="${customFormContent.status==0}">
				                      未处理
				       </c:when>
						<c:when test="${customFormContent.status==1}">
				                    已处理
				        </c:when>
					</c:choose>
				</span>
			</div>
			<div class="order-line">
				<span class="left">提交时间:</span> <span class="right"> 
				   <fmt:formatDate value="${customFormContent.ctime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</span>
			</div>
			<c:if test="${customFormContent.result!=null}">
				<div class="order-line">
					<span class="left">处理记录:</span> <span class="right"
						style="color: #999">${customFormContent.result}</span>
				</div>
			</c:if>
			<c:if test="${customFormContent.result==null}">
				<div class="order-line">
					<span class="left">处理记录:</span> <span class="right"><textarea
							name="result" class="layui-textarea"></textarea></span>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>