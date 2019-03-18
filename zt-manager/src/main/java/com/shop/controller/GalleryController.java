package com.shop.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.framework.utils.ResultUtil;
import com.shop.model.GalleryDO;
import com.shop.service.IGalleryService;
import com.shop.utils.CommonFileUtils;
import com.shop.utils.IConstant;

/**
 * 各个页面的大图广告
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-10-03 13:48:24
 */
@RestController
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private IGalleryService galleryService;

	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping("/list.action")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		List<GalleryDO> list = galleryService.selectAll();
		mv.addObject("list", list);
		mv.setViewName("gallery_list");
		return mv;
	}

	@RequestMapping("/preEditgallery.action")
	public ModelAndView editCategory(@RequestParam(value = "id", required = false) String id) {
		ModelAndView mv = new ModelAndView();
		if (id != null) {
			GalleryDO gallery = this.galleryService.selectByPrimaryKey(id);
			mv.addObject("gallery", gallery);
		}
		mv.setViewName("gallery_edit");
		return mv;
	}

	@RequestMapping("/addGallery.action")
	public String addCategory(GalleryDO gallery) {
		gallery.setId(UUID.randomUUID().toString());
		if (StringUtils.isNotEmpty(gallery.getPath())) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			gallery.setPath(CommonFileUtils.copyFile(gallery.getPath(), IConstant.GALLERY));
		}
		if (StringUtils.isNotEmpty(gallery.getMpath())) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			gallery.setMpath(CommonFileUtils.copyFile(gallery.getMpath(), IConstant.GALLERY));
		}
		Date time = new Date();
		gallery.setCtime(time);
		gallery.setModify(time);
		this.logger.info("gallery=============>" + gallery);
		this.galleryService.insertSelective(gallery);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/updateGallery.action")
	public String updateGallery(GalleryDO gallery) {
		if (StringUtils.isNotEmpty(gallery.getPath())) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			gallery.setPath(CommonFileUtils.copyFile(gallery.getPath(), IConstant.GALLERY));
		}
		if (StringUtils.isNotEmpty(gallery.getMpath())) {
			// 拷贝临时文件到 正式环境,并返回新的路径结果
			gallery.setMpath(CommonFileUtils.copyFile(gallery.getMpath(), IConstant.GALLERY));
		}
		gallery.setModify(new Date());
		this.galleryService.updateByPrimaryKeySelective(gallery);
		return ResultUtil.buildResultJson(true);
	}

	@RequestMapping("/delGallery.action")
	public String delGallery(@RequestParam(value = "id", required = true) String id) {
		this.galleryService.deleteByPrimaryKey(id);
		return ResultUtil.buildResultJson(true);
	}
}
