package com.shop.utils;

import java.util.ArrayList;
import java.util.List;

import com.shop.api.vo.InfoCategoryApiVO;
import com.shop.model.InfoCategoryDO;

public class InfoCategoryConvert {
	public static List<InfoCategoryApiVO> convertInfoCategoryDOTOVOList(List<InfoCategoryDO> list) {
		List<InfoCategoryApiVO> infoVOList = new ArrayList<InfoCategoryApiVO>();
		if (list != null) {
			for (InfoCategoryDO content : list) {
				InfoCategoryApiVO vo = new InfoCategoryApiVO();
				vo.setId(content.getId());
				vo.setName(content.getName());
				vo.setImage(content.getImage());
				vo.setInfoContentList(InfoContentConvert.convertInfoContentDOTOVOList(content.getInfoContentList()));
				infoVOList.add(vo);
			}
		}
		return infoVOList;
	}
}
