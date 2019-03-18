package com.shop.service;

import java.util.List;

import com.shop.model.GalleryDO;

public interface IGalleryService {
	int deleteByPrimaryKey(String id);

	int insert(GalleryDO record);

	int insertSelective(GalleryDO record);

	GalleryDO selectByPrimaryKey(String id);

	List<GalleryDO> selectAll();

	int updateByPrimaryKeySelective(GalleryDO record);

	int updateByPrimaryKey(GalleryDO record);
}
