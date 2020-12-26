package com.example.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bean.User;

public interface UserRepo extends JpaRepository<User,Serializable>{

	public User findByEmail(String email);
	public User findByEmailAndPwd(String email,String password);
	
}
