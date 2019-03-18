package com.shop.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.framework.utils.ResultUtil;
import com.shop.model.InfoCategoryDO;
import com.shop.service.IInfoCategoryService;
import com.shop.service.IInfoContentService;
import com.shop.utils.CommonFileUtils;
import com.shop.utils.IConstant;
import com.shop.utils.ThumbnailatorUtils;

@RestController
@RequestMapping("/info")
public class InfoCategoryController extends BaseController {
	@Autowired
	private IInfoCategoryService infoCategoryService;
	@Autowired
	private IInfoContentService infoContentService;

	@RequestMapping("/showCategory.action")
	public ModelAndView showCategory() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("info_category");
		return mv;
	}

	/**
	 * @param id
	 *            排除的分类id,编辑分类时不应该显示当前分类及其自类
	 * @return
	 */
	@RequestMapping("/infoCateogryListAjax.action")
	public List<InfoCategoryDO> infoCateogryListAjax(String id) {
		List<InfoCategoryDO> list = null;
		if (StringUtils.isNotEmpty(id)) {
			list = infoCategoryService.selectAllInfoCategoryExclude(Integer.valueOf(id));
		} else {
			list = infoCategoryService.findAllCategory();
		}
		return list;
	}

	@RequestMapping("/preAddCategory.action")
	public ModelAndView preAddCategory(Integer parentid) {
		ModelAndView mv = new ModelAndView();
		if (parentid != null) {
			InfoCategoryDO pcategory = this.infoCategoryService.selectInfoCategoryByPrimaryKey(parentid);
			mv.addObject("parentid", parentid);
			mv.addObject("parentName", pcategory.getName());
		}
		mv.setViewName("info_category_edit");
		return mv;
	}

	@RequestMapping("/preEditCategory.action")
	public ModelAndView editCategory(@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mv = new ModelAndView();
		InfoCategoryDO category = this.infoCategoryService.findCategoryAndParentById(id);
		mv.addObject("category", category);
		mv.setViewName("info_category_edit");
		return mv;
	}

	@RequestMapping("/addCategory.action")
	public String addCategory(InfoCategoryDO category) {
		if (StringUtils.isNotEmpty(category.getImage())) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			category.setImage(CommonFileUtils.copyFileAndCompress(category.getImage(), IConstant.INFO, 1,
					ThumbnailatorUtils.INFO_CATEGORY_TYPE));
		}
		Date time = new Date();
		category.setCreated(time);
		category.setModified(time);
		this.infoCategoryService.insertInfoCategorySelective(category);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/updateCategory.action")
	public String updateCategory(InfoCategoryDO category) {
		category.setModified(new Date());
		if (StringUtils.isNotEmpty(category.getImage())
				&& category.getImage().startsWith(File.separator + IConstant.TEMP)) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			category.setImage(CommonFileUtils.copyFileAndCompress(category.getImage(), IConstant.INFO, 1,
					ThumbnailatorUtils.INFO_CATEGORY_TYPE));
		}
		this.infoCategoryService.updateInfoCategoryByPrimaryKeySelective(category);
		return ResultUtil.buildResultJson(true);
	}

	/**
	 * 删除展示分类 业务
	 * 
	 * (1)删除展示分类,有子分类的父节点不允许删除
	 * 
	 * @param id
	 * @param out
	 */
	@RequestMapping("/delCategory.action")
	public String delCategory(@RequestParam(value = "id", required = true) Integer id) {
		Integer code = 0;
		Boolean success = true;
		InfoCategoryDO infocategory = this.infoCategoryService.findCategoryAndChildrenById(id);
		if (infocategory.getChildren() != null && infocategory.getChildren().size() > 0) {
			// 子节点不为空
			code = 1;
			success = false;
		} else {
			Integer count = infoContentService.countInfoContentByCategoryId(id);
			if (count > 0) {
				// 分类下面有商品
				code = 2;
				success = false;
			} else {
				infoCategoryService.deleteInfoCategoryByPrimaryKey(id);
			}
		}
		return ResultUtil.buildResultJson(success, code);
	}

	@RequestMapping("/checkInfoCategoryExist.action")
	public String checkInfoCategoryExist(Integer id, @RequestParam(value = "name", required = true) String name) {
		InfoCategoryDO productCategory = this.infoCategoryService.selectInfoCategoryByName(name);
		if (productCategory == null
				|| (productCategory != null && id != null && id.intValue() == productCategory.getId().intValue())) {
			return ResultUtil.buildResultJson(true);
		} else {
			return ResultUtil.buildResultJson(false);
		}
	}

}
