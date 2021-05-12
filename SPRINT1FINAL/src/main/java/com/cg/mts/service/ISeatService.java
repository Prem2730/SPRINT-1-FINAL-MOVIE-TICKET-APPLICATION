package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.Seat;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.exception.SeatExistsException;
import com.cg.mts.exception.SeatNotFoundException;

public interface ISeatService {
	public Seat bookSeat(int seatId) throws SeatNotFoundException;
	public Seat cancelSeatBooking(int seatId) throws SeatNotFoundException;
	public Seat blockSeat(int seatId) throws SeatNotFoundException; //not available for any booking 
	public List<Seat> getAll() throws SeatNotFoundException;
	public Seat addSeat(Seat seat) throws SeatExistsException;
}
