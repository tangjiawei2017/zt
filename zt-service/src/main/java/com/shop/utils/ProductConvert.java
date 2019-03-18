package com.shop.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.shop.api.vo.ProductApiVO;
import com.shop.model.ProductDO;
import com.shop.vo.ProductVO;

public class ProductConvert {
	public static List<ProductVO> convertProductDOToVO(List<ProductDO> list) {
		List<ProductVO> voList = new ArrayList<ProductVO>();
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		if (list != null) {
			for (ProductDO p : list) {
				ProductVO vo = new ProductVO();
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
				vo.setDescription(p.getDescription());
				vo.setCategory(p.getProductCategory().getName());
				vo.setBrand(p.getBrand().getName());
				vo.setCreated(format.format(p.getCreated()));
				vo.setModified(format.format(p.getModified()));
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
				voList.add(vo);
			}
		}
		return voList;
	}

	public static List<ProductApiVO> convertProductDOToApiVOList(List<ProductDO> list) {
		List<ProductApiVO> voList = new ArrayList<ProductApiVO>();
		if (list != null && list.size() > 0) {
			for (ProductDO p : list) {
				ProductApiVO vo = new ProductApiVO();
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
				vo.setDescription(p.getDescription());
				vo.setBrand(p.getBrand().getName());
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
				voList.add(vo);
			}
		}
		return voList;
	}

	public static ProductApiVO convertProductDOToApiVO(ProductDO p) {
		ProductApiVO vo = new ProductApiVO();
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
		vo.setDescription(p.getDescription());
		vo.setBrand(p.getBrand().getName());
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
		return vo;
	}
}
