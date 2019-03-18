package com.shop.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import com.shop.model.InfoContentDO;
import com.shop.service.ICommonCategoryService;
import com.shop.service.ICommonContentService;
import com.shop.service.impl.CommonCategoryServiceImpl;
import com.shop.service.impl.CommonContentServiceImpl;
import com.shop.utils.CommonContentConvert;
import com.shop.utils.CommonFileUtils;
import com.shop.utils.CommonUtils;
import com.shop.utils.IConstant;
import com.shop.utils.InfoContentConvert;
import com.shop.utils.ThumbnailatorUtils;
import com.shop.utils.WebSiteConstant;
import com.shop.vo.CommonContentVO;
import com.shop.vo.InfoContentVO;

/**
 * 通用内容设计
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-17 01:39:00
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/commonContent")
public class CommonContentController {
	@Autowired
	private ICommonContentService commonContentService;
	@Autowired
	private ICommonCategoryService commonCategoryService;

	@RequestMapping("/commonContentListAjax.action")
	public ResultJson<List<CommonContentVO>> infoContentListAjax(
			@RequestParam(value = "page", required = true) Integer currpage,
			@RequestParam(value = "limit", required = true) Integer limit, String categoryId) {
		Integer count = null;
		List<CommonContentDO> commonContentList = null;
		List<CommonCategoryDO> categoryList = commonCategoryService.selectAllCommonCategory();
		if (categoryId == null || categoryId.equals("-1")) {
			count = commonContentService.countCommonContent();
			commonContentList = commonContentService.selectCommonContentByPage(currpage, limit);
		} else {
			count = commonContentService.countCommonContentByCategoryId(categoryId);
			commonContentList = commonContentService.selectCommonContentByPageAndCategory(currpage, limit, categoryId);
		}
		List<CommonContentVO> voList = CommonContentConvert.convertCommonContentDOToVO(commonContentList, categoryList);
		ResultJson<List<CommonContentVO>> result = new ResultJson<List<CommonContentVO>>();
		result.setCode(0);
		result.setCount(count);
		result.setMsg("success");
		result.setData(voList);
		return result;
	}

	@RequestMapping("/preAddCommonContent.action")
	public ModelAndView preAddInfoContent() {
		ModelAndView mv = new ModelAndView();
		List<CommonCategoryDO> categoryList = this.commonCategoryService.selectAllCommonCategory();
		mv.addObject("categoryList", categoryList);
		mv.setViewName("common_content_edit");
		return mv;
	}

	@RequestMapping("/preEditCommonContent.action")
	public ModelAndView predEditInfoContent(@RequestParam(value = "id", required = true) String id) {
		ModelAndView mv = new ModelAndView();
		CommonContentDO commonContent = this.commonContentService.selectByPrimaryKey(id);
		List<CommonCategoryDO> categoryList = this.commonCategoryService.selectAllCommonCategory();
		mv.addObject("commonContent", commonContent);
		mv.addObject("categoryList", categoryList);
		mv.setViewName("common_content_edit");
		return mv;
	}

	@RequestMapping("/delCommonContent.action")
	public String delInfoContent(@RequestParam(value = "id", required = true) String id) {
		this.commonContentService.deleteByPrimaryKey(id);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/addCommonContent.action")
	public String addInfoContent(CommonContentDO commonContent) {
		Date time = new Date();
		commonContent.setId(UUID.randomUUID().toString());
		commonContent.setCtime(time);
		setCommonContentImages(commonContent);
		this.commonContentService.insertSelective(commonContent);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/updateCommonContent.action")
	public String updateInfoContent(CommonContentDO commonContent) {
		setCommonContentImages(commonContent);
		this.commonContentService.updateByPrimaryKeySelective(commonContent);
		return ResultUtil.buildResultJson(true);
	}

	// 处理资讯内容图片
	private void setCommonContentImages(CommonContentDO commonContent) throws RuntimeException {
		File dirs = new File(WebSiteConstant.INFO);
		if (!dirs.exists()) {
			dirs.mkdirs();
		}
		String desc = commonContent.getContent();
		if (desc != null) {
			Document doc = Jsoup.parse(desc);
			Elements elements = doc.getElementsByTag("img");
			for (Element element : elements) {
				String filePath = element.attr("src");
				String suffix = filePath.substring(filePath.lastIndexOf('.'), filePath.length());
				String imgName = CommonUtils.getRandomString(10);
				String path = WebSiteConstant.CLUSTER + filePath;
				File oldFile = new File(path);
				if (filePath.startsWith(File.separator + IConstant.TEMP)) {
					// 图片路径以 /temp 开头,则需要从临时目录拷贝到 图片目录
					File newFile = new File(dirs, imgName + suffix);
					if (CommonFileUtils.copyFile(oldFile, newFile)) {
						StringBuffer sb = new StringBuffer();
						sb.append(File.separator).append(IConstant.REPOSITORY).append(File.separator)
								.append(IConstant.INFO).append(File.separator).append(imgName).append(suffix);
						element.attr("src", sb.toString());
					}
				}
			}
			commonContent.setContent(doc.html());
		}
	}
}
