package com.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AccountMapper;
import com.shop.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public void modifyPwd(String account, String newPwd, String originalPassword) {
		accountMapper.updateAccount(account, newPwd, originalPassword);
	}

	@Override
	public boolean checkAdminPwd(String account, String password) {
		return accountMapper.checkAdminPwd(account, password) > 0 ? true : false;
	}

}
