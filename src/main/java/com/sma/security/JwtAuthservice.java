package com.sma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sma.entity.Login;
import com.sma.entity.UserRepository;
import com.sma.entity.Users;
import com.sma.exception.UsernameexistsException;
import com.sma.service.UserService;

@Service
public class JwtAuthservice {
	@Autowired
	UserService uservice;

	@Autowired
	AuthenticationManager authm;

	@Autowired
	UserRepository repo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JWTService jwtservice;

	public JwtResponse login(Login user) {
		if (repo.existsByUsername(user.getUsername())) {
			throw new UsernameexistsException("Username already exists choose another and try again");
		}
		Users reguser = new Users(user.getUsername(), passwordEncoder.encode(user.getPassword()));
		repo.save(reguser);
		var jwttoker = jwtservice.generateToken(reguser);

		return new JwtResponse(jwttoker);

	}

	public JwtResponse authenciate(Login user) {
		authm.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		var loginuser = repo.findByUsername(user.getUsername()).get();
		var jwttoker = jwtservice.generateToken(loginuser);

		return new JwtResponse(jwttoker);
	}

}
