package com.shop.utils;

import java.util.ArrayList;
import java.util.List;

import com.shop.model.ProductCategoryDO;
import com.shop.model.ProductTemplateDO;
import com.shop.vo.ProductTemplateVO;
import com.shop.vo.TemplateFieldsVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProductTemplateConvert {

	public static List<ProductTemplateVO> convertProductTempalteDOToVO(List<ProductTemplateDO> list) {
		List<ProductTemplateVO> templateVoList = new ArrayList<ProductTemplateVO>();
		if (list != null) {
			for (ProductTemplateDO templateDO : list) {
				ProductTemplateVO vo = new ProductTemplateVO();
				vo.setId(templateDO.getId());
				vo.setName(templateDO.getName());
				vo.setBasicattr(templateDO.getBasicattr());
				vo.setExtendattr(templateDO.getExtendattr());
				StringBuffer sb = new StringBuffer();
				for (ProductCategoryDO category : templateDO.getCategoryList()) {
					sb.append(category.getName()).append(";  ");
				}
				vo.setCategorys(sb.toString());
				vo.setIsdefault(templateDO.getIsdefault());
				templateVoList.add(vo);
			}
		}
		return templateVoList;
	}

	public static ProductTemplateVO convertProductTempalteDOToVO(ProductTemplateDO templateDO) {
		ProductTemplateVO vo = new ProductTemplateVO();
		vo.setId(templateDO.getId());
		vo.setName(templateDO.getName());
		vo.setBasicattr(templateDO.getBasicattr());
		vo.setExtendattr(templateDO.getExtendattr());
		return vo;
	}

	public static List<TemplateFieldsVO> convertProductTempalteDOToTemplateFieldsVO(ProductTemplateDO template) {
		List<TemplateFieldsVO> list = new ArrayList<TemplateFieldsVO>();
		String basicAttr = template.getBasicattr();
		String extendAttr = template.getExtendattr();
		JSONArray basicArray = JSONArray.fromObject(basicAttr);
		JSONArray extendArray = JSONArray.fromObject(extendAttr);
		for (int i = 0; i < basicArray.size(); i++) {
			JSONObject basicObj = basicArray.getJSONObject(i);
			TemplateFieldsVO fieldsVO = new TemplateFieldsVO();
			fieldsVO.setCode(basicObj.getString("code"));
			// 商品编号和商品名称 必填,必须显示
			if (fieldsVO.getCode().equals("code") || fieldsVO.getCode().equals("name")) {
				fieldsVO.setIsRead(true);
			}
			fieldsVO.setName(basicObj.getString("name"));
			fieldsVO.setShowName(basicObj.getString("showName"));
			fieldsVO.setUnit(basicObj.getString("unit"));
			fieldsVO.setIsShow(basicObj.getBoolean("isShow"));
			fieldsVO.setIsNeed(basicObj.getBoolean("isNeed"));
			fieldsVO.setIsBasic(true);
			list.add(fieldsVO);
		}
		for (int j = 0; j < extendArray.size(); j++) {
			JSONObject basicObj = extendArray.getJSONObject(j);
			TemplateFieldsVO fieldsVO = new TemplateFieldsVO();
			fieldsVO.setCode(basicObj.getString("code"));
			fieldsVO.setName(basicObj.getString("name"));
			fieldsVO.setType(basicObj.getInt("type"));
			fieldsVO.setIsShow(basicObj.getBoolean("isShow"));
			fieldsVO.setIsNeed(basicObj.getBoolean("isNeed"));
			fieldsVO.setValue(basicObj.getString("value"));
			fieldsVO.setIsBasic(false);
			list.add(fieldsVO);
		}
		return list;
	}

}
