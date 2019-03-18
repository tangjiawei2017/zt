package com.shop.api;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.api.vo.ProductApiVO;
import com.shop.framework.response.ResultEnum;
import com.shop.framework.response.ResultJson;
import com.shop.framework.utils.ResultUtil;
import com.shop.model.ProductDO;
import com.shop.model.SiteConfigDO;
import com.shop.service.IProductService;
import com.shop.service.ISiteConfigService;
import com.shop.utils.IConstant;
import com.shop.utils.ProductConvert;
import com.shop.utils.XSSJudgeUtils;

import net.sf.json.JSONObject;

@RestController
@SuppressWarnings("all")
@RequestMapping("/product")
public class ProductApi {
	@Autowired
	private IProductService productService;
	@Autowired
	private ISiteConfigService siteConfigService;
	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/detail.action", method = RequestMethod.POST)
	public ResultJson<JSONObject> productDetail(@RequestParam(value = "id") Integer id) {
		JSONObject result = new JSONObject();
		ProductApiVO product = ProductConvert.convertProductDOToApiVO(productService.findProductDoById(id));
		result.put("product", product);
		SiteConfigDO config = siteConfigService.getSiteConfigValue(IConstant.REVIEW_SERVICE);
		return ResultUtil.success(result);
	}

	/**
	 * 根据扩展信息 得到产品信息
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	@RequestMapping(value = "/findProductByExtAttr.action", method = RequestMethod.POST)
	public ResultJson<List<ProductApiVO>> findProductByExtAttr(String name, String value) {
		if (XSSJudgeUtils.isSqlInject(name) || XSSJudgeUtils.isSqlInject(value)) {
			this.logger.info("not legal request!");
			return ResultUtil.error(ResultEnum.PARAMETER_ERROR.getCode(), ResultEnum.PARAMETER_ERROR.getMsg());
		}
		List<ProductDO> list = productService.findProductByExtAttr(name, value);
		return ResultUtil.success(ProductConvert.convertProductDOToApiVOList(list));
	}
}
