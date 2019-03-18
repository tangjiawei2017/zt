package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.model.MessageDO;

public interface MessageMapper {

	public MessageDO findMessageById(Integer id);

	public List<MessageDO> findMessageByStatusPage(@Param("status") Integer status, @Param("start") Integer start,
			@Param("limit") Integer limit);

	public Integer countMessageByStatus(Integer status);

	public void addMessage(MessageDO message);

	public void updateMessage(MessageDO message);

	public void delMessage(Integer id);

}
