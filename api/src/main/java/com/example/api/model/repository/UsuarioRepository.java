package com.example.api.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Double> {
	
	Optional<Usuario> findByEmail(String email); // Query méthod, não preciso informar o sql
	
	boolean existsByEmail(String email);
}
