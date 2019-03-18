/** 表单数据校验 * */
$(function() {
	$.fn.formValidation = function(cmd) {
		var setting = {
			errorMsg : '',
		};
		var messages = {
			required : "该输入项为必填项，请填写。",
			ajax : "该项已存在！",
			price : "价格输入不合法！",
			number : "输入不合法",
		};
		var methods = {
			required : function(result, value, options, $element) {
				result.status = !(value === '');
				result.msg = messages.required;
				return result;
			},
			ajax : function(result, value, options, $element, errorMsg) {
				result.status = !(value === ''); //先为空判断
				result.msg = messages.required;
				if (result.status) {
					var url = options['ajax'];
					var name = $element.attr("name");
					$.ajax({
							url : url,
							data : name + "=" + value,
							type : "POST",
							async : false,
							success : function(msg) {
								result.status = (JSON.parse(msg).result == "success") ? true
										: false;
							}
						})
					result.msg = (errorMsg == null || errorMsg == "") ? messages.ajax
							: errorMsg;
				}
				return result;
			},
			price : function(result, value, options, $element, errorMsg) {
				result.status = true; //先为空判断
				result.msg = "";
				if (!(value === '')) {
					var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
					result.status = reg.test(value) ? true : false;
					result.msg = (errorMsg == null || errorMsg == "") ? messages.price
							: errorMsg;
				}
				return result;
			},
			number : function(result, value, options, $element, errorMsg) {
				result.status = true;
				result.msg = "";
				if (!(value === '')) {
					var reg = /^\d+$/;
					result.status = reg.test(value) ? true : false;
					result.msg = (errorMsg == null || errorMsg == "") ? messages.number
							: errorMsg;
				}
				return result;
			},
		};
		if (arguments.length == 0) {
			$(this).find("input,textarea").each(function() {
				blurValidate($(this));
			});
		} else if (arguments.length == 1 && cmd == "submit") {
			var rstatus = true;
			$(this).find("input:visible,textarea:visible").each(
					function() {
						var options = $(this).data("rule");
						if (typeof options != "undefiend" && options != null
								&& options != '') {
							setting = {
								errorMsg : '',
							};
							$.extend(setting, options);
							if (!validateText($(this), setting,
									setting.errorMsg)) {
								rstatus = false;
							}
						}
					});
			return rstatus;
		}
		function validateText($element, validateType, msg) {
			var result = {
				status : false,
				msg : ''
			};
			for ( var role in validateType) {
				if ($.isFunction(methods[role])) {
					result = methods[role](result, $.trim($element.val()),
							validateType, $element, msg);
					if (!result.status) {
						showErrorMsg($element, result.msg);
						return false;
					}
				}
			}
			return true;
		}
		function blurValidate($element) {
			var result = {
				status : false,
				msg : ''
			};
			$element.blur(function() {
				var options = $(this).data("rule");
				if (typeof options != "undefiend" && options != null
						&& options != '') {
					setting = {
						errorMsg : '',
					};
					$.extend(setting, options);
				}
				for ( var role in setting) {
					if ($.isFunction(methods[role])) {
						result = methods[role](result, $.trim($element.val()),
								setting, $element, setting.errorMsg);
						if (result.status) {
							showSuccessMsg($(this));
						} else {
							showErrorMsg($(this), result.msg);
							return;
						}
					}
				}
			});
		}
		function showErrorMsg($element, msg) {
			$element.parent().siblings(".shop-check-msg").html(
					'<span class="error">' + msg + '</span>');
		}
		function showSuccessMsg($element) {
			$element.parent().siblings(".shop-check-msg").html(
					'<span class="ok"></span>');
		}
	}
});