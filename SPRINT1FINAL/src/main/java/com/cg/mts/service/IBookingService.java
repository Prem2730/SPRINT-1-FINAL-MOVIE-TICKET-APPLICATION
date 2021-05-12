
package com.cg.mts.service;

import java.time.LocalDate;

import java.util.List;

import com.cg.mts.entities.Booking;
import com.cg.mts.exception.BookingExistsException;
import com.cg.mts.exception.BookingNotFoundException;

//implemented functions with exceptions
public interface IBookingService {
	public Booking addBooking(Booking booking)throws BookingNotFoundException,BookingExistsException;
	public Booking updateBooking(Booking booking) throws BookingNotFoundException;
	public Booking cancelBooking(int bookingid) throws BookingNotFoundException;
	public List<Booking> showAllBookings(int movieid)throws BookingNotFoundException;
	public List<Booking> showBookingList(int showid)throws BookingNotFoundException;
	public double calculateTotalCost(int bookingid)throws BookingNotFoundException;
	public List<Booking> getBooking()throws BookingNotFoundException;
	
	
	
}

