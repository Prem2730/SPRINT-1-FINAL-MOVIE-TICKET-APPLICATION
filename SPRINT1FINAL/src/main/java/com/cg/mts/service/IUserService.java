package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.User;

public interface IUserService {
	public  User addNewUser(User user) ;
	public User signIn(User user);
	public User signOut(User user);
	public List<User> getAll();
}
