package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.City;
import com.example.bean.Country;
import com.example.bean.State;
import com.example.bean.User;
import com.example.repo.CityRepo;
import com.example.repo.CountryRepo;
import com.example.repo.StateRepo;
import com.example.repo.UserRepo;

@Service
public class UserServiceImpl implements ServiceIntr{
	
	@Autowired
	private UserRepo ur;
	@Autowired
	private CountryRepo cr;
	@Autowired
	private StateRepo sr;
	@Autowired
	private CityRepo ctr;
	

	@Override
	public Map<Integer, String> findCountry() {
		Map<Integer, String> countries=new HashMap<Integer,String>();
		List<Country> countriesList = cr.findAll();
		countriesList.forEach(country -> {
			countries.put(country.getCid(), country.getCname());
		});
		return countries;
	}

	@Override
	public Map<Integer, String> findState(Integer cid) {
		Map<Integer, String> states =new HashMap<>();
		List<State> findAll = sr.findAll();
		findAll.forEach(state -> {
			states.put(state.getSid(),state.getSname());
		});
		return states;
	}

	@Override
	public Map<Integer, String> findCities(Integer sid) {
		Map<Integer, String> cities =new HashMap<>();
		List<City> findAll = ctr.findAll();
		findAll.forEach(city -> {
			cities.put(city.getCtid(),city.getCname());
		});
		return cities;
	}
	
	private String password() {
		char[] pass=new char[5];
		String str="abcdefgABCDEFG01234546789";
		Random rand=new Random();
		for(int i=0;i<5;i++) {
			pass[i]=str.charAt(rand.nextInt());
		}
		return pass.toString();
	}

	@Override
	public boolean addUser(User user) {
		user.setPwd(password());
		user.setActive("LOCKED");
		User save = ur.save(user);
		return save.getUid()!=null;
	}
	
	@Override
	public boolean isEmailUnique(String email) {
		User findByEmail = ur.findByEmail(email);
		return findByEmail==null;
	}

	
	@Override
	public String loginCheck(String email, String pwd) {
		User findByEmailAndPwd = ur.findByEmailAndPwd(email, pwd);
		if(findByEmailAndPwd!=null) {
			if(findByEmailAndPwd.getPwd().equals("LOCKED")) {
				return "ACCOUNT LOCKED";
			}else {
				return"LOGIN SUCCESS";
			}
		}
		return "INVALID CREDENTIALS";	
	}

	@Override
	public boolean isTempPwdValid(String email, String tempwd) {
		User findByEmailAndPwd = ur.findByEmailAndPwd(email, tempwd);
		return findByEmailAndPwd.getUid()!=null;
	}

	@Override
	public boolean unlockAccount(String email, String npwd) {
		User findByEmail = ur.findByEmail(email);
		findByEmail.setPwd(npwd);
		findByEmail.setActive("UNLOCKED");
		try {
			User save = ur.save(findByEmail);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public String forgotPassword(String email) {
		User findByEmail = ur.findByEmail(email);
		if(findByEmail!=null) {
			return findByEmail.getPwd();
		}
		return "INVALID EMAIL";
	}
	

}






