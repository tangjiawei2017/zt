package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.CommonCategoryMapper;
import com.shop.model.CommonCategoryDO;
import com.shop.service.ICommonCategoryService;

@Service
public class CommonCategoryServiceImpl implements ICommonCategoryService {

	@Autowired
	private CommonCategoryMapper commonCategoryMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return commonCategoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CommonCategoryDO record) {
		return commonCategoryMapper.insert(record);
	}

	@Override
	public int insertSelective(CommonCategoryDO record) {
		return commonCategoryMapper.insertSelective(record);
	}

	@Override
	public CommonCategoryDO selectByPrimaryKey(String id) {
		return commonCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CommonCategoryDO record) {
		return commonCategoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(CommonCategoryDO record) {
		return commonCategoryMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(CommonCategoryDO record) {
		return commonCategoryMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CommonCategoryDO> selectAllCommonCategory() {
		return commonCategoryMapper.selectAllCommonCategory();
	}

	@Override
	public CommonCategoryDO selectCommonCategoryByName(String name) {
		return commonCategoryMapper.selectCommonCategoryByName(name);
	}

}
