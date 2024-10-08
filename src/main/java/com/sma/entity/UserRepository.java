package com.sma.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	Boolean existsByUsername(String username);

	Optional<Users> findByUsername(String username);
}
