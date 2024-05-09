package com.pratik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.pratik.dao.AccountDao;
import com.pratik.dao.UserDao;
import com.pratik.model.Account;
import com.pratik.model.UserData;
import com.pratik.model.UserWrapper;

import jakarta.transaction.Transactional;

@Service
public class BankService 
{
	@Autowired
	UserDao userRepo;
	
	@Autowired
	AccountDao accountRepo;

	public int createUser(UserData user) 
	{
		userRepo.save(user);
		Account ac = new Account(user, 0, 0);
		accountRepo.save(ac);
		return user.getAccountNo();
	}

	public ResponseEntity<UserWrapper> getUserByAccountNo(int accountNo) 
	{
		UserData ud =  userRepo.findByAccountNo(accountNo);
		UserWrapper uw = new UserWrapper();
		uw.setAccountNo(ud.getAccountNo());
		uw.setEmail(ud.getEmail());
		uw.setName(ud.getName());
		uw.setPhno(ud.getPhno());

		return new ResponseEntity<>(uw,HttpStatus.OK);
	}
	
	@Transactional
	public void deposit(int amount, int accountNo) 
	{
		Account ac = accountRepo.findByAccountNo(accountNo);
		int newBalance = ac.getBalance() + amount;
		accountRepo.updateBalance(accountNo, newBalance);
		System.out.println(ac);
	}
}
