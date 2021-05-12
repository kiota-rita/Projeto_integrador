package com.doare.repassa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doare.repassa.model.User;

public interface UserRepository {

	public interface UsuarioRepository extends JpaRepository<User, Long> {
		public Optional<User> findByNome(String nome);
	}
}
