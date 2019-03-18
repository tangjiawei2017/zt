package com.shop.service;

import java.util.List;

import com.shop.model.ProductTemplateDO;
import com.shop.vo.ProductTemplateVO;
import com.shop.vo.TemplateFieldsVO;

public interface IProductTemplateService {
	int deleteTemplateByPrimaryKey(Integer id);

	int addTemplate(ProductTemplateDO template);

	int addTemplateByJsonData(String data);

	Integer judgeTemplateIsUse(Integer id);

	int updateTemplateByJsonData(String data);

	int addTemplateSelective(ProductTemplateDO template);

	ProductTemplateDO selectTemplateByPrimaryKey(Integer id);

	ProductTemplateDO selectTemplateByProductId(Integer id);

	List<TemplateFieldsVO> selectTemplateFieldsVOByPrimaryKey(Integer id);

	ProductTemplateDO selectTemplateByName(String name);

	List<ProductTemplateDO> selectAllTemplate();

	List<ProductTemplateVO> selectAllTemplateVO();

	int updateTempalteByPrimaryKeySelective(ProductTemplateDO record);

	int updateTemplateByPrimaryKey(ProductTemplateDO record);
}
