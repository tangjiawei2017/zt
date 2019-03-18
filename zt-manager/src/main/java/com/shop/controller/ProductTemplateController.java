package com.shop.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.framework.utils.ResultUtil;
import com.shop.model.ProductTemplateDO;
import com.shop.service.IProductTemplateService;
import com.shop.utils.ProductTemplateConvert;
import com.shop.vo.ProductTemplateVO;
import com.shop.vo.TemplateFieldsVO;

/**
 * 商品属性模板controller
 * 
 * @author:xiaotang
 * @date:2018-07-16 00:28:42
 */
@RestController
@RequestMapping("/productTemplate")
public class ProductTemplateController {
	@Autowired
	private IProductTemplateService productTemplateService;

	@RequestMapping("/list.action")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		List<ProductTemplateVO> list = productTemplateService.selectAllTemplateVO();
		mv.addObject("templateList", list);
		mv.setViewName("product_template");
		return mv;
	}

	@RequestMapping("/preAddTemplate.action")
	public ModelAndView preAddTemplate() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("product_template_edit");
		return mv;
	}

	@RequestMapping("/preEditTemplate.action")
	public ModelAndView preEditTemplate(@RequestParam(value = "id", required = true) Integer templateId) {
		ModelAndView mv = new ModelAndView();
		ProductTemplateDO template = productTemplateService.selectTemplateByPrimaryKey(templateId);
		List<TemplateFieldsVO> templateFields = ProductTemplateConvert
				.convertProductTempalteDOToTemplateFieldsVO(template);
		mv.addObject("template", template);
		mv.addObject("templateFields", templateFields);
		mv.setViewName("product_template_edit");
		return mv;
	}

	/**
	 * 添加模板 参数 json格式
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping("/addTemplate.action")
	public String addTemplate(@RequestParam(value = "data", required = true) String data) {
		productTemplateService.addTemplateByJsonData(data);
		return ResultUtil.buildResultJson(true);
	}

	/**
	 * 更新模板 参数 json格式
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping("/updateTemplate.action")
	public String updateTemplate(@RequestParam(value = "data", required = true) String data) {
		productTemplateService.updateTemplateByJsonData(data);
		return ResultUtil.buildResultJson(true);
	}

	/**
	 * 删除模板
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("/delTemplate.action")
	public String delTemplate(@RequestParam(value = "id", required = true) Integer id) {
		productTemplateService.deleteTemplateByPrimaryKey(id);
		return ResultUtil.buildResultJson(true);
	}

	/**
	 * 判断模板名称是否存在
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("/checkTemplateExist.action")
	public String checkTemplateExist(Integer id, @RequestParam(value = "name", required = true) String name) {
		ProductTemplateDO template = this.productTemplateService.selectTemplateByName(name);
		if (template == null || (template != null && id != null && id.intValue() == template.getId().intValue())) {
			return ResultUtil.buildResultJson(true);
		} else {
			return ResultUtil.buildResultJson(false);
		}
	}

	@RequestMapping("/preEditTemplateExtendAttrValue.action")
	public ModelAndView preEditTemplateExtendAttrValue(String data) {
		ModelAndView mv = new ModelAndView();
		if (StringUtils.isNotEmpty(data)) {
			mv.addObject("extendAttrValueArray", data.split(";"));
		}
		mv.setViewName("product_template_value");
		return mv;
	}

	@RequestMapping("/checkTemplateIsUse.action")
	public String checkTemplateIsUse(Integer id) {
		Integer count = productTemplateService.judgeTemplateIsUse(id);
		boolean success = count == 0 ? true : false;
		return ResultUtil.buildResultJson(success);
	}

}
