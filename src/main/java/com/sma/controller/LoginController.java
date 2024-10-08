package com.sma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sma.entity.Login;
import com.sma.entity.UserRepository;
import com.sma.security.JWTService;
import com.sma.security.JwtAuthservice;
import com.sma.security.JwtResponse;

import jakarta.validation.Valid;

@RestController
public class LoginController {
	@Autowired
	JWTService jwtService;

	@Autowired
	UserDetailsService uds;

	@Autowired
	JwtAuthservice authserv;

	@Autowired
	UserRepository repo;

	@Autowired
	PasswordEncoder pass;

	@PostMapping(path = "/register")
	public ResponseEntity<JwtResponse> auth(@Valid @RequestBody Login user) {
		return ResponseEntity.ok(authserv.login(user));
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> authenticate(@Valid @RequestBody Login user) {
		return ResponseEntity.ok(authserv.authenciate(user));
	}
}
