package com.shop.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.framework.response.ResultJson;
import com.shop.framework.utils.ResultUtil;
import com.shop.model.CommonCategoryDO;
import com.shop.model.CommonContentDO;
import com.shop.service.ICommonCategoryService;
import com.shop.service.ICommonContentService;
import com.shop.utils.CommonCategoryConvert;
import com.shop.vo.CommonCategoryVO;

import net.sf.json.JSONObject;

@SuppressWarnings("all")
@RestController
@RequestMapping("/commonCategory")
public class CommonCategoryController {
	@Autowired
	private ICommonCategoryService commonCategoryService;
	@Autowired
	private ICommonContentService commonContentService;

	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping("/listAjax.action")
	public ResultJson<List<CommonCategoryVO>> listAjax(@RequestParam(value = "page", required = true) Integer currpage,
			@RequestParam(value = "limit", required = true) Integer limit) {
		List<CommonCategoryDO> list = commonCategoryService.selectAllCommonCategory();
		List<CommonCategoryVO> voList = CommonCategoryConvert.convertCategoryDOToVO(list);
		ResultJson<List<CommonCategoryVO>> result = new ResultJson<List<CommonCategoryVO>>();
		result.setCode(0);
		result.setCount(voList.size());
		result.setMsg("success");
		result.setData(voList);
		return result;
	}

	/**
	 * @param id
	 * 
	 * @return
	 */
	@RequestMapping("/cateogryListAjax.action")
	public List<CommonCategoryDO> cateogryListAjax() {
		return commonCategoryService.selectAllCommonCategory();
	}

	@RequestMapping("/preEditcommonCategory.action")
	public ModelAndView preEditcommonCategory(String id) {
		ModelAndView mv = new ModelAndView();
		if (id != null) {
			CommonCategoryDO commonCategory = commonCategoryService.selectByPrimaryKey(id);
			mv.addObject("commonCategory", commonCategory);
		}
		mv.setViewName("common_category_edit");
		return mv;
	}

	@RequestMapping("/editcommonCategory.action")
	public String editcommonCategory(CommonCategoryDO commonCategory) {
		this.logger.info("category===>" + commonCategory);
		if (commonCategory.getId() != null) {
			commonCategoryService.updateByPrimaryKeySelective(commonCategory);
		} else {
			commonCategory.setId(UUID.randomUUID().toString());
			commonCategory.setCtime(new Date());
			commonCategoryService.insert(commonCategory);
		}
		return "success";
	}

	@RequestMapping("/checkCommonCategoryExist.action")
	public String checkCommonCategoryExist(String id, @RequestParam(value = "name", required = true) String name) {
		CommonCategoryDO commonCategory = this.commonCategoryService.selectCommonCategoryByName(name);
		if (commonCategory == null || (commonCategory != null && id != null && id.equals(commonCategory.getId()))) {
			return ResultUtil.buildResultJson(true);
		} else {
			return ResultUtil.buildResultJson(false);
		}
	}

	@RequestMapping("/delCommonCategory.action")
	public String delCommonCategory(String id) {
		JSONObject result = new JSONObject();
		Boolean success = true;
		Integer code = 0;
		List<CommonContentDO> list = commonContentService.selectCommonContentByCategoryId(id);
		if (list != null && list.size() > 0) {
			success = false;
			code = 1;
		} else {
			commonCategoryService.deleteByPrimaryKey(id);
		}
		result.put("success", success);
		result.put("code", code);
		return result.toString();
	}
}
