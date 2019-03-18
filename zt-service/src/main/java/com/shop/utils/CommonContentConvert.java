package com.shop.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.shop.api.vo.ComContentApiVO;
import com.shop.model.CommonCategoryDO;
import com.shop.model.CommonContentDO;
import com.shop.vo.CommonContentVO;

public class CommonContentConvert {
	public static List<CommonContentVO> convertCommonContentDOToVO(List<CommonContentDO> list,
			List<CommonCategoryDO> categoryList) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<CommonContentVO> voList = new ArrayList<CommonContentVO>();
		if (list != null) {
			for (CommonContentDO content : list) {
				CommonContentVO vo = new CommonContentVO();
				vo.setId(content.getId());
				vo.setCategoryid(content.getCategoryid());
				for (CommonCategoryDO category : categoryList) {
					if (category.getId().equals(content.getCategoryid())) {
						vo.setCategoryName(category.getName());
					}
				}
				vo.setCtime(format.format(content.getCtime()));
				vo.setContent(content.getContent());
				vo.setTitle(content.getTitle());
				voList.add(vo);
			}
		}
		return voList;
	}

	public static ComContentApiVO convertCommonContentDOToApiVO(CommonContentDO content) {
		if (content != null) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ComContentApiVO vo = new ComContentApiVO();
			vo.setContent(content.getContent());
			vo.setTitle(content.getTitle());
			vo.setCtime(format.format(content.getCtime()));
			return vo;
		}
		return null;
	}

}
