package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.CustomFormItemMapper;
import com.shop.model.CustomFormItemDO;
import com.shop.service.ICustomFormItemService;

@Service
public class CustomFormItemServiceImpl implements ICustomFormItemService {

	@Autowired
	private CustomFormItemMapper customFormItemMapper;

	@Override
	public int deleteCustomFormItemByPrimaryKey(Integer id) {
		return customFormItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertCustomFormItem(CustomFormItemDO record) {
		return customFormItemMapper.insert(record);
	}

	@Override
	public int insertCustomFormItemSelective(CustomFormItemDO record) {
		return customFormItemMapper.insertSelective(record);
	}

	@Override
	public CustomFormItemDO selectCustomFormItemByPrimaryKey(Integer id) {
		return customFormItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CustomFormItemDO> selectAllCustomFormItem() {
		return customFormItemMapper.selectAll();
	}

	@Override
	public int updateCustomFormItemDOByPrimaryKeySelective(CustomFormItemDO record) {
		return customFormItemMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateCustomFormItemDOByPrimaryKey(CustomFormItemDO record) {
		return customFormItemMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<CustomFormItemDO> selectCustomFormItemByFormId(String id) {
		return customFormItemMapper.selectCustomFormItemByFormId(id);
	}

	@Override
	public void deleteCustomFormItemByFormId(String formId) {
		customFormItemMapper.deleteByCustomFormId(formId);
	}
}
