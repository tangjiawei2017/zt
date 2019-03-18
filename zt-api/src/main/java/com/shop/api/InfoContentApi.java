package com.shop.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.framework.response.ResultJson;
import com.shop.framework.utils.ResultUtil;
import com.shop.model.InfoContentDO;
import com.shop.service.IInfoContentService;
import com.shop.utils.InfoContentConvert;
import com.shop.vo.InfoContentVO;

import net.sf.json.JSONObject;

@RestController
@SuppressWarnings("all")
@RequestMapping("/info")
public class InfoContentApi {
	@Autowired
	private IInfoContentService infoContentService;

	@RequestMapping(value = "/detail.action", method = RequestMethod.POST)
	public ResultJson<InfoContentVO> detal(@RequestParam("id") Integer id) {
		InfoContentDO info = infoContentService.selectInfoContentByPrimaryKey(id);
		return ResultUtil.success(InfoContentConvert.convertInfoContentDOToVO(info));
	}

	/**
	 * 获取最新资讯
	 * 
	 * @param limit
	 *            限制的条数
	 * @return
	 */
	@RequestMapping(value = "/getNewInfo.action", method = RequestMethod.POST)
	public ResultJson<List<InfoContentVO>> getNewInfo(Integer limit) {
		List<InfoContentDO> infoList = this.infoContentService.getNewInfo(limit);
		List<InfoContentVO> voList = InfoContentConvert.convertInfoContentDOTOVOList(infoList);
		ResultJson<List<InfoContentVO>> result = new ResultJson<List<InfoContentVO>>();
		result.setCode(200);
		result.setCount(limit);
		result.setData(voList);
		result.setSuccess(true);
		result.setMsg("成功");
		return result;
	}

	@RequestMapping(value = "/list.action", method = RequestMethod.POST)
	public ResultJson<List<InfoContentVO>> list(@RequestParam(value = "curr", required = false) Integer currpage,
			@RequestParam(value = "limit", required = false) Integer limit, @RequestParam("cid") Integer cid) {
		Integer count = infoContentService.countInfoContentByCategoryId(cid);
		if (currpage == null || limit == null) {
			currpage = 1;
			limit = count;
		}
		List<InfoContentDO> infoList = infoContentService.selectInfoContentByPageAndCategory(currpage, limit, cid);
		List<InfoContentVO> voList = InfoContentConvert.convertInfoContentDOTOVOList(infoList);
		ResultJson<List<InfoContentVO>> result = new ResultJson<List<InfoContentVO>>();
		result.setCode(200);
		result.setCount(count);
		result.setData(voList);
		result.setSuccess(true);
		result.setMsg("成功");
		return result;
	}

	@RequestMapping(value = "/getPreAndNextInfoByAjax.action", method = RequestMethod.POST)
	public ResultJson<JSONObject> getPreAndNextInfoByAjax(@RequestParam(value = "id", required = true) Integer id) {
		InfoContentDO infoContent = infoContentService.selectInfoContentByPrimaryKey(id);
		InfoContentDO preInfoContent = null, nextInfoContent = null;
		if (infoContent != null && infoContent.getCategoryId() != null) {
			preInfoContent = this.infoContentService.getPreInfoContent(infoContent.getCategoryId(),
					infoContent.getCreated());
			nextInfoContent = this.infoContentService.getNextInfoContent(infoContent.getCategoryId(),
					infoContent.getCreated());
		}
		JSONObject result = new JSONObject();
		if (preInfoContent != null) {
			result.put("preNewsId", preInfoContent.getId());
			result.put("preNewsTitle", preInfoContent.getName());
		}
		if (nextInfoContent != null) {
			result.put("nextNewsId", nextInfoContent.getId());
			result.put("nextNewsTitle", nextInfoContent.getName());
		}
		return ResultUtil.success(result);
	}
}
