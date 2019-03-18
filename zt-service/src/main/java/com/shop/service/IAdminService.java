package com.shop.service;

public interface IAdminService {
	public void modifyPwd(String account, String newPwd, String originalPassword);

	public boolean checkAdminPwd(String account, String password);
}
