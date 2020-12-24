package com.example.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.bean.User;
import com.example.repo.UserRepo;

public class UserServiceImpl implements Service{
	
	@Autowired
	private UserRepo ur;
	
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
		User save = ur.save(user);
		if(save.getUid()==null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isEmailUnique(String email) {
		User findByEmail = ur.findByEmail(email);
		if(email.equals(findByEmail.getEmail())) {
			return true;
		}
		return false;
	}

	@Override
	public String loginCheck(String email, String pwd) {
		User findByEmail = ur.findByEmail(email);
		String msg="";
		if(findByEmail.getUid()==null) {
			msg="User Doesn't Exist";
		}
		
		if(email.equals(findByEmail.getEmail()) && pwd.equals(findByEmail.getPwd())) {
			msg="valid Credentials";
		}else {
			msg="Invalid Password";
		}
		return msg;
		
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
