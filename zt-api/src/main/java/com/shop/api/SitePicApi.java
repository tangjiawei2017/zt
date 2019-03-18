package com.shop.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shop.framework.response.ResultJson;
import com.shop.framework.utils.ResultUtil;
import com.shop.model.SitePicDO;
import com.shop.service.ISitePicService;

/**
 * 首页轮播图
 * 
 * @author xiaotang
 * @version 1.0
 * @date 2018-10-04 16:21:59
 */
@RestController
@RequestMapping("/index")
@SuppressWarnings("all")
public class SitePicApi {
	@Autowired
	private ISitePicService sitePicService;

	@RequestMapping(value = "/bannerList.action", method = RequestMethod.POST)
	public ResultJson<List<SitePicDO>> sitePicList() {
		List<SitePicDO> list = sitePicService.findAllSitePic();
		return ResultUtil.success(list);
	}
}
