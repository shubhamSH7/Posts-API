package com.sma.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sma.entity.PostRepository;
import com.sma.entity.UserRepository;
import com.sma.entity.Users;

@Service
public class UserService {
	PasswordEncoder passwordEncoder;
	UserRepository userRepo;
	PostRepository postRepo;

	public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder, PostRepository postRepo) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.postRepo = postRepo;
	}

	public List<Users> GetAll() {
		return userRepo.findAll();
	}

	public Users GetbyUsername(String username) {
		return userRepo.findByUsername(username).get();
	}

	public ResponseEntity<Users> CreateUser(Users users) {
		users.setPwd(passwordEncoder.encode(users.getPassword()));
		userRepo.save(users);
		return new ResponseEntity<Users>(HttpStatus.OK);
	}

	public ResponseEntity<Users> UpdateUser(String username, Users users) {
		Optional<Users> extuser = userRepo.findByUsername(username);
		if (extuser.isPresent()) {
			extuser.get().setName(users.getUsername());
			userRepo.save(extuser.get());
			return new ResponseEntity<Users>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Users>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> DeleteUser(String username) {
		if (userRepo.existsByUsername(username)) {
			int id = userRepo.findByUsername(username).get().getId();
			postRepo.deleteAllById(null);
			userRepo.deleteById(id);

			return ResponseEntity.ok("Deleted");
		}
		return ResponseEntity.ok("NO User OF ID " + " FOUND");
	}

}
