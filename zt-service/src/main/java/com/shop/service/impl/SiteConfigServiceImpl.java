package com.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.SiteConfigMapper;
import com.shop.model.SiteConfigDO;
import com.shop.service.ISiteConfigService;

@Service
public class SiteConfigServiceImpl implements ISiteConfigService {
	@Autowired
	private SiteConfigMapper siteConfigMapper;

	@Override
	public SiteConfigDO getSiteConfigValue(String identifier) {
		return siteConfigMapper.getSiteConfigValue(identifier);
	}

	@Override
	public void updateSiteConfig(SiteConfigDO siteConfig) {
		siteConfigMapper.updateSiteConfig(siteConfig);
	}

	@Override
	public void addSiteConfig(SiteConfigDO siteConfig) {
		siteConfigMapper.addSiteConfig(siteConfig);
	}

	@Override
	public List<SiteConfigDO> selectAllSiteConfig() {
		return siteConfigMapper.selectAllSiteConfigMap();
	}

	@Override
	public Map<String, String> selectAllSiteConfigMap() {
		List<SiteConfigDO> list = selectAllSiteConfig();
		Map<String, String> map = new HashMap<String, String>();
		for (SiteConfigDO config : list) {
			map.put(config.getIdentifier(), config.getValue());
		}
		return map;
	}
}
