package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.CommonContentMapper;
import com.shop.model.CommonContentDO;
import com.shop.service.ICommonContentService;

@Service
public class CommonContentServiceImpl implements ICommonContentService {
	@Autowired
	private CommonContentMapper commonContentMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return commonContentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CommonContentDO record) {
		return commonContentMapper.insert(record);
	}

	@Override
	public int insertSelective(CommonContentDO record) {
		return commonContentMapper.insertSelective(record);
	}

	@Override
	public CommonContentDO selectByPrimaryKey(String id) {
		return commonContentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CommonContentDO record) {
		return commonContentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(CommonContentDO record) {
		return commonContentMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(CommonContentDO record) {
		return commonContentMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CommonContentDO> selectCommonContentByCategoryId(String id) {
		return commonContentMapper.selectCommonContentByCategoryId(id);
	}

	@Override
	public Integer countCommonContent() {
		return commonContentMapper.countCommonContent();
	}

	@Override
	public Integer countCommonContentByCategoryId(String categoryId) {
		return commonContentMapper.countCommonContentByCategoryId(categoryId);
	}

	@Override
	public List<CommonContentDO> selectCommonContentByPage(Integer currpage, Integer limit) {
		int start = (currpage - 1) * limit;
		return commonContentMapper.selectCommonContentByPage(start, limit);
	}

	@Override
	public List<CommonContentDO> selectCommonContentByPageAndCategory(Integer currpage, Integer limit,
			String categoryId) {
		int start = (currpage - 1) * limit;
		return commonContentMapper.selectCommonContentByPageAndCategory(start, limit, categoryId);
	}

}
