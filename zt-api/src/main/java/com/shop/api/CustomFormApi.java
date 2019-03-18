package com.shop.api;

import java.lang.reflect.Method;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shop.model.CustomFormContentDO;
import com.shop.service.ICustomFormContentService;
import com.shop.utils.XSSJudgeUtils;

@RestController
@SuppressWarnings("all")
@RequestMapping("/form")
public class CustomFormApi {
	@Autowired
	private ICustomFormContentService customFormContentService;

	@RequestMapping(value = "/addCustomFormContent.action", method = RequestMethod.POST)
	public String addCustomFormContent(CustomFormContentDO customForm) {
		if (checkStringInjection(customForm)) {
			customForm.setCtime(new Date());
			customForm.setStatus(0);
			customForm.setResult(null);
			customFormContentService.insertCustomFormContentSelective(customForm);
			return "success";
		} else {
			return "fail";
		}
	}

	/**
	 * SQL 注入
	 * 
	 * @param customForm
	 * @return
	 */
	private boolean checkStringInjection(CustomFormContentDO customForm) {
		try {
			if (XSSJudgeUtils.isSqlInject(customForm.getFormId())) {
				return false;
			}
			for (int i = 1; i <= 15; i++) {
				Method method = customForm.getClass().getMethod("getExt" + i, null);
				Object obj = method.invoke(customForm, null);
				if (XSSJudgeUtils.isSqlInject((String) obj)) {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
