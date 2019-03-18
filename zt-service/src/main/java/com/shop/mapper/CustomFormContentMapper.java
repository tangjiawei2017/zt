package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.model.CustomFormContentDO;
import com.shop.vo.CustomFormCondition;

public interface CustomFormContentMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CustomFormContentDO record);

	int insertSelective(CustomFormContentDO record);

	CustomFormContentDO selectByPrimaryKey(Integer id);

	public List<CustomFormContentDO> findCustomFormContentByCondition(@Param(value = "start") Integer start,
			@Param(value = "limit") Integer limit, @Param("condition") CustomFormCondition condition);

	int countCustomFormContent(CustomFormCondition condition);

	List<CustomFormContentDO> selectByFromId(String formId);

	List<CustomFormContentDO> selectAll();

	int updateByPrimaryKeySelective(CustomFormContentDO record);

	int updateByPrimaryKeyWithBLOBs(CustomFormContentDO record);

	int updateByPrimaryKey(CustomFormContentDO record);
}