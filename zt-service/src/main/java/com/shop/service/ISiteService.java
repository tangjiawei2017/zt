package com.shop.service;

import com.shop.model.SiteDO;

public interface ISiteService {
	public SiteDO getSiteDO();

	public void addSite(SiteDO site);

	public void updateSiteDO(SiteDO site);

	public String getSiteName();

}
