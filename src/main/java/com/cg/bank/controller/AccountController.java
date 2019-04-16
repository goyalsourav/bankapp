package com.cg.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cg.bank.model.Account;
import com.cg.bank.model.Transaction;
import com.cg.bank.service.AccountServiceImpl;
import com.cg.bank.service.TransactionServiceImpl;

@RestController
@RequestMapping("/admin")
public class AccountController {

	int temp;
	@Autowired
	private AccountServiceImpl accountService;
	
	@Autowired
	private TransactionServiceImpl transactionService;

	@GetMapping("/")
	public ModelAndView findAll() {
		List<Account> accountlist = accountService.findAll();
		ModelAndView modelAndView = new ModelAndView("listaccount");
		modelAndView.addObject("ACCOUNTLIST", accountlist);
		return modelAndView;
	}
	
	/*
	 * //for printing the transaction done by a particular user
	 * 
	 * @GetMapping("/transaction") public ModelAndView gettransaction(@RequestParam
	 * int id) {
	 * 
	 * List<Transaction> list1 = transactionService.findByAccountId(id);
	 * ModelAndView modelAndView = new ModelAndView("printtransaction");
	 * 
	 * modelAndView.addObject("PRINT", list1); return modelAndView; }
	 */
	@PostMapping("login")
	public ModelAndView login(@RequestParam String username, @RequestParam String password) {
		String a = username;
		String b = password;
		ModelAndView modelAndView = new ModelAndView();
		if (a.equals("admin") && b.equals("admin")) {
			modelAndView = new ModelAndView("createaccount");

		}
		return modelAndView;
	}

	@PostMapping("userlogin")
	public ModelAndView userlogin(@RequestParam String username, @RequestParam String password) {
		String a = username;
		String b = password;
		ModelAndView modelAndView = new ModelAndView();
		if (a.equals("user") && b.equals("user")) {
			modelAndView = new ModelAndView("menu");

		}
		return modelAndView;
	}

	@PostMapping("save")
	public ModelAndView save(/* @RequestParam Integer id */ @RequestParam String accounttype,
			@RequestParam double balance, @RequestParam String name, @RequestParam String mobile) {
		Account account = new Account();
		/* account.setId(0); */
		account.setAccounttype(accounttype);
		account.setBalance(balance);
		account.setName(name);
		account.setMobile(mobile);
		accountService.save(account);
		List<Account> accountlist = accountService.findAll();
		ModelAndView modelAndView = new ModelAndView("listaccount");
		modelAndView.addObject("ACCOUNTLIST", accountlist);
		return modelAndView;
	}
	
	@GetMapping("update")
	public ModelAndView deposit(@RequestParam Integer id,@RequestParam int deposit) {
		ModelAndView modelAndView;
		Account account =accountService.findById(id);
		double oldBalance=account.getBalance();
		double newBalance=deposit+oldBalance;	
		account.setBalance(newBalance);
		
		
		Transaction transaction = new Transaction();
		transaction.setAmount(deposit);
		transaction.setType("DEPOSITED");
		transaction.setAccount(account);
		transactionService.save(transaction);
		accountService.save(account);
		
		
		
		modelAndView = new ModelAndView("menu");
		return modelAndView;
	}	
	
	@GetMapping("update1")
	public ModelAndView withdraw(@RequestParam Integer id,@RequestParam double withdraw) {
		ModelAndView modelAndView;
		Account account =accountService.findById(id);
		double oldBalance=account.getBalance();
		double newBalance=oldBalance-withdraw;	
		account.setBalance(newBalance);
		accountService.save(account);
		modelAndView = new ModelAndView("menu");
		return modelAndView;
	}
	
	@GetMapping("/balance")
	public ModelAndView showbalance(@RequestParam Integer id) {
		ModelAndView modelAndView;
		Account account = accountService.findById(id);
		modelAndView = new ModelAndView("showbalance");
		modelAndView.addObject("ACCOUNT", account);
		return modelAndView;
	}
	
	@GetMapping("/fundTransfer")
	public ModelAndView fundTransfer(@RequestParam int id1,@RequestParam int id, @RequestParam double credit) {
		ModelAndView modelAndView;
		Account sender = accountService.findById(id1);
		double oldBalance = sender.getBalance();
		double newBalance = oldBalance - credit;
		sender.setBalance(newBalance);
		accountService.save(sender);

		Account reciver = accountService.findById(id);
		double oldBalance1 = reciver.getBalance();
		double newBalance1 = oldBalance1 + credit;
		reciver.setBalance(newBalance1);
		accountService.save(reciver);
		modelAndView = new ModelAndView("menu");
		return modelAndView;

	}

	@GetMapping("showTransaction")
	public ModelAndView showTransaction(@RequestParam int id) {

		// Find all the transactions made by the customer with the Id
		List<Transaction> transactionsList = transactionService.findByAccountId(id);

		// Display the JSP page
		ModelAndView modelandview = new ModelAndView("printtransaction");
		modelandview.addObject("TRANSACTIONSLIST", transactionsList);

		// Return the JSP page
		return modelandview;

	}
	
}
