package com.shop.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.shop.model.InfoCategoryDO;
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

	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/listAjax.action")
	public List<CommonCategoryDO> listAjax() {
		return commonCategoryService.selectAllCommonCategory();
	}
}
