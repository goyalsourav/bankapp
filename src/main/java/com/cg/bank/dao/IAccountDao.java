package com.cg.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bank.model.Account;

public interface IAccountDao extends JpaRepository<Account, Integer> {

	public Account findByIdAndMobile(Integer id, String mobile);
	
}
