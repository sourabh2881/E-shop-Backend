package com.sourabh.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourabh.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	boolean existsByEmail(String email);
	User findById(int id);
	List<User> findByEmail(String email);

}
