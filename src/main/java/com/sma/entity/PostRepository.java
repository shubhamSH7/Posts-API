package com.sma.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findAllByUsers(Users users);

	Boolean existsByUsers(Users users);

}
