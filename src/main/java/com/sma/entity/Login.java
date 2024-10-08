package com.sma.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Login {
	@NotNull(message = "Username cannot be null")
	@Size(min = 4, max = 16, message = "Username must be between 4 to 16 characters long")
	private String username;

	@NotNull(message = "Password cannot be null")
	@Size(min = 4, max = 16, message = "Password must be between 4 to 16 characters long")
	private String password;

	public Login() {
		super();
	}

	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
