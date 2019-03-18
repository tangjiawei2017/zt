package com.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.framework.utils.ResultUtil;
import com.shop.model.SiteConfigDO;
import com.shop.service.ISiteConfigService;
import com.shop.utils.IConstant;

@RestController
@RequestMapping("/siteServiceConfig")
public class SiteServiceConfigController {
	@Autowired
	private ISiteConfigService siteConfigService;

	@RequestMapping("/list.action")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		List<SiteConfigDO> list = siteConfigService.selectAllSiteConfig();
		for (SiteConfigDO config : list) {
			mv.addObject(config.getIdentifier(), config.getValue());
		}
		mv.setViewName("site_service_config");
		return mv;
	}

	@RequestMapping("/updateSiteService.action")
	public String updateSiteService(HttpServletRequest request) {
		String serviceArray[] = new String[] { IConstant.PRODUCT_SERVICE, IConstant.NEWS_SERVICE,
				IConstant.CUSTOMER_SERVICE, IConstant.CUSTOM_FORM_SERVICE, IConstant.COMCONTENT_SERVICE,
				IConstant.MESSAGE_SERVICE, "ggService" };
		for (String str : serviceArray) {
			String service = request.getParameter(str);
			SiteConfigDO config = new SiteConfigDO();
			config.setIdentifier(str);
			config.setValue(service);
			siteConfigService.updateSiteConfig(config);
		}
		return ResultUtil.buildResultJson(true);
	}
}
