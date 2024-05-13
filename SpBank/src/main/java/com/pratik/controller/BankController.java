package com.pratik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratik.model.UserData;
import com.pratik.model.UserWrapper;
import com.pratik.service.BankService;

@RestController
@RequestMapping("bank")
public class BankController 
{
	@Autowired
	BankService bank;
	
	@PostMapping("create")
	public String createUser(@RequestBody UserData user) 
	{
		return "Your New Account Number is : "+bank.createUser(user);
		
	}
	
	@GetMapping(value="user/{account}", produces={"application/json"})
	public ResponseEntity<UserWrapper> fetchUser(@PathVariable("account") int accountNo)
	{
		return bank.getUserByAccountNo(accountNo);
	}
	
	@PutMapping("deposit")
	public String deposit(@RequestParam(name = "amount") int amount, @RequestParam(name = "accountNo") int accountNo)
	{
		bank.deposit(amount, accountNo);
		return "Amount Successfully Deposited ";
	}
	
	@PutMapping("withdraw")
	public String withdraw(@RequestParam(name = "amount") int amount, @RequestParam(name = "accountNo") int accountNo, 
							@RequestParam(name = "password") String password)
	{
		return bank.withdraw(amount, accountNo, password);
	}
	
	@GetMapping(value="balance/{account}", produces={"application/json"})
	public ResponseEntity<Integer> fetchBalance(@PathVariable("account") int accountNo)
	{
		return bank.getBalance(accountNo);
	}
	
	@PutMapping("getloan")
	public String getLoan(@RequestParam(name = "amount") int amount, @RequestParam(name = "accountNo") int accountNo, 
				@RequestParam(name = "password") String password)
	{
		return bank.checkLoan(amount, accountNo, password);
	}
	
	@PutMapping("repayloan")
	public String repay(@RequestParam(name = "amount") int amount, @RequestParam(name = "accountNo") int accountNo,
				@RequestParam(name = "password") String password)
	{
		return bank.repay(amount, accountNo, password);
	}
}
