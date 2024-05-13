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
	}
	
	@Transactional
	public String withdraw(int amount, int accountNo, String password) 
	{
		UserData ud = userRepo.findByAccountNo(accountNo);
		if(password.equals(ud.getPassword()))
		{
			Account ac = accountRepo.findByAccountNo(accountNo);
			if(ac.getBalance() >= amount)
			{
				int newBalance = ac.getBalance() - amount;
				accountRepo.updateBalance(accountNo, newBalance);
				return "Amount Successfully Withdrawn ";
			}
			else
				return "Insufficient Balance";
		}
		else
			return "Password Incorrect";
	}

	public ResponseEntity<Integer> getBalance(int accountNo) 
	{
		Account ac = accountRepo.findByAccountNo(accountNo);
		return new ResponseEntity<>(ac.getBalance(),HttpStatus.OK);
	}
	
	@Transactional
	public String checkLoan(int amount, int accountNo, String password) 
	{
		UserData ud = userRepo.findByAccountNo(accountNo);
		if(password.equals(ud.getPassword()))
		{
			Account ac = accountRepo.findByAccountNo(accountNo);
			if((ac.getBalance()*5 >= amount ) &&(ac.getLoanTaken() == 0))
			{
				getLoan(amount, accountNo);
				return "Loan Granted and sanctioned into your bank account";
			}
			else
			{
				if(ac.getLoanTaken() == 0)
					return "Loan Rejacted \nYou can get maximum Rs "+5 * ac.getBalance()+" as a total loan. "
							+ "\n(This Bank can offer loan upto 5x times your current balance)";
				else
					return "Clear the Previous Loan First";
			}
		}
		else
			return "Password Incorrect";
	}
	
	@Transactional
	public void getLoan(int amount, int accountNo) 
	{
		deposit(amount, accountNo);
		Account ac = accountRepo.findByAccountNo(accountNo);
		int newLoan = ac.getLoanTaken() + amount;
		accountRepo.updateLoan(accountNo, newLoan);
	}
	
	@Transactional
	public String repay(int amount, int accountNo, String password) 
	{
		Account ac = accountRepo.findByAccountNo(accountNo);
		UserData ud = userRepo.findByAccountNo(accountNo);
		if(password.equals(ud.getPassword()))
		{
			if((amount <= ac.getLoanTaken()) && (amount <= ac.getBalance()))
			{
				int newBalance = ac.getBalance() - amount;
				accountRepo.updateBalance(accountNo, newBalance);
				int newLoan = ac.getLoanTaken() - amount;
				accountRepo.updateLoan(accountNo, newLoan);
				return "Transaction Successful";
			}
			else if(amount > ac.getLoanTaken())
				return "Excess Amount!! Loan is Just Rs. "+ ac.getLoanTaken();
			else 
				return "Insufficient Balance";
		}
		else
			return "Password Incorrect";
	}

	
}
