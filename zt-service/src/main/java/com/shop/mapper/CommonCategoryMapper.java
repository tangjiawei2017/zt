package com.shop.mapper;

import java.util.List;

import com.shop.model.CommonCategoryDO;

public interface CommonCategoryMapper {
	int deleteByPrimaryKey(String id);

	int insert(CommonCategoryDO record);

	int insertSelective(CommonCategoryDO record);

	CommonCategoryDO selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(CommonCategoryDO record);

	int updateByPrimaryKeyWithBLOBs(CommonCategoryDO record);

	int updateByPrimaryKey(CommonCategoryDO record);

	List<CommonCategoryDO> selectAllCommonCategory();

	CommonCategoryDO selectCommonCategoryByName(String name);
}