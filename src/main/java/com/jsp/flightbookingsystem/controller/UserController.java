package com.jsp.flightbookingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.flightbookingsystem.dto.LoginRequest;
import com.jsp.flightbookingsystem.dto.RegisterRequest;
import com.jsp.flightbookingsystem.dto.ResponseStructure;
import com.jsp.flightbookingsystem.entity.User;
import com.jsp.flightbookingsystem.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<User>> registerUser(@RequestBody RegisterRequest request){
		return userService.register(request);
	}
	 @PostMapping("/login")
	 public ResponseEntity<ResponseStructure<String>> login(@RequestBody LoginRequest request) {
	    return userService.login(request);
	 }
}
