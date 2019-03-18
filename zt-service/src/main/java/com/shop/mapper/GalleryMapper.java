package com.shop.mapper;

import java.util.List;

import com.shop.model.GalleryDO;

public interface GalleryMapper {
	int deleteByPrimaryKey(String id);

	int insert(GalleryDO record);

	int insertSelective(GalleryDO record);

	List<GalleryDO> selectAll();

	GalleryDO selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(GalleryDO record);

	int updateByPrimaryKey(GalleryDO record);
}