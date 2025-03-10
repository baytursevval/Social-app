package com.sevval.socialapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevval.socialapp.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);

}
