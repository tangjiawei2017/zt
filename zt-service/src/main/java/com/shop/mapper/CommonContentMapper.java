package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.model.CommonContentDO;

public interface CommonContentMapper {
	int deleteByPrimaryKey(String id);

	int insert(CommonContentDO record);

	int insertSelective(CommonContentDO record);

	CommonContentDO selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(CommonContentDO record);

	int updateByPrimaryKeyWithBLOBs(CommonContentDO record);

	int updateByPrimaryKey(CommonContentDO record);

	List<CommonContentDO> selectCommonContentByCategoryId(String categoryId);

	List<CommonContentDO> selectCommonContentByPage(@Param("start") Integer start, @Param("limit") Integer limit);

	List<CommonContentDO> selectCommonContentByPageAndCategory(@Param("start") Integer start,
			@Param("limit") Integer limit, @Param("categoryId") String categoryId);

	Integer countCommonContent();

	Integer countCommonContentByCategoryId(String categoryId);
}