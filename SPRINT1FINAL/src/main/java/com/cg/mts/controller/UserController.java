package com.cg.mts.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entities.Customer;
import com.cg.mts.entities.User;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.exception.UserNotFoundException;
import com.cg.mts.service.IUserService;



@RestController
@RequestMapping("/mts/user")
public class UserController {

	@Autowired
	IUserService service;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@ExceptionHandler
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		logger.info("Inside addUser method");
		User userData = service.addNewUser(user);
		return new ResponseEntity<User>(userData, HttpStatus.OK);
	}

	@PostMapping("/signIn")
	public ResponseEntity<String> userSignin(@Valid @RequestBody User user){
		logger.info("Inside userSignin method");
		User userData = service.signIn(user);
		
		return new ResponseEntity<String>("Sign in succesful, welcome user: "+userData.getUserId(), HttpStatus.OK);
	}

	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser(){
		logger.info("Inside getAllUser method");
		List<User> userList = service.getAll();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

}
