package com.shop.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

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
import com.shop.model.InfoCategoryDO;
import com.shop.model.InfoContentDO;
import com.shop.service.IInfoCategoryService;
import com.shop.service.IInfoContentService;
import com.shop.utils.CommonFileUtils;
import com.shop.utils.CommonUtils;
import com.shop.utils.IConstant;
import com.shop.utils.InfoContentConvert;
import com.shop.utils.WebSiteConstant;
import com.shop.vo.InfoContentVO;

@RestController
@RequestMapping("/info")
public class InfoContentController {
	@Autowired
	private IInfoContentService infoContentService;
	@Autowired
	private IInfoCategoryService infoCategoryService;

	@RequestMapping("/infoContentListAjax.action")
	public ResultJson<List<InfoContentVO>> infoContentListAjax(
			@RequestParam(value = "page", required = true) Integer currpage,
			@RequestParam(value = "limit", required = true) Integer limit, Integer categoryId) {
		Integer count = null;
		List<InfoContentDO> infoContentList = null;
		if (categoryId == null || categoryId == -1) {
			count = infoContentService.countInfoContent();
			infoContentList = infoContentService.selectInfoContentByPage(currpage, limit);
		} else {
			count = infoContentService.countInfoContentByCategoryId(categoryId);
			infoContentList = infoContentService.selectInfoContentByPageAndCategory(currpage, limit, categoryId);
		}
		List<InfoContentVO> infoContentVOList = InfoContentConvert.convertInfoContentDOTOVOList(infoContentList);
		ResultJson<List<InfoContentVO>> result = new ResultJson<List<InfoContentVO>>();
		result.setCode(0);
		result.setCount(count);
		result.setMsg("success");
		result.setData(infoContentVOList);
		return result;
	}

	@RequestMapping("/preAddInfoContent.action")
	public ModelAndView preAddInfoContent() {
		ModelAndView mv = new ModelAndView();
		// 设置默认类型(第一个没有子分类的类型)
		InfoCategoryDO category = null;
		List<InfoCategoryDO> categoryList = infoCategoryService.findAllCategoryAndChildren();
		for (InfoCategoryDO pc : categoryList) {
			if (pc.getChildren().size() == 0) {
				category = pc;
				break;
			}
		}
		mv.addObject("category", category);
		mv.setViewName("info_content_edit");
		return mv;
	}

	@RequestMapping("/preEditInfoContent.action")
	public ModelAndView predEditInfoContent(@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mv = new ModelAndView();
		InfoContentDO infoContent = this.infoContentService.selectInfoContentAndCategoryByPrimaryKey(id);
		mv.addObject("infoContent", infoContent);
		mv.setViewName("info_content_edit");
		return mv;
	}

	@RequestMapping("/delInfoContent.action")
	public String delInfoContent(@RequestParam(value = "id", required = true) Integer id) {
		this.infoContentService.deleteInfoContentByPrimaryKey(id);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/batchDelInfoContent.action")
	public String batchDelInfoContent(@RequestParam(value = "ids", required = true) String ids) {
		this.infoContentService.batchDelInfoContent(ids);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/addInfoContent.action")
	public String addInfoContent(InfoContentDO infoContent) {
		Date time = new Date();
		infoContent.setCreated(time);
		infoContent.setModified(time);
		// 资讯缩略图
		if (StringUtils.isNotEmpty(infoContent.getImage())) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			infoContent.setImage(CommonFileUtils.copyFile(infoContent.getImage(), IConstant.INFO));
		}
		setInfoContentImages(infoContent);
		this.infoContentService.insertInfoContentSelective(infoContent);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/updateInfoContent.action")
	public String updateInfoContent(InfoContentDO infoContent) {
		// 资讯缩略图
		if (StringUtils.isNotEmpty(infoContent.getImage())) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			infoContent.setImage(CommonFileUtils.copyFile(infoContent.getImage(), IConstant.INFO));
		}
		setInfoContentImages(infoContent);
		this.infoContentService.updateInfoContentByPrimaryKeySelective(infoContent);
		return ResultUtil.buildResultJson(true);
	}

	// 处理资讯内容图片
	private void setInfoContentImages(InfoContentDO infoContent) throws RuntimeException {
		File dirs = new File(WebSiteConstant.INFO);
		if (!dirs.exists()) {
			dirs.mkdirs();
		}
		String desc = infoContent.getContent();
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
			infoContent.setContent(doc.html());
		}
	}
}
