package com.cg.mts.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entities.Customer;

import com.cg.mts.exception.CustomerNotFoundException;

import com.cg.mts.service.ICustomerService;



@RestController
@RequestMapping("/mts/customer")
public class CustomerController {

	@Autowired
	ICustomerService service;
	
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
        // implementation for adding customer
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
		logger.info("Inside addCustomer method");
		Customer customerData = service.addCustomer(customer);
		return new ResponseEntity<Customer>(customerData, HttpStatus.OK);
	}
// implementation for deleting customer using by customerid
	@DeleteMapping("/deleteCustomerById/{customerId}")
	public ResponseEntity<Object> deleteCustomer(@RequestParam int customerId){
		logger.info("Inside deleteCustomerById method");
		try {
		     service.deleteCustomer(customerId);
			return new ResponseEntity<Object>("Customer deleted", HttpStatus.OK);
		}
		catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Object>("Customer data not found", HttpStatus.UNAUTHORIZED);
	}

//implementaion for getting Customer  using userid
	@GetMapping("/getCustomerById/{userId}")
	public ResponseEntity<Object> getCustomerById(@PathVariable("userId") int id) {
		logger.info("Inside getCustomerById method");
		Customer customerData;
		try {
			customerData = service.viewCustomer(id);
			return new ResponseEntity<Object>(customerData, HttpStatus.OK);
		} 
		catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Object>("Customer not found", HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/getCustomerByMovieId/{movieId}")
	public ResponseEntity<Object> getCustomerMovieId(@PathVariable("movieId") int id) {
		logger.info("Inside getCustomerById method");
		List<Customer> customerData;
		try {
			customerData = service.viewAllCustomersByMovieId(id);
			return new ResponseEntity<Object>(customerData, HttpStatus.OK);
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return new ResponseEntity<Object>("MovieId not found", HttpStatus.UNAUTHORIZED);
	}
// gettting all customers
	@GetMapping("/getAllCustomer")
	public ResponseEntity<Object>getAllCustomer(){
		logger.info("Inside getAllCustomer method");
		List<Customer> customerList;
		try {
			customerList = service.showAll();
			return new ResponseEntity<Object>(customerList, HttpStatus.OK);
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return new ResponseEntity<Object>("Customers not found", HttpStatus.UNAUTHORIZED);
	}
//to update customer
	@PutMapping("/updateCustomer")
	public ResponseEntity<Object> updateCustomer(@Valid @RequestBody Customer customer) {
		logger.info("Inside updateCustomer method");
		Customer customerData;
		try {
			customerData = service.updateCustomer(customer);
			return new ResponseEntity<Object>(customerData, HttpStatus.OK);
		} 
		catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return new ResponseEntity<Object>("Customer not updated", HttpStatus.UNAUTHORIZED);
	}

}
