package com.sma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sma.entity.Users;
import com.sma.service.UserService;

@RestController
@RequestMapping("/admin")
public class UserController {
	@Autowired
	UserService userservice;

	@GetMapping(path = "/users")
	public List<Users> GetAllUsers() {
		return userservice.GetAll();
	}

	@GetMapping(path = "/user/{username}")
	public Users GetID(@PathVariable String username) {
		return userservice.GetbyUsername(username);
	}

	@PostMapping(path = "/createuser")
	public ResponseEntity<Users> createuser(@RequestBody Users users) {

		return userservice.CreateUser(users);
	}

	@PutMapping(path = "/update/{username}")
	public ResponseEntity<Users> update(@RequestBody Users users, @PathVariable String username) {
		return userservice.UpdateUser(username, users);
	}

	@DeleteMapping(path = "/delete/{username}")
	public ResponseEntity<String> delete(@PathVariable String username) {
		return userservice.DeleteUser(username);

	}

}
