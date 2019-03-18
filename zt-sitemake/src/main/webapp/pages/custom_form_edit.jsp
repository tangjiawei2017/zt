<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>自定义表单</title>
<link rel="stylesheet" href="${ctx}/res/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/res/css/common.css">
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

.product-template-extendattr tbody tr td a {
	text-decoration: underline;
	color: blue;
}

img {
	cursor: pointer;
}
table input{
   max-width:100px;
}
</style>
<script src="${ctx}/res/js/jquery-2.1.0.js"></script>
<script src="${ctx}/res/layui/layui.js"></script>
<script src="${ctx}/res/js/formValidation.js"></script>
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
			//form.render(); //更新渲染全部组件
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
			if (length >= 15) {
				layer.msg("已达到最大数量!", {
					time : 2000,
					skin : 'error-class'
				});
				return;
			}
			//扩展属性 tr 拼装
			var tr = '<tr><td><input type="text" name="ext'+(length+1)+'"></td><td><input type="text" name="itemName" value=""></td>'
					+ '<td><select name="type" onchange="selectType(this)" lay-ignore>'
					+ '<option value="0">单行文本</option><option value="1">多行文本</option>'
					+ '<option value="2">单选</option><option value="3">复选</option>'
					+ '<option value="4">下拉框</option><option value="5">日期</option>'
					+ '<option value="6">地址</option><option value="7">图片</option></select></td>'
					+ '<td><select name="dataType" class="dataType-select" lay-ignore>'
					+ '<option value="0">任意字符<option value="1">手机号码</option>'
					+ '<option value="2">邮箱地址</option><option value="3">价格</option>'
					+ '<option value="4">自然数</option></select>'
					+ '<a class="dataType-a setValuesArea" style="display:none;" href="javascript:"'
					+ 'onclick="setExtendOptionsValue(this)">设置选项</a>'
					+ '<span class="valuesListArea" style="display:none;">'
					+ '<a href="javascript:" onclick="setExtendOptionsValue(this)" data-key=""></a></span></td>'
					+ '<td><input type="text" name="itemPrompt"></td>'
					+ '<td><img src="${ctx}/res/images/saleon.png" onclick="updateShowAndNeedStatus(this);" data-value="1" /></td>'
					+ '<td><img src="${ctx}/res/images/saleon.png" onclick="updateShowAndNeedStatus(this);" data-value="1" /></td>'
					+ '<td><a href="javaScript:void(0)" onclick="delExtendAttrObj(this)">删除</a></td></tr>';
			$(".product-template-extendattr tbody").append(tr);
		});
	});
	//提交表单时,校验数据的合法性 返回对象包含是否合法状态值，以及表单序列化结果集
	window.checkData = function() {
		var result = {
			"status" : true,
			"data" : {}
		};
		//表单初始化校验
		result.status = $("#form").formValidation("submit");
		var extendAttrArray = [];
		//添加扩展属性
		$(".product-template-extendattr tbody tr").each(function(index) {
			var $childrens = $(this).children("td");
			var id='';
			var $id = $childrens.eq(0).children("input[name='id']");
			if($id.length>0){
				id=$id.val();
			}
			var $input = $childrens.eq(0).children("input[name='ext"+(index+1)+"']");
			var name = $.trim($input.val());
			if (result.status && name == '') {
				layer.msg(name + "扩展属性名称不能为空!", {
					time : 1500,
				});
				result.status = false;
				return result;
			}
			var itemName= $childrens.eq(1).children("input[name='itemName']").val();
			var type = Number($childrens.eq(2).children(
					"select").val());
			var dataType;
			var $dataValue = $childrens.eq(3).find(
					".valuesListArea:visible a");
			var value = '';
			if ($dataValue.length > 0) {
				dataType = 5;
				value = $dataValue.data("key");
			}else if($childrens.eq(3).children(".dataType-select").is(":visible")){
				dataType = Number($childrens.eq(3).children(".dataType-select").val());
			}else if($childrens.eq(3).children(".setValuesArea").is(":visible")){
				layer.msg("值约束不能为空!", {
					time : 1500,
				});
				result.status = false;
				return result;	
			}
			var itemPrompt = $.trim($childrens.eq(4).children("input[name='itemPrompt']").val());
			var isRequired = $childrens.eq(5).children("img").data(
					"value") == 1 ? true : false;
			var isItem = $childrens.eq(6).children("img").data(
					"value") == 1 ? true : false;
			extendAttrArray.push(createExtendAtrrObj(id,name,itemName,type,dataType,value,itemPrompt,isRequired,isItem));
		});
		result.data.customFormId = $.trim($("input[name='customFormId']").val());
		result.data.customFormName = $.trim($("input[name='name']").val());
		result.data.customFormItemArray = extendAttrArray;
		return result;
	}


	//创建扩展属性对象
	function createExtendAtrrObj(id,name,itemName,type, dataType, value, itemPrompt,
			isRequired, isItem) {
		var obj = new Object();
		obj.id = id;
		obj.name = name;
		obj.itemName= itemName;
		obj.type = type;
		obj.dataType = dataType;
		obj.value = value;
		obj.itemPrompt = itemPrompt;
		obj.isRequired = isRequired;
		obj.isItem = isItem;
		return obj;
	}

	//扩展属性 添加选项值
	function setExtendOptionsValue(obj) {
		var data = '';
		if ($(obj).parent().is(".valuesListArea")) {
			data = $.trim($(obj).data("key"));
		}
		layer.open({
			type : 2,
			title : '编辑属性值',
			content : "${ctx}/customForm/preEditCustomFormAttrValue.action?data="
					+ data,
			area : [ '550px', '420px' ],
			btn : [ '保存', '取消' ],
			yes : function(index, layero) {
				var childFrame = window.frames["layui-layer-iframe"
						+ index]; //得到加载层页面window对象
				var result = childFrame.checkData(); //掉用其全局函数,校验并获取数据
				if (result.status) {
					layer.close(index); //关闭加载页面
					var str = $.trim(result.data.join(";"));
					var $parent = $(obj).parent();
					var $a;
					if ($parent.is(".valuesListArea")) {
						$a = $(obj);
					} else {
						$a = $parent.children(".valuesListArea")
								.children("a");
					}
					if (str == "") {
						$parent.children(".setValuesArea").show();
						$parent.children(".valuesListArea").hide();
					} else {
						$parent.children(".setValuesArea").hide();
						$parent.children(".valuesListArea").show();
						//限制扩展属性值长度不大于16
						str.length < 16 ? $a.html(str) : $a.html(str
								.substring(0, 15)
								+ "...");
					}
					$a.data("key", str);
				}
			},
		});
	}

	//设置是否显示和是否必填
	function updateShowAndNeedStatus(obj) {
		if ($(obj).data("value") == 1) {
			$(obj).attr("src", "${ctx}/res/images/saleoff.png");
			$(obj).data("value", 0);
		} else {
			$(obj).attr("src", "${ctx}/res/images/saleon.png");
			$(obj).data("value", 1);
		}
	}
	//删除当前节点 tr
	function delExtendAttrObj(obj) {
		$(obj).parent().parent().remove();
		//其他 tr中 input域的名字需要 ext0,ext1按顺序重新修改
		$(".product-template-extendattr tbody tr").each(function(index) {
			$(this).find("input[name^='ext']").attr("name", "ext" + (index+1));
		});
	}

	//选择 类型
	function selectType(obj) {
		var $next = $(obj).parent().next("td");
		var value = $.trim($(obj).val());
		//得到下拉框的值
		if (value == "0" || value == "1") {
			$next.children(".dataType-select").show();
			$next.children(".setValuesArea").hide();
			$next.children(".valuesListArea").hide();
		} else if (value == "2" || value == "3" || value == "4") {
			$next.children(".dataType-select").hide();
			$next.children(".setValuesArea").show();
			var value = $.trim($next.children(".valuesListArea").children("a")
					.html());
			if (value == '') {
				$next.children(".setValuesArea").show();
				$next.children(".valuesListArea").hide();
			} else {
				$next.children(".setValuesArea").hide();
				$next.children(".valuesListArea").show();
			}
		} else if (value == "5" || value == "6") {
			$next.children(".dataType-select").hide();
			$next.children(".setValuesArea").hide();
			$next.children(".valuesListArea").hide();
		}
	}
