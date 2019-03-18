package com.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.SiteMapper;
import com.shop.model.SiteDO;
import com.shop.service.ISiteService;

@Service
public class SiteServiceImpl implements ISiteService {
	@Autowired
	private SiteMapper siteMapper;

	@Override
	public SiteDO getSiteDO() {
		return siteMapper.getSiteDO();
	}

	@Override
	public void addSite(SiteDO site) {
		siteMapper.addSite(site);
	}

	@Override
	public void updateSiteDO(SiteDO site) {
		siteMapper.updateSiteDO(site);
	}

	@Override
	public String getSiteName() {
		return siteMapper.getSiteName();
	}

}
