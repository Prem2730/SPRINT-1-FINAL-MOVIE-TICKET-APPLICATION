package com.cg.mts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.mts.entities.Booking;
import com.cg.mts.entities.BookingState;
import com.cg.mts.entities.Seat;
import com.cg.mts.entities.Ticket;
import com.cg.mts.exception.BookingExistsException;
import com.cg.mts.exception.BookingNotFoundException;
import com.cg.mts.repository.IBookingRepository;
import com.cg.mts.service.IBookingService;

@SpringBootTest
class Bookingtest {

	@Autowired
	IBookingService service;

	@MockBean
	private IBookingRepository repository;
	static LocalDate date = LocalDate.now();

	public Booking getBooking() {
		Seat seat = new Seat(1,"M12","Exec",120,BookingState.Available);
		List<Seat> seatList = new ArrayList<Seat>();
		seatList.add(seat);
		Ticket ticket = new Ticket(1, 1, seatList, true, "Screen 1");
		Booking booking = new Booking(1, 1, 1, date, 1, 120, ticket);
		return booking;
	}

	public List<Booking> getBookingList() {
		List<Booking> testBookingList = new ArrayList<Booking>();
		Seat seat = new Seat(1,"M12","Exec",120,BookingState.Available);
		List<Seat> seatList = new ArrayList<Seat>();
		seatList.add(seat);
		Ticket ticket = new Ticket(1, 1, seatList, true, "Screen 1");
		testBookingList.add(new Booking(1, 1, 1, date, 1, 100, ticket));
		testBookingList.add(new Booking(2, 2, 2, date, 2, 200, ticket));
		return testBookingList;
	}

	/*@Test
	void testAddBooking() throws BookingExistsException, BookingNotFoundException {
		Booking bookingData = getBooking();
		Mockito.when(repository.existsById(bookingData.getBookingId())).thenReturn(false);
		Mockito.when(repository.save(bookingData)).thenReturn(bookingData);
		Booking res = service.addBooking(bookingData);
		assertEquals(res.getBookingId(), bookingData.getBookingId());

	}*/

	@Test
	void testUpdateBooking() throws BookingNotFoundException {

		Booking bookingData = getBooking();
		Mockito.when(repository.existsById(bookingData.getBookingId())).thenReturn(true);
		Mockito.when(repository.save(bookingData)).thenReturn(bookingData);
		Booking res = service.updateBooking(bookingData);
		assertEquals(res.getBookingId(), bookingData.getBookingId());
	}

	@Test
	void testCancelBooking() throws BookingNotFoundException {
		Booking deleteBooking = getBooking();
		int bookingId = deleteBooking.getBookingId();
		Mockito.when(repository.existsById(bookingId)).thenReturn(true);
		service.cancelBooking(deleteBooking.getBookingId());
		verify(repository, times(1)).existsById(bookingId);
	}

	@Test
	public void getBookingByMovieId() throws BookingNotFoundException {
		List<Booking> bookingList = new ArrayList<Booking>();
		bookingList.add(getBooking());

		Mockito.when(repository.findAllBymovieId(1)).thenReturn(bookingList);
		List<Booking> bookingListData = service.showAllBookings(1);
		assertEquals(bookingList, bookingListData);

	}

	@Test
	public void getBookingByShowId() throws BookingNotFoundException {
		List<Booking> bookList = new ArrayList<Booking>();
		bookList.add(getBooking());

		Mockito.when(repository.findAllByShowId(0)).thenReturn(bookList);
		List<Booking> bookListData = service.showBookingList(0);
		assertEquals(bookList, bookListData);

	}

	@Test
	void testViewMovieList() throws BookingNotFoundException {

		List<Booking> bookingList = getBookingList();

		Mockito.when(repository.findAll()).thenReturn(bookingList);
		assertEquals(2, service.getBooking().size());
	}

	/*@Test
	void testViewBookingListLocalDate() throws BookingNotFoundException {
		List<Booking> bookingList = getBookingList();
		Mockito.when(repository.findAllByBookingDate(date)).thenReturn(bookingList);
		assertEquals(2, service.showAllBookings(date).size());
	}*/
}
