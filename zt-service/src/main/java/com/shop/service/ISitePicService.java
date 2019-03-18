package com.shop.service;

import java.util.List;

import com.shop.model.SitePicDO;

public interface ISitePicService {
	public void addSitePic(SitePicDO sitePic);

	public List<SitePicDO> findAllSitePic();

	public SitePicDO findSitePicById(Integer id);

	public Integer findSitePicByPath(String path);

	public void saveSitePic(String data);

	public void updateSitePic(SitePicDO sitePic);

	public void delSitePic(Integer id);
}
