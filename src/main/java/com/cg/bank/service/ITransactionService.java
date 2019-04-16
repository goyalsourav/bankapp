package com.cg.bank.service;

import java.util.List;

import com.cg.bank.model.Transaction;

public interface ITransactionService {
	
	public void save(Transaction transaction);

	public List<Transaction> findByAccountId(Integer accountId);
}
