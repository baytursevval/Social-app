package com.sevval.socialapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sevval.socialapp.entities.Comment;
import com.sevval.socialapp.entities.Post;
import com.sevval.socialapp.entities.User;
import com.sevval.socialapp.repos.CommentRepository;
import com.sevval.socialapp.requests.CommentCreateRequest;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	private UserService userService;
	private PostService postService;

	public CommentService(CommentRepository commentRepository, UserService userService, 
			PostService postService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {

		if(userId.isPresent() && postId.isPresent()) {
			return commentRepository.findByUserIdAndPostId(userId.get(), postId.get()); // Değer varsa alınır.
		}else if(userId.isPresent()) {
			return commentRepository.findByUserId(userId.get());
		}else if(postId.isPresent())
			return commentRepository.findByPostId(postId.get());
		else 
			return commentRepository.findAll();
	}

	public Comment getOneCommentById(Long commentId) {

		return commentRepository.findById(commentId).orElse(null);
	}

	public Comment createOneComment(CommentCreateRequest commentRequest) {
		
		User user = userService.getOneUser(commentRequest.getUserId());
		Post post =postService.getOnePostById(commentRequest.getPostId());
		
		if(user != null && post != null) {
			Comment comment= new Comment();
			comment.setId(commentRequest.getId());
			comment.setUser(user);
			comment.setPost(post);
			comment.setText(commentRequest.getText());
			return commentRepository.save(comment);
		}else
		
			return null;
	}

	public Comment updateOneComment(Long commentId, CommentCreateRequest commentRequest) {
		Optional<Comment> comment= commentRepository.findById(commentId);
		if(comment.isPresent()) {
			Comment commentToUpdate= comment.get();
			commentToUpdate.setText(commentRequest.getText());
			return commentRepository.save(commentToUpdate);
		}else
			return null;
	}

	public void deleteOneCommentById(Long commentId) {
		commentRepository.deleteById(commentId);
		
	}
	
	

}
