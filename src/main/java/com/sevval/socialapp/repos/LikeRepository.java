package com.sevval.socialapp.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevval.socialapp.entities.Like;


public interface LikeRepository extends JpaRepository<Like, Long> {
	
	List<Like> findByUserIdAndPostId(Long userId, Long postId);

	List<Like> findByUserId(Long userId);

	List<Like> findByPostId(Long postId);

}
