package com.shop.utils;

import java.util.ArrayList;
import java.util.List;

import com.shop.api.vo.ProductCategoryVO;
import com.shop.model.ProductCategoryDO;

public class ProductCategoryConvert {
	public static List<ProductCategoryVO> convertProductCategoryDOToVOList(List<ProductCategoryDO> list) {
		List<ProductCategoryVO> pvoList = new ArrayList<ProductCategoryVO>();
		if (list != null) {
			for (ProductCategoryDO category : list) {
				ProductCategoryVO vo = new ProductCategoryVO();
				vo.setId(category.getId());
				vo.setName(category.getName());
				vo.setImage(category.getImage());
				vo.setProductList(ProductConvert.convertProductDOToApiVOList(category.getProductList()));
				pvoList.add(vo);
			}
		}
		return pvoList;
	}

	public static ProductCategoryVO convertProductCategoryDOToVO(ProductCategoryDO category) {
		ProductCategoryVO vo = new ProductCategoryVO();
		if (category != null) {
			vo.setId(category.getId());
			vo.setName(category.getName());
			vo.setImage(category.getImage());
			vo.setProductList(ProductConvert.convertProductDOToApiVOList(category.getProductList()));
		}
		return vo;
	}
}
