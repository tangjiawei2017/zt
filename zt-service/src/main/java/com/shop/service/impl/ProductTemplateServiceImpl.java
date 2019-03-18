package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.ProductTemplateMapper;
import com.shop.model.ProductTemplateDO;
import com.shop.service.IProductTemplateService;
import com.shop.utils.ProductTemplateConvert;
import com.shop.vo.ProductTemplateVO;
import com.shop.vo.TemplateFieldsVO;

import net.sf.json.JSONObject;

@Service
public class ProductTemplateServiceImpl implements IProductTemplateService {

	@Autowired
	private ProductTemplateMapper productTemplateMapper;

	@Override
	public int deleteTemplateByPrimaryKey(Integer id) {
		return productTemplateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int addTemplate(ProductTemplateDO template) {
		return productTemplateMapper.insert(template);
	}

	@Override
	public int addTemplateByJsonData(String data) {
		ProductTemplateDO template = new ProductTemplateDO();
		JSONObject dataObj = JSONObject.fromObject(data);
		if (dataObj.has("templateName")) {
			template.setName(dataObj.getString("templateName"));
		}
		if (dataObj.has("basicAttrArray")) {
			template.setBasicattr(dataObj.getJSONArray("basicAttrArray").toString());
		}
		if (dataObj.has("extendAttrArray")) {
			template.setExtendattr(dataObj.getJSONArray("extendAttrArray").toString());
		}
		return addTemplate(template);
	}

	@Override
	public int addTemplateSelective(ProductTemplateDO template) {
		return productTemplateMapper.insertSelective(template);
	}

	@Override
	public ProductTemplateDO selectTemplateByPrimaryKey(Integer id) {
		return productTemplateMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TemplateFieldsVO> selectTemplateFieldsVOByPrimaryKey(Integer id) {
		ProductTemplateDO template = productTemplateMapper.selectByPrimaryKey(id);
		return ProductTemplateConvert.convertProductTempalteDOToTemplateFieldsVO(template);
	}

	@Override
	public ProductTemplateDO selectTemplateByName(String name) {
		return productTemplateMapper.selectByName(name);
	}

	@Override
	public List<ProductTemplateDO> selectAllTemplate() {
		return productTemplateMapper.selectAllTemplate();
	}

	@Override
	public List<ProductTemplateVO> selectAllTemplateVO() {
		List<ProductTemplateDO> templateList = productTemplateMapper.selectAllTemplate();
		return ProductTemplateConvert.convertProductTempalteDOToVO(templateList);
	}

	@Override
	public int updateTempalteByPrimaryKeySelective(ProductTemplateDO record) {
		return productTemplateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateTemplateByPrimaryKey(ProductTemplateDO record) {
		return productTemplateMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateTemplateByJsonData(String data) {
		ProductTemplateDO template = new ProductTemplateDO();
		JSONObject dataObj = JSONObject.fromObject(data);
		if (dataObj.has("templateId")) {
			template.setId(dataObj.getInt("templateId"));
		}
		if (dataObj.has("templateName")) {
			template.setName(dataObj.getString("templateName"));
		}
		if (dataObj.has("basicAttrArray")) {
			template.setBasicattr(dataObj.getJSONArray("basicAttrArray").toString());
		}
		if (dataObj.has("extendAttrArray")) {
			template.setExtendattr(dataObj.getJSONArray("extendAttrArray").toString());
		}
		return updateTempalteByPrimaryKeySelective(template);
	}

	@Override
	public Integer judgeTemplateIsUse(Integer id) {
		return productTemplateMapper.judgeTemplateIsUse(id);
	}

	@Override
	public ProductTemplateDO selectTemplateByProductId(Integer id) {
		return productTemplateMapper.findTemplateDOByProductId(id);
	}
}
