package com.shop.service;

import java.util.List;

import com.shop.model.CustomerDO;
import com.shop.vo.CustomerCondition;

public interface ICustomerService {
	public List<CustomerDO> findAllCustomer();

	public CustomerDO findCustomerByAccount(String account);

	public CustomerDO findCustomerByPhone(String phone);

	public void updateCustomerIconById(Integer id, String imgpath);

	public void updateCustomer(CustomerDO customer);

	/**
	 * 登录校验
	 * 
	 * @param account
	 * @param pwd
	 * @return
	 */
	public CustomerDO docheckLoginByAccountAndPwd(String account, String pwd);

	public CustomerDO findCustomerByAccountOrPhone(String account);

	public int countCustomer(CustomerCondition condition);

	public CustomerDO findCustomerById(Integer id);

	public List<CustomerDO> findCustomersByCondition(Integer currpage, Integer limit, CustomerCondition condition);

	public void addCustomer(CustomerDO customer);

	public void updateCustomerStatus(Integer id, Integer status);

	public void delCustomer(Integer id);

	public void batchDelCustomer(String ids);

	public void batchUpdateCustomerStatus(String ids, int status);

	public Integer checkCustomerAccountExist(String account);

	public Integer checkCustomerPhoneExist(String phone);

}
