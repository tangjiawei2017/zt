package com.shop.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.appservice.ICustomFormAppService;
import com.shop.framework.response.ResultJson;
import com.shop.framework.utils.ResultUtil;
import com.shop.model.CustomFormContentDO;
import com.shop.model.CustomFormDO;
import com.shop.service.ICustomFormContentService;
import com.shop.service.ICustomFormService;

/**
 * 自定义表单
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-08-20 23:41:03
 */
@RestController
@RequestMapping("/customForm")
public class CustomFormController {
	@Autowired
	private ICustomFormService customFormService;
	@Autowired
	private ICustomFormAppService customFormAppService;
	@Autowired
	private ICustomFormContentService customFormContentService;
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * @param currpage
	 * @param limit
	 * @param condition
	 * @param response
	 */
	@RequestMapping("/listAjax.action")
	public ResultJson<List<CustomFormDO>> listAjax(@RequestParam(value = "page", required = true) Integer currpage,
			@RequestParam(value = "limit", required = true) Integer limit) {
		logger.info("=========>customFormListAjax");
		Integer count = customFormService.countCustomForm();
		List<CustomFormDO> customFormList = customFormService.selectCustomFormPage(currpage, limit);
		ResultJson<List<CustomFormDO>> result = new ResultJson<List<CustomFormDO>>();
		result.setCode(0);
		result.setCount(count);
		result.setMsg("success");
		result.setData(customFormList);
		return result;
	}

	@RequestMapping("/preAddCustomForm.action")
	public ModelAndView preAddCustomForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("custom_form_edit");
		return mv;
	}

	@RequestMapping("/preEditCustomForm.action")
	public ModelAndView preEditTemplate(@RequestParam(value = "id", required = true) String formId) {
		ModelAndView mv = new ModelAndView();
		CustomFormDO customForm = customFormService.selectCustomFormAndItemByPrimaryKey(formId);
		mv.addObject("customForm", customForm);
		mv.setViewName("custom_form_edit");
		return mv;
	}

	@RequestMapping("/addCustomForm.action")
	public String addCustomForm(@RequestParam(value = "data", required = true) String data) {
		this.logger.info("data========>" + data);
		customFormAppService.addCustomForm(data);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/updateCustomForm.action")
	public String updateCustomForm(@RequestParam(value = "data", required = true) String data) {
		customFormAppService.updateCustomForm(data);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/delCustomForm.action")
	public String delCustomForm(@RequestParam(value = "id", required = true) String formId) {
		List<CustomFormContentDO> list = customFormContentService.selectCustomFormContentByFromId(formId);
		boolean success = true;
		// code 0 代表成功 1代表有数据
		Integer code = 0;
		if (list.size() == 0) {
			customFormAppService.delCustomForm(formId);
		} else {
			// 表单已经有数据提交,不能删除
			success = false;
			code = 1;
		}
		return ResultUtil.buildResultJson(success, code);
	}

	@RequestMapping("/preEditCustomFormAttrValue.action")
	public ModelAndView preEditTemplateExtendAttrValue(String data) {
		ModelAndView mv = new ModelAndView();
		if (StringUtils.isNotEmpty(data)) {
			mv.addObject("customFormItemValueArray", data.split(";"));
		}
		mv.setViewName("custom_form_item_value");
		return mv;
	}

	@RequestMapping("/CustomFormDetail.action")
	public ModelAndView CustomFormDetail(@RequestParam(value = "id", required = true) String formId) {
		ModelAndView mv = new ModelAndView();
		CustomFormDO customForm = customFormService.selectCustomFormAndItemByPrimaryKey(formId);
		mv.addObject("customForm", customForm);
		mv.setViewName("custom_form_detail");
		return mv;
	}

	@RequestMapping("/addCustomFormContent.action")
	public void addCustomFormContent() {

	}
}
