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

/**
 * 系统设置
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-08-23 13:50:42
 */
@RestController
@RequestMapping("/systemConfig")
public class SystemConfigController {
	@Autowired
	private ISiteConfigService siteConfigService;

	@RequestMapping("/list.action")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		List<SiteConfigDO> list = siteConfigService.selectAllSiteConfig();
		for (SiteConfigDO config : list) {
			mv.addObject(config.getIdentifier(), config.getValue());
		}
		mv.setViewName("system_config");
		return mv;
	}

	@RequestMapping("/updateSystemSetting.action")
	public String updateSiteService(HttpServletRequest request) {
		String serviceArray[] = new String[] { IConstant.SYSTEM_SITE_RUN_STATE };
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
