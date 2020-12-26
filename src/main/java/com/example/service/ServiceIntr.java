package com.example.service;

import java.util.Map;

import com.example.bean.User;

public interface ServiceIntr {

	public Map<Integer, String> findCountry();
	public Map<Integer, String> findState(Integer cid);
	public Map<Integer, String> findCities(Integer sid);
	public boolean addUser(User user);
	public boolean isEmailUnique(String email);
	
	public String loginCheck(String email,String pwd);
	
	public boolean isTempPwdValid(String email,String pwd);
	
	public boolean unlockAccount(String email,String npwd);
	
	public String forgotPassword(String email);
	
}
