package com.pratik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.pratik.model.Account;

public interface AccountDao extends JpaRepository<Account, Integer> 
{
	@Query(value = "SELECT * FROM account WHERE account_no = :accountNo", nativeQuery = true)
	Account findByAccountNo(int accountNo);

	@Modifying
	@Query(value = "UPDATE account SET balance = :balance WHERE account_no = :accountNo", nativeQuery = true)
	void updateBalance(int accountNo, int balance);

	@Modifying
	@Query(value = "UPDATE account SET loan_taken = :newLoan WHERE account_no = :accountNo", nativeQuery = true)
	void updateLoan(int accountNo, int newLoan);

}
