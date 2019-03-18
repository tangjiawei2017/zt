<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="common.jsp"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.shop.service.ISiteService"%>
<%@page import="com.shop.service.impl.SiteServiceImpl"%>
<%@page import="com.shop.model.SiteDO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>电商管理系统</title>
<link href="${ctx}/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-2.1.0.js"></script>
<script>
	$(document).ready(function() {
	    document.getElementsByTagName('body')[0].style.height = window.innerHeight
								+ 'px';
	    $("#verifyCode").on({
			mouseover : function() {
				$(this).css("cursor", "pointer");
			},
			click : function() {
				var src = $(this).attr("src");
				var random = Math.random();
				if (src != null && src.indexOf("?") > 0) {
					src = src.substring(0, src.indexOf("?") + 1) + random;
				} else {
					src = src + "?" + random;
				}
				$(this).attr("src", src);
			}
		});
     });
</script>
</head>
<!-- 授权失败3次,显示验证码 -->
<c:if test="${loginFailureCount>=3}">
	<script>
		window.onload = function() {
			$(".login_verifyCode").css("display", "block");
			var contextPath = window.location.pathname.split("/")[1];
			$("#verifyCode").attr("src",
					"/" + contextPath + "/servlet/verifyCode");
		}
	</script>
</c:if>
<body>
	<div class="login_box">
		<div class="login_l_img">
			<img src="${ctx}/images/login-img.png" />
		</div>
		<div class="login">
			<div class="login_logo">
				<a href="#"><img src="${ctx}/images/login_logo.png" /></a>
			</div>
			<div class="login_name">
				<p>后台管理系统</p>
			</div>
			<form action="/manager/login.action" method="post" id="loginForm">
				<c:choose>
					<c:when test="${param.login_error==1}">
						<div style="color: #f00;">用户名或密码错误!</div>
					</c:when>
					<c:when test="${param.login_error==2}">
						<div style="color: #f00;">验证码错误!</div>
					</c:when>
					<c:otherwise>
						<div style="visibility: hidden;">请输入数据</div>
					</c:otherwise>
				</c:choose>
				<input name="username" type="text" value="" placeholder="用户名">
				<input name="pwd" type="password" id="password" value=""
					placeholder="密码" />
				<div class="login_verifyCode" style="display: none;">
					<input name="veryCode" id="veryCode" class="input-text" type="text"
						placeholder="请输入验证码" style="width:50%;"> <img id="verifyCode"
						src="javaScript:void(0)" />
				</div>
				<input value="登录" style="width: 100%;" type="submit" name="login"
					id="login">
			</form>
		</div>
	</div>
</body>
</html>
<script>
<%ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			ISiteService siteService = (ISiteService) ctx.getBean(SiteServiceImpl.class);
			SiteDO site = siteService.getSiteDO();
			if (site != null) {%>
   $(document).attr("title",'<%=site.getSiteName()%>' + '后台管理系统');
<%}%>
	
</script>
