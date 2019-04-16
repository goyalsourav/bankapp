package com.cg.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bank.dao.ITransactionDao;
import com.cg.bank.model.Transaction;

@Service("transactionService")
public class TransactionServiceImpl implements ITransactionService{

	@Autowired
	private ITransactionDao transactionDao;
	
	@Override
	public void save(Transaction transaction) {
		transactionDao.save(transaction);
		
	}

	@Override
	public List<Transaction> findByAccountId(Integer accountId) {
		List<Transaction> list = transactionDao.findByAccountId(accountId);
		return list;
	}

}
