package com.sevval.socialapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sevval.socialapp.entities.Post;
import com.sevval.socialapp.entities.User;
import com.sevval.socialapp.repos.PostRepository;
import com.sevval.socialapp.requests.PostCreateRequest;
import com.sevval.socialapp.requests.PostUpdateRequest;

import responses.PostResponse;

@Service
public class PostService {
	
	private PostRepository postRepository;
	private UserService userService;

	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService=userService;
	}

	public List<PostResponse> getAllPosts(Optional<Long> userId) {
		List<Post> list;
		if(userId.isPresent())
			list= postRepository.findByUserId(userId.get());
		list= postRepository.findAll();
		return list.stream()
				.map(p -> new PostResponse(p)) // Her Post objesini PostResponse DTO'suna dönüştür
				.collect(Collectors.toList()); // Listeyi dönüştürerek döndür
	}

	public Post getOnePostById(Long postId) {
		
		return postRepository.findById(postId).orElse(null);
	}

	public Post createOnePost(PostCreateRequest newPostRequest) {
		
		User user = userService.getOneUser(newPostRequest.getUserId());
		if(user==null)
			return null;
		Post toSave=new Post();
		toSave.setId(newPostRequest.getId());
		toSave.setText(newPostRequest.getText());
		toSave.setTitle(newPostRequest.getTitle());
		toSave.setUser(user);
		return postRepository.save(toSave);
	}

	/*
	public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
		
		Optional<Post> post= postRepository.findById(postId);
		if(post.isPresent()) {
			Post toUpdate= post.get();
			toUpdate.setText(updatePost.getText());
			toUpdate.setTitle(updatePost.getTitle());
			postRepository.save(toUpdate);
		}
		return null;
	}*/
	
	

	public void deleteOnePostById(Long postId) {
		
		postRepository.deleteById(postId);
		
	}

	public Post updateOnePostById(Long postId, PostCreateRequest postUpdateRequest) {
		
		Optional<Post> post= postRepository.findById(postId);
		if(post.isPresent()) {
			Post toUpdate= post.get();
			toUpdate.setText(postUpdateRequest.getText());
			toUpdate.setTitle(postUpdateRequest.getTitle());
			postRepository.save(toUpdate);
		}
		return null;
	}

	

}
