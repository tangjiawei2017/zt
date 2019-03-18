package com.shop.utils;

import com.shop.api.vo.GalleryApiVO;
import com.shop.model.GalleryDO;

public class GalleryConvert {
	public static GalleryApiVO convertGalleryDOToApiVO(GalleryDO gallery) {
		GalleryApiVO apiVO = new GalleryApiVO();
		apiVO.setName(gallery.getName());
		apiVO.setPath(gallery.getPath());
		apiVO.setContent(gallery.getContent());
		apiVO.setMpath(gallery.getMpath());
		return apiVO;
	}
}
