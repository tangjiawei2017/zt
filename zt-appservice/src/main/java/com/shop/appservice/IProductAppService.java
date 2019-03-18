package com.shop.appservice;

import com.shop.model.ProductDO;

public interface IProductAppService {
	void delProductByPrimaryKey(Integer id);

	void batchDelProduct(String ids);

	void saveProduct(ProductDO product, String relateIds);

	void updateProduct(ProductDO product, String relateIds);

}
