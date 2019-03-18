package com.shop.service;

import java.util.List;

import com.shop.model.ProductRelateDO;

public interface IProductRelateService {

	int deleteByPrimaryKey(Integer id);

	void deleteProductRelateByPid(Integer id);

	void deleteProductRelate(Integer id);

	int insert(ProductRelateDO record);

	int insertSelective(ProductRelateDO record);

	ProductRelateDO selectByPrimaryKey(Integer id);

	List<ProductRelateDO> selectRelateProductByPid(Integer id);

	int updateByPrimaryKeySelective(ProductRelateDO record);

	int updateByPrimaryKey(ProductRelateDO record);
}