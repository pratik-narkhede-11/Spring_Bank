package com.pratik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.pratik.model.UserData;

public interface UserDao extends JpaRepository<UserData, Integer> 
{
	@Query(value = "SELECT * FROM user_data WHERE account_no = :accountNo", nativeQuery = true)
	UserData findByAccountNo(int accountNo);
}
