package com.shop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.api.vo.ComContentApiVO;
import com.shop.framework.response.ResultEnum;
import com.shop.framework.response.ResultJson;
import com.shop.framework.utils.ResultUtil;
import com.shop.model.CommonContentDO;
import com.shop.service.ICommonContentService;
import com.shop.utils.CommonContentConvert;
import com.shop.utils.XSSJudgeUtils;

@RestController
@SuppressWarnings("all")
@RequestMapping("/comContent")
public class ComContentApi {
	@Autowired
	private ICommonContentService commonContentService;

	@RequestMapping(value = "/showComContent.action", method = RequestMethod.POST)
	public ResultJson<ComContentApiVO> showComContent(@RequestParam("comContentId") String id) {
		if (XSSJudgeUtils.isSqlInject(id)) {
			return ResultUtil.error(ResultEnum.PARAMETER_ERROR.getCode(), "参数错误");
		}
		CommonContentDO content = commonContentService.selectByPrimaryKey(id);
		return ResultUtil.success(CommonContentConvert.convertCommonContentDOToApiVO(content));
	}
}
