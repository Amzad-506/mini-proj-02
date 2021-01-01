package com.example.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.Validate;
import com.example.service.ServiceIntr;

@RestController
public class LoginControl {
	
	@Autowired
	private ServiceIntr serv;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Validate val){
		
		return new ResponseEntity<String>(
				serv.loginCheck(val.getEmail(),val.getPassword()),HttpStatus.OK);
	}
}
