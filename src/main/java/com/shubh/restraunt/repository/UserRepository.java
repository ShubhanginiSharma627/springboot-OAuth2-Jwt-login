package com.shubh.restraunt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.restraunt.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
