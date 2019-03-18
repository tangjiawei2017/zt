package com.shop.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.InfoContentDO;
import com.shop.vo.InfoContentVO;

public class InfoContentConvert {
	public static List<InfoContentVO> convertInfoContentDOTOVOList(List<InfoContentDO> list) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<InfoContentVO> infoVOList = new ArrayList<InfoContentVO>();
		if (list != null) {
			for (InfoContentDO content : list) {
				InfoContentVO vo = new InfoContentVO();
				vo.setId(content.getId());
				vo.setCategoryId(content.getCategoryId());
				vo.setName(content.getName());
				vo.setContent(content.getContent());
				vo.setImage(content.getImage());
				vo.setSummary(content.getSummary());
				if (content.getCategory() != null) {
					vo.setCategoryName(content.getCategory().getName());
				}
				vo.setCreated(format.format(content.getCreated()));
				infoVOList.add(vo);
			}
		}
		return infoVOList;
	}

	public static InfoContentVO convertInfoContentDOToVO(InfoContentDO content) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		InfoContentVO vo = new InfoContentVO();
		vo.setId(content.getId());
		vo.setCategoryId(content.getCategoryId());
		vo.setName(content.getName());
		vo.setContent(content.getContent());
		vo.setImage(content.getImage());
		vo.setSummary(content.getSummary());
		if (content.getCategory() != null) {
			vo.setCategoryName(content.getCategory().getName());
		}
		vo.setCreated(format.format(content.getCreated()));
		return vo;
	}
}
