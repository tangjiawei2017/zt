package com.shop.mapper;

import java.util.List;

import com.shop.model.ProductTemplateDO;

public interface ProductTemplateMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ProductTemplateDO record);

	int insertSelective(ProductTemplateDO record);

	ProductTemplateDO selectByPrimaryKey(Integer id);

	ProductTemplateDO findTemplateDOByProductId(Integer id);

	Integer judgeTemplateIsUse(Integer id);

	ProductTemplateDO selectByName(String name);

	int updateByPrimaryKeySelective(ProductTemplateDO record);

	int updateByPrimaryKey(ProductTemplateDO record);

	List<ProductTemplateDO> selectAllTemplate();
}