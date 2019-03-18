package com.shop.utils;

import com.shop.api.vo.SiteApiVO;
import com.shop.model.SiteDO;

/**
 * @author xiaotang
 * @version 1.0
 * @date 2018-09-10 00:01:57
 */
public class SiteConvert {
	public static SiteApiVO convertSiteDOToApiVO(SiteDO site) {
		SiteApiVO apiVO = new SiteApiVO();
		apiVO.setSiteName(site.getSiteName());
		apiVO.setLogo(site.getLogo());
		return apiVO;
	}
}
