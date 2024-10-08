package com.sma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sma.DTO.PostsDTO;
import com.sma.service.PostService;
import com.sma.service.UserService;

import jakarta.validation.Valid;

@RestController
public class PostController {
	@Autowired
	UserService userservice;
	@Autowired
	PostService postservice;

	@GetMapping(path = "/allposts")
	public ResponseEntity<List<PostsDTO>> getPosts() {
		List<PostsDTO> allposts = postservice.getAllPost();
		return new ResponseEntity<List<PostsDTO>>(allposts, HttpStatus.ACCEPTED);
	}

	@PostMapping(path = "/newpost")
	public ResponseEntity<PostsDTO> createpost(@Valid @RequestBody PostsDTO npost) {
		postservice.Create(npost);
		return new ResponseEntity<PostsDTO>(npost, HttpStatus.CREATED);

	}

	@GetMapping(path = "/byuser")
	public ResponseEntity<List<PostsDTO>> allposts() {
		List<PostsDTO> byCurrent = postservice.getByCurrent();
		return new ResponseEntity<List<PostsDTO>>(byCurrent, HttpStatus.FOUND);
	}

	@DeleteMapping("/deletepost/{id}")
	public ResponseEntity<String> del(@PathVariable int id) {
		postservice.delete(id);
		return new ResponseEntity<String>("deleted", HttpStatus.ACCEPTED);
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<PostsDTO> updatePost(@Valid @RequestBody PostsDTO updatePost, @PathVariable int id) {
		postservice.update(updatePost, id);
		return new ResponseEntity<PostsDTO>(updatePost, HttpStatus.ACCEPTED);

	}
}
