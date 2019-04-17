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

	int ID;
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
	public ModelAndView userlogin(@RequestParam int id, @RequestParam String mobile) {
		Account account = accountService.findByIdAndMobile(id, mobile);
		ModelAndView modelAndView = new ModelAndView();
		if (account == null) {
			modelAndView = new ModelAndView("noaccount");
		}

		// Login if its a valid customer in the table
		else {
			// Assign the given id to customer id
			ID = id;
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
	public ModelAndView deposit(@RequestParam int deposit) {
		ModelAndView modelAndView;
		Account account = accountService.findById(ID);
		double oldBalance = account.getBalance();
		double newBalance = deposit + oldBalance;
		account.setBalance(newBalance);

		Transaction transaction = new Transaction();
		transaction.setAmount(deposit);
		transaction.setType("DEPOSITED");
		transaction.setAccount(account);
		transactionService.save(transaction);
		accountService.save(account);

		modelAndView = new ModelAndView("showbalance");
		 modelAndView.addObject("ACCOUNT",newBalance); 
		return modelAndView;
	}

	@GetMapping("update1")
	public ModelAndView withdraw(@RequestParam int withdraw) {
		ModelAndView modelAndView;
		Account account = accountService.findById(ID);
		double oldBalance = account.getBalance();
		if(oldBalance > withdraw) {
		double newBalance = oldBalance - withdraw;
		account.setBalance(newBalance);
		
		Transaction transaction = new Transaction();
		transaction.setAmount(withdraw);
		transaction.setType("WITHDRAWN");
		transaction.setAccount(account);
		transactionService.save(transaction);
		accountService.save(account);
		modelAndView = new ModelAndView("showbalance");
		 modelAndView.addObject("ACCOUNT",newBalance); 
		}
		else{
			modelAndView = new ModelAndView("error");
		}
		
		return modelAndView;
	}
    
	@GetMapping("/balance")
	public ModelAndView showbalance(/* @RequestParam int id */ ) {
		ModelAndView modelAndView;
		Account account = accountService.findById(ID);
		/* double bal = account.getBalance(); */		
		modelAndView = new ModelAndView("showbalance");
		modelAndView.addObject("ACCOUNT",account);
		return modelAndView;
	}

	@GetMapping("/fundTransfer")
	public ModelAndView fundTransfer(@RequestParam int id, @RequestParam int credit) {
		ModelAndView modelAndView;
		Account sender = accountService.findById(ID);
		double oldBalance = sender.getBalance();
		if(oldBalance > credit) {
		double newBalance = oldBalance - credit;
		sender.setBalance(newBalance);
		accountService.save(sender);

		Account reciver = accountService.findById(id);
		double oldBalance1 = reciver.getBalance();
		double newBalance1 = oldBalance1 + credit;
		reciver.setBalance(newBalance1);
		accountService.save(reciver);

		Transaction send = new Transaction();
		send.setAmount(credit);
		send.setType("SENT to" + reciver);
		send.setAccount(sender);
		transactionService.save(send);

		Transaction receive = new Transaction();
		receive.setAmount(credit);
		receive.setType("RECEIVED from" + sender);
		receive.setAccount(reciver);
		transactionService.save(receive);

		modelAndView = new ModelAndView("showbalance");
		 modelAndView.addObject("ACCOUNT",newBalance); 
		}
		else{
			modelAndView = new ModelAndView("error");
		}
		return modelAndView;
	}

	@GetMapping("showTransaction")
	public ModelAndView showTransaction( @RequestParam int id ) {

		// Find all the transactions made by the customer with the Id
		List<Transaction> transactionsList = transactionService.findByAccountId(id);

		// Display the JSP page
		ModelAndView modelandview = new ModelAndView("printtransaction");
		modelandview.addObject("TRANSACTIONSLIST", transactionsList);

		// Return the JSP page
		return modelandview;

	}

}
