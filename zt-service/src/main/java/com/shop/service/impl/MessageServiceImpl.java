package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.MessageMapper;
import com.shop.model.MessageDO;
import com.shop.service.IMessageService;

@Service
public class MessageServiceImpl implements IMessageService {
	@Autowired
	private MessageMapper messageMapper;

	@Override
	public MessageDO findMessageById(Integer id) {
		return messageMapper.findMessageById(id);
	}

	@Override
	public List<MessageDO> findMessageByStatusPage(Integer status, Integer currPage, Integer limit) {
		Integer start = (currPage - 1) * limit;
		return messageMapper.findMessageByStatusPage(status, start, limit);
	}

	@Override
	public Integer countMessageByStatus(Integer status) {
		return messageMapper.countMessageByStatus(status);
	}

	@Override
	public void addMessage(MessageDO message) {
		messageMapper.addMessage(message);
	}

	@Override
	public void updateMessage(MessageDO message) {
		messageMapper.updateMessage(message);
	}

	@Override
	public void delMessage(Integer id) {
		messageMapper.delMessage(id);
	}

}
