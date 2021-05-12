package com.cg.mts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.entities.BookingState;
import com.cg.mts.entities.Seat;
import com.cg.mts.exception.SeatExistsException;
import com.cg.mts.exception.SeatNotFoundException;
import com.cg.mts.repository.ISeatRepository;

@Service
@Transactional
public class SeatService implements ISeatService {

	@Autowired
	ISeatRepository repository;
	
	Logger logger = LoggerFactory.getLogger(SeatService.class);
	
	@Override
	public Seat bookSeat(int seatId) throws SeatNotFoundException {
		logger.info("Inside bookSeat method");
		Seat newSeat = repository.findBySeatId(seatId);
		if(newSeat == null) {
			logger.error("Exception in bookSeat method");
			throw new SeatNotFoundException("Invalid Seat Id");
		}
		else {
			newSeat.setState(BookingState.Booked);
			logger.info("Seat Booked");
			return newSeat;
		}
		
	}

	@Override
	public Seat cancelSeatBooking(int seatId) throws SeatNotFoundException {
		logger.info("Inside cancelSeatBooking method");
		Seat newSeat = repository.findBySeatId(seatId);
		if(newSeat == null) {
			logger.error("Exception in cancelSeatBooking method");
			throw new SeatNotFoundException("Invalid Seat Id");
		}
		else {
			newSeat.setState(BookingState.Available);
			logger.info("Seat Cancelled");
			return newSeat;
		}
	}

	@Override
	public Seat blockSeat(int seatId) throws SeatNotFoundException {
		logger.info("Inside blockSeat method");
		Seat newSeat = repository.findBySeatId(seatId);
		if(newSeat == null){
			logger.error("Exception in blockSeat method");
			throw new SeatNotFoundException("Invalid Seat Id");
		}
		else {
			newSeat.setState(BookingState.Blocked);
			logger.info("Seat Blocked");
			return newSeat;
		}
	}
	
	@Override
	public List<Seat> getAll() throws SeatNotFoundException {
		logger.info("Inside getAll method");
		List<Seat> seatList = repository.findAll();
		if(seatList.isEmpty()){
			logger.error("Seat Database is empty");
			throw new SeatNotFoundException("Seat Database is empty");
		}
		else
			return seatList;
	}

	@Override
	public Seat addSeat(Seat seat) {
		if(repository.existsById(seat.getSeatId())) {
			logger.error("Seat Already exists");
			throw new SeatExistsException("Seat Id already exists");
		}
		Seat newSeat = repository.save(seat);
		logger.info("New Seat Added");
		return newSeat;
	}
	
}
