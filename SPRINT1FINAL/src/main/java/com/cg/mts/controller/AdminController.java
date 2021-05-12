package com.cg.mts.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entities.Admin;
import com.cg.mts.exception.AdminExistsException;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.service.IAdminService;

@RestController
@RequestMapping("/mts/admin")
public class AdminController {
	
	@Autowired
	IAdminService service;
	
	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	//for adding admin 
	@PostMapping("/addAdmin")
	public ResponseEntity<Object> addAdmin(@Valid @RequestBody Admin admin) {
		logger.info("Inside addAdmin method");
		Admin adminData;
		try {
			adminData = service.addNewAdmin(admin);
			return new ResponseEntity<Object>(adminData, HttpStatus.OK);
		} catch (AdminExistsException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	// for deleting admin
	@DeleteMapping("/deleteAdmin")
	public ResponseEntity<Object> deleteAdmin(@RequestParam int userId){
		logger.info("Inside deleteAdmin method");
		try {
			service.deleteAdmin(userId);
			return new ResponseEntity<Object>("Admin deleted", HttpStatus.OK);
		} catch (AdminNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
