package com.shop.service;

import java.util.List;

import com.shop.model.InfoCategoryDO;

public interface IInfoCategoryService {
	List<InfoCategoryDO> findChildCategoryAndContentByParentId(Integer id);

	List<InfoCategoryDO> findChildCategoryByParentId(Integer id);

	int deleteInfoCategoryByPrimaryKey(Integer id);

	int insertInfoCategory(InfoCategoryDO record);

	int insertInfoCategorySelective(InfoCategoryDO record);

	InfoCategoryDO selectInfoCategoryByPrimaryKey(Integer id);

	InfoCategoryDO findCategoryAndParentById(Integer id);

	int updateInfoCategoryByPrimaryKeySelective(InfoCategoryDO record);

	int updateInfoCategoryByPrimaryKey(InfoCategoryDO record);

	InfoCategoryDO findCategoryAndChildrenById(Integer id);

	InfoCategoryDO selectInfoCategoryByName(String name);

	List<InfoCategoryDO> findAllCategory();

	List<InfoCategoryDO> selectAllInfoCategoryExclude(Integer id);

	List<InfoCategoryDO> findAllCategoryAndChildren();
}
