<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>${siteName}后台管理系统</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/neat.css" />
<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />
<link rel="stylesheet" href="${ctx}/css/index.css" />
<script type="text/javascript" src="${ctx}/js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="${ctx}/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.ztree.all.min.js"></script>
<script>
	var layer;
	$(function() {
		layui.use([ 'element', 'layer' ], function() {
			var element = layui.element;
			layer = layui.layer;
			layer.config({
				extend : 'myskin/style.css', //加载新皮肤
				skin : 'layer-ext-myskin' //一旦设定，所有弹层风格都采用此主题。
			});
			//初始化数据
			var obj = $("dl.submenu_list:visible").eq(0).find(
					"dd:visible:first a").get(0);
			redirect(obj);
		});
		//左侧菜单展开出现三级标题
		$(".leftarea dt").css({
			"background-color" : "#3992d0"
		});
		$(".leftarea dd").hide();
		$(".leftarea .submenu_list:visible").eq(0).find("dd").show();
		$(".leftarea dt").click(function() {
			$(".leftarea dd").hide();
			$(this).parent().find('dd').removeClass("menu_chioce");
			$(".menu_chioce").slideUp();
			$(this).parent().find('dd').slideToggle();
			$(this).parent().find('dd').addClass("menu_chioce");
		});
	});

	function redirect(obj) {
		layer.closeAll();
		layer.config({
			extend : 'myskin/style.css', //加载新皮肤
			skin : 'layer-ext-myskin' //一旦设定，所有弹层风格都采用此主题。
		});
		var url = $(obj).data("url");
		if (typeof url != "undefined" && url != "") {
			$.ajax({
				url : url,
				type : "post",
				success : function(data) {
					$(".layui-body").html(data);
				}
			});
		}
	}
	//修改密码
	function modifyPwd() {
		layer.config({
			extend : 'myskin/style.css', //加载新皮肤
			skin : 'layer-ext-myskin' //一旦设定，所有弹层风格都采用此主题。
		});
		layer.open({
			type : 2,
			title : "修改密码",
			content : "${ctx}/admin/preModifyPassword.action",
			area : [ '600px', '350px' ],
			btn : [ '保存', '取消' ],
			yes : function(index, layero) {
				var childFrame = window.frames["layui-layer-iframe" + index]; //得到加载层页面window对象
				var result = childFrame.checkData(); //掉用其全局函数,校验并获取数据
				if (result.status) {
					layer.close(index); //关闭加载页面
					$.ajax({
						url : "${ctx}/admin/modifyPassword.action",
						data : result.data,
						type : "POST",
						success : function(result) {
							msg = result == "success" ? "修改密码成功!" : "修改密码失败!";
							layer.msg(msg, {
								time : 1500,
								skin : result == "success" ? 'success-class'
										: 'error-class',
								anim : result == "success" ? 5 : 6,
								isOutAnim : true,
							});
						}
					});
				}
			},
		});
	}

	//刷新数据，重新加载
	function refresh(url, type, result) {
		$.ajax({
			url : url,
			type : "POST",
			async : "true",
			cache : false,
			success : function(data) {
				if (typeof type !== "undefined" && type != "") {
					var msg = "";
					switch (type) {
					case 1:
						msg = result == "success" ? "添加成功!" : "添加失败";
						break;
					case 2:
						msg = result == "success" ? "修改成功!" : "修改失败";
						break;
					case 3:
						msg = result == "success" ? "删除成功!" : "删除失败";
						break;
					}
					layer.msg(msg, {
						time : 1500,
						skin : result == "success" ? 'success-class'
								: 'error-class',
						anim : result == "success" ? 5 : 6,
						isOutAnim : true,
					});
				}
				$(".layui-body").html(data);
			}
		});
	}
	function logout() {
		window.location.href = "${ctx}/logout.action";
	}
</script>
</head>
<body>
	<div class="top">
		<div class="banner">
			<p class="mainword">${site.siteName}</p>
			<p class="username">
				<span>${admin}</span>，欢迎您！
			</p>
			<p class="modifypwd" onclick="modifyPwd()">修改密码</p>
			<p class="logout" onclick="logout()">[退出]</p>
			<img src="images/banner1.png">
		</div>
	</div>

	<div class="con clearfix">
		<div class="leftarea">
		    <c:forEach items="${list}" var="item">
		       <c:if test="${item.value=='1'}">
					<dl class="submenu_list">
						<dt>
							${item.name}<img src="${ctx}/images/submenu_sum.png">
						</dt>
						<c:forEach items="${item.subMenuList}" var="subitem">
						   <c:if test="${(subitem.service!=null && subitem.value=='1')  || subitem.service==null}"> 
								<dd>
								<img src="${ctx}/images/submenu_right.png"> <a
									href="javaScript:void(0)" onclick="redirect(this)"
									data-url="${ctx}${subitem.url}">${subitem.name}</a>
							    </dd>
						    </c:if>
						</c:forEach>
					</dl>
				</c:if>
			</c:forEach>
		</div>
		<div class="layui-body">
			<%-- <jsp:include page="welcome2.jsp"></jsp:include> --%>
		</div>
	</div>
</body>
</html>