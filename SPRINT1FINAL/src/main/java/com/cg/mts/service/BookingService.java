

package com.cg.mts.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.controller.BookingController;
import com.cg.mts.entities.Booking;
import com.cg.mts.entities.BookingState;

import com.cg.mts.entities.Seat;
import com.cg.mts.entities.Ticket;
import com.cg.mts.exception.BookingExistsException;
import com.cg.mts.exception.BookingNotFoundException;

import com.cg.mts.repository.IBookingRepository;
import com.cg.mts.repository.IMovieRepository;
import com.cg.mts.repository.IShowRepository;
import com.cg.mts.service.IBookingService;

@Service
@Transactional

public class BookingService implements IBookingService {
@Autowired
IBookingRepository repository;
@Autowired
IShowRepository showrepository;
@Autowired
IMovieRepository movierepository;
Logger logger=LoggerFactory.getLogger(BookingController.class);
	


@Override
	public Booking addBooking(Booking booking) throws BookingNotFoundException,BookingExistsException {
	logger.debug("BookingService is implementing add booking method");
	if(repository.existsById(booking.getBookingId())) {
		logger.error("BookingExistsException thrown");
		throw new BookingExistsException("Booking Id already exists");
	}
	if(!(showrepository.existsById(booking.getShowId()) && movierepository.existsById(booking.getMovieId()))) {
		logger.error("BookingNotFoundException thrown");
		throw new BookingNotFoundException("Booking Cannot Done as Id not exists either Movie or Show");

	}
	
		Booking bookingData =repository.save(booking);
		logger.info("New Booking Added");

		return bookingData;
}	
	
	@Override
	public List<Booking> getBooking() throws BookingNotFoundException {
		logger.debug("BookingService is implementing find all booking method");
		List<Booking> bookingList = repository.findAll();
		if(bookingList.isEmpty()){
			logger.error("Booking Database is empty");
			throw new BookingNotFoundException("Booking Database is empty");
		}
		else
			logger.info("Booking List");
			return bookingList;	}
	
	
	
	
	@Override
	public Booking updateBooking(Booking booking) throws BookingNotFoundException {
		logger.debug("BookingService is implementing update booking method");
		 booking=repository.save(booking);
		if(!repository.existsById(booking.getBookingId())) {
			logger.error("Booking Not Found Exception thrown");
			throw new BookingNotFoundException("Booking not Found with given details" +booking);
			}
		else
			logger.info("Booking Updated with given inputs");
		 return booking;
	}

	
	
	@Override
	public Booking cancelBooking(int bookingId) throws BookingNotFoundException {
		logger.debug("BookingService is implementing cancel booking method");
		if(!repository.existsById(bookingId)) {
			 logger.error("Booking Not Found Exception thrown");
			 throw new BookingNotFoundException("Booking is not available with the given booking ID" +bookingId);
			 }
			 repository.deleteById(bookingId);
			 logger.info("Booking Deleted");
			return null;
	}
	
	
@Override
	public List<Booking> showAllBookings(int movieId) throws BookingNotFoundException {
		logger.debug("BookingService is implementing find booking by movie id method");
		List<Booking> bookingList = repository.findAllBymovieId(movieId);
		if (bookingList.isEmpty()) {
			logger.error("BookingNotFoundException in getByMovieId method");
			throw new BookingNotFoundException("No Customer present");
		}

		else
			logger.info("Booking List By MovieId");
			return bookingList;
	}


@Override
	public List<Booking> showBookingList(int showId) throws BookingNotFoundException {
		logger.debug("BookingService is implementing find booking by showid method");
		List<Booking> bookingList = repository.findAllByShowId(showId);
		if (bookingList.isEmpty()) {
			logger.error("BookingNotFoundException in getByShowId method");
			throw new BookingNotFoundException("No Booking present");
		}

		else
			logger.info("Booking List By ShowId");
			return bookingList;
	}
		
		
	

	@Override
	public double calculateTotalCost(int bookingId) throws BookingNotFoundException {
		logger.debug("BookingService is implementing calculating total cost method");
		Booking book1=repository.getOne(bookingId);
		Ticket ticket =book1.getTicket();
		int numSeat=ticket.getNoOfSeats();
		List<Seat> seatList =ticket.getSeatNumber();
		Seat seat =seatList.get(0);
		double seatPrice = seat.getPrice();
		double TotalCost=seatPrice*numSeat;
		if(!repository.existsById(bookingId)) {
			 logger.error("Booking Not Found Exception thrown");
			 throw new BookingNotFoundException("Booking is not available with the given booking ID" +bookingId);
			 }
		else
			return TotalCost;
	}
	
	
	
	/*@Override
	public List<Booking> showAllBookings(LocalDate bookingdate) throws BookingNotFoundException {
		logger.debug("BookingService is implementing showAllBookings by booking Date method");
		List<Booking> bookList = repository.findAllByBookingDate(bookingdate);
		if (bookList.isEmpty()) {
			logger.error("BookingNotFoundException in getByShowId method");
			throw new BookingNotFoundException("No Booking present");
		}
		else
			logger.info("Booking List By BookingDate");
		return bookList;
		
	}*/

}

