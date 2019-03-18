package com.shop.api;

import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Connection.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.api.vo.ProductCategoryVO;
import com.shop.framework.response.ResultJson;
import com.shop.framework.utils.ResultUtil;
import com.shop.model.ProductCategoryDO;
import com.shop.service.IProductCategoryService;
import com.shop.utils.ProductCategoryConvert;

@SuppressWarnings("all")
@RestController
@RequestMapping("/productCategory")
public class ProductCategoryApi {
	@Autowired
	private IProductCategoryService productCategoryService;

	private Logger logger = Logger.getLogger(ProductCategoryApi.class);

	/**
	 * 获取所有分类并包含商品
	 */
	@RequestMapping(value = "/list.action", method = RequestMethod.POST)
	public ResultJson<List<ProductCategoryVO>> list() {
		List<ProductCategoryDO> list = productCategoryService.findAllCategoryAndProducts();
		return ResultUtil.success(ProductCategoryConvert.convertProductCategoryDOToVOList(list));
	}

	/**
	 * 只获取分类集合,并不获取分类下商品(只获取一级分类,不显示二级分类)
	 */
	@RequestMapping(value = "/RootCategory.action", method = RequestMethod.POST)
	public ResultJson<List<ProductCategoryVO>> miniList() {
		List<ProductCategoryDO> list = productCategoryService.findRootsCategory();
		return ResultUtil.success(ProductCategoryConvert.convertProductCategoryDOToVOList(list));
	}

	/**
	 * @param id
	 *            获取单个分类并包含商品
	 * @return
	 */
	@RequestMapping(value = "/category.action", method = RequestMethod.POST)
	public ResultJson<List<ProductCategoryVO>> category(@RequestParam("cid") Integer id) {
		ProductCategoryDO productCategory = productCategoryService.findCategoryAndProducts(id);
		return ResultUtil.success(ProductCategoryConvert.convertProductCategoryDOToVO(productCategory));
	}
}
