package com.cg.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bank.dao.IAccountDao;
import com.cg.bank.model.Account;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountDao accountDao;

	@Override
	public void save(Account account) {
		accountDao.save(account);

	}

	@Override
	public List<Account> findAll() {
		List<Account> accountlist = accountDao.findAll();
		return accountlist;
	}

	@Override
	public Account findById(Integer id) {
		Account account = accountDao.findById(id).get();
		return account;
	}

}
