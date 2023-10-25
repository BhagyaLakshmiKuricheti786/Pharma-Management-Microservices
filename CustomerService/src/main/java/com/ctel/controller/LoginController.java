package com.ctel.controller;

import java.security.Principal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctel.config.JwtUtil;
import com.ctel.entity.LoginRequest;
import com.ctel.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	@Autowired
	private JwtUtil util;
	
	@Autowired
	private CustomerService customerservice;
	
	@PostMapping("/login")
	public HashMap<String, Object> loginCustomer(@RequestBody LoginRequest request)
	{
		System.out.println("before authenticationManager");
		authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmailId(),request.getPassword()));
		System.out.println("after authenticationManager");
		String accessToken = util.generateToken(request.getEmailId());
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("accessToken", accessToken);
		return map;
	}
	@PostMapping("/welcome")
	public ResponseEntity<String> accessData(Principal p) {
		return ResponseEntity.ok("Hello User!!!" + p.getName());
	}
}
