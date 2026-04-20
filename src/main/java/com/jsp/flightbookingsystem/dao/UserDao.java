package com.jsp.flightbookingsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.flightbookingsystem.entity.User;
import com.jsp.flightbookingsystem.repository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
