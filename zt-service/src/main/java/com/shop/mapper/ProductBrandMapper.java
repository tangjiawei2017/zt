package com.shop.mapper;

import java.util.List;

import com.shop.model.ProductBrandDO;

public interface ProductBrandMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ProductBrandDO record);

	int insertSelective(ProductBrandDO record);

	ProductBrandDO selectByPrimaryKey(Integer id);

	List<ProductBrandDO> selectAllProductBrand();

	int updateByPrimaryKeySelective(ProductBrandDO record);

	int updateByPrimaryKey(ProductBrandDO record);

	public ProductBrandDO selectProductCategoryByName(String name);
}