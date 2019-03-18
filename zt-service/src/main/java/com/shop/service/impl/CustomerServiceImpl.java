package com.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.CustomerMapper;
import com.shop.model.CustomerDO;
import com.shop.service.ICustomerService;
import com.shop.vo.CustomerCondition;

@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public int countCustomer(CustomerCondition condition) {
		return customerMapper.countCustomer(condition);
	}

	@Override
	public List<CustomerDO> findCustomersByCondition(Integer currpage, Integer limit, CustomerCondition condition) {
		int start = (currpage - 1) * limit;
		List<CustomerDO> customerList = this.customerMapper.findCustomersByCondition(start, limit, condition);
		return customerList;
	}

	@Override
	public CustomerDO findCustomerById(Integer id) {
		return customerMapper.findCustomerById(id);
	}

	@Override
	public void updateCustomerStatus(Integer id, Integer status) {
		customerMapper.updateCustomerStatus(id, status);
	}

	/*
	 * 删除会员
	 */
	@Override
	public void delCustomer(Integer id) {
		customerMapper.delCustomer(id);
	}

	@Override
	public void batchDelCustomer(String ids) {
		String[] idArrays = ids.split(",");
		for (String id : idArrays) {
			this.delCustomer(Integer.valueOf(id));
		}
	}

	@Override
	public void batchUpdateCustomerStatus(String ids, int status) {
		String[] idArrays = ids.split(",");
		for (String id : idArrays) {
			this.updateCustomerStatus(Integer.valueOf(id), status);
		}
	}

	@Override
	public void addCustomer(CustomerDO customer) {
		Date time = new Date();
		customer.setRegtime(time);
		customer.setLogintime(time);
		customer.setLoginCount(1);
		if (StringUtils.isNotEmpty(customer.getPassword())) {
			customer.setPassword(DigestUtils.md5Hex(customer.getPassword()));
		}
		this.customerMapper.addCustomer(customer);
	}

	@Override
	public CustomerDO findCustomerByAccount(String account) {
		return customerMapper.findCustomerByAccount(account);
	}

	@Override
	public CustomerDO findCustomerByPhone(String phone) {
		return customerMapper.findCustomerByPhone(phone);
	}

	@Override
	public CustomerDO docheckLoginByAccountAndPwd(String account, String pwd) {
		return customerMapper.docheckLoginByAcccountAndPwd(account, pwd);
	}

	@Override
	public Integer checkCustomerAccountExist(String account) {
		return customerMapper.checkCustomerAccountExist(account);
	}

	@Override
	public Integer checkCustomerPhoneExist(String phone) {
		return customerMapper.checkCustomerPhoneExist(phone);
	}

	@Override
	public List<CustomerDO> findAllCustomer() {
		return customerMapper.findAllCustomer();
	}

	@Override
	public void updateCustomerIconById(Integer id, String imgpath) {
		customerMapper.updateCustomerIconById(id, imgpath);
	}

	@Override
	public void updateCustomer(CustomerDO customer) {
		customerMapper.updateCustomer(customer);
	}

	@Override
	public CustomerDO findCustomerByAccountOrPhone(String account) {
		return customerMapper.findCustomerByAccountOrPhone(account);
	}

}
