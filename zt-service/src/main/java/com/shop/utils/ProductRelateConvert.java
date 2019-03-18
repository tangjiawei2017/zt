package com.shop.utils;

import java.util.ArrayList;
import java.util.List;

import com.shop.vo.ProductRelateVO;
import com.shop.vo.ProductVO;

public class ProductRelateConvert {
	public static ProductRelateVO convertProductVOToRelateVO(ProductVO p, Boolean checked) {
		ProductRelateVO vo = new ProductRelateVO();
		vo.setId(p.getId());
		vo.setCode(p.getCode());
		vo.setName(p.getName());
		vo.setMarketprice(p.getMarketprice());
		vo.setPrice(p.getPrice());
		vo.setSale(p.getSale());
		vo.setStock(p.getStock());
		vo.setUnit(p.getUnit());
		vo.setImage(p.getImage());
		vo.setImages(p.getImages());
		vo.setCollectImg(p.getImage());
		vo.setSpec(p.getSpec());
		vo.setDescription(p.getDescription());
		vo.setCategory(p.getCategory());
		vo.setBrand(p.getBrand());
		vo.setCreated(p.getCreated());
		vo.setModified(p.getModified());
		vo.setExt0(p.getExt0());
		vo.setExt1(p.getExt1());
		vo.setExt2(p.getExt2());
		vo.setExt3(p.getExt3());
		vo.setExt4(p.getExt4());
		vo.setExt5(p.getExt5());
		vo.setExt6(p.getExt6());
		vo.setExt7(p.getExt7());
		vo.setExt8(p.getExt8());
		vo.setExt8(p.getExt9());
		vo.setLAY_CHECKED(checked);
		return vo;
	}

	public static List<ProductRelateVO> convertProductVOToRelateVOLits(List<ProductVO> pList) {
		List<ProductRelateVO> relateList = new ArrayList<ProductRelateVO>();
		if (pList != null) {
			for (ProductVO p : pList) {
				ProductRelateVO vo = new ProductRelateVO();
				vo.setId(p.getId());
				vo.setLAY_CHECKED(false);
				vo.setCode(p.getCode());
				vo.setName(p.getName());
				vo.setMarketprice(p.getMarketprice());
				vo.setPrice(p.getPrice());
				vo.setSale(p.getSale());
				vo.setStock(p.getStock());
				vo.setUnit(p.getUnit());
				vo.setImage(p.getImage());
				vo.setImages(p.getImages());
				vo.setCollectImg(p.getImage());
				vo.setSpec(p.getSpec());
				vo.setDescription(p.getDescription());
				vo.setCategory(p.getCategory());
				vo.setBrand(p.getBrand());
				vo.setCreated(p.getCreated());
				vo.setModified(p.getModified());
				vo.setExt0(p.getExt0());
				vo.setExt1(p.getExt1());
				vo.setExt2(p.getExt2());
				vo.setExt3(p.getExt3());
				vo.setExt4(p.getExt4());
				vo.setExt5(p.getExt5());
				vo.setExt6(p.getExt6());
				vo.setExt7(p.getExt7());
				vo.setExt8(p.getExt8());
				vo.setExt8(p.getExt9());
				relateList.add(vo);
			}
		}
		return relateList;
	}

}
