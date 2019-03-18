package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.ProductRelateMapper;
import com.shop.model.ProductRelateDO;
import com.shop.service.IProductRelateService;

@Service
public class ProductRelateServiceImpl implements IProductRelateService {
	@Autowired
	private ProductRelateMapper productRelateMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return productRelateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ProductRelateDO record) {
		return productRelateMapper.insert(record);
	}

	@Override
	public int insertSelective(ProductRelateDO record) {
		return productRelateMapper.insertSelective(record);
	}

	@Override
	public ProductRelateDO selectByPrimaryKey(Integer id) {
		return productRelateMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductRelateDO record) {
		return productRelateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ProductRelateDO record) {
		return productRelateMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ProductRelateDO> selectRelateProductByPid(Integer id) {
		return productRelateMapper.selectRelateProductByPid(id);
	}

	@Override
	public void deleteProductRelate(Integer id) {
		productRelateMapper.deleteProductRelate(id);
	}

	@Override
	public void deleteProductRelateByPid(Integer id) {
		productRelateMapper.deleteProductRelateByPid(id);
	}
}
