package com.shop.utils;

import com.shop.model.CustomerDO;
import com.shop.vo.CustomerVO;

/**
 * 会员转换类
 * 
 */
public class CustomerConvert {

	/**
	 * 将do转换成VO
	 * 
	 * @return
	 */
	public static CustomerVO convertCustomerDOToVO(CustomerDO c) {
		CustomerVO customer = new CustomerVO();
		customer.setId(c.getId());
		customer.setAccount(c.getAccount());
		customer.setName(c.getName());
		customer.setSex(c.getSex());
		customer.setImagePath(c.getImagePath());
		customer.setCellphone(c.getCellphone());
		customer.setEmail(c.getEmail());
		customer.setRegtime(c.getRegtime());
		customer.setLogintime(c.getLogintime());
		customer.setLoginCount(c.getLoginCount());
		customer.setStatus(c.getStatus());
		customer.setSource(c.getSource());
		return customer;
	}

}
