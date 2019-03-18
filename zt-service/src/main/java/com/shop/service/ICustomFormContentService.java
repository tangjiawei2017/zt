package com.shop.service;

import java.util.List;

import com.shop.model.CustomFormContentDO;
import com.shop.vo.CustomFormCondition;

public interface ICustomFormContentService {
	int deleteCustomFormContentByPrimaryKey(Integer id);

	int insertCustomFormContent(CustomFormContentDO record);

	int insertCustomFormContentSelective(CustomFormContentDO record);

	CustomFormContentDO selectCustomFormContentByPrimaryKey(Integer id);

	public List<CustomFormContentDO> findCustomFormContentByCondition(Integer currpage, Integer limit,
			CustomFormCondition condition);

	int countCustomFormContent(CustomFormCondition condition);

	List<CustomFormContentDO> selectCustomFormContentByFromId(String formId);

	List<CustomFormContentDO> selectAll();

	int updateCustomFormContentByPrimaryKeySelective(CustomFormContentDO record);

	int updateCustomFormContentByPrimaryKeyWithBLOBs(CustomFormContentDO record);

	int updateCustomFormContentByPrimaryKey(CustomFormContentDO record);
}
