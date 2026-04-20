package com.jsp.flightbookingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.flightbookingsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
