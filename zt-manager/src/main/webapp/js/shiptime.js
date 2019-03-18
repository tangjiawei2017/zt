var ShipTime = {
	timeInterval : 1, // 时间间隔
	timeArray : [ '00:00', '01:00', '02:00', '03:00', '04:00', '05:00',
			'06:00', '07:00', '08:00', '09:00', "10:00", "11:00", "12:00",
			"13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
			"20:00", "21:00", "22:00", "23:00", "24:00" ],
	initComplexShipTime:function(array){
		for(var i=0;i<array.length;i++){
			var temp=array[i].split("~"); 
			ShipTime.createNewTime();
			$("select[name='start"+i+"']").val(temp[0]);
			$("select[name='end"+i+"']").val(temp[1]);
		}
	},
	setTimeInterval : function(interval) {
		layer.confirm(
				'由于可选时间间隔变更，需要重新设置每日配送时间段。点选“确定”，将重新设置每日配送时间段；点选“取消”，将放弃当前操作。',
				{
					icon : 3,
					title : '提示'
				}, function(index) {
					$('.sendtime-table tbody tr').hide();
					// 注意此处必须先 parseInt(),否则当字符串去处理了
					ShipTime.timeInterval = parseInt(interval);
					layer.close(index);
				});
	},
	createNewTime : function() {
		var index = 0;
		$(".sendtime-table tbody tr:visible").each(function(i, element) {
			index = i + 1;
			return;
		});
		if (index == 5) {
			layer.msg('最多只能设置5个时间段!', {
				time : 1500,
				skin : 'error-class',
				anim : 5,
				isOutAnim : true,
			});
		} else {
			ShipTime.createSelectValue($("select[name='start" + index + "']"),
					$("select[name='end" + index + "']"), index == 0 ? 0 : $(
							"select[name='end" + (index - 1) + "']").val());
		}
	},
	createSelectValue : function($select_start, $select_end, prevalue) {
		var startIndex = 0;
		if (prevalue != 0) {
			for (var i = 0; i < ShipTime.timeArray.length; i++) {
				if (ShipTime.timeArray[i] == prevalue) {
					startIndex = i;
					break;
				}
			}
		}
		// 清空操作
		$select_start.empty();
		$select_end.empty();
		for (var i = startIndex; i < ShipTime.timeArray.length; i = i
				+ ShipTime.timeInterval) {
			$select_start.append("<option value='" + ShipTime.timeArray[i]
					+ "'>" + ShipTime.timeArray[i] + "</option>");
			if (i + ShipTime.timeInterval < ShipTime.timeArray.length) {
				$select_end.append("<option value='"
						+ ShipTime.timeArray[i + ShipTime.timeInterval] + "'>"
						+ ShipTime.timeArray[i + ShipTime.timeInterval]
						+ "</option>");
			}
		}
		$select_start.parents("tr").show();
	},
	updateNextSelect : function($obj, flag) {
		// flag 是标识 如果是start,则需要更新 end select框，然后隐藏后面select,否则直接隐藏即可
		if (flag = "start") {
			var startIndex = 0;
			for (var i = 0; i < ShipTime.timeArray.length; i++) {
				if (ShipTime.timeArray[i] == $obj.val()) {
					startIndex = i + ShipTime.timeInterval;
					break;
				}
			}
			$select = $obj.parent("td").next("td").children("select");
			$select.empty();
			for (var i = startIndex; i < ShipTime.timeArray.length; i = i
					+ ShipTime.timeInterval) {
				$select.append("<option value='" + ShipTime.timeArray[i] + "'>"
						+ ShipTime.timeArray[i] + "</option>");
			}
		}
		// 隐藏后面所有下拉框
		$obj.parents("tr").nextAll().hide();
	},
	removeSendTime : function(obj) {
		layer.confirm('确认删除该时间段?', {
			icon : 3,
			title : '提示'
		}, function(index) {
			$(obj).parents("tr").hide().nextAll().hide();
			layer.close(index);
		});
	}
};