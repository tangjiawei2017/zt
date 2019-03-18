package com.shop.service;

import java.util.List;

import com.shop.model.CommonContentDO;

public interface ICommonContentService {
	int deleteByPrimaryKey(String id);

	int insert(CommonContentDO record);

	int insertSelective(CommonContentDO record);

	CommonContentDO selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(CommonContentDO record);

	int updateByPrimaryKeyWithBLOBs(CommonContentDO record);

	int updateByPrimaryKey(CommonContentDO record);

	List<CommonContentDO> selectCommonContentByCategoryId(String id);

	List<CommonContentDO> selectCommonContentByPage(Integer currpage, Integer limit);

	List<CommonContentDO> selectCommonContentByPageAndCategory(Integer currpage, Integer limit, String categoryId);

	Integer countCommonContent();

	Integer countCommonContentByCategoryId(String categoryId);
}
