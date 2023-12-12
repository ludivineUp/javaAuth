package com.livec.auth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livec.auth.models.User;

import jakarta.transaction.Transactional;

// Lien entre les tables et les classes ("requêtes SQL")
@Repository
@Transactional // droit d'écriture en base
public interface UserRepository extends JpaRepository<User, Long>{
	
	// généré automatiquement select by id, select all, update, insert, delete
	
	// select % from uses where id=? and pass = ?
	public Optional<User> findByIdAndPassword(long id, String pass); 
	
	public Optional<User> findByName(String name);

}
