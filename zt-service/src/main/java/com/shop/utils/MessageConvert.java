package com.shop.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.MessageDO;
import com.shop.vo.MessageVO;

public class MessageConvert {
	public static List<MessageVO> convertMessageDOToVO(List<MessageDO> list) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<MessageVO> voList = new ArrayList<MessageVO>();
		if (list != null && list.size() > 0) {
			for (MessageDO m : list) {
				MessageVO vo = new MessageVO();
				vo.setId(m.getId());
				vo.setAccount(m.getAccount());
				vo.setPhone(m.getPhone());
				vo.setEmail(m.getEmail());
				vo.setContent(m.getContent());
				vo.setCtime(format.format(m.getCtime()));
				vo.setStatus(m.getStatus());
				vo.setAnswer(m.getAnswer());
				voList.add(vo);
			}
		}
		return voList;
	}
}
