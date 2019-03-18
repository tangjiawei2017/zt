package com.shop.service;

import java.util.List;

import com.shop.model.CustomFormDO;

public interface ICustomFormService {
	int deleteCustomFormByPrimaryKey(String id);

	int insertCustomForm(CustomFormDO record);

	int countCustomForm();

	int insertCustomFormSelective(CustomFormDO record);

	CustomFormDO selectCustomFormByPrimaryKey(String id);

	CustomFormDO selectCustomFormAndItemByPrimaryKey(String id);

	List<CustomFormDO> selectCustomFormPage(int currpage, int limit);

	List<CustomFormDO> selectAllCustomForm();

	int updateCustomFormByPrimaryKeySelective(CustomFormDO record);

	int updateCustomFormByPrimaryKey(CustomFormDO record);
}
