package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.model.CustomerDO;
import com.shop.vo.CustomerCondition;

public interface CustomerMapper {
	public CustomerDO findCustomerByAccount(String account);

	public List<CustomerDO> findAllCustomer();

	public CustomerDO findCustomerByPhone(String phone);

	public CustomerDO findCustomerById(Integer id);

	public CustomerDO findCustomerByAccountOrPhone(String account);

	public List<CustomerDO> findCustomersByCondition(@Param(value = "start") Integer start,
			@Param(value = "limit") Integer limit, @Param("condition") CustomerCondition condition);

	public Integer checkCustomerAccountExist(String account);

	public CustomerDO docheckLoginByAcccountAndPwd(@Param(value = "account") String account,
			@Param(value = "password") String password);

	public int countCustomer(CustomerCondition condition);

	public void updateCustomer(CustomerDO customer);

	public void updateCustomerStatus(@Param(value = "id") Integer id, @Param(value = "status") Integer status);

	public void updateCustomerIconById(@Param(value = "id") Integer id, @Param(value = "imgpath") String imgpath);

	public void delCustomer(Integer id);

	public void addCustomer(CustomerDO customer);

	public Integer checkCustomerPhoneExist(String phone);

}
