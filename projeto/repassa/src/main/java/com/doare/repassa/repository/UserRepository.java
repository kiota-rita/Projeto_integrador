package com.doare.repassa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doare.repassa.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByEmail(String email);
}
