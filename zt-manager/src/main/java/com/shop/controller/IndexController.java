package com.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shop.model.SiteDO;
import com.shop.service.IMenuService;
import com.shop.service.ISiteService;
import com.shop.vo.MenuItemVO;

@Controller
public class IndexController extends BaseController {
	@Autowired
	private ISiteService siteService;
	@Autowired
	private IMenuService menuService;

	@RequestMapping("/index.action")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String admin = this.getCurrentLoginAdmin();
		mv.addObject("admin", admin);
		SiteDO site = siteService.getSiteDO();
		mv.addObject("site", site);
		List<MenuItemVO> list = menuService.getMenuItem();
		mv.addObject("list", list);
		mv.setViewName("index");
		return mv;
	}
}
