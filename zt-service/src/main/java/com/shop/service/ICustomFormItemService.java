package com.shop.service;

import java.util.List;

import com.shop.model.CustomFormItemDO;

public interface ICustomFormItemService {
	int deleteCustomFormItemByPrimaryKey(Integer id);

	void deleteCustomFormItemByFormId(String formId);

	int insertCustomFormItem(CustomFormItemDO record);

	int insertCustomFormItemSelective(CustomFormItemDO record);

	CustomFormItemDO selectCustomFormItemByPrimaryKey(Integer id);

	List<CustomFormItemDO> selectCustomFormItemByFormId(String id);

	List<CustomFormItemDO> selectAllCustomFormItem();

	int updateCustomFormItemDOByPrimaryKeySelective(CustomFormItemDO record);

	int updateCustomFormItemDOByPrimaryKey(CustomFormItemDO record);
}
