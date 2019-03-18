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
import com.shop.model.ProductCategoryDO;
import com.shop.model.ProductTemplateDO;
import com.shop.service.IProductCategoryService;
import com.shop.service.IProductService;
import com.shop.service.IProductTemplateService;
import com.shop.utils.CommonFileUtils;
import com.shop.utils.IConstant;
import com.shop.utils.ProductTemplateConvert;
import com.shop.utils.ThumbnailatorUtils;
import com.shop.vo.ProductTemplateVO;

@RestController
@RequestMapping("/product")
public class ProductCategoryController extends BaseController {
	@Autowired
	private IProductCategoryService productCategoryService;
	@Autowired
	private IProductTemplateService productTemplateService;
	@Autowired
	private IProductService productService;

	@RequestMapping("/showCategory.action")
	public ModelAndView showCategory() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("product_category");
		return mv;
	}

	@RequestMapping("/productCateogryListAjax.action")
	public List<ProductCategoryDO> productCateogryListAjax(String id) {
		List<ProductCategoryDO> list = null;
		if (StringUtils.isNotEmpty(id)) {
			list = productCategoryService.selectAllProductCategoryExclude(Integer.valueOf(id));
		} else {
			list = productCategoryService.findAllCategory();
		}
		return list;
	}

	@RequestMapping("/preAddCategory.action")
	public ModelAndView preAddCategory(Integer parentid) {
		ModelAndView mv = new ModelAndView();
		List<ProductTemplateDO> templateList = productTemplateService.selectAllTemplate();
		if (parentid != null) {
			ProductCategoryDO pcategory = this.productCategoryService.findCategoryById(parentid);
			mv.addObject("parentid", parentid);
			mv.addObject("parentName", pcategory.getName());
		}
		mv.addObject("templateList", templateList);
		mv.setViewName("product_category_edit");
		return mv;
	}

	@RequestMapping("/preEditCategory.action")
	public ModelAndView editCategory(@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mv = new ModelAndView();
		ProductCategoryDO productCategory = this.productCategoryService.findCategoryAndParentById(id);
		List<ProductTemplateDO> templateList = productTemplateService.selectAllTemplate();
		mv.addObject("productCategory", productCategory);
		mv.addObject("templateList", templateList);
		mv.setViewName("product_category_edit");
		return mv;
	}

	@RequestMapping("/addCategory.action")
	public String addCategory(ProductCategoryDO productCategory) {
		if (StringUtils.isNotEmpty(productCategory.getImage())) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			productCategory.setImage(CommonFileUtils.copyFileAndCompress(productCategory.getImage(), IConstant.PRODUCT,
					1, ThumbnailatorUtils.PRODUCT_CATEGORY_TYPE));
		}
		this.productCategoryService.addCategory(productCategory);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/updateCategory.action")
	public String updateCategory(ProductCategoryDO productCategory) {
		productCategory.setModified(new Date());
		this.logger.info("updateCategory1========>" + productCategory);
		if (StringUtils.isNotEmpty(productCategory.getImage())
				&& productCategory.getImage().startsWith(File.separator + IConstant.TEMP)) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			productCategory.setImage(CommonFileUtils.copyFileAndCompress(productCategory.getImage(), IConstant.PRODUCT,
					1, ThumbnailatorUtils.PRODUCT_CATEGORY_TYPE));
		}
		this.logger.info("updateCategory2========>" + productCategory);
		this.productCategoryService.updateCategory(productCategory);
		return ResultUtil.buildResultJson(true);
	}

	/**
	 * 删除展示分类 业务
	 * 
	 * (1)删除展示分类,有子分类的父节点不允许删除 (2)分类里包含商品,不允许删除
	 * 
	 * @param id
	 * @param out
	 */
	@RequestMapping("/delCategory.action")
	public String delCategory(@RequestParam(value = "id", required = true) Integer id) {
		Integer code = 0;
		Boolean success = true;
		ProductCategoryDO pcategory = this.productCategoryService.findCategoryAndChildrenById(id);
		if (pcategory.getChildren() != null && pcategory.getChildren().size() > 0) {
			// 子节点不为空
			code = 1;
			success = false;
		} else {
			Integer count = productService.countProductByCategoryId(id);
			if (count > 0) {
				// 分类下面有商品
				code = 2;
				success = false;
			} else {
				productCategoryService.delCategory(id);
			}
		}
		return ResultUtil.buildResultJson(success, code);
	}

	@RequestMapping("/checkProductCategoryExist.action")
	public String checkProductCategoryExist(Integer id, @RequestParam(value = "name", required = false) String name) {
		if (StringUtils.isNotEmpty(name)) {
			ProductCategoryDO productCategory = this.productCategoryService.checkProductCategoryExist(name);
			if (productCategory == null
					|| (productCategory != null && id != null && id.intValue() == productCategory.getId().intValue())) {
				return ResultUtil.buildResultJson(true);
			}
		}
		return ResultUtil.buildResultJson(false);
	}

	@RequestMapping("/selectTemplateByCategory.action")
	public ProductTemplateVO selectTemplateByCategoryId(@RequestParam(value = "id", required = true) Integer id) {
		ProductTemplateDO productTemplateDO = productCategoryService.findCategoryAndTemplateByCId(id).getTemplate();
		return ProductTemplateConvert.convertProductTempalteDOToVO(productTemplateDO);
	}
}
