package com.cg.mts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.Customer;
import com.cg.mts.exception.CustomerNotFoundException;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
	public List<Customer> findAllByMovieId(int movieId);
	public Customer findByUserId(int userId);
	public void deleteByCustomerId(int customerId);
	public boolean existsByCustomerId(int customerId);
	public boolean existsByUserId(int userId);
	
}
