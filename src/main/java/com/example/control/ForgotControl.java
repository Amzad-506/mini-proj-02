package com.example.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ServiceIntr;

@RestController
public class ForgotControl {

	@Autowired
	private ServiceIntr serv;
	
	@GetMapping("/forgotEmail/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable String email){
		return new ResponseEntity<String>(serv.forgotPassword(email),HttpStatus.OK);
	}
	
}
