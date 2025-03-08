package com.sevval.socialapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sevval.socialapp.entities.Like;
import com.sevval.socialapp.entities.Post;
import com.sevval.socialapp.entities.User;
import com.sevval.socialapp.repos.LikeRepository;
import com.sevval.socialapp.requests.LikeCreateRequest;

@Service
public class LikeService {

	private LikeRepository likeRepository;
	private UserService userService;
	private PostService postService;
	
	public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
		this.likeRepository = likeRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {

		if(userId.isPresent() && postId.isPresent())
			return likeRepository.findByUserIdAndPostId(userId.get(), postId.get()); // Değer varsa alınır.
		else if(userId.isPresent())
			return likeRepository.findByUserId(userId.get());
		else if(postId.isPresent())
			return likeRepository.findByPostId(postId.get());
		else
			return likeRepository.findAll();
	}

	public Like getOneLike(Long likeId) {
		
		return likeRepository.findById(likeId).orElse(null);
	}

	public Like createOneLike(LikeCreateRequest likeRequest) {
		
		User user= userService.getOneUser(likeRequest.getUserId());
		Post post=postService.getOnePostById(likeRequest.getPostId());
		
		if(user !=null && post != null) {
			Like like=new Like();
			like.setId(likeRequest.getId());
			like.setPost(post);
			like.setUser(user);
			return likeRepository.save(like);
		}else		
			return null;
	}

	public void deleteOneLikeById(Long likeId) {
		
		likeRepository.deleteById(likeId);
	}

	
	
	
}
