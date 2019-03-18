package com.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.shop.mapper.AccountMapper;
import com.shop.model.AccountDO;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AccountMapper accountMapper;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (StringUtils.isEmpty(username)) {
			throw new UsernameNotFoundException("username is Empty!");
		}
		AccountDO account = this.accountMapper.findAccountByLoginName(username);
		if (account == null) {
			throw new UsernameNotFoundException("username is not found!");
		}
		if (account.getRole() == null) {
			throw new UsernameNotFoundException("username's authority is not Empty!");
		}
		return createUserDetails(account);
	}

	protected UserDetails createUserDetails(AccountDO account) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(account.getRole()));
		return new User(account.getUsername(), account.getPassword(), true, true, true, true, authorities);
	}
}