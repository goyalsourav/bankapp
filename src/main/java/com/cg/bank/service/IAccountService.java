package com.cg.bank.service;

import java.util.List;

import com.cg.bank.model.Account;

public interface IAccountService {

	public List<Account> findAll();

	public void save(Account account);

	public Account findById(Integer id);
	
	public Account findByIdAndMobile(Integer id, String mobile);
}
