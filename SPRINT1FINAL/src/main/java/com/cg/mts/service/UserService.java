package com.cg.mts.service;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.entities.User;
import com.cg.mts.exception.UserNotFoundException;
import com.cg.mts.repository.IUserRepository;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	IUserRepository repository;
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Override
	public User addNewUser(User user) {
		logger.info("Inside addNewUser method");
		User newUser = repository.save(user);
		return newUser;
	}

	@Override
	public User signIn(User user) {
		logger.info("Inside signIn method");
		int id = user.getUserId();
		String password = user.getPassword();
		String role = user.getRole();
		User userData = repository.findByUserIdAndPasswordAndRole(id, password, role);
		if(userData==null) {
			logger.error("UserNotFoundException in userSignIn method");
			throw new UserNotFoundException("No user present");
		}
		else {
			
			return userData;
		}
		
	}

	@Override
	public User signOut(User user) {
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		List<User> userList = repository.findAll();
		if(userList.isEmpty()) {
			logger.error("UserNotFoundException in getAlluser method");
			throw new UserNotFoundException("No Users found");
		}
		else
			return userList;
	}

}
