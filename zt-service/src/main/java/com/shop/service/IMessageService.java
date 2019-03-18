package com.shop.service;

import java.util.List;

import com.shop.model.MessageDO;

public interface IMessageService {
	public MessageDO findMessageById(Integer id);

	public List<MessageDO> findMessageByStatusPage(Integer status, Integer start, Integer limit);

	public Integer countMessageByStatus(Integer status);

	public void addMessage(MessageDO message);

	public void updateMessage(MessageDO message);

	public void delMessage(Integer id);
}