</script>
</head>
<body>
	<div class="container">
		<div class="content-r">
			<div class="layui-field-box">
				<form  action="" id="form">
					<c:choose>
						<c:when test="${customForm!=null}">
							<input type="hidden" name="customFormId" value="${customForm.id}" />
							<div class="layui-form-item">
								<label class="layui-form-label">模板名称:*</label>
								<div class="layui-input-inline customFormDiv">
									<input type="text" name="name" class="layui-input custom-form-input"
										value="${customForm.name}" data-rule='{"required":true}'>
								</div>
								<div class="layui-form-mid shop-check-msg"></div>
							</div>
							<button
								class="layui-btn layui-btn-normal layui-btn-sm add-extend-btn"
								type="button">添加表单项</button>
							<table class="layui-table product-template-extendattr"
								lay-skin='line'>
								<colgroup>
									<col width="150">
									<col width="130">
									<col width="130">
									<col width="100">
									<col width="100">
									<col width="150">
									<col>
								</colgroup>
								<thead>
									<tr>
										<th>属性名</th>
										<th>后台列表名</th>
										<th>数据类型</th>
										<th>值约束</th>
										<th>输入提示</th>
										<th>是否必填</th>
										<th>作为列表项</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${customForm.itemList}" var="item" varStatus="status">
											<tr>
												<td><input type="hidden" name="id" value="${item.id}"><input type="text" name="ext${status.count}"
													value="${item.name}"></td>
												 <td><input type="text" name="itemName"
													value="${item.itemName}"></td>
											<td><select name="type" onchange="selectType(this)">
													<option value="0" <c:if test="${item.type==0}">selected</c:if>>单行文本</option>
													<option value="1" <c:if test="${item.type==1}">selected</c:if>>多行文本</option>
													<option value="2" <c:if test="${item.type==2}">selected</c:if>>单选</option>
													<option value="3" <c:if test="${item.type==3}">selected</c:if>>复选</option>
													<option value="4" <c:if test="${item.type==4}">selected</c:if>>下拉框</option>
													<option value="5" <c:if test="${item.type==5}">selected</c:if>>日期</option>
													<option value="6" <c:if test="${item.type==6}">selected</c:if>>地址</option>
													<option value="7" <c:if test="${item.type==7}">selected</c:if>>图片</option>
											</select></td>
											<td><c:choose>
													<c:when test="${item.dataType==5}">
														<select name="dataType" class="dataType-select"  style="display:none;">
															<option value="0">任意字符</option>
															<option value="1">手机号码</option>
															<option value="2">邮箱地址</option>
															<option value="3">价格</option>
															<option value="4">自然数</option>
														</select>
														<a class="dataType-a setValuesArea" style="display:none;" href="javascript:" onclick="setExtendOptionsValue(this)">设置选项</a>
														<span class="valuesListArea">
															<a href="javascript:"
															onclick="setExtendOptionsValue(this)"
															data-key="${item.dataValue}">${item.dataValue}</a>
														</span>
													</c:when>
													<c:otherwise>
                                                        <select name="dataType" class="dataType-select" lay-ignore>
															<option value="0" <c:if test="${item.dataType==0}">selected</c:if>>任意字符</option>
															<option value="1" <c:if test="${item.dataType==1}">selected</c:if>>手机号码</option>
															<option value="2" <c:if test="${item.dataType==2}">selected</c:if>>邮箱地址</option>
															<option value="3" <c:if test="${item.dataType==3}">selected</c:if>>价格</option>
															<option value="4" <c:if test="${item.dataType==4}">selected</c:if>>自然数</option>
														</select>
														<a class="dataType-a setValuesArea" style="display:none;" href="javascript:" onclick="setExtendOptionsValue(this)">设置选项</a>
														<span class="valuesListArea" style="display:none">
															<a href="javascript:"
															onclick="setExtendOptionsValue(this)"
															data-key="${item.dataValue}">${item.dataValue}</a>
														</span>
													</c:otherwise>
												</c:choose></td>
											<td><input type="text" name="itemPrompt" value="${item.itemPrompt }"></td>
											<td><c:choose>
													<c:when test="${item.isRequried}">
														<img src="${ctx}/res/images/saleon.png"
															onclick="updateShowAndNeedStatus(this)" data-value="1" />
													</c:when>
													<c:otherwise>
														<img src="${ctx}/res/images/saleoff.png"
															onclick="updateShowAndNeedStatus(this)" data-value="0" />
													</c:otherwise>
												</c:choose></td>
											<td><c:choose>
													<c:when test="${item.isItem}">
														<img src="${ctx}/res/images/saleon.png"
															onclick="updateShowAndNeedStatus(this)" data-value="1" />
													</c:when>
													<c:otherwise>
														<img src="${ctx}/res/images/saleoff.png"
															onclick="updateShowAndNeedStatus(this)" data-value="0" />
													</c:otherwise>
												</c:choose></td>
											<td><a href="javaScript:void(0)"
												onclick="delExtendAttrObj(this)">删除</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<div class="layui-form-item">
								<label class="layui-form-label">表单名称:*</label>
								<div class="layui-input-inline customFormDiv">
									<input type="text" name="name" class="layui-input custom-form-input" value="" 
										data-rule='{"required":true}'>
								</div>
								<div class="layui-form-mid shop-check-msg"></div>
							</div>
							<button
								class="layui-btn layui-btn-normal layui-btn-sm add-extend-btn"
								type="button">添加表单项</button>
							<table class="layui-table product-template-extendattr"
								lay-skin='line'>
								<colgroup>
									<col width="150">
									<col width="130">
									<col width="130">
									<col width="100">
									<col width="100">
									<col width="150">
									<col>
								</colgroup>
								<thead>
									<tr>
										<th>属性名</th>
										<th>后台列表名</th>
										<th>数据类型</th>
										<th>值约束</th>
										<th>输入提示</th>
										<th>是否必填</th>
										<th>作为列表项</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</c:otherwise>
					</c:choose>
				</form>
			</div>
		</div>
	</div>
</body>
</html>