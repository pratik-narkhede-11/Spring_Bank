package com.pratik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		return "Account No is : "+bank.createUser(user);
		
	}
	
	@GetMapping(value="user/{account}", produces={"application/json"})
	public ResponseEntity<UserWrapper> fetchUser(@PathVariable("account") int accountNo)
	{
		return bank.getUserByAccountNo(accountNo);
	}
	
	@PostMapping("deposit")
	public String deposit(@RequestParam(name = "amount") int amount, @RequestParam(name = "accountNo") int accountNo)
	{
		bank.deposit(amount, accountNo);
		return "Amount Successfully Deposited ";
	}
}
