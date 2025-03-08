package com.sevval.socialapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sevval.socialapp.entities.Post;
import com.sevval.socialapp.requests.PostCreateRequest;
import com.sevval.socialapp.requests.PostUpdateRequest;
import com.sevval.socialapp.services.PostService;

import responses.PostResponse;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {
	
	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){
		return postService.getAllPosts(userId);
	}
	
	@GetMapping("/{postId}")
	public Post getOnePost(@PathVariable Long postId) {
		return postService.getOnePostById(postId);
	}
	
	@PostMapping
	public Post createOnePost(@RequestBody PostCreateRequest newPostRequest) {
		return postService.createOnePost(newPostRequest);
	}
	
	/*
	@PutMapping("/{postId}")
	public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest) {
		return postService.updateOnePostById(postId, postUpdateRequest);
	} */
	
	@PutMapping("/{postId}")
	public Post updateOnePost(@PathVariable Long postId, @RequestBody PostCreateRequest postUpdateRequest) {
		return postService.updateOnePostById(postId, postUpdateRequest);
	}
	
	@DeleteMapping("/{postId}")
	public void deleteOnePost(@PathVariable Long postId) {
		postService.deleteOnePostById(postId);
	}

}
