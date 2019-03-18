package com.shop.mapper;

import com.shop.model.SiteDO;

public interface SiteMapper {
	public SiteDO getSiteDO();

	public void addSite(SiteDO site);

	public void updateSiteDO(SiteDO site);

	public String getSiteName();

	public String getHotLine();

	public void delSitePic(Integer id);

	public void updateSitePic(SiteDO site);
}
