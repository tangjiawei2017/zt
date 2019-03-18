package com.shop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shop.api.vo.SiteApiVO;
import com.shop.controller.BaseController;
import com.shop.framework.response.ResultJson;
import com.shop.framework.utils.ResultUtil;
import com.shop.model.SiteConfigDO;
import com.shop.model.SiteDO;
import com.shop.service.ISiteConfigService;
import com.shop.service.ISiteService;
import com.shop.utils.IConstant;
import com.shop.utils.SiteConvert;

import net.sf.json.JSONObject;

@RestController
@SuppressWarnings("all")
@RequestMapping("/site")
public class SiteApi extends BaseController {
	@Autowired
	private ISiteService siteService;
	@Autowired
	private ISiteConfigService siteConfigService;

	@RequestMapping(value = "/getSite.action", method = RequestMethod.POST)
	public ResultJson<SiteApiVO> getSite() {
		SiteDO site = siteService.getSiteDO();
		SiteApiVO siteApi = SiteConvert.convertSiteDOToApiVO(site);
		return ResultUtil.success(siteApi);
	}

	@RequestMapping(value = "/getSiteColse.action", method = RequestMethod.POST)
	public JSONObject getSiteColse() {
		JSONObject result = new JSONObject();
		SiteConfigDO config = siteConfigService.getSiteConfigValue(IConstant.SITE_RUN_STATE);
		result.put("result", config.getJsonvalue());
		return result;
	}

}
