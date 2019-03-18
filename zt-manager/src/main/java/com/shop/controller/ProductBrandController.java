package com.shop.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.framework.utils.ResultUtil;
import com.shop.model.ProductBrandDO;
import com.shop.service.IProductBrandService;
import com.shop.utils.CommonFileUtils;
import com.shop.utils.IConstant;
import com.shop.utils.ThumbnailatorUtils;

/**
 * @author xiaotang
 * @version 1.0
 * @date 2018-07-24 00:19:24
 */
@RestController
@RequestMapping("/product")
public class ProductBrandController {
	@Autowired
	private IProductBrandService productBrandService;

	@RequestMapping("/showBrand.action")
	public ModelAndView showBrand() {
		ModelAndView mv = new ModelAndView();
		List<ProductBrandDO> list = productBrandService.selectAllProductBrand();
		mv.addObject("brandList", list);
		mv.setViewName("product_brand");
		return mv;
	}

	@RequestMapping("/preAddBrand.action")
	public ModelAndView preAddBrand() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("product_brand_edit");
		return mv;
	}

	@RequestMapping("/preEditBrand.action")
	public ModelAndView preEditBrand(@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mv = new ModelAndView();
		ProductBrandDO brand = productBrandService.selectProductBrandByPrimaryKey(id);
		mv.addObject("brand", brand);
		mv.setViewName("product_brand_edit");
		return mv;
	}

	@RequestMapping("/saveBrand.action")
	public String saveBrand(ProductBrandDO brand) {
		if (brand.getImage() != null) {
			brand.setImage(CommonFileUtils.copyFileAndCompress(brand.getImage(), IConstant.PRODUCT, 1,
					ThumbnailatorUtils.PRODUCT_BRAND_TYPE));
		}
		Date time = new Date();
		brand.setCreatetime(time);
		brand.setUpdatetime(time);
		productBrandService.insertBrand(brand);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/updateBrand.action")
	public String updateBrand(ProductBrandDO brand) {
		if (brand.getImage() != null && brand.getImage().startsWith(File.separator + IConstant.TEMP)) {
			brand.setImage(CommonFileUtils.copyFileAndCompress(brand.getImage(), IConstant.PRODUCT, 1,
					ThumbnailatorUtils.PRODUCT_BRAND_TYPE));
		}
		brand.setUpdatetime(new Date());
		productBrandService.updateProductBrandByPrimaryKeySelective(brand);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/delBrand.action")
	public String delBrand(@RequestParam(value = "id", required = true) Integer id) {
		productBrandService.deleteBrandByPrimaryKey(id);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/checkbrandExist.action")
	public String checkbrandExist(Integer id, @RequestParam(value = "name", required = true) String name) {
		ProductBrandDO productBrand = this.productBrandService.checkProductBrandExist(name);
		if (productBrand == null
				|| (productBrand != null && id != null && id.intValue() == productBrand.getId().intValue())) {
			return ResultUtil.buildResultJson(true);
		} else {
			return ResultUtil.buildResultJson(false);
		}
	}
}
