package com.cg.bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "seq10_cus_id", allocationSize = 1)
	private int id;
	private String accounttype;
	private double balance;
	private String name; 
	private String mobile;

	/*
	 * @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL) private
	 * Set<Transaction> transaction = new HashSet<>();
	 * 
	 * public void addTransaction(Transaction transaction) {
	 * transaction.setAccount(this); // this will avoid nested cascade
	 * this.getTransaction().add(transaction); }
	 * 
	 * public Set<Transaction> getTransaction() { return transaction; }
	 * 
	 * public void setTransaction(Set<Transaction> transaction) { this.transaction =
	 * transaction; }
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accounttype=" + accounttype + ", balance=" + balance + ", name=" + name
				+ ", mobile=" + mobile + "]";
	}

}
