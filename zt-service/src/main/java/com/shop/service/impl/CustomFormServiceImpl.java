package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.CustomFormMapper;
import com.shop.model.CustomFormDO;
import com.shop.service.ICustomFormService;

@Service
public class CustomFormServiceImpl implements ICustomFormService {
	@Autowired
	private CustomFormMapper customFormMapper;

	@Override
	public int deleteCustomFormByPrimaryKey(String id) {
		return customFormMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertCustomForm(CustomFormDO record) {
		return customFormMapper.insert(record);
	}

	@Override
	public int insertCustomFormSelective(CustomFormDO record) {
		return customFormMapper.insertSelective(record);
	}

	@Override
	public CustomFormDO selectCustomFormByPrimaryKey(String id) {
		return customFormMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CustomFormDO> selectAllCustomForm() {
		return customFormMapper.selectAll();
	}

	@Override
	public int updateCustomFormByPrimaryKeySelective(CustomFormDO record) {
		return customFormMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateCustomFormByPrimaryKey(CustomFormDO record) {
		return customFormMapper.updateByPrimaryKey(record);
	}

	@Override
	public int countCustomForm() {
		return customFormMapper.countAll();
	}

	@Override
	public List<CustomFormDO> selectCustomFormPage(int currpage, int limit) {
		int start = (currpage - 1) * limit;
		List<CustomFormDO> customFormList = this.customFormMapper.selectCustomFormPage(start, limit);
		return customFormList;
	}

	@Override
	public CustomFormDO selectCustomFormAndItemByPrimaryKey(String id) {
		return customFormMapper.selectCustomFormAndItemByPrimaryKey(id);
	}

}
