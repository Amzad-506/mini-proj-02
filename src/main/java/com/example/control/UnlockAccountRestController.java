package com.example.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.Uvalidate;
import com.example.service.ServiceIntr;
import com.example.service.UserServiceImpl;

@RestController("/unlock")
public class UnlockAccountRestController {
	
	@Autowired
	private ServiceIntr serv; 

	@PostMapping(
			value = "/unlockAccount",consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<String> unlockaccount(@RequestBody Uvalidate uv){
		boolean tempPwdValid = serv.isTempPwdValid(uv.getEmail(),uv.getTpwd());
		if(tempPwdValid==true) {
			boolean unlockAccount = serv.unlockAccount(uv.getEmail(), uv.getRpwd());
			return new ResponseEntity<String>("User Account Unlocked",HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("User Account Locked",HttpStatus.CREATED);
	}
}
