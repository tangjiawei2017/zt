package com.shop.service;

import java.util.List;

import com.shop.model.ProductBrandDO;

public interface IProductBrandService {
	int deleteBrandByPrimaryKey(Integer id);

	int insertBrand(ProductBrandDO record);

	int insertBrandSelective(ProductBrandDO record);

	List<ProductBrandDO> selectAllProductBrand();

	ProductBrandDO selectProductBrandByPrimaryKey(Integer id);

	int updateProductBrandByPrimaryKeySelective(ProductBrandDO record);

	int updateProductBrandByPrimaryKey(ProductBrandDO record);

	ProductBrandDO checkProductBrandExist(String name);

}
