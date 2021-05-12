
package com.cg.mts.controller;


import java.time.LocalDate;


import java.time.format.DateTimeFormatter;
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

import com.cg.mts.entities.Booking;
import com.cg.mts.exception.BookingExistsException;
import com.cg.mts.exception.BookingNotFoundException;

import com.cg.mts.service.IBookingService;

@RestController
@RequestMapping("MovieBooking/booking")
public class BookingController {

	@Autowired
	IBookingService service;
	Logger logger=LoggerFactory.getLogger(BookingController.class);
	
	// implementation of post method-by nagendra
	@PostMapping("/addBooking")
	public ResponseEntity<Object> addBooking(@Valid @RequestBody Booking booking) {
		logger.debug("BookingController is executing addBooking ");
		Booking bookingData;
		try {
			bookingData = service.addBooking(booking);
			return new ResponseEntity<Object>(bookingData, HttpStatus.OK);
		}
		 catch (BookingNotFoundException e) {
			 return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		catch (BookingExistsException e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
		
	
@PutMapping("/updateBooking")
	public ResponseEntity<Object> updateBooking(@Valid @RequestBody Booking booking)  {
		logger.debug("BookingController is executing updateBooking ");
		Booking bookingData = null;
		try {
			bookingData = service.updateBooking(booking);
			return new ResponseEntity<Object>(bookingData, HttpStatus.OK);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return new ResponseEntity<Object>("No Bookings found in DataBase with given ID",HttpStatus.UNAUTHORIZED);
	}
	@DeleteMapping("/cancelBooking/{bookingId}")
	public ResponseEntity<Object> cancelBooking(@PathVariable int bookingId){
		logger.debug("BookingController is executing cancelBooking ");
		Booking bookingData;
		try {
			bookingData = service.cancelBooking(bookingId);
			return new ResponseEntity<Object>(bookingData, HttpStatus.OK);
		
		} catch (BookingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Object>("No Bookings found in DataBase with given ID",HttpStatus.UNAUTHORIZED);
	}
	// This GET method implemented by raviteja
	@GetMapping("/showAllByMovieId/{movieId}")
	public ResponseEntity<Object> showAllBooking(@PathVariable int movieId)  {
		logger.debug("BookingController is executing find booking by movieId");
		List<Booking> bookingList;
		try {
			bookingList=service.showAllBookings(movieId);
			return new ResponseEntity<Object>(bookingList, HttpStatus.OK);
			}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>("No Bookings found in DataBase with given ID",HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/showAllByshowId/{showId}")
	public ResponseEntity<Object> showBookingList(@PathVariable int showId) {
		logger.debug("BookingController is executing find booking by show id");
		List<Booking> bookingList;
		try {
			bookingList=service.showBookingList(showId);
			return new ResponseEntity<Object>(bookingList, HttpStatus.OK);
			}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>("No Bookings found in DataBase with given ID",HttpStatus.UNAUTHORIZED);	}
	
	
	@GetMapping("/calculate/{bookingId}")
	public ResponseEntity<Object> calculateTotalCost(@PathVariable int bookingId){
		logger.debug("BookingController is executing calculate total amount ");
		Double bookingData;
		try {
			bookingData =service.calculateTotalCost(bookingId);
			return new ResponseEntity<Object>(bookingData,HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return new ResponseEntity<Object>("No Bookings found in DataBase with given ID",HttpStatus.UNAUTHORIZED);
		
		
	}
	
	@GetMapping("/selectAll")
	public ResponseEntity<Object> selectAllBooking() {
		logger.debug("BookingController is executing selectAll Booking ");

		List<Booking> bookingList;
		try {
			bookingList = service.getBooking();
			return new ResponseEntity<Object>(bookingList,HttpStatus.OK);
		}catch (BookingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Object>("No Bookings found in DataBase",HttpStatus.NO_CONTENT);	
	}
	
	
}

