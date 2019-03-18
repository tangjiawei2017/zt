package com.shop.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.appservice.IProductAppService;
import com.shop.framework.response.ResultJson;
import com.shop.framework.utils.ResultUtil;
import com.shop.model.ProductBrandDO;
import com.shop.model.ProductCategoryDO;
import com.shop.model.ProductDO;
import com.shop.model.ProductRelateDO;
import com.shop.service.IProductBrandService;
import com.shop.service.IProductCategoryService;
import com.shop.service.IProductRelateService;
import com.shop.service.IProductService;
import com.shop.utils.CommonUtils;
import com.shop.utils.ProductRelateConvert;
import com.shop.vo.Condition;
import com.shop.vo.ProductRelateVO;
import com.shop.vo.ProductVO;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
	@Autowired
	private IProductCategoryService productCategoryService;
	@Autowired
	private IProductService productService;
	@Autowired
	private IProductAppService productAppService;
	@Autowired
	private IProductBrandService productBrandService;
	@Autowired
	private IProductRelateService productRelateService;

	@RequestMapping("/list.action")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		List<ProductBrandDO> brandList = productBrandService.selectAllProductBrand();
		mv.addObject("brandList", brandList);
		mv.setViewName("product_list");
		return mv;
	}

	@RequestMapping("/productlistAjax.action")
	public ResultJson<List<ProductVO>> productListAjax(@RequestParam(value = "page", required = true) Integer currpage,
			@RequestParam(value = "limit", required = true) Integer limit, Condition condition) {
		Integer count = productService.countProduct(condition);
		List<ProductVO> productList = productService.findProductsVOByCondition(currpage, limit, condition);
		ResultJson<List<ProductVO>> result = new ResultJson<List<ProductVO>>();
		result.setCode(0);
		result.setCount(count);
		result.setMsg("success");
		result.setData(productList);
		return result;
	}

	@RequestMapping("/productRelateListAjax.action")
	public ResultJson<List<ProductRelateVO>> productRelateListAjax(
			@RequestParam(value = "page", required = true) Integer currpage,
			@RequestParam(value = "limit", required = true) Integer limit, Condition condition, Integer id) {
		Integer count = productService.countProduct(condition);
		List<ProductVO> productList = productService.findProductsVOByCondition(currpage, limit, condition);
		List<ProductDO> hasRelateList = new ArrayList<ProductDO>();
		List<ProductRelateVO> productRelateList = new ArrayList<ProductRelateVO>();
		if (id != null) {
			List<ProductRelateDO> list = productRelateService.selectRelateProductByPid(id);
			for (ProductRelateDO relate : list) {
				ProductDO p = relate.getRelateProduct();
				hasRelateList.add(p);
			}
			// 使用迭代器遍历删除
			Iterator<ProductVO> iterator = productList.iterator();
			while (iterator.hasNext()) {
				ProductVO vo = iterator.next();
				if (vo.getId().equals(id)) {
					iterator.remove();
					count = count - 1;
				} else {
					boolean checked = false;
					for (ProductDO p : hasRelateList) {
						if (p.getId().equals(vo.getId())) {
							checked = true;
						}
					}
					ProductRelateVO relateVO = ProductRelateConvert.convertProductVOToRelateVO(vo, checked);
					productRelateList.add(relateVO);
				}
			}
		} else {
			productRelateList = ProductRelateConvert.convertProductVOToRelateVOLits(productList);
		}
		ResultJson<List<ProductRelateVO>> result = new ResultJson<List<ProductRelateVO>>();
		result.setCode(0);
		result.setCount(count);
		result.setMsg("success");
		result.setData(productRelateList);
		return result;
	}

	@RequestMapping("/preAddProduct.action")
	public ModelAndView preAddProduct() {
		ModelAndView mv = new ModelAndView();
		// 设置默认类型(第一个没有子分类的类型)
		ProductCategoryDO category = null;
		List<ProductCategoryDO> categoryList = productCategoryService.findCategoryAndChildren();
		for (ProductCategoryDO pc : categoryList) {
			if (pc.getChildren().size() == 0) {
				category = pc;
				break;
			}
		}
		List<ProductBrandDO> brandList = productBrandService.selectAllProductBrand();
		mv.addObject("category", category);
		mv.addObject("brandList", brandList);
		mv.setViewName("product_add");
		return mv;
	}

	@RequestMapping("/preEditProduct.action")
	public ModelAndView predEditProduct(@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mv = new ModelAndView();
		ProductDO product = this.productService.findProductDoById(id);
		List<ProductBrandDO> brandList = productBrandService.selectAllProductBrand();
		List<ProductRelateDO> relateList = this.productRelateService.selectRelateProductByPid(id);
		if (relateList != null && relateList.size() > 0) {
			List<ProductDO> productRelateList = new ArrayList<ProductDO>();
			for (ProductRelateDO relate : relateList) {
				productRelateList.add(relate.getRelateProduct());
			}
			mv.addObject("productRelateList", productRelateList);
		}
		mv.addObject("product", product);
		mv.addObject("brandList", brandList);
		mv.setViewName("product_edit");
		return mv;
	}

	@RequestMapping("/delProduct.action")
	public String delProduct(@RequestParam(value = "id", required = true) Integer id) {
		this.productAppService.delProductByPrimaryKey(id);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/batchDelProduct.action")
	public String batchDelProduct(@RequestParam(value = "ids", required = true) String ids) {
		this.productAppService.batchDelProduct(ids);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/batchPutOnProduct.action")
	public String batchPutOnProduct(@RequestParam(value = "ids", required = true) String ids) {
		this.productService.batchUpdateProductsSaleOn(CommonUtils.convertStringToListInt(ids));
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/batchPutOffProduct.action")
	public String batchPutOffProduct(@RequestParam(value = "ids", required = true) String ids) {
		this.productService.batchUpdateProductPutOff(CommonUtils.convertStringToArrayInt(ids));
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/addProduct.action")
	public String saveProduct(ProductDO product, String relateIds) {
		this.logger.info("product====>" + product);
		productAppService.saveProduct(product, relateIds);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/updateProduct.action")
	public String updateProduct(ProductDO product, String relateIds) {
		this.productAppService.updateProduct(product, relateIds);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/updateSaleStatus.action")
	public String updateProductSaleStatus(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "sale", required = true) Integer sale) {
		this.productService.updateSaleStatus(id, sale);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/checkProductCodeExist.action")
	public String checkProductCodeExist(Integer id, @RequestParam(value = "code", required = false) String code) {
		if (StringUtils.isNotEmpty(code)) {
			ProductDO prodcut = this.productService.findProductByCode(code);
			if (prodcut == null || (prodcut != null && id != null && id.intValue() == prodcut.getId().intValue())) {
				return ResultUtil.buildResultJson(true);
			}
		}
		return ResultUtil.buildResultJson(false);
	}

	@RequestMapping("/preModifyCategory.action")
	public ModelAndView preModifyCategory() {
		ModelAndView mv = new ModelAndView();
		List<ProductCategoryDO> categorys = this.productCategoryService.findAllCategory();
		mv.addObject("productCategoryList", categorys);
		mv.setViewName("product_category_modify");
		return mv;
	}

}
