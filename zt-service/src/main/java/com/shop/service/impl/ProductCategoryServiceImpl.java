package com.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.ProductCategoryMapper;
import com.shop.model.ProductCategoryDO;
import com.shop.service.IProductCategoryService;
import com.shop.utils.CommonUtils;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
	@Autowired
	private ProductCategoryMapper productCategoryMapper;

	@Override
	public List<ProductCategoryDO> findAllCategory() {
		return productCategoryMapper.findAllCategory();
	}

	@Override
	public List<ProductCategoryDO> findAllCategoryAndParent() {
		return productCategoryMapper.findAllCategoryAndParent();
	}

	@Override
	public ProductCategoryDO findCategoryById(Integer id) {
		return productCategoryMapper.findCategoryById(id);
	}

	@Override
	public void addCategory(ProductCategoryDO productCategory) {
		Date time = new Date();
		productCategory.setCreated(time);
		productCategory.setModified(time);
		productCategory.setSequence(createSequence());
		productCategoryMapper.addCategory(productCategory);
	}

	@Override
	public List<ProductCategoryDO> findAllCategoryAndProducts() {
		return productCategoryMapper.findAllCategoryAndProducts();
	}

	@Override
	public ProductCategoryDO findCategoryAndParentById(Integer id) {
		return productCategoryMapper.findCategoryAndParentById(id);
	}

	@Override
	public ProductCategoryDO findCategoryAndChildrenById(Integer id) {
		return productCategoryMapper.findCategoryAndChildrenById(id);
	}

	@Override
	public void updateCategory(ProductCategoryDO productCategory) {
		productCategoryMapper.updateCategory(productCategory);
	}

	@Override
	public void delCategory(Integer id) {
		productCategoryMapper.delCategory(id);
	}

	@Override
	public void delCategoryAndChildren(Integer id) {
		productCategoryMapper.delCategoryAndChildren(id);
	}

	@Override
	public Integer findCategoryIdByName(String name) {
		return productCategoryMapper.findCategoryIdByName(name);
	}

	@Override
	public List<Integer> findCategoryIds(String[] name) {
		return productCategoryMapper.findCategoryIds(name);
	}

	/**
	 * 创建唯一的序列
	 */
	private String createSequence() {
		List<String> sequences = productCategoryMapper.findAllSequence();
		String str = "";
		while (true) {
			str = CommonUtils.getRandomString(10);
			if (!sequences.contains(str)) {
				break;
			}
		}
		return str;
	}

	public ProductCategoryDO checkProductCategoryExist(String name) {
		return this.productCategoryMapper.checkProductCategoryExist(name);
	}

	@Override
	public List<ProductCategoryDO> findCategoryAndChildren() {
		return this.productCategoryMapper.findCategoryAndChildren();
	}

	@Override
	public ProductCategoryDO findCategoryAndTemplateByCId(Integer id) {
		return this.productCategoryMapper.findCategoryAndTemplateByCId(id);
	}

	@Override
	public List<ProductCategoryDO> selectAllProductCategoryExclude(Integer id) {
		return this.productCategoryMapper.selectAllProductCategoryExclude(id);
	}

	@Override
	public ProductCategoryDO findCategoryAndProducts(Integer id) {
		return this.productCategoryMapper.findCategoryAndProducts(id);
	}

	@Override
	public List<ProductCategoryDO> findRootsCategory() {
		return this.productCategoryMapper.findRootsCategory();
	}

}
