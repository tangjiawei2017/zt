package com.shop.mapper;

import java.util.List;

import com.shop.model.SiteConfigDO;

public interface SiteConfigMapper {
	public void addSiteConfig(SiteConfigDO siteConfig);

	public SiteConfigDO getSiteConfigValue(String identifier);

	public void updateSiteConfig(SiteConfigDO siteConfig);

	public List<SiteConfigDO> selectAllSiteConfigMap();
}
