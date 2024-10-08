package com.sma.DTO;

import java.util.List;

import com.sma.entity.Post;

public class UsersDTO {
	private String username;
	private List<Post> posts;

	public UsersDTO(String username, List<Post> posts) {
		super();
		this.username = username;
		this.posts = posts;
	}

	public String getUsername() {
		return username;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
