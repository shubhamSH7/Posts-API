package com.sma.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sma.DTO.PostsDTO;
import com.sma.entity.Post;
import com.sma.entity.PostRepository;
import com.sma.entity.UserRepository;
import com.sma.entity.Users;
import com.sma.exception.DeleteExceptions;
import com.sma.exception.PostNotFound;
import com.sma.exception.UpdateException;

@Service
public class PostService implements PostServiceInterface {

	@Autowired
	PostRepository postRepo;
	@Autowired
	UserRepository userRepo;

//	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	private Users getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return userRepo.findByUsername(username).get();
	}

	public Post Create(PostsDTO post) {
		Users users = getCurrentUser();
		Post newpost = new Post(post.getDesc());
		newpost.setUser(users);
		return postRepo.save(newpost);
	}

	public List<PostsDTO> getAllPost() {
		List<PostsDTO> pdto = postRepo.findAll().stream()
				.map(m -> new PostsDTO(m.getId(), m.getDesc(), m.getUser().getUsername())).collect(Collectors.toList());
		if (pdto.isEmpty()) {
			throw new PostNotFound("No posts available try creating new posts");
		}
		return pdto;
	}

	@Override
	public List<PostsDTO> getByUserName(String username) {
		Users users = userRepo.findByUsername(username).get();
		if (!postRepo.existsByUsers(users)) {
			throw new UsernameNotFoundException(username + "No POST found");
		}
		List<PostsDTO> pdto = postRepo.findAllByUsers(users).stream()
				.map(m -> new PostsDTO(m.getId(), m.getDesc(), m.getUser().getUsername())).collect(Collectors.toList());
		return pdto;
	}

	public List<PostsDTO> getByCurrent() {
		Users users = getCurrentUser();
		if (!postRepo.existsByUsers(users)) {
			throw new PostNotFound("No POST found");
		}
		List<PostsDTO> pdto = postRepo.findAllByUsers(users).stream()
				.map(m -> new PostsDTO(m.getId(), m.getDesc(), m.getUser().getUsername())).collect(Collectors.toList());
		return pdto;
	}

	@Override
	public PostsDTO update(PostsDTO post, int id) {

		if (!postRepo.existsById(id)) {
			throw new PostNotFound("No POST found");
		}

		Post p = postRepo.findById(id).get();
		Users users = getCurrentUser();
		post.setUsersname(users.getUsername());
		if (!users.getUsername().equals(p.getUser().getUsername())) {
			throw new UpdateException("You are not the author of this post");
		}
		p.setDesc(post.getDesc());
		postRepo.save(p);
		return post;
	}

	@Override
	public String delete(int id) {
		Users users = getCurrentUser();
		if (!postRepo.existsById(id)) {
			throw new DeleteExceptions("Post Does not exist!");
		}
		Post post = postRepo.findById(id).get();
		if (!users.equals(post.getUser())) {
			throw new DeleteExceptions("You are the owner of this post");
		} else {
			postRepo.deleteById(id);
			return "deleted";
		}

	}

}
