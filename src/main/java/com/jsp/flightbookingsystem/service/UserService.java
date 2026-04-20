package com.jsp.flightbookingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.flightbookingsystem.dao.UserDao;
import com.jsp.flightbookingsystem.dto.LoginRequest;
import com.jsp.flightbookingsystem.dto.RegisterRequest;
import com.jsp.flightbookingsystem.dto.ResponseStructure;
import com.jsp.flightbookingsystem.entity.User;
import com.jsp.flightbookingsystem.security.JwtUtil;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired 
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtil jwtUtil;
	public ResponseEntity<ResponseStructure<User>> register(RegisterRequest request){
		User user=new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole());
		User savedUser=userDao.saveUser(user);
		ResponseStructure<User> response =new ResponseStructure<User>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("User registered");
		response.setData(savedUser);
		return new ResponseEntity<ResponseStructure<User>>(response, HttpStatus.CREATED);
	}
	 public ResponseEntity<ResponseStructure<String>> login(LoginRequest request) {
	        User user = userDao.findByEmail(request.getEmail());

	        if (user == null)
	            throw new RuntimeException("User not found");

	        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
	            throw new RuntimeException("Invalid credentials");

	        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

	        ResponseStructure<String> response = new ResponseStructure<String>();
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Login successful");
	        response.setData(token);
	        return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
	    }
}
