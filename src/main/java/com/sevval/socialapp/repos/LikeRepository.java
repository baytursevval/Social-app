package com.sevval.socialapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevval.socialapp.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
