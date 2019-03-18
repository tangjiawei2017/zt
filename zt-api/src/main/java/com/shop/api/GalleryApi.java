package com.shop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.api.vo.GalleryApiVO;
import com.shop.framework.response.ResultEnum;
import com.shop.framework.response.ResultJson;
import com.shop.framework.utils.ResultUtil;
import com.shop.model.GalleryDO;
import com.shop.service.IGalleryService;
import com.shop.utils.GalleryConvert;
import com.shop.utils.XSSJudgeUtils;

@RestController
@SuppressWarnings("all")
@RequestMapping("/gallery")
public class GalleryApi {
	@Autowired
	private IGalleryService galleryService;

	@RequestMapping(value = "/picture.action", method = RequestMethod.POST)
	public ResultJson<GalleryApiVO> picture(@RequestParam("id") String id) {
		if (XSSJudgeUtils.isSqlInject(id)) {
			return ResultUtil.error(ResultEnum.PARAMETER_ERROR.getCode(), "参数错误");
		}
		GalleryDO gallery = galleryService.selectByPrimaryKey(id);
		return ResultUtil.success(GalleryConvert.convertGalleryDOToApiVO(gallery));
	}
}
