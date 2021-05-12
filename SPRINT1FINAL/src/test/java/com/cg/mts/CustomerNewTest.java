package com.cg.mts;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.mts.entities.Booking;
import com.cg.mts.entities.Customer;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.ICustomerRepository;
import com.cg.mts.service.CustomerService;
import com.cg.mts.service.ICustomerService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class CustomerNewTest {
	
	@Autowired
	ICustomerService service;

	@MockBean
	ICustomerRepository repository;
	
	Customer customer = new Customer();
	
	public Customer getCustomer() {
		Customer testCustomer = new Customer(5, "Nivvii", "9764312580", null, 7);
		return testCustomer;
		}
	
    public List<Customer> getCustomers(){
	    List<Customer> customers = new ArrayList<>();
	    customers.add(new Customer(2,"Sai", "9764318025", null, 4));
	    customers.add(new Customer(3,"Tarun", "9764318052", null, 5));
		return customers;
}
	
	public List<Customer> getMovieId() {
		List<Customer> testCustomer = getMovieId();
		testCustomer.add(new Customer(4,"Sony", "9764310825", null, 4));
	    testCustomer.add(new Customer(6,"Divya", "7963418052", null, 5));
		return testCustomer;
	}
	
	public Customer getCustomerId() {
		Customer testcustomer = getCustomerId();
		customer.setCustomerId(56);
		customer.setCustomerName("Prathyusha");
        customer.setMobileNumber("6549873210");
        customer.setMovieId(78);
		return testcustomer;
	}
	  
	@Test  
	void testAddCustomer() {
	
		Customer testAddCustomer = getCustomer();	
		Mockito.when(repository.existsById(testAddCustomer.getCustomerId())).thenReturn(false);
		Mockito.when(repository.save(testAddCustomer)).thenReturn(testAddCustomer);
		Customer result = service.addCustomer(testAddCustomer);
		assertEquals(result.getCustomerId(), testAddCustomer.getCustomerId());
	
	}
	
	@Test
	void testViewMovieId() throws CustomerNotFoundException {
		List<Customer> testViewMovieId = new ArrayList<Customer>();
		testViewMovieId.add(getCustomer());
		Mockito.when(repository.findAllByMovieId(2)).thenReturn(testViewMovieId);
		List<Customer> result = service.viewAllCustomersByMovieId(2);
		assertEquals(testViewMovieId, result);
	}
	
	@Test
	void testViewCustomerId() throws CustomerNotFoundException {
		Customer testViewCustomerId = getCustomer();
		int userId = customer.getCustomerId();
		Mockito.when(repository.existsById(userId)).thenReturn(true);
		Mockito.when(repository.findByUserId(userId)).thenReturn(testViewCustomerId);
		Customer result = service.viewCustomer(customer.getCustomerId());
		assertEquals(testViewCustomerId, result);
	}

}


