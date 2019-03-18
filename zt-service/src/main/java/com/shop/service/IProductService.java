package com.shop.service;

import java.util.List;

import com.shop.model.ProductDO;
import com.shop.vo.Condition;
import com.shop.vo.ProductVO;

public interface IProductService {
	public List<ProductDO> findProductsByCondition(Integer currpage, Integer limit, Condition condition);

	public List<ProductVO> findProductsVOByCondition(Integer currpage, Integer limit, Condition condition);

	public void addProduct(ProductDO productDO) throws RuntimeException;

	public Integer countProduct(Condition condition);

	public List<ProductDO> findSimpleProducts();

	public void updateSaleStatus(Integer id, Integer sale);

	public void updateProduct(ProductDO product);

	public Integer countProductByCategoryId(Integer id);

	/**
	 * 批量修改商品上下架状态
	 * 
	 * @param ids
	 */
	public void batchUpdateProductsSaleOn(List<Integer> ids);

	public void batchUpdateProductPutOff(Integer[] ids);

	public void delProduct(Integer id);

	public ProductDO findProductByCode(String code);

	public ProductDO findProductDoById(Integer id);

	public List<ProductDO> findProductByExtAttr(String name, String value);
}
