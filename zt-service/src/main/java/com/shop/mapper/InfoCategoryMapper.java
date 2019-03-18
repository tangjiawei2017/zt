package com.shop.mapper;

import java.util.List;

import com.shop.model.InfoCategoryDO;

public interface InfoCategoryMapper {
	List<InfoCategoryDO> findChildCategoryAndContentByParentId(Integer id);

	List<InfoCategoryDO> findChildCategoryByParentId(Integer id);

	int deleteByPrimaryKey(Integer id);
	
	int insert(InfoCategoryDO record);	

	int insertSelective(InfoCategoryDO record);

	InfoCategoryDO selectByPrimaryKey(Integer id);

	InfoCategoryDO selectInfoCategoryByName(String name);

	InfoCategoryDO findCategoryAndParentById(Integer id);

	public List<InfoCategoryDO> selectAllInfoCateogry();

	List<InfoCategoryDO> selectAllInfoCategoryExclude(Integer id);

	public InfoCategoryDO findCategoryAndChildrenById(Integer id);

	int updateByPrimaryKeySelective(InfoCategoryDO record);

	List<InfoCategoryDO> findAllCategoryAndChildren();

	int updateByPrimaryKey(InfoCategoryDO record);
}