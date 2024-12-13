package com.sevval.socialapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevval.socialapp.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
