package com.shop.mapper;

import org.apache.ibatis.annotations.Param;

import com.shop.model.AccountDO;

public interface AccountMapper {
	public AccountDO findAccountByLoginName(String username);

	public void updateAccount(@Param("account") String account, @Param("password") String password,
			@Param("originalPassword") String originalPassword);

	public int checkAdminPwd(@Param("username") String username, @Param("password") String password);
}
