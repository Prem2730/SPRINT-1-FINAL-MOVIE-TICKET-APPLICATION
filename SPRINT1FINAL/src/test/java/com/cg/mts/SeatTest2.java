package com.cg.mts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.mts.entities.BookingState;
import com.cg.mts.entities.Seat;
import com.cg.mts.exception.SeatNotFoundException;
import com.cg.mts.repository.ISeatRepository;
import com.cg.mts.service.ISeatService;

@SpringBootTest
public class SeatTest2 {
	
	@Autowired
	ISeatService service;
	
	@MockBean
	private ISeatRepository repository;
	
	public Seat getSeat() {
		Seat seat = new Seat(28,"M12","Special",120,BookingState.Booked);
		return seat;
	}
	
	public Seat getSeat2() {
		Seat seat = new Seat(28,"M12","Special",120,BookingState.Available);
		return seat;
	}
	
	@Test
	void testAddSeat() {
		Seat seatData = getSeat();
		Mockito.when(repository.existsBySeatId(seatData.getSeatId())).thenReturn(false);
		Mockito.when(repository.save(seatData)).thenReturn(seatData);
		Seat res = service.addSeat(seatData);
		assertEquals(res.getSeatId(), seatData.getSeatId());
	}
	
	@Test
	void testBlockSeat() throws SeatNotFoundException {
		Seat seatDataObj = getSeat();
		Mockito.when(repository.existsBySeatId(seatDataObj.getSeatId())).thenReturn(true);
		Mockito.when(repository.findBySeatId(seatDataObj.getSeatId())).thenReturn(seatDataObj);
		System.out.println("before"+seatDataObj);
		Seat res = service.blockSeat(seatDataObj.getSeatId());
		System.out.println("after"+res);
		assertEquals(res.getState(), seatDataObj.getState());
	}
	
	@Test
	void testCancelSeat() throws SeatNotFoundException {
		Seat seatDataObj = getSeat();
		Mockito.when(repository.existsBySeatId(seatDataObj.getSeatId())).thenReturn(true);
		Mockito.when(repository.findBySeatId(seatDataObj.getSeatId())).thenReturn(seatDataObj);
		System.out.println(seatDataObj);
		Seat res = service.cancelSeatBooking(seatDataObj.getSeatId());
		assertEquals(res.getState(), seatDataObj.getState());
	}
	
	@Test
	void testBookSeat() throws SeatNotFoundException {
		Seat seatDataObj = getSeat2();
		Mockito.when(repository.existsBySeatId(seatDataObj.getSeatId())).thenReturn(true);
		Mockito.when(repository.findBySeatId(seatDataObj.getSeatId())).thenReturn(seatDataObj);
		System.out.println(seatDataObj);
		Seat res = service.bookSeat(seatDataObj.getSeatId());
		assertEquals(res.getState(), seatDataObj.getState());
	}
}
