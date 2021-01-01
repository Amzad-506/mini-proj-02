package com.example.control;

import java.util.Map;

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

@RestController
public class RegistrationRestController {
	
	@Autowired
	private ServiceIntr serv;
	
	@GetMapping(value = "/country")
	public Map<Integer,String> countries(){
		return serv.findCountry();
	}
	@GetMapping(value = "/states/{cid}")
	public Map<Integer,String> states(@PathVariable Integer cid){
		return serv.findState(cid);
	}
	@GetMapping(value = "/cities/{sid}")
	public Map<Integer,String> cities(@PathVariable Integer sid){
		return serv.findCities(sid);
	}

	
	@PostMapping(
			value = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<String> addUser(@RequestBody User user){
		boolean addUser = serv.addUser(user);
		if(addUser) {
			return new ResponseEntity<String>("User Account Created",HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Account Creation Failure",HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<String> emailUnique(@PathVariable("email") String email){
		boolean emailUnique = serv.isEmailUnique(email);
		if(emailUnique) {
			return new ResponseEntity<String>("Unique Email",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Email Alredy In Use",HttpStatus.OK);
	}
	
}
