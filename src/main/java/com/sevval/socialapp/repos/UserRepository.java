package com.sevval.socialapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevval.socialapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
