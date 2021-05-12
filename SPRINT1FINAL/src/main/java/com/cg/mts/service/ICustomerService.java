package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.Booking;
import com.cg.mts.entities.Customer;
import com.cg.mts.exception.CustomerFoundException;
import com.cg.mts.exception.CustomerNotFoundException;

public interface ICustomerService {
	public Customer addCustomer(Customer customer); 
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	public void deleteCustomer(int customerId) throws CustomerNotFoundException;
	public Customer viewCustomer(int userId) throws CustomerNotFoundException;
	public List<Customer> viewAllCustomersByMovieId(int movieid) throws CustomerNotFoundException;
	public List<Customer> showAll() throws CustomerNotFoundException;
		
}
