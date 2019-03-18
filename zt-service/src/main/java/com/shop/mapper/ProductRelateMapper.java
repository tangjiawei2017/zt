package com.shop.mapper;

import java.util.List;

import com.shop.model.ProductRelateDO;

public interface ProductRelateMapper {
	int deleteByPrimaryKey(Integer id);

	void deleteProductRelate(Integer id);

	void deleteProductRelateByPid(Integer id);

	int insert(ProductRelateDO record);

	int insertSelective(ProductRelateDO record);

	ProductRelateDO selectByPrimaryKey(Integer id);

	List<ProductRelateDO> selectRelateProductByPid(Integer id);

	int updateByPrimaryKeySelective(ProductRelateDO record);

	int updateByPrimaryKey(ProductRelateDO record);
}