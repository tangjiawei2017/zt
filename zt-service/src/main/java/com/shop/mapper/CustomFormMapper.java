package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.model.CustomFormDO;

public interface CustomFormMapper {
	int deleteByPrimaryKey(String id);

	int insert(CustomFormDO record);

	int insertSelective(CustomFormDO record);

	int countAll();

	CustomFormDO selectByPrimaryKey(String id);

	CustomFormDO selectCustomFormAndItemByPrimaryKey(String id);

	List<CustomFormDO> selectCustomFormPage(@Param("start") int start, @Param("limit") int limit);

	List<CustomFormDO> selectAll();

	int updateByPrimaryKeySelective(CustomFormDO record);

	int updateByPrimaryKey(CustomFormDO record);
}