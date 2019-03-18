package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.ProductBrandMapper;
import com.shop.model.ProductBrandDO;
import com.shop.service.IProductBrandService;

@Service
public class ProductBrandServiceImpl implements IProductBrandService {
	@Autowired
	private ProductBrandMapper productBrandMapper;

	@Override
	public int deleteBrandByPrimaryKey(Integer id) {
		return productBrandMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertBrand(ProductBrandDO record) {
		return productBrandMapper.insert(record);
	}

	@Override
	public int insertBrandSelective(ProductBrandDO record) {
		return productBrandMapper.insertSelective(record);
	}

	@Override
	public ProductBrandDO selectProductBrandByPrimaryKey(Integer id) {
		return productBrandMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateProductBrandByPrimaryKeySelective(ProductBrandDO record) {
		return productBrandMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateProductBrandByPrimaryKey(ProductBrandDO record) {
		return productBrandMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ProductBrandDO> selectAllProductBrand() {
		return productBrandMapper.selectAllProductBrand();
	}

	@Override
	public ProductBrandDO checkProductBrandExist(String name) {
		return productBrandMapper.selectProductCategoryByName(name);
	}

}
