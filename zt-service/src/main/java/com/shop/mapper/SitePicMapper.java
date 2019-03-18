package com.shop.mapper;

import java.util.List;

import com.shop.model.SitePicDO;

public interface SitePicMapper {
	public void addSitePic(SitePicDO sitePic);

	public List<SitePicDO> findAllSitePic();

	public SitePicDO findSitePicById(Integer id);

	public Integer findSitePicByPath(String path);

	public void updateSitePic(SitePicDO sitePic);
	
	public void delSitePic(Integer id);

}
