package com.shop.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.framework.response.ResultJson;
import com.shop.framework.utils.ResultUtil;
import com.shop.model.CustomFormContentDO;
import com.shop.model.CustomFormDO;
import com.shop.model.CustomFormItemDO;
import com.shop.service.ICustomFormContentService;
import com.shop.service.ICustomFormItemService;
import com.shop.service.ICustomFormService;
import com.shop.utils.CommonUtils;
import com.shop.utils.CustomFormItemConvert;
import com.shop.utils.JsonConfigFactory;
import com.shop.vo.CustomFormCondition;
import com.shop.vo.CustomFormDetaiVO;
import com.shop.vo.CustomFormItemVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@SuppressWarnings("all")
@RequestMapping("/customForm")
public class CustomFormController {
	@Autowired
	private ICustomFormService customFormService;
	@Autowired
	private ICustomFormItemService customFormItemService;
	@Autowired
	private ICustomFormContentService customFormContentService;

	private Logger logger = Logger.getLogger(CustomFormController.class);

	@RequestMapping("/list.action")
	public ModelAndView list(@RequestParam("id") String id) {
		ModelAndView mv = new ModelAndView();
		CustomFormDO customForm = customFormService.selectCustomFormByPrimaryKey(id);
		mv.addObject("customForm", customForm);
		mv.setViewName("customForm_list");
		return mv;
	}

	@RequestMapping(value = "/customFormItemList.action", method = RequestMethod.POST)
	public ResultJson<List<CustomFormItemVO>> customFormItemList(String id) {
		List<CustomFormItemVO> list = CustomFormItemConvert
				.convertCustomFormDOToVOList(customFormItemService.selectCustomFormItemByFormId(id));
		return ResultUtil.success(list);
	}

	@RequestMapping("/customFormContentListAjax.action")
	public void customFormConentListAjax(@RequestParam(value = "page", required = true) Integer currpage,
			@RequestParam(value = "limit", required = true) Integer limit, String ctime, CustomFormCondition condition,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject result = new JSONObject();
		Integer code = 0, count = 0;
		String msg = "success";
		JSONArray data = null;
		try {
			CommonUtils.convertStringToDate(ctime, condition);
			count = customFormContentService.countCustomFormContent(condition);
			List<CustomFormContentDO> contentList = customFormContentService.findCustomFormContentByCondition(currpage,
					limit, condition);
			data = JSONArray.fromObject(contentList, JsonConfigFactory.getInstance());
		} catch (Exception e) {
			this.logger.error(e);
			code = 1;
			count = 0;
			msg = "fail";
		}
		result.put("code", code);
		result.put("msg", msg);
		result.put("count", count);
		result.put("data", data);
		try {
			response.getWriter().print(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/customFormContentDetail.action")
	public ModelAndView customFormContentDetail(String formId, Integer contentId) {
		ModelAndView mv = new ModelAndView();
		List<CustomFormDetaiVO> list = new ArrayList<CustomFormDetaiVO>();
		List<CustomFormItemDO> itemList = customFormItemService.selectCustomFormItemByFormId(formId);
		CustomFormContentDO formContent = customFormContentService.selectCustomFormContentByPrimaryKey(contentId);
		for (int i = 0; i < itemList.size(); i++) {
			CustomFormDetaiVO detail = new CustomFormDetaiVO();
			detail.setName(itemList.get(i).getItemName());
			detail.setType(itemList.get(i).getType());
			Method method;
			try {
				method = formContent.getClass().getMethod("getExt" + (i + 1), null);
				detail.setValue((String) method.invoke(formContent, null));
			} catch (Exception e) {
				this.logger.error("exception", e);
			}
			list.add(detail);
		}
		mv.setViewName("customForm_detail");
		mv.addObject("customFormContent", formContent);
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping(value = "/updatecustomFormContent.action")
	public String updatecustomFormContent(CustomFormContentDO content) {
		if (StringUtils.isNotEmpty(content.getResult())) {
			content.setStatus(1);
			customFormContentService.updateCustomFormContentByPrimaryKeySelective(content);
		}
		return "success";
	}
}
