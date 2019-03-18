package com.shop.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.api.vo.InfoCategoryApiVO;
import com.shop.framework.response.ResultJson;
import com.shop.framework.utils.ResultUtil;
import com.shop.model.InfoCategoryDO;
import com.shop.service.IInfoCategoryService;
import com.shop.utils.InfoCategoryConvert;

/**
 * 获取所有新闻分类
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-10-13 17:43:10
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/infoCategory")
public class InfoCategoryApi {
	@Autowired
	private IInfoCategoryService infoCategoryService;

	/**
	 * 获取资讯一级分类下的二级分类信息(不包含产品)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/SecondCategory.action", method = RequestMethod.POST)
	public ResultJson<List<InfoCategoryApiVO>> SecondCategory(@RequestParam(value = "parentid") Integer parentId) {
		List<InfoCategoryDO> list = infoCategoryService.findChildCategoryByParentId(parentId);
		return ResultUtil.success(InfoCategoryConvert.convertInfoCategoryDOTOVOList(list));
	}

	/**
	 * 获取资讯一级分类下的二级分类信息(包含产品)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/SecondCategoryAndContent.action", method = RequestMethod.POST)
	public ResultJson<List<InfoCategoryApiVO>> SecondCategoryAndContent(
			@RequestParam(value = "parentid") Integer parentId) {
		List<InfoCategoryDO> list = infoCategoryService.findChildCategoryAndContentByParentId(parentId);
		return ResultUtil.success(InfoCategoryConvert.convertInfoCategoryDOTOVOList(list));
	}
}
