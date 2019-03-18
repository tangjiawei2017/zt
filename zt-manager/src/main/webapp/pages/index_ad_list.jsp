<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>首页广告</title>
<style>
.content {
	width: 100%;
	padding-left: 20px;
	padding-top: 20px;
}
.wapper{
  width:100%;
  background: #ECECEC;
}
.gallery_wapper {
	width: 100%;
	background-color: #CFCFCF;
	margin-top:20px;
	overflow: hidden;
}

.gallery_wapper .gallery .gallery_big_pic img {
	max-width: 940px;
    max-height: 260px;
    margin:0 auto;
}
.gallery_text{
  padding-top:20px;
}
.gallery_small_pic{
   width:98%;
   background: #F5F5F5;
   margin:0 auto;
}
.gallery_small_pic ul{
   width:100%;
   text-align: center;
}
.gallery_small_pic li{
   display:inline-block;
   width:140px;
   height:106px;
   line-height:100px;
   text-align: center;
   background:#fff;
   border:1px solid #ccc;
}
.gallery_small_pic li:first a{
   background:#d4e6f5 !important;
}
.gallery_small_pic img{
  width:126px;
  height:90px;
  text-align:left;
  margin-top:5px;
}
.bg-color-click{
     background-color: #d4e6f5 !important;
}
</style>
<script>
var upload;
$(function(){
		layui.use('upload',function() {
			upload = layui.upload;
			upload.render({
				elem : '.editBtn',
				url : '${ctx}/upload/commonUpload.action',
				accept : 'images',
				size : 1024, 
				done : function(res) {
					if (res.code == 0) {
						var src=res.data.src;
						//获取当前触发上传的元素，一般用于 elem 绑定 class 的情况，注意：此乃 layui 2.1.0 新增
					   var index=$(this.item).data("index");
					   $(".gallery_big_pic img").eq(index).attr("src",src);
					   $(".gallery_small_pic ul li").eq(index).find("img").attr("src",src);
					}
				},
				error : function() {
			}
		});
		$(".small-pic").click(function(){
			$(this).parent().addClass("bg-color-click").siblings().removeClass("bg-color-click");
			var index =$(this).data("index");
			$(".gallery_big_pic img").eq(index).css("display","block").siblings().hide();
			$(".gallery_text").eq(index).show().siblings().hide();
		});
		$("#addBtn").click(function(){
			layer.open({
				type : 2,
				title:'新增广告',
				content : "${ctx}/ad/preEdit.action",
				area : [ '680px', '500px' ],
				scrollbar :false,
				btn : [ '保存', '取消' ],
				yes : function(index, layero) {
					var childFrame = window.frames["layui-layer-iframe" + index];    //得到加载层页面window对象
					var result=childFrame.checkData(); //掉用其全局函数,校验并获取数据
					if(result.status){
						layer.close(index);   //关闭加载页面
						$.ajax({
							url:"${ctx}/ad/addSitePic.action",
							data:result.data,
							type:"POST",
							success:function(result){
								refresh("${ctx}/ad/list.action",1,result);
							}
						});
					}
				},
			});
		});
		$(".saveBtn").click(function(){
			var sitePicArray=[];
			$(".gallery_text").each(function(index){
				var id=$(this).find("input[name='id']").val();
				var number = $.trim($(this).find("input[name='number']").val());
				var link = $.trim($(this).find("input[name='link']").val());
				var path = $(".gallery_big_pic img").eq(index).attr("src");
				sitePicArray.push(createSitePicAttrObj(id,path,link,number));
			});
			$.ajax({
				url:"${ctx}/ad/saveSitePic.action",
				data:{"data":JSON.stringify(sitePicArray)},
				type:"POST",
				success:function(result){
					refresh("${ctx}/ad/list.action",2,result);
				}
			});
		});
	});
});
	//创建基本属性对象
	function createSitePicAttrObj(id, path, link, number) {
		var obj = new Object();
		obj.id = id;
		obj.path = path;
		obj.link = link;
		obj.number = number;
		return obj;
	}

	function removeSitePic(id){
		$.ajax({
			url:"${ctx}/ad/delSitePic.action?id="+id,
			type:"POST",
			success:function(result){
				refresh("${ctx}/ad/list.action",3,result);
			}
		});
	}
</script>
</head>
<body>
	<div class="container">
		<div class="main-panel-head">
			<div class="tilte">首页幻灯广告</div>
		</div>
		<div class="content">
			<div>
				<button type="button" class="layui-btn layui-btn-normal" id="addBtn">新增广告</button>
			</div>
			<div class="wapper">
			<div class="gallery_wapper">
				<div class="gallery">
					<div class="gallery_big_pic">
						<c:forEach items="${list}" var="gallery" varStatus="status">
							<c:choose>
								<c:when test="${status.first}">
									<img alt="" src="${gallery.path}" style="display: block">
								</c:when>
								<c:otherwise>
									<img alt="" src="${gallery.path}" style="display: none">
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>
			</div>
			<div>
			<c:forEach items="${list}" var="gallery" varStatus="status">
				<div class="gallery_text"    <c:if test="${!status.first }">style="display:none;"</c:if>>
				   <div class="layui-form-item">
							<label class="layui-form-label">序号:</label>
							<div class="layui-input-inline">
							    <input type="hidden" name="id" value="${gallery.id}"/>
								<input type="text" name="number" class="layui-input" value="${gallery.number}">
							</div>
							<div class="layui-form-mid layui-word-aux">序号越小,幻灯片显示图片越靠前</div>
					</div>
			    	<div class="layui-form-item">
							<label class="layui-form-label">链接:</label>
							<div class="layui-input-inline" style="width:300px;">
								<input type="text" name="link" class="layui-input" style="width:300px;" value="${gallery.link}">
							</div>
							<div class="layui-form-mid">
							     <button type="button" class="layui-btn layui-btn-small editBtn"   data-index="${status.index}"><i class="layui-icon">&#xe67c;</i>更换图片</button>
					             <button type="button" class="layui-btn layui-btn-small" id="removeBtn" onclick="removeSitePic('${gallery.id}')">删除</button>
							</div>
					</div>
				</div>
			</c:forEach>
			</div>
			<div class="gallery_small_pic">
			       <ul>
			       <c:forEach items="${list}" var="gallery" varStatus="status">
						<li <c:if test="${status.first}">class="bg-color-click"</c:if>><a class="small-pic   href="javascript:;" data-index="${status.index}"><img
							src="${gallery.path}"></a></li>
					</c:forEach>
			       </ul>
	        </div>
		 </div>
		 <button type="button" class="layui-btn layui-btn-normal saveBtn" style="margin:10px;">保存</button>
		</div>
	</div>
</body>