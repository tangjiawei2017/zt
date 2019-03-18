package com.shop.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.framework.utils.ResultUtil;
import com.shop.model.SiteConfigDO;
import com.shop.model.SiteDO;
import com.shop.service.ISiteConfigService;
import com.shop.service.ISiteService;
import com.shop.utils.CommonFileUtils;
import com.shop.utils.IConstant;

@RequestMapping("/site")
@RestController
public class SiteContoller extends BaseController {
	@Autowired
	private ISiteService siteService;
	@Autowired
	private ISiteConfigService siteConfigService;

	@RequestMapping("/config.action")
	public ModelAndView config() {
		ModelAndView mv = new ModelAndView();
		SiteDO site = siteService.getSiteDO();
		mv.addObject("site", site);
		mv.setViewName("site");
		return mv;
	}

	@RequestMapping("/aboutWe.action")
	public ModelAndView aboutWe() {
		ModelAndView mv = new ModelAndView();
		SiteDO site = siteService.getSiteDO();
		mv.addObject("site", site);
		mv.setViewName("aboutus");
		return mv;
	}

	@RequestMapping("/updateSite.action")
	public String updateSite(SiteDO site) {
		// 拷贝临时文件到 正式环境,并返回新的路径结果
		// logo 处理：原图大小
		site.setLogo(CommonFileUtils.copyFile(site.getLogo(), IConstant.SITE));
		SiteDO osite = siteService.getSiteDO();
		if (osite == null) {
			site.setCtime(new Date());
			siteService.addSite(site);
		} else {
			site.setId(osite.getId());
			siteService.updateSiteDO(site);
		}
		return ResultUtil.buildResultJson(true);
	}

	/**
	 * 网店运营状态页面
	 * 
	 * @return
	 */
	@RequestMapping("/siteRunSetting.action")
	public ModelAndView siteRunSetting() {
		ModelAndView mv = new ModelAndView();
		SiteConfigDO config = siteConfigService.getSiteConfigValue(IConstant.SITE_RUN_STATE);
		mv.addObject("siteConfig", config);
		mv.setViewName("site_run_state");
		return mv;
	}

	/**
	 * 设置网店运营状态
	 * 
	 * @param state
	 * @param content
	 */
	@RequestMapping("/saveSiteRunState.action")
	public String saveSiteRunState(Integer state, String content) {
		SiteConfigDO siteConfig = new SiteConfigDO();
		siteConfig.setIdentifier(IConstant.SITE_RUN_STATE);
		siteConfig.setValue(String.valueOf(state));
		siteConfig.setJsonvalue(content);
		SiteConfigDO site = siteConfigService.getSiteConfigValue(IConstant.SITE_RUN_STATE);
		if (site == null) {
			siteConfigService.addSiteConfig(siteConfig);
		} else {
			siteConfigService.updateSiteConfig(siteConfig);
		}
		return ResultUtil.buildResultJson(true);
	}
}
