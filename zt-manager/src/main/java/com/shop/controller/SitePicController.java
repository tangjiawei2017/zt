package com.shop.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.model.SitePicDO;
import com.shop.service.ISitePicService;

/**
 * 首页轮播图
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-10-03 02:04:55
 */
@RestController
@RequestMapping("/ad")
public class SitePicController {
	@Autowired
	private ISitePicService sitePicService;

	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping("/list.action")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		List<SitePicDO> list = sitePicService.findAllSitePic();
		mv.addObject("list", list);
		mv.setViewName("index_ad_list");
		return mv;
	}

	@RequestMapping("/preEdit.action")
	public ModelAndView preEdit(Integer id) {
		ModelAndView mv = new ModelAndView();
		if (id != null) {
			SitePicDO sitePic = sitePicService.findSitePicById(id);
			mv.addObject("sitePic", sitePic);
		}
		mv.setViewName("index_ad_edit");
		return mv;
	}

	@RequestMapping("/addSitePic.action")
	public String addSitePic(SitePicDO sitePic) {
		sitePicService.addSitePic(sitePic);
		return "success";
	}

	@RequestMapping("/saveSitePic.action")
	public String saveSitePic(@RequestParam("data") String data) {
		this.logger.info("data====>" + data);
		if (StringUtils.isNotEmpty(data)) {
			sitePicService.saveSitePic(data);
		}
		return "success";
	}

	@RequestMapping("/updateSitePic.action")
	public String updateitePic(SitePicDO sitePic) {
		sitePicService.updateSitePic(sitePic);
		return "success";
	}

	@RequestMapping("/delSitePic.action")
	public String delSitePic(Integer id) {
		sitePicService.delSitePic(id);
		return "success";
	}
}
