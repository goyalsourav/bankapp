package com.cg.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bank.model.Transaction;

public interface ITransactionDao extends JpaRepository<Transaction, Integer> {

	public List<Transaction> findByAccountId(Integer id);

}