<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>添加会员</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/customerDetail.css">
<script src="${ctx}/js/jquery-2.1.0.js"></script>
<script src="${ctx}/layui/layui.js"></script>
<script>
	$(function() {
		layui.use('element', function() {
			var element = layui.element;
		});
	});
</script>
<body style="height:430px;">
	<div class="container">
		<div class="layui-tab layui-tab-brief">
			<ul class="layui-tab-title">
				<li class="layui-this">客户信息</li>
				<li>购买记录</li>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show">
					<div class="ces-infor-customer">
						<div class="customer-infor-left">
							<div class="head">
								<div class="title-img">
									<c:if test="${customer.imagePath==null}">
										<img src="${ctx}/images/customer_without_image.png">
									</c:if>
									<c:if test="${customer.imagePath!=null}">
										<img src="${customer.imagePath}">
									</c:if>
								</div>
								<div class="title-txt">
									<div>${customer.account}</div>
								</div>
							</div>
							<div class="customer-line">
								<span class="left">客户姓名:</span> <span class="right">${customer.name }</span>
							</div>
							<div class="customer-line">
								<span class="left">性别:</span> <span class="right"><c:choose>
										<c:when test="${customer.sex==0}">
											    保密
											</c:when>
										<c:when test="${customer.sex==1}">
											    女
											</c:when>
										<c:when test="${customer.sex==2}">
											   男
											</c:when>
									</c:choose></span>
							</div>
							<div class="customer-line">
								<span class="left">手机号码:</span> <span class="right">${customer.cellphone }</span>
							</div>
							<div class="customer-line">
								<span class="left">邮箱:</span> <span class="right">${customer.email }</span>
							</div>
							<div class="customer-line">
								<span class="left">所在地区:</span> <span class="right">${customer.address}</span>
							</div>
							<div class="customer-line">
								<span class="left">详细地址:</span> <span class="right">${customer.detailAdrr}</span>
							</div>
							<div class="customer-line">
								<span class="left">累计消费次数:</span> <span class="right">${customer.consumeCount}</span>
							</div>
						</div>
						<div class="customer-infor-right">
							<ul class="head">
								<li class="frequency">
									<div class="text">累计消费次数</div>
									<div class="number">${customer.consumeCount}</div>
								</li>
								<li class="money">
									<div class="text">累计消费金额</div>
									<div class="number">￥${customer.consumeTotal}</div>
								</li>
							</ul>
							<ul class="infor">
								<li class="status"><em>会员状态：</em> <c:if
										test="${customer.status==1}">
										启用
									</c:if> <c:if test="${customer.status==0}">
										停用
									</c:if></li>
								<li class="source"><em>客户来源：</em> <span><c:if
											test="${customer.source==0}">前台注册</c:if> <c:if
											test="${customer.source==1}">后台添加</c:if></span></li>
								<li class="time"><em>注册时间：</em><span><fmt:formatDate
											value="${customer.regtime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></li>
								<li class="time"><em>上次登录：</em><span><fmt:formatDate
											value="${customer.logintime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></li>
								<li class="count"><em>登录次数：</em><span>${customer.loginCount}</span></li>
								<li class="total"><em>评价次数：</em><span>${customer.reviewCount}</span></li>
								<li class="total"><em>微信是否绑定：</em><span><c:if test="${customer.wxId!=null}">是</c:if><c:if test="${customer.wxId==null}">否</c:if></span></li>
								<li class="total"><em>累计消费金额：</em><span>￥${customer.consumeTotal}</span></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="layui-tab-item">
				    <input type="hidden" name="payerId" value="${customer.id}"/>
					<jsp:include page="customer_order_list.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</body>
</html>