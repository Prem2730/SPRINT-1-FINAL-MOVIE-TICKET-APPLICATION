package com.cg.mts.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="customer_master")
public class Customer extends User {

	private int customerId;

	private String customerName;

	private String mobileNumber;
	@OneToMany(fetch = FetchType.EAGER )
	@JoinColumn(name="customer_id")
	private List<Booking> myBookings = new ArrayList<>();
	private int movieId;
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public List<Booking> getMyBookings() {
		return myBookings;
	}
	public void setMyBookings(List<Booking> myBookings) {
		this.myBookings = myBookings;
	}
	public Customer() {
		super();
	}
	
	public Customer(String customerName, String mobileNumber, List<Booking> myBookings, int movieId) {
		super();
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.myBookings = myBookings;
		this.movieId = movieId;
	}
	public Customer(int customerId, String customerName, String mobileNumber, List<Booking> myBookings,
			int movieId) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.myBookings = myBookings;
		this.movieId = movieId;
	}
	
}
