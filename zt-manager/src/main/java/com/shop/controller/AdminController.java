package com.shop.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.service.IAdminService;

@RequestMapping("/admin")
@Controller
public class AdminController extends BaseController {
	@Autowired
	private IAdminService adminService;

	@RequestMapping("/preModifyPassword.action")
	public String preModifyPassword() {
		return "modifyPassword";
	}

	@RequestMapping("/modifyPassword.action")
	public void modifyPassword(HttpServletRequest request, String pwd, PrintWriter out) {
		String result = "fail";
		try {
			String account = this.getCurrentLoginAdmin();
			adminService.modifyPwd(account, DigestUtils.md5Hex(pwd), pwd);
			result = "success";
		} catch (Exception e) {
			this.logger.error(e);
			result = "fail";
		}
		out.print(result);
	}

	@RequestMapping("/checkAccountPwd.action")
	public void checkAccountPwd(String password, PrintWriter out) {
		String msg = "fail";
		JSONObject result = new JSONObject();
		try {
			if (StringUtils.isNotEmpty(password)) {
				boolean isOk = adminService.checkAdminPwd(this.getCurrentLoginAdmin(), DigestUtils.md5Hex(password));
				msg = isOk ? "success" : "fail";
			}
		} catch (Exception e) {
			this.logger.error(e);
			msg = "fail";
		}
		result.put("result", msg);
		out.print(result.toString());
	}
}
