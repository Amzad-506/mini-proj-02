package com.example.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	@Autowired
	private JavaMailSender mail;

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
		List<State> findAll = sr.findByCid(cid);
		findAll.forEach(state -> {
			states.put(state.getSid(),state.getSname());
		});
		return states;
	}

	@Override
	public Map<Integer, String> findCities(Integer sid) {
		Map<Integer, String> cities =new HashMap<>();
		List<City> findAll = ctr.findBySid(sid);
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
				pass[i]=str.charAt(rand.nextInt(str.length()));
			}
			return pass.toString();
		
	}

	@Override
	public boolean addUser(User user) {
		boolean emailUnique = isEmailUnique(user.getEmail());
		user.setPwd(password());
		user.setIsActive("LOCKED");
		User save = ur.save(user);
		
		String emailBody = getUnlockAccEmailBody(user);
		String sub="Unlock Your Account | IES";
		boolean isSent = sendAccountUnlockEmail(sub, emailBody, user.getEmail());
		
		return save.getUid()!=null && isSent;
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
			if(findByEmailAndPwd.getIsActive().equals("LOCKED")) {
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
		findByEmail.setIsActive("UNLOCKED");
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
			if(findByEmail.getIsActive().equals("LOCKED")) {
				return "Your Account Is Locked! Unlock it First";
			}
			String body = getForgotEmail(findByEmail);
			sendAccountUnlockEmail("Your Password Is Safe", body, findByEmail.getEmail());
			return "Password Sent to Your Email";
		}
		return "INVALID EMAIL";
	}

	@Override
	public boolean sendAccountUnlockEmail(String sub,String body,String to) {
		try {
			MimeMessage mime = mail.createMimeMessage();
			MimeMessageHelper mm=new MimeMessageHelper(mime);
			mm.setTo(to);
			mm.setSubject(sub);
			mm.setText(body,true);
			mail.send(mime);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private String getUnlockAccEmailBody(User user){

		StringBuffer sb = new StringBuffer("");
		try {
			File f = new File("unlockmsg.txt");

		    FileReader fr = new FileReader(f);

		    BufferedReader br = new BufferedReader(fr);

		    String line = br.readLine();

		while(line!=null){
			
			if(line.contains("{FNAME}")){
				line = line.replace("{FNAME}",user.getFname());
			}

			if(line.contains("{LNAME}")){
				line = line.replace("{LNAME}",user.getLname());
			}
			
			if(line.contains("{TEMP-PWD}")){
			     line = line.replace("{TEMP-PWD}", user.getPwd());
			}

			if(line.contains("{EMAIL}")){
			     line = line.replace("{EMAIL}", user.getEmail());
			}

			sb.append(line);

			line = br.readLine();
		   }
		br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	private String getForgotEmail(User user){

		StringBuffer sb = new StringBuffer("");
		try {
			File f = new File("forgotmsg.txt");

		    FileReader fr = new FileReader(f);

		    BufferedReader br = new BufferedReader(fr);

		    String line = br.readLine();

		while(line!=null){
			
			if(line.contains("{FNAME}")){
				line = line.replace("{FNAME}",user.getFname());
			}

			if(line.contains("{LNAME}")){
				line = line.replace("{LNAME}",user.getLname());
			}
			
			if(line.contains("{TEMP-PWD}")){
			     line = line.replace("{TEMP-PWD}", user.getPwd());
			}

			if(line.contains("{EMAIL}")){
			     line = line.replace("{EMAIL}", user.getEmail());
			}

			sb.append(line);

			line = br.readLine();
		   }
		br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	

}






