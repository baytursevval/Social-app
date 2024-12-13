package com.sevval.socialapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevval.socialapp.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
