package com.shop.mapper;

import java.util.List;

import com.shop.model.CustomFormItemDO;

public interface CustomFormItemMapper {
	int deleteByPrimaryKey(Integer id);

	void deleteByCustomFormId(String formId);

	int insert(CustomFormItemDO record);

	int insertSelective(CustomFormItemDO record);

	CustomFormItemDO selectByPrimaryKey(Integer id);

	List<CustomFormItemDO> selectCustomFormItemByFormId(String id);

	List<CustomFormItemDO> selectAll();

	int updateByPrimaryKeySelective(CustomFormItemDO record);

	int updateByPrimaryKey(CustomFormItemDO record);
}