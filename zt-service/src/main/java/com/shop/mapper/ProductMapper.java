package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.model.ProductDO;
import com.shop.vo.Condition;

public interface ProductMapper {

	public List<ProductDO> findProductsByCondition(@Param("start") Integer start, @Param("limit") Integer limit,
			@Param("condition") Condition condition);

	public void addProduct(ProductDO productDO);

	public List<ProductDO> findSimpleProducts();

	public List<ProductDO> findProductByName(String name);

	public ProductDO findProductDoById(Integer id);

	public Integer countProductByCategoryId(Integer id);


	public void updateProduct(ProductDO productDO);

	/**
	 * 批量修改商品上下架状态
	 * 
	 * @param ids
	 */
	public void updateProductsSaleOn(List<Integer> ids);

	public void updateProductsPutOff(Integer[] ids);

	public void delProduct(Integer id);

	public Integer countProduct(Condition condition);

	public void updateProductStatus(@Param("id") Integer id, @Param("sale") Integer sale);

	public ProductDO findProductByCode(String code);

	public List<ProductDO> findProductByExtAttr(@Param("name") String name, @Param("value") String value);
}
