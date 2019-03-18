package com.shop.service;

import java.util.List;

import com.shop.model.CommonCategoryDO;

public interface ICommonCategoryService {
	int deleteByPrimaryKey(String id);

	int insert(CommonCategoryDO record);

	int insertSelective(CommonCategoryDO record);

	CommonCategoryDO selectByPrimaryKey(String id);

	CommonCategoryDO selectCommonCategoryByName(String name);

	int updateByPrimaryKeySelective(CommonCategoryDO record);

	int updateByPrimaryKeyWithBLOBs(CommonCategoryDO record);

	int updateByPrimaryKey(CommonCategoryDO record);

	List<CommonCategoryDO> selectAllCommonCategory();
}
