package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.CustomFormContentMapper;
import com.shop.model.CustomFormContentDO;
import com.shop.service.ICustomFormContentService;
import com.shop.vo.CustomFormCondition;

@Service
public class CustomFormContentServiceImpl implements ICustomFormContentService {

	@Autowired
	private CustomFormContentMapper customFormContentMapper;

	@Override
	public int deleteCustomFormContentByPrimaryKey(Integer id) {
		return customFormContentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertCustomFormContent(CustomFormContentDO record) {
		return customFormContentMapper.insert(record);
	}

	@Override
	public int insertCustomFormContentSelective(CustomFormContentDO record) {
		return customFormContentMapper.insertSelective(record);
	}

	@Override
	public CustomFormContentDO selectCustomFormContentByPrimaryKey(Integer id) {
		return customFormContentMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CustomFormContentDO> selectCustomFormContentByFromId(String formId) {
		return customFormContentMapper.selectByFromId(formId);
	}

	@Override
	public List<CustomFormContentDO> selectAll() {
		return customFormContentMapper.selectAll();
	}

	@Override
	public int updateCustomFormContentByPrimaryKeySelective(CustomFormContentDO record) {
		return customFormContentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateCustomFormContentByPrimaryKeyWithBLOBs(CustomFormContentDO record) {
		return customFormContentMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateCustomFormContentByPrimaryKey(CustomFormContentDO record) {
		return customFormContentMapper.updateByPrimaryKey(record);
	}

	@Override
	public int countCustomFormContent(CustomFormCondition condition) {
		return customFormContentMapper.countCustomFormContent(condition);
	}

	@Override
	public List<CustomFormContentDO> findCustomFormContentByCondition(Integer currpage, Integer limit,
			CustomFormCondition condition) {
		int start = (currpage - 1) * limit;
		return customFormContentMapper.findCustomFormContentByCondition(start, limit, condition);
	}

}
