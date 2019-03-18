package com.shop.api;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shop.framework.response.ResultEnum;
import com.shop.framework.response.ResultJson;
import com.shop.framework.utils.ResultUtil;
import com.shop.model.MessageDO;
import com.shop.service.IMessageService;
import com.shop.utils.XSSJudgeUtils;

@RestController
@SuppressWarnings("all")
@RequestMapping("/message")
public class MessageApi {
	@Autowired
	private IMessageService messageService;
	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/addMessage.action", method = RequestMethod.POST)
	public ResultJson<String> addMessage(MessageDO message, String verify, HttpSession session) {
		this.logger.info("message===>" + message);
		Object obj = session.getAttribute("verifyCode");
		this.logger.info("obj====>" + obj + "   verify===>" + verify);
		if (obj != null && obj instanceof java.lang.String && message != null) {
			String verifyCode = (String) obj;
			if (verifyCode.equalsIgnoreCase(verify)) {
				if (!XSSJudgeUtils.isSqlInject(message.getAccount()) && !XSSJudgeUtils.isSqlInject(message.getPhone())
						&& !XSSJudgeUtils.isSqlInject(message.getEmail())
						&& !XSSJudgeUtils.isSqlInject(message.getContent())) {
					message.setCtime(new Date());
					message.setStatus(0);
					messageService.addMessage(message);
					return ResultUtil.success("success");
				}
			} else {
				return ResultUtil.error(ResultEnum.PARAMETER_ERROR.getCode(), "验证码错误!");
			}
		}
		return ResultUtil.error(ResultEnum.PARAMETER_ERROR.getCode(), "参数错误");
	}
}
