package com.pratik.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserData 
{
	@Id
	@Column (name="account_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountNo;
	
	private String name;
	private String phno;
	private String email;
	private String password;
	
	public UserData() {
		super();
	}
	public UserData(int accountNo, String name, String phno, String email, String password) {
		super();
		this.accountNo = accountNo;
		this.name = name;
		this.phno = phno;
		this.email = email;
		this.password = password;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [accountNo=" + accountNo + ", name=" + name + ", phno=" + phno + ", email=" + email + ", password="
				+ password + "]";
	}
	
}
