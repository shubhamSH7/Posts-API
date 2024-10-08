package com.sma.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sma.entity.Admin;
import com.sma.entity.UserRepository;
import com.sma.entity.Users;

@Service
public class UserManager implements UserDetailsService {

	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> users = userRepo.findByUsername(username);
		if (users.get().getUsername().equals("admin")) {
			Admin admin = new Admin(users.get().getUsername(), users.get().getPassword());
			return admin;
		}
		if (users.isEmpty()) {
			throw new UsernameNotFoundException(username + "Not FOund");
		} else
			return users.get();
	}

}
