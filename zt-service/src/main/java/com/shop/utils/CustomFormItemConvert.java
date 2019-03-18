package com.shop.utils;

import java.util.ArrayList;
import java.util.List;

import com.shop.model.CustomFormItemDO;
import com.shop.vo.CustomFormItemVO;

/**
 * 自定义表单转换类
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-12 20:00:30
 */
public class CustomFormItemConvert {
	public static List<CustomFormItemVO> convertCustomFormDOToVOList(List<CustomFormItemDO> list) {
		List<CustomFormItemVO> itemVOList = new ArrayList<CustomFormItemVO>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CustomFormItemDO item = list.get(i);
				if (item.getIsItem()) {
					CustomFormItemVO vo = new CustomFormItemVO();
					vo.setCode("ext" + (i + 1));
					vo.setId(item.getId());
					vo.setName(item.getName());
					vo.setItemName(item.getItemName());
					vo.setType(item.getType());
					vo.setDataType(item.getDataType());
					vo.setDataValue(item.getDataValue());
					itemVOList.add(vo);
				}
			}
		}
		return itemVOList;
	}
}
