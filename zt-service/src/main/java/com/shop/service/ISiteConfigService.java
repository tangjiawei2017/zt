package com.shop.service;

import java.util.List;
import java.util.Map;

import com.shop.model.SiteConfigDO;

public interface ISiteConfigService {
	public SiteConfigDO getSiteConfigValue(String identifier);

	public void updateSiteConfig(SiteConfigDO siteConfig);

	public void addSiteConfig(SiteConfigDO siteConfig);

	public List<SiteConfigDO> selectAllSiteConfig();

	public Map<String, String> selectAllSiteConfigMap();
}
