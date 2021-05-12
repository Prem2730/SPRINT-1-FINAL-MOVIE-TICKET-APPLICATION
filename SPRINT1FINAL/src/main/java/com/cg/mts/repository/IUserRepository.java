package com.cg.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	public User findByUserIdAndPasswordAndRole(int userId, String password, String role);
	public boolean existsByUserId(int userId);
	public User findByUserId(int userId);
}
