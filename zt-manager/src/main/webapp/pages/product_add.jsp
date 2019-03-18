<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="${ctx}/js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="${ctx}/layui/layui.js"></script>
<script src="${ctx}/js/formValidation.js"></script>
<script src="${ctx}/wangEditor/wangEditor.min.js"></script>
<meta charset="utf-8">
<title>商品添加</title>
<style>
.shop-select {
   cursor: pointer;
}
.up-section{
  margin-top:10px;
  width:120px;
}
.close-upimg{
  width:15px;
}
.layui-form-label{
  width:100px; 
}
.ces-input-select {
	background: url("${ctx}/images/down-btn.png");
	position: absolute;
    right: 10px;
    top: 50%;
    margin-top: -3px;
    cursor: pointer;
    width:13px;
    height:6px;
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
  h1{ 
  font-size: 2em; 
  margin: .67em 0 
  } 
  h2 { 
  font-size: 1.5em; 
  margin: .75em 0 
  } 
  h3{ 
  font-size: 1.17em; 
  margin: .83em 0 
  }
</style>
<script>
    var images=[];
	var index=0,layedit=null;
	var table;
	var specount=0;
	var editor;
	var form;
	var setting = {
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true,
					idKey : 'id',
					pIdKey : 'parentid',
					rootPId : 0
				}
			},
			callback: {
				onClick: onClick
			}
	};
	function onClick(e, treeId, treeNode) {
		var nodes = $.fn.zTree.getZTreeObj("categoryTree").getSelectedNodes();
		if(nodes[0].isParent){
		    layer.msg("请选择子分类",{time:1500});
		}else{
			$("input[name='category_id']").val(nodes[0].name);
			$("input[name='category_id']").data("value",nodes[0].id);
			$("#menuContent").slideUp("fast");
			loadProductTemplate();
		}
	}
	function showMenu() {
		if($("#menuContent").is(":visible")){
			$("#menuContent").slideUp("fast");
		}else{
			var $input = $("input[name='category_id']");
			var $offset = $input.offset();
			$("#menuContent").css({ 
				"left":$offset.left + "px",
				"top": $offset.top + $input.outerHeight() + "px"
			}).slideDown("fast");
		}
	}
    //数组删除指定值
    function removeByValue(arr, val) {
    	  for(var i=0; i<arr.length; i++) {
    	    if(arr[i] == val) {
    	      arr.splice(i, 1);
    	      break;
    	    }
    	  }
    	  console.log("remove====>"+arr)
    }
  
	$(function() {
		layui.use('form', function() {
			form = layui.form;
			form.render(); //更新渲染全部组件
		});
		layui.use('upload',function() {
				upload = layui.upload;
				layui.use('upload',function() {
			            upload = layui.upload;
						//多文件列表示例
						var demoListView = $('#demoList');
						var files = null;
						upload.render({
						elem : '#testList',
						url : '${ctx}/upload/imageUpload.action',
						multiple : true,
						size : 500,
						auto : true,
						before : function(obj) {
							files = obj.pushFile(); //将每次选择的文件追加到文件队列
							//读取本地文件
							obj.preview(function(index,file,result) {
								images.push(files[index].name);
								var div = $([
										'<div class="img-div" id="upload-'+ index +'">',
										'<img src="'+ result +'"/>',
										'<i class="product-img-delete"></i>',
										'</div>' ]
										.join(''));
								//删除
								div.find('.product-img-delete').on('click',function() {
										removeByValue(images,files[index].name);
										delete files[index]; //删除对应的文件
										div.remove();
									});
								        demoListView.append(div);
									});
									},
									done : function(res, index,upload) {
										if (res.code == 0) { 
											//上传成功   多图片上传会调用多次请求,所以上传图片不能在此处处理
											return;
										}
									},
									error : function(index,upload) {
									}
								});
						//执行实例(缩略图)
						var uploadInst = upload.render({
							elem : '#logo',
							url : '${ctx}/upload/commonUpload.action',
							accept:'images',
							size: 120,
							done : function(res) {
								if (res.code == 0) {
									$(".logo-img").remove();
									//上传成功,存储上传图片路径
									$("input[name='image']").val(res.data.src);
									$div = '<section class="up-section logo-img"><span class="up-span"></span>'
									      +'<img class="close-upimg" src="${ctx}/images/del.png"><img class="up-img" src="'
									      + res.data.src+'" data-src="'+ res.data.src+'" ></section>';
									$("#logo").parent().append($div);
								}
							},
							error : function() {
							}
					     });
						$(".img-div").delegate(".close-upimg","click",function(){
							$(".logo-img").remove();
							$("input[name='image']").val('');
						});
					});
			});
		
			$(".img-div").delegate(".close-upimg","click",function(){
				$(".logo-img").remove(); 
			});
			//创建富文本编辑器
			var E = window.wangEditor;
			editor = new E('#editor');
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
		    //表单初始化校验
		    $("#form").formValidation();
		    //加载商品分类
			$.ajax({
				url:"${ctx}/product/productCateogryListAjax.action",
	            type:"post",
	            dataType:"json",
	            success:function(zNodes){
	                $.fn.zTree.init($("#categoryTree"), setting, zNodes);
	                //ZTree 初始化分类 选择节点(选中初始化节点)
	                var id=$.trim($("input[name='category_id'").data("value"));
	                var treeObj = $.fn.zTree.getZTreeObj("categoryTree"); //ztree树的ID
	                var node = treeObj.getNodeByParam("id", id);//根据ID找到该节点
	    			treeObj.selectNode(node);     //根据该节点选中
	            }
			});
			loadProductTemplate();
			//商品关联
			$(".add-relate-btn").click(function(){
				parent.layer.open({
					type : 2,
					title:'选择商品',
					content : "${ctx}/pages/product_relate.jsp",
					area : [ '800px', '540px' ],
					btn : [ '保存', '取消' ],
					yes : function(index, layero) {
						var childFrame = parent.window.frames["layui-layer-iframe" + index];    //得到加载层页面window对象
						var result=childFrame.checkData(); //掉用其全局函数,校验并获取数据
						if(result.status){
							parent.layer.close(index);   //关闭加载页面
							//先清空数据
							$(".product-relate-div .product-relate-table tbody").html("");
							for(var i=0;i<result.data.length;i++){
								var product=result.data[i];
								var $tr  = '<tr><td><input type="hidden" name="id" value="'+product.id+'">'
								         + '<span>'+product.name+'</span></td><td>'+product.code+'</td><td>'
								         + '<a href="javaScript:void(0)" style="color:blue" onclick="removeRealteProduct(this)">删除</a></td></tr>';
								$(".product-relate-div .product-relate-table tbody").append($tr);
							}
						}
					},
				});			
			});
	});
	
	//删除商品当前商品关联
	function removeRealteProduct(obj){
		$(obj).parent().parent().remove();
	}
	//根据商品类型加载关联商品模板
	function loadProductTemplate() {
		var category_id = $.trim($("input[name='category_id']").data("value"));  
		$.ajax({
			url : "${ctx}/product/selectTemplateByCategory.action?id="
					+ category_id,
			type : "post",
			dataType : "json",
			success : function(data) {
				var basicattr=JSON.parse(data.basicattr);
				for (var i = 0; i < basicattr.length; i++) {
					var $obj = null;
					if (basicattr[i].code == "brandid") {
						$obj = $("select[name='brandid']");
					} else if(basicattr[i].code == "unit"){
						$obj = $("select[name='unit']");
					}else {
						$obj = $("input[name='" + basicattr[i].code + "']");
						if(basicattr[i].code =="price" || basicattr[i].code =="marketprice"){
							$obj.parent().next().html(basicattr[i].unit);
						}
					}
					$obj.parent().prev().children(".showName").html(
							basicattr[i].showName);
					if(basicattr[i].code != "code" && basicattr[i].code!='name'){
						if(basicattr[i].isNeed){
							$obj.parent().prev().children(".need").html("*");
							var options=$obj.data("rule");
							if(typeof options=="object"){
								options.required=true;
								$obj.data("rule",options);  //这样修改只是修改缓存中值,并不修改dom节点
								$obj.attr("data-rule",JSON.stringify(options));
							}
						}else{
							$obj.parent().prev().children(".need").html("");
							var options=$obj.data("rule");
		                    if (typeof options == "object") {
								//删除属性
								delete options.required;
								$obj.data("rule", options);  //这样修改只是修改缓存中值,并不修改dom节点
								$obj.attr("data-rule",JSON.stringify(options));
							}
		                   var obj= $("input[name='stock']").data("rule");
						}
						if (basicattr[i].isShow) {
							$obj.parent().parent().show();
						} else {
							$obj.parent().parent().hide();
						}
					}
				}
				var extendAttr=JSON.parse(data.extendattr);
				//没有扩展属性,内容清空
				$(".extendattr").html('');
				$(".extendattr").append('<legend>商品属性:</legend>');
				for(var i=0;i<extendAttr.length;i++){
					if (extendAttr[i].isShow) {
						var $needspan='';
						if(extendAttr[i].isNeed){
							$needspan='<span class="extend-need">*</span>';
						}
						var $div = '<div class="layui-form-item">'
								+ '<label class="layui-form-label">'
								+ extendAttr[i].name + ':'+$needspan+'</label>'
								+ '<div class="layui-input-inline">';
						var $element='';
						if (extendAttr[i].type == 1) {
							$element = '<input type="text" name="'+ extendAttr[i].code +'" class="layui-input">';
						} else if (extendAttr[i].type == 2) {
                               $element = '<select name="'+ extendAttr[i].code +'">';
                               var array=extendAttr[i].value.split(";");
                               for(var j=0;j<array.length;j++){
                                   $element = $element + '<option value="'+array[j]+'">'+array[j]+'</option>';
                               }
                               $element = $element + "</select>";
						} else if (extendAttr[i].type == 3) {
                               var array=extendAttr[i].value.split(";");
                               for(var j=0;j<array.length;j++){
                                   $element = $element +'<input name="'+ extendAttr[i].code +'" lay-skin="primary" value="'+array[j]+'" title="'+array[j]+'"type="checkbox" >';
                               }
                               $element = $element + "</select>"; 
						}
						$(".extendattr").append($div + $element+"</div></div>");
					}
				}
				form.render();
			}
		})
	}
	//提交表单时,校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : false,
			"data" : {}
		};
		//表单提交校验
		result.status = $("#form").formValidation("submit");
		//表单校验失败,直接返回错误
		if(!result.status){
			return result;
		}
		//类型处理
		$("input[name='categoryid']").val($("input[name='category_id']").data("value"));
		//商品缩略图不能为空
		var image=$("input[name='image']").val();
		if(image==''){
			layer.msg("商品缩略图不能为空!");
			result.status = false;
			return result;
		}
		//图片回调值处理
		if (images != null && images instanceof Array && images.length > 0) {
			$("input[name='images']").val(images.join(","));
		} 
		//商品扩展属性 必填项判断
		$(".extendattr .layui-form-item").each(function(){
			if ($(this).find(".extend-need").length > 0) {
				var $child = $(this).find(".layui-input-inline").children();
				if ($child.is("input[type='text']") && $child.val() == '') {
					layer.msg("商品属性不能为空!");
					result.status = false;
					return result;
				} else if ($child.is("input[type='checkbox']")) {
                    var count=0;
                    $child.each(function(){
                    	if($(this).is(":checked")){
                    		count++;
                    	}
                    });
                   if(count==0){
                	   layer.msg("商品属性不能为空!");
   					   result.status = false;
   					   return result;
                   }
				}
			}
		});
		//关联商品处理
		var relateArray=[];
		$(".product-relate-div .product-relate-table tbody tr").each(function(){
			relateArray.push($(this).find("input[name='id']").val());
		});
		var stock = $.trim($("input[name='stock']").val());
		//商品描述赋值
		$("textarea[name='description']").val(editor.txt.html());
		//手动将 图片路径以及规格值传递过去
		result.data = $("#form").serialize() + "&stock=" + stock+"&relateIds="+relateArray.join(",");
		return result;
	}
</script>
</head>
<body>
	<div class="container">
		<div class="content-r">
			<div class="layui-field-box">
				<form class="layui-form" action="" id="form">
					<fieldset style="min-height: 250px; border: 1px solid #d9e0e8;">
						<legend class="">基本信息:</legend>
						<div class="layui-form-item name-module">
							<label class="layui-form-label"><span class="showName">商品名称</span>:<span
								class="need">*</span></label>
							<div class="layui-input-inline">
								<input type="text" name="name" class="layui-input"
									data-rule='{"required":true}'>
							</div>
							<div class="layui-form-mid shop-check-msg"></div>
						</div>
						<div class="layui-form-item code-module">
							<label class="layui-form-label"><span class="showName">商品编号</span>:<span
								class="need">*</span></label>
							<div class="layui-input-inline">
								<input type="text" name="code" class="layui-input"
									data-rule='{"ajax":"${ctx}/product/checkProductCodeExist.action","errorMsg":"该商品编号已存在!"}'>
							</div>
							<div class="layui-form-mid shop-check-msg"></div>
						</div>
						<div class="layui-form-item category-module">
							<label class="layui-form-label"><span class="showName">所属类型</span>:<span
								class="need">*</span></label>
							<div class="layui-input-inline">
							    <input type="hidden" name="categoryid" value="">
								<input type="text" name="category_id" value="${category.name}"
									data-value="${category.id}" class="layui-input shop-select"
									readonly onclick="showMenu(); return false;"> <i
									class="ces-input-select" id="showcateControl"
									onclick="showMenu(); return false;"></i>
							</div>
							<div id="menuContent" class="menuContent">
								<ul id="categoryTree" class="ztree"></ul>
							</div>
						</div>
						<div class="layui-form-item barnd-module">
							<label class="layui-form-label"><span class="showName">品牌</span>:<span
								class="need">*</span></label>
							<div class="layui-input-inline">
								<select name="brandid">
									<c:forEach items="${brandList}" var="brand">
										<option value="${brand.id}">${brand.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label"><span class="showName">市场价</span>:<span
								class="need">*</span></label>
							<div class="layui-input-inline">
								<input type="text" name="marketprice" class="layui-input"
									value="0" data-rule='{"price":true}'>
							</div>
							<div class="layui-form-mid layui-word-aux">元</div>
							<div class="layui-form-mid shop-check-msg"></div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label"><span class="showName">价格</span>:<span
								class="need">*</span></label>
							<div class="layui-input-inline">
								<input type="text" name="price" class="layui-input" value="0"
									data-rule='{"required":true,"price":true}'>
							</div>
							<div class="layui-form-mid layui-word-aux">元</div>
							<div class="layui-form-mid shop-check-msg"></div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label"><span class="showName">库存</span>:<span
								class="need">*</span></label>
							<div class="layui-input-inline">
								<input type="text" name="stock" class="layui-input" value="0"
									data-rule='{"required":true,"number":true,"errorMsg":"库存输入不合法!"}'>
							</div>
							<div class="layui-form-mid shop-check-msg"></div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label"><span class="showName">计量单位</span>:<span
								class="need">*</span></label>
							<div class="layui-input-inline">
								<select name="unit">
									<option value="斤">斤</option>
									<option value="个">个</option>
									<option value="件">件</option>
									<option value="把">把</option>
									<option value="盒">盒</option>
									<option value="箱">箱</option>
									<option value="对">对</option>
									<option value="件">件</option>
									<option value="双">双</option>
									<option value="瓶">瓶</option>
								</select>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label"><span class="showName">销售状态</span>:<span
								class="need">*</span></label>
							<div class="layui-input-block">
								<input name="sale" value="1" title="上架" checked="" type="radio">
								<input name="sale" value="0" title="下架" type="radio">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">缩略图:*</label>
							<div class="layui-input-inline image img-div">
								<button type="button" class="layui-btn layui-btn-normal"
									id="logo">选择</button>
							</div>
							<input type="hidden" name="image" value="" />
						</div>
						<fieldset class="shop-fieldset-style extendattr">
							<legend>商品属性:</legend>

						</fieldset>
					</fieldset>
					<fieldset style="min-height: 250px; border: 1px solid #d9e0e8;">
						<legend>商品图片:</legend>
						<div class="layui-upload">
							<button type="button" class="layui-btn layui-btn-normal"
								id="testList">图片上传</button>
							<div class="layui-upload-list">
								<div class="product-img-div" id="demoList"></div>
							</div>
						</div>
						<input type="hidden" name="images" value="" />
					</fieldset>
					<fieldset class="product-relate-div"
						style="min-height: 250px; border: 1px solid #d9e0e8;">
						<legend>相关商品:</legend>
							<button type="button" class="layui-btn layui-btn-normal add-relate-btn">新增关联商品</button>
							<table class="layui-table product-relate-table">
							    <colgroup><col width="40%"><col width="30%"><col><col width="30%"><col></colgroup>
								<thead>
								   <tr><th>商品名称</th><th>商品编码</th><th>操作</th></tr>
								</thead>
								<tbody>
								</tbody>
							</table>
					</fieldset>
					<div
						style="margin-bottom: 10px; font-size: 16px; margin-top: 10px;">商品描述：</div>
					<div id="editor"></div>
					<textarea name="description" style="display: none;"></textarea>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
</html>