package com.cg.mts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.entities.Admin;
import com.cg.mts.exception.AdminExistsException;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.repository.IAdminRepository;
import com.cg.mts.repository.IUserRepository;

@Service
@Transactional
public class AdminService implements IAdminService {
	
	@Autowired
	IAdminRepository repository;
	
	@Autowired
	IUserRepository userRepository;
	
	Logger logger = LoggerFactory.getLogger(AdminService.class);
	// this method is for adding admin
	@Override
	public Admin addNewAdmin(Admin admin) throws AdminExistsException {
		logger.info("Inside addNewAdmin method");
		if(userRepository.existsByUserId(admin.getUserId())) {
			logger.error("Admin already exists");
			throw new AdminExistsException("Admin Id exists already");
		}
		else {
			Admin adminObj = repository.save(admin);
			logger.info("Admin added");
			return adminObj;
		}
	}
	// this method is for deleting
	@Override
	public void deleteAdmin(int userId) throws AdminNotFoundException {
		logger.info("Inside deleteAdmin method");
		if(userRepository.existsByUserId(userId)) {
			repository.deleteByUserId(userId);
		}
		else {
			logger.error("No admin with given id found");
			throw new AdminNotFoundException("No admin found with given userId");
		}
	}

}
