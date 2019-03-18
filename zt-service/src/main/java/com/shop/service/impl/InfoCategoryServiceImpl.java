package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.InfoCategoryMapper;
import com.shop.model.InfoCategoryDO;
import com.shop.service.IInfoCategoryService;

@Service
public class InfoCategoryServiceImpl implements IInfoCategoryService {

	@Autowired
	private InfoCategoryMapper infoCategoryMapper;

	@Override
	public int deleteInfoCategoryByPrimaryKey(Integer id) {
		return infoCategoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertInfoCategory(InfoCategoryDO record) {
		return infoCategoryMapper.insert(record);
	}

	@Override
	public int insertInfoCategorySelective(InfoCategoryDO record) {
		return infoCategoryMapper.insertSelective(record);
	}

	@Override
	public InfoCategoryDO selectInfoCategoryByPrimaryKey(Integer id) {
		return infoCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateInfoCategoryByPrimaryKeySelective(InfoCategoryDO record) {
		return infoCategoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateInfoCategoryByPrimaryKey(InfoCategoryDO record) {
		return infoCategoryMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<InfoCategoryDO> findAllCategory() {
		return infoCategoryMapper.selectAllInfoCateogry();
	}

	@Override
	public InfoCategoryDO findCategoryAndChildrenById(Integer id) {
		return infoCategoryMapper.findCategoryAndChildrenById(id);
	}

	@Override
	public InfoCategoryDO selectInfoCategoryByName(String name) {
		return infoCategoryMapper.selectInfoCategoryByName(name);
	}

	@Override
	public InfoCategoryDO findCategoryAndParentById(Integer id) {
		return infoCategoryMapper.findCategoryAndParentById(id);
	}

	@Override
	public List<InfoCategoryDO> findAllCategoryAndChildren() {
		return infoCategoryMapper.findAllCategoryAndChildren();
	}

	@Override
	public List<InfoCategoryDO> selectAllInfoCategoryExclude(Integer id) {
		return infoCategoryMapper.selectAllInfoCategoryExclude(id);
	}

	@Override
	public List<InfoCategoryDO> findChildCategoryAndContentByParentId(Integer id) {
		return infoCategoryMapper.findChildCategoryAndContentByParentId(id);
	}

	@Override
	public List<InfoCategoryDO> findChildCategoryByParentId(Integer id) {
		return infoCategoryMapper.findChildCategoryByParentId(id);
	}
}
