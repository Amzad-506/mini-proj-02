package com.example.service;

import java.util.HashMap;
import java.util.Map;

import com.example.bean.User;

public class UserServiceImpl implements Service{

	@Override
	public Map<Integer, String> findCountry() {
		Map<Integer, String> m=new HashMap<Integer,String>();
		
		return null;
	}

	@Override
	public Map<Integer, String> findState(Integer cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> findCities(Integer sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmailUnique(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String loginCheck(String email, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTempPwdValid(String email, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unlockAccount(String email, String npwd) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
