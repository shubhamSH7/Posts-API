package com.sma.service;

import java.util.List;

import com.sma.DTO.PostsDTO;

public interface PostServiceInterface {
	List<PostsDTO> getAllPost();

	List<PostsDTO> getByUserName(String username);

	String delete(int id);

	PostsDTO update(PostsDTO post, int id);
}
