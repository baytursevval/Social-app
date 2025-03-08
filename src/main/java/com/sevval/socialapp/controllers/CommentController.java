package com.sevval.socialapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sevval.socialapp.entities.Comment;
import com.sevval.socialapp.requests.CommentCreateRequest;
import com.sevval.socialapp.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping
	public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
		
		return commentService.getAllCommentsWithParam(userId, postId);
		
	}
	
	@GetMapping("/{commentId}")
	public Comment getOneComment(@PathVariable Long commentId) {
		return commentService.getOneCommentById(commentId);
	}

	@PostMapping
	public Comment createOneComment(@RequestBody CommentCreateRequest commentRequest) {
		return commentService.createOneComment(commentRequest);
	}
	
	@PutMapping("/{commentId}")
	public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentCreateRequest commentRequest) {
		return commentService.updateOneComment(commentId, commentRequest);
	}
	
	@DeleteMapping("/{commentId}")
	public void deleteComment(@PathVariable Long commentId) {
		commentService.deleteOneCommentById(commentId);
	}
}
