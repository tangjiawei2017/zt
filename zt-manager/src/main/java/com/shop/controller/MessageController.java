package com.shop.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.model.MessageDO;
import com.shop.service.IMessageService;
import com.shop.utils.MessageConvert;

import net.sf.json.JSONObject;

@RequestMapping("/message")
@RestController
public class MessageController {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private IMessageService messageService;

	/**
	 * 获取留言列表
	 * 
	 * @param currpage
	 * @param limit
	 * @param ctime
	 * @param response
	 */
	@RequestMapping("/listAjax.action")
	public JSONObject listAjax(@RequestParam(value = "page", required = true) Integer currpage,
			@RequestParam(value = "limit", required = true) Integer limit,
			@RequestParam(value = "status", required = true) Integer status) {
		this.logger.info("status====>" + status);
		JSONObject result = new JSONObject();
		String msg = "success";
		Integer count = messageService.countMessageByStatus(status);
		List<MessageDO> messageList = messageService.findMessageByStatusPage(status, currpage, limit);
		result.put("msg", msg);
		result.put("data", MessageConvert.convertMessageDOToVO(messageList));
		result.put("code", 0);
		result.put("count", count);
		return result;
	}

	/**
	 * 获取留言详情
	 * 
	 * @param currpage
	 * @param limit
	 * @param ctime
	 * @param response
	 */
	@RequestMapping("/detail.action")
	public ModelAndView detail(Integer id) {
		ModelAndView mv = new ModelAndView();
		MessageDO message = messageService.findMessageById(id);
		mv.addObject("message", message);
		mv.addObject("type", "detal");
		mv.setViewName("message_detail");
		return mv;
	}

	/**
	 * 处理留言
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/dealwith.action")
	public ModelAndView dealwith(Integer id) {
		ModelAndView mv = new ModelAndView();
		MessageDO message = messageService.findMessageById(id);
		mv.addObject("message", message);
		mv.addObject("type", "edit");
		mv.setViewName("message_detail");
		return mv;
	}

	/**
	 * 处理留言
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateMessage.action")
	public String updateMessage(MessageDO message) {
		messageService.updateMessage(message);
		return "success";
	}

	/**
	 * 处理留言
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delMessage.action")
	public String delMessage(Integer id) {
		messageService.delMessage(id);
		return "success";
	}
}
