package com.pratik.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Account 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_no", referencedColumnName = "account_no")
    private UserData user;
	@Column(columnDefinition = "integer default 0")
	private int balance;
	@Column(columnDefinition = "integer default 0")
	private int loanTaken;
	public Account() {
		super();
	}
	public Account(UserData user, int balance, int loanTaken) {
		super();
		this.user = user;
		this.balance = balance;
		this.loanTaken = loanTaken;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserData getUser() {
		return user;
	}
	public void setUser(UserData user) {
		this.user = user;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getLoanTaken() {
		return loanTaken;
	}
	public void setLoanTaken(int loanTaken) {
		this.loanTaken = loanTaken;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", user=" + user + ", balance=" + balance + ", loanTaken=" + loanTaken + "]";
	}
	
	
}

