package com.shop.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shop.utils.CommonUtils;
import com.shop.utils.IConstant;
import com.shop.utils.WebSiteConstant;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/upload")
public class ImageUploadController extends BaseController {

	private Logger logger = Logger.getLogger(ImageUploadController.class);

	/**
	 * 商品描述图片上传 图片保存到临时目录--->文件名取原名
	 * 
	 * @param file
	 * @param request
	 * @param response
	 */
	@RequestMapping("/productDescriptionImage.action")
	public JSONObject productDescriptionImage(@RequestParam MultipartFile[] file, HttpServletRequest request) {
		Integer code = 0;
		String msg = "succes";
		String src = "";
		JSONObject result = new JSONObject();
		JSONArray data = new JSONArray();
		for (int i = 0; i < file.length; i++) {
			String img = file[i].getOriginalFilename();
			String suffix = img.substring(img.lastIndexOf('.'), img.length());
			String imgName = CommonUtils.getRandomString(10);
			src = File.separator + IConstant.TEMP + File.separator + imgName + suffix;
			try {
				if (file[i] != null) {
					File f = new File(WebSiteConstant.TEMP, imgName + suffix);
					if (!f.exists()) {
						f.mkdirs();
					}
					file[i].transferTo(f);
					data.add(i, src);
				}
			} catch (Exception e) {
				logger.error(e);
				code = 1;
			}
		}
		result.put("errno", code);
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}

	/**
	 * 通用上传 上传到临时目录 文件名 随机生成的 10位长度的字符串
	 * 
	 * @param file
	 * @param module
	 *            模块名,若为空则上传到临时目录
	 * @return
	 */
	@RequestMapping("/commonUpload.action")
	public String commonUpload(@RequestParam MultipartFile file) {
		Integer code = 0;
		String msg = "succes";
		String src = "";
		String img = file.getOriginalFilename();
		String suffix = img.substring(img.lastIndexOf('.'), img.length());
		String imgName = CommonUtils.getRandomString(10);
		src = File.separator + IConstant.TEMP + File.separator + imgName + suffix;
		try {
			if (file != null) {
				File f = new File(WebSiteConstant.TEMP, imgName + suffix);
				if (!f.exists()) {
					f.mkdirs();
				}
				file.transferTo(f);
			}
		} catch (Exception e) {
			logger.error(e);
			code = 1;
		}
		return "{\"code\":" + code + ",\"msg\":\"" + msg + "\",\"data\":{\"src\":\"" + src + "\"}}";
	}

	/**
	 * 图片保存到临时目录--->文件名取原名 商品 图片批量上传
	 * 
	 * @param file
	 * @param request
	 * @param response
	 */
	@RequestMapping("/imageUpload.action")
	public void imageUpload(@RequestParam MultipartFile file, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		Integer code = 0;
		String msg = "success";
		JSONObject data = new JSONObject();
		try {
			if (file != null) {
				File f = new File(WebSiteConstant.TEMP, file.getOriginalFilename());
				if (!f.exists()) {
					f.mkdirs();
				}
				file.transferTo(f);
				data.put("src", File.separator + IConstant.TEMP + File.separator + f.getName());
			}
			result.put("data", data);
		} catch (Exception e) {
			code = 1;
			this.logger.error(e);
		}
		result.put("code", code);
		result.put("msg", msg);
		result.put("data", data);
		try {
			response.getWriter().print(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
