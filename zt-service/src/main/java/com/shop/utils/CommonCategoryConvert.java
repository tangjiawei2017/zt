package com.shop.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.CommonCategoryDO;
import com.shop.vo.CommonCategoryVO;

/**
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-16 21:31:58
 */
public class CommonCategoryConvert {
	public static List<CommonCategoryVO> convertCategoryDOToVO(List<CommonCategoryDO> list) {
		List<CommonCategoryVO> voList = new ArrayList<CommonCategoryVO>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (CommonCategoryDO category : list) {
			CommonCategoryVO vo = new CommonCategoryVO();
			vo.setId(category.getId());
			vo.setName(category.getName());
			vo.setCtime(format.format(category.getCtime()));
			vo.setDescription(category.getDescription());
			voList.add(vo);
		}
		return voList;
	}
}
