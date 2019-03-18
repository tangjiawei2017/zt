<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>商品属性模板编辑</title>
<link rel="stylesheet" href="${ctx}/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<style>
.layui-tab-content {
	padding: 0px;
}

.layui-table tbody tr:hover {
	background-color: transparent !important;
}

.layui-table {
	margin: 0px;
}

.add-extend-btn {
	margin: 10px;
}
.product-template-extendattr tbody tr td a{
   text-decoration: underline;
   color: blue; 
}
img{
   cursor: pointer;
}
</style>
<script src="${ctx}/js/jquery-2.1.0.js"></script>
<script src="${ctx}/layui/layui.js"></script>
<script src="${ctx}/js/formValidation.js"></script>
<script>
	var layer;
	var element;
	var form;
	//扩展Array 功能
	Array.prototype.remove = function(b) { 
		var a = this.indexOf(b); 
		if (a >= 0) { 
		this.splice(a, 1); 
		return true; 
		} 
		return false; 
	}; 
	$(function() {
		layui.use('form', function() {
			form = layui.form;
			layer = layui.layer;
			form.render(); //更新渲染全部组件
			
			//监听扩展属性 select 框
			form.on('select(select)', function(data){
				  var $next=$(data.elem).parent().next("td");
				  //得到下拉框的值
				  if(data.value=="1"){
					  $next.children().hide();
				  }else if(data.value=="2" || data.value=="3"){
					  var value=$.trim($next.children(".valuesListArea").children("a").html());
					  if(value==""){
					       $next.children(".setValuesArea").show();
					       $next.children(".valuesListArea").hide();
					  }else{
						   $next.children(".setValuesArea").hide();
					       $next.children(".valuesListArea").show();
					  }
				  }
		   });   
		});
		//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
		layui.use('element', function() {
			element = layui.element;
		});
		//表单初始化校验
		$("#form").formValidation();
		//添加扩展属性
		$(".add-extend-btn").click(function() {
			var length = $(".product-template-extendattr tbody tr").length;
			if (length >= 10) {
				layer.msg("已达到最大数量!", {
					time : 2000,
					skin : 'error-class'
				});
				return;
			}
			//扩展属性 tr 拼装
			var tr = '<tr><td><input type="text" name="ext'+length+'"></td>';
			tr = tr + '<td><select name="type" lay-filter="select"><option value="1">文本项</option><option value="2">下拉选项</option>';
			tr = tr + '<option value="3">复选项</option></select></td>';
			tr = tr + '<td><span class="setValuesArea" style="display:none;"><a href="javascript:" onclick="setExtendOptionsValue(this)">设置选项</a></span><span class="valuesListArea" style="display:none;"><a href="javascript:" onclick="setExtendOptionsValue(this)" data-key=""></a></span></td>';
			tr = tr + '<td><img src="${ctx}/images/saleon.png" onclick="updateShowAndNeedStatus(this);" data-value="1" /></td>';
			tr = tr + '<td><img src="${ctx}/images/saleon.png" onclick="updateShowAndNeedStatus(this);" data-value="1" /></td>';
			tr = tr + '<td><a href="javaScript:void(0)" onclick="delExtendAttrObj(this)">删除</a></td></tr>';
			$(".product-template-extendattr tbody").append(tr);
			form.render('select'); //更新渲染下拉选择框
		});
	});  
	//提交表单时,校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : true,
			"data" : {}
		};
		//表单初始化校验
		result.status=$("#form").formValidation("submit");
		var basicAttrArray = [];
		var extendAttrArray = [];
		//添加基本属性
	    $(".product-template-basicattr tbody tr").each(function(){
			    var $childrens = $(this).children("td");
				var $input = $childrens.eq(1).children("input");
				var name = $.trim($childrens.eq(0).html());
				var code = $.trim($input.attr("name"));
				var unit = "";
				if($(this).is(".price")){
				  unit = $.trim($(this).find("select[name='priceUnit']").val());
				}
				var showName = $.trim($input.val()); 
				if(result.status && showName==''){
					layer.msg(name+"显示名称必填!", {
						time : 1500,
					});
					result.status=false;
					return result;
				}
				var isShow = $childrens.eq(3).children("img").data(
						"value") == 1 ? true : false;
				var isNeed = $childrens.eq(4).children("img").data(
						"value") == 1 ? true : false;
				basicAttrArray.push(createBasicAttrObj(code,name,showName,unit,isShow,isNeed));
		});
		//添加扩展属性
		$(".product-template-extendattr tbody tr").each(function(){
			    var $childrens = $(this).children("td");
			    var $input = $childrens.eq(0).children("input");
				var code = $.trim($input.attr("name"));
				var name = $.trim($input.val());
				if(result.status && name==''){
					layer.msg(name+"扩展属性名称不能为空!", {
						time : 1500,
					});
					result.status=false;
					return result;
				}
				var type = Number($childrens.eq(1).children("select").val());
				var $value = $childrens.eq(2).find(".valuesListArea a");
				var value ='';
				if($value.length > 0){
					value = $value.data("key");
				}
				var isShow = $childrens.eq(3).children("img").data(
						"value") == 1 ? true : false;
				var isNeed = $childrens.eq(4).children("img").data(
						"value") == 1 ? true : false;
				extendAttrArray.push(createExtendAtrrObj(code,name,type,value,isShow,isNeed));
		});
		result.data.templateId=$.trim($("input[name='templateId']").val());
		result.data.templateName=$.trim($(".templateDiv input[name='name']").val());
	    result.data.basicAttrArray = basicAttrArray;
		result.data.extendAttrArray = extendAttrArray;
		return result;
	}

	//创建基本属性对象
	function createBasicAttrObj(code, name, showName, unit, isShow, isNeed) {
		var obj = new Object();
		obj.code = code;
		obj.name = name;
		obj.showName = showName;
		obj.unit = unit;
		obj.isShow = isShow;
		obj.isNeed = isNeed;
		return obj;
	}

	//创建扩展属性对象
	function createExtendAtrrObj(code, name, type,value, isShow, isNeed) {
		var obj = new Object();
		obj.code = code;
		obj.name = name;
		obj.type = type;
		obj.value = value;
		obj.isShow = isShow;
		obj.isNeed = isNeed;
		return obj;
	}
	
	//扩展属性 添加选项值
	function setExtendOptionsValue(obj){
	    var data='';
	    if($(obj).parent().is(".valuesListArea")){
	    	data=$.trim($(obj).data("key"));
	    }
		layer.open({
			type : 2,
			title:'编辑属性值',
			content : "${ctx}/productTemplate/preEditTemplateExtendAttrValue.action?data="+data,
			area : [ '550px', '420px' ],
			btn : [ '保存', '取消' ],
			yes : function(index, layero) {
				var childFrame = window.frames["layui-layer-iframe" + index];    //得到加载层页面window对象
				var result=childFrame.checkData(); //掉用其全局函数,校验并获取数据
				if(result.status){
					layer.close(index);   //关闭加载页面
					var str=$.trim(result.data.join(";"));
					var $parent=$(obj).parent().parent();
					var $a=$parent.children(".valuesListArea").children("a");
					if(str==""){
					    $parent.children(".setValuesArea").show();
					    $parent.children(".valuesListArea").hide();
					}else{
						$parent.children(".setValuesArea").hide();
					    $parent.children(".valuesListArea").show();
					    //限制扩展属性值长度不大于16
					    str.length < 16 ? $a.html(str):$a.html(str.substring(0,15)+"...");
				   }
				   $a.data("key",str);
				}
			},
		});
	}

	//商品编号和名称必填
	function alertPrompt(type) {
		var msg = ""
		switch (type) {
		case 1:
			msg = "商品编号必须显示!"
			break;
		case 2:
			msg = "商品编号必填!";
			break;
		case 3:
			msg = "商品名称必须显示!";
			break;
		case 4:
			msg = "商品名称必填!";
			break;
		default:
	        msg = "该项不可更改!";
			break;
		}
		layer.msg(msg, {
			time : 2000,
		});
	}
	//设置是否显示和是否必填
	function updateShowAndNeedStatus(obj) {
		if ($(obj).data("value") == 1) {
			$(obj).attr("src", "${ctx}/images/saleoff.png");
			$(obj).data("value", 0);
		} else {
			$(obj).attr("src", "${ctx}/images/saleon.png");
			$(obj).data("value", 1);
		}
	}
	//删除当前节点 tr
	function delExtendAttrObj(obj) {
		$(obj).parent().parent().remove();
		//其他 tr中 input域的名字需要 ext0,ext1按顺序重新修改
		$(".product-template-extendattr tbody tr").each(function(index) {
			$(this).find("input[name^='ext']").attr("name", "ext" + index);
		});
	}
