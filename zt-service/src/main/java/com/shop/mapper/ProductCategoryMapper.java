package com.shop.mapper;

import java.util.List;

import com.shop.model.ProductCategoryDO;

public interface ProductCategoryMapper {
	public List<ProductCategoryDO> findAllCategory();

	public List<ProductCategoryDO> findAllCategoryAndParent();

	public ProductCategoryDO findCategoryAndProducts(Integer id);

	public ProductCategoryDO findCategoryById(Integer id);

	public ProductCategoryDO findCategoryAndParentById(Integer id);

	public ProductCategoryDO findCategoryAndChildrenById(Integer id);

	public List<ProductCategoryDO> selectAllProductCategoryExclude(Integer id);

	public List<ProductCategoryDO> findCategoryAndChildren();

	public List<ProductCategoryDO> findRootsCategory();

	public void addCategory(ProductCategoryDO productCategory);

	public void updateCategory(ProductCategoryDO productCategory);

	public void delCategory(Integer id);

	public void delCategoryAndChildren(Integer id);

	public Integer findCategoryIdByName(String name);

	public List<Integer> findCategoryIds(String name[]);

	public List<String> findAllSequence();

	public ProductCategoryDO checkProductCategoryExist(String name);

	public List<ProductCategoryDO> findAllCategoryAndProducts();

	public ProductCategoryDO findCategoryAndTemplateByCId(Integer id);
}
