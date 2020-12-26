package com.example.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.User;
import com.example.bean.Validate;
import com.example.service.ServiceIntr;

@RestController("/reg")
public class RegistrationRestController {
	
	@Autowired
	private ServiceIntr serv;

	@PostMapping(
			value = "/login",
			consumes = {MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<String> login(@RequestBody Validate val){
			String loginCheck = serv.loginCheck(val.getEmail(), val.getPassword());
			return new ResponseEntity<String>(loginCheck,HttpStatus.OK);
	}
	@PostMapping(
			value = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<String> addUser(@RequestBody User user){
		boolean addUser = serv.addUser(user);
		if(addUser==true) {
			return new ResponseEntity<String>("User Account Created",HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Account Creation Failure",HttpStatus.OK);
	}
	
	@GetMapping(value = "/forgot/{fp}")
	public ResponseEntity<String> forgot(@PathVariable("fp") String email){
		String forgotPassword = serv.forgotPassword(email);
		return new ResponseEntity<String>(forgotPassword,HttpStatus.OK);
	}
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<String> emailUnique(@PathVariable("email") String email){
		boolean emailUnique = serv.isEmailUnique(email);
		if(emailUnique==true) {
			return new ResponseEntity<String>("Unique Email",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Email Alredy In Use",HttpStatus.OK);
	}
	
}