</script>
</head>
<body>
	<div class="container">
		<div class="content-r">
			<div class="layui-field-box">
			   <form class="layui-form" action="" id="form">
				<c:choose>
					<c:when test="${template!=null}">
					    <input type="hidden" name="templateId" value="${template.id}"/>
						<div class="layui-form-item">
							<label class="layui-form-label">模板名称:*</label>
							<div class="layui-input-inline templateDiv">
								<input type="text" name="name" class="layui-input" value="${template.name}" data-rule='{"ajax":"${ctx}/productTemplate/checkTemplateExist.action?id=${template.id}","errorMsg":"该模板名称已存在!"}'>
							</div>
							<div class="layui-form-mid shop-check-msg"></div>
						</div>
						<div class="layui-tab">
							<ul class="layui-tab-title">
								<li class="layui-this">基本属性</li>
								<li>扩展属性</li>
							</ul>
							<div class="layui-tab-content">
								<div class="layui-tab-item layui-show">
									<table class="layui-table product-template-basicattr" lay-skin='line'>
										  <colgroup>
										       <col width="20%">
										       <col width="25%">
										       <col width="15%">
										       <col>
									       </colgroup>
									     <thead>
											<tr>
												<th>基本属性名称</th>
												<th>显示名称</th>
												<th>单位</th>
												<th>是否显示</th>
												<th>是否必填</th>
											</tr>
										</thead>
										<tbody>
										    <c:forEach items="${templateFields}" var="field">
										    <c:if test="${field.isBasic}">
										    <tr <c:if test="${field.unit!=null && field.unit!=''}"> class="price" </c:if>>
												<td>${field.name}</td>
												<td><input type="text" name="${field.code}" value="${field.showName}"></td>
												<td>
												<c:if test="${field.unit!=null && field.unit!=''}">
												<div class="layui-input-inline">
														<select name="priceUnit">
															<option value="元" <c:if test="${field.unit=='元'}">selected</c:if>>元</option>
															<option value="万元" <c:if test="${field.unit=='万元'}">selected</c:if>>万元</option>
														</select>
												</div>
												</c:if>
												</td>
												<td><c:choose>
														<c:when test="${field.isShow}">
														  <img src="${ctx}/images/saleon.png"  <c:if test="${field.isRead}"> onclick="alertPrompt(5)"</c:if> 
												              <c:if test="${not field.isRead}"> onclick="updateShowAndNeedStatus(this)"</c:if> data-value="1"/>
														</c:when>  
													    <c:otherwise>
													     <img src="${ctx}/images/saleoff.png" <c:if test="${field.isRead}"> onclick="alertPrompt(5)"</c:if> 
												            <c:if test="${not field.isRead}"> onclick="updateShowAndNeedStatus(this)"</c:if>  data-value="0"/>
													    </c:otherwise>
													</c:choose></td>
												<td><c:choose>
														<c:when test="${field.isNeed}">
														   <img src="${ctx}/images/saleon.png"  <c:if test="${field.isRead}"> onclick="alertPrompt(5)"</c:if> 
												              <c:if test="${not field.isRead}"> onclick="updateShowAndNeedStatus(this)"</c:if> data-value="1"/>
														</c:when>  
													    <c:otherwise>
					                                       <img src="${ctx}/images/saleoff.png" <c:if test="${field.isRead}"> onclick="alertPrompt(5)"</c:if> 
												            <c:if test="${not field.isRead}"> onclick="updateShowAndNeedStatus(this)"</c:if>  data-value="0"/>
													    </c:otherwise>
												     </c:choose>
												</td>
											</tr>
											</c:if>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="layui-tab-item">
									<button
										class="layui-btn layui-btn-normal layui-btn-sm add-extend-btn" type="button">添加扩展属性</button>
									<table class="layui-table product-template-extendattr" lay-skin='line'>
										<colgroup>
   												 <col width="200">
   												 <col width="130">
   												 <col width="130">
   												 <col width="100">
   												 <col width="100">
   												 <col>
  										</colgroup>
										<thead>
											<tr>
												<th>属性名</th>
												<th>数据类型</th>
												<th>属性值</th>
												<th>是否显示</th>
												<th>是否必填</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
										   <c:forEach items="${templateFields}" var="field">
										    <c:if test="${not field.isBasic}">
										    <tr>
											  <td><input type="text" name="${field.code}" value="${field.name}"></td>
											  <td><select name="type" lay-filter="select"><option value="1" <c:if test="${field.type==1}">selected</c:if>>文本项</option>
											  <option value="2" <c:if test="${field.type==2}">selected</c:if>>下拉选项</option><option value="3" <c:if test="${field.type==3}">selected</c:if>>复选项</option>
											  </select></td>
											  <c:choose>
											     <c:when test="${field.type==1}">
											        <td><span class="setValuesArea" style="display:none;"><a href="javascript:" onclick="setExtendOptionsValue(this)">设置选项</a></span><span class="valuesListArea" style="display:none;"><a href="javascript:" onclick="setExtendOptionsValue(this)" data-key="">
											       </a></span></td>
											     </c:when>
											     <c:when test="${field.type==2 || field.type==3}">
											          <c:if test="${fn:length(field.value)==0}">
											              <td><span class="setValuesArea" ><a href="javascript:" onclick="setExtendOptionsValue(this)">设置选项</a></span><span class="valuesListArea" style="display:none;">
											                 <a href="javascript:" onclick="setExtendOptionsValue(this)" data-key=""></a></span></td>
											          </c:if>
											          <c:if test="${fn:length(field.value) > 0}">
											             <td><span class="setValuesArea" style="display:none;"><a href="javascript:" onclick="setExtendOptionsValue(this)">设置选项</a></span><span class="valuesListArea">
											                 <a href="javascript:" onclick="setExtendOptionsValue(this)" data-key="${field.value}"><c:choose>
											                 <c:when test="${fn:length(field.value) > 16}"><c:out value="${fn:substring(field.value, 0, 15)}..." /></c:when><c:otherwise> <c:out value="${field.value}" /></c:otherwise>
                                                             </c:choose></a></span></td>
											          </c:if>
											     </c:when>
											  </c:choose>
											  <td><c:choose>
														<c:when test="${field.isShow}">
														  <img src="${ctx}/images/saleon.png"
												              onclick="updateShowAndNeedStatus(this)" data-value="1"/>
														</c:when>  
													    <c:otherwise>
													     <img src="${ctx}/images/saleoff.png"
												              onclick="updateShowAndNeedStatus(this)" data-value="0"/>
													    </c:otherwise>
													</c:choose></td>
											  <td><c:choose>
														<c:when test="${field.isNeed}">
														  <img src="${ctx}/images/saleon.png"
												              onclick="updateShowAndNeedStatus(this)" data-value="1"/>
														</c:when>  
													    <c:otherwise>
													     <img src="${ctx}/images/saleoff.png"
												              onclick="updateShowAndNeedStatus(this)" data-value="0"/>
													    </c:otherwise>
												</c:choose></td>
											  <td><a href="javaScript:void(0)" onclick="delExtendAttrObj(this)">删除</a></td>
											</tr>
											</c:if>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="layui-form-item">
							<label class="layui-form-label">模板名称:*</label>
							<div class="layui-input-inline templateDiv">
								<input type="text" name="name" class="layui-input" value="" data-rule='{"ajax":"${ctx}/productTemplate/checkTemplateExist.action","errorMsg":"该模板名称已存在!"}'>
							</div>
							<div class="layui-form-mid shop-check-msg"></div>
						</div>
						<div class="layui-tab">
							<ul class="layui-tab-title">
								<li class="layui-this">基本属性</li>
								<li>扩展属性</li>
							</ul>
							<div class="layui-tab-content">
								<div class="layui-tab-item layui-show">
									<table class="layui-table product-template-basicattr" lay-skin='line'>
									    <colgroup>
									       <col width="20%">
									       <col width="25%">
									       <col width="15%">
									       <col>
									    </colgroup>
										<thead>
											<tr>
												<th>基本属性名称</th>
												<th>显示名称</th>
												<th>单位</th>
												<th>是否显示</th>
												<th>是否必填</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>商品编号</td>
												<td><input type="text" name="code" value="商品编号"></td>
												<td></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="alertPrompt(1)" data-value="1"/></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="alertPrompt(2)" data-value="1"/></td>
											</tr>
											<tr>
												<td>商品名称</td>
												<td><input type="text" name="name" value="商品名称"></td>
												<td></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="alertPrompt(3)" data-value="1"/></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="alertPrompt(4)" data-value="1" /></td>
											</tr>
											<tr>
												<td>品牌</td>
												<td><input type="text" name="brandid" value="品牌"></td>
												<td></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="updateShowAndNeedStatus(this);" data-value="1" /></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="updateShowAndNeedStatus(this);" data-value="1" /></td>
											</tr>
											<tr>
												<td>销售状态</td>
												<td><input type="text" name="sale" value="销售状态"></td>
												<td></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="updateShowAndNeedStatus(this);" data-value="1" /></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="updateShowAndNeedStatus(this);" data-value="1" /></td>
											</tr>
											<tr>
												<td>库存</td>
												<td><input type="text" name="stock" value="库存"></td>
												<td></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="updateShowAndNeedStatus(this);" data-value="1" /></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="updateShowAndNeedStatus(this);" data-value="1" /></td>
											</tr>
											<tr>
												<td>计量单位</td>
												<td><input type="text" name="unit" value="计量单位"></td>
												<td></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="updateShowAndNeedStatus(this);" data-value="1" /></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="updateShowAndNeedStatus(this);" data-value="1" /></td>
											</tr>
											<tr class="price">
												<td>市场价</td>
												<td><input type="text" name="marketprice" value="市场价"></td>
												<td><div class="layui-input-inline">
														<select name="priceUnit">
															<option value="元">元</option>
															<option value="万元">万元</option>
														</select>
												</div></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="updateShowAndNeedStatus(this);" data-value="1"  /></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="updateShowAndNeedStatus(this);"  data-value="1" /></td>
											</tr>
											<tr class="price">
												<td>价格</td>
												<td><input type="text" name="price" value="价格"></td>
												<td><div class="layui-input-inline">
														<select name="priceUnit">
															<option value="元">元</option>
															<option value="万元">万元</option>
														</select>
												</div></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="updateShowAndNeedStatus(this);" data-value="1"  /></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="updateShowAndNeedStatus(this);"  data-value="1" /></td>
											</tr>
											<tr>
												<td>详细介绍</td>
												<td><input type="text" name="description" value="详细介绍"></td>
												<td></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="updateShowAndNeedStatus(this);"  data-value="1" /></td>
												<td><img src="${ctx}/images/saleon.png"
													onclick="updateShowAndNeedStatus(this);" data-value="1"  /></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="layui-tab-item">
									<button
										class="layui-btn layui-btn-normal layui-btn-sm add-extend-btn" type="button">添加扩展属性</button>
									<table class="layui-table product-template-extendattr" lay-skin='line'>
										<colgroup>
   												 <col width="200">
   												 <col width="130">
   												 <col width="130">
   												 <col width="100">
   												 <col width="100">
   												 <col>
  										</colgroup>
										<thead>
											<tr>
												<th>属性名</th>
												<th>数据类型</th>
												<th>属性值</th>
												<th>是否显示</th>
												<th>是否必填</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</form>
			</div>
		</div>
	</div>
</body>
</html>