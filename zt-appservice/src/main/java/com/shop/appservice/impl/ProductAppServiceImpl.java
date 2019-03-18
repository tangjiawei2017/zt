package com.shop.appservice.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.appservice.IProductAppService;
import com.shop.model.ProductDO;
import com.shop.model.ProductRelateDO;
import com.shop.service.IProductRelateService;
import com.shop.service.IProductService;

/**
 * 商品Appservice
 * 
 * @author:xiaotang
 * @date:2018-07-15 21:16:50
 */
@Service
public class ProductAppServiceImpl implements IProductAppService {
	@Autowired
	private IProductService productService;
	@Autowired
	private IProductRelateService productRelateService;

	@Override
	public void delProductByPrimaryKey(Integer id) {
		// 删除商品
		this.productService.delProduct(id);
		// 删除关联商品
		productRelateService.deleteProductRelate(id);
	}

	/*
	 * 批量删除商品
	 */
	@Override
	public void batchDelProduct(String ids) {
		String[] array = ids.split(",");
		for (String id : array) {
			this.delProductByPrimaryKey(Integer.valueOf(id));
		}
	}

	/*
	 * 添加商品
	 */
	@Override
	public void saveProduct(ProductDO product, String relateIds) {
		// 保存商品
		this.productService.addProduct(product);
		ProductDO p = this.productService.findProductByCode(product.getCode());
		// 保存商品关联
		if (StringUtils.isNotEmpty(relateIds)) {
			for (String id : relateIds.split(",")) {
				ProductRelateDO relate = new ProductRelateDO();
				relate.setProductId(p.getId());
				relate.setRelateId(Integer.valueOf(id));
				this.productRelateService.insert(relate);
			}
		}
	}

	@Override
	public void updateProduct(ProductDO product, String relateIds) {
		this.productService.updateProduct(product);
		// 先删除 product的商品关联
		this.productRelateService.deleteProductRelateByPid(product.getId());
		// 保存商品关联
		if (StringUtils.isNotEmpty(relateIds)) {
			for (String id : relateIds.split(",")) {
				ProductRelateDO relate = new ProductRelateDO();
				relate.setProductId(product.getId());
				relate.setRelateId(Integer.valueOf(id));
				this.productRelateService.insert(relate);
			}
		}
	}
}
