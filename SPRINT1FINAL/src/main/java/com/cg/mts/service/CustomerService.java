package com.cg.mts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.entities.Booking;
import com.cg.mts.entities.Customer;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.ICustomerRepository;

@Service
@Transactional
public class CustomerService implements ICustomerService{

	@Autowired
	ICustomerRepository repository;
	
	Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	@Override
	public Customer addCustomer(Customer customer) {
		logger.info("Inside addCustomer method");
		Customer customerObj = repository.save(customer);
		return customerObj;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		logger.info("Inside updateCustomer method");
		return repository.save(customer);
	}

	@Override
	public void deleteCustomer(int customerId) throws CustomerNotFoundException {
		logger.info("Inside deleteCustomer method");
			if(repository.existsByCustomerId(customerId)) {
				repository.deleteByCustomerId(customerId);
			}
			else 
			 throw new CustomerNotFoundException("Invalid Id, cannot delete customer");
									
	}

	@Override
	public Customer viewCustomer(int userId) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		logger.info("Inside viewCustomer method");
		Customer customerData = repository.findByUserId(userId);
			if (customerData == null) {
				logger.error("CustomerNotFoundException in getCustomerById method");
				throw new CustomerNotFoundException("No Customer present with the given id " + userId);
			}
			else
				return customerData;
	}

	@Override
	public List<Customer> viewAllCustomersByMovieId(int movieid) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		logger.info("Inside viewAllCustomersByMovieId method");
		List<Customer> customerList = repository.findAllByMovieId(movieid);
		if (customerList.isEmpty()) {
			logger.error("CustomerNotFoundException in getMovieById method");
			throw new CustomerNotFoundException("No MovieId present");
		}
		return customerList;
	}

	
	@Override
	public List<Customer> showAll() throws CustomerNotFoundException{
		// TODO Auto-generated method stub
		logger.info("Inside showAll method");
		List<Customer> customerList = repository.findAll();
		if(customerList.isEmpty()) {
			logger.error("CustomerNotFoundException in getAllCustomer method");
			throw new CustomerNotFoundException("No Customers found");
		}
		else
			return customerList;
	}

}
