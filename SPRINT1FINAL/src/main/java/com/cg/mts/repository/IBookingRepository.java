package com.cg.mts.repository;



import java.time.LocalDate;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.Booking;
import com.cg.mts.exception.BookingNotFoundException;

import ch.qos.logback.classic.pattern.DateConverter;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Integer> {
	 public List<Booking> findAllByShowId( int  showid);
	 public List<Booking> findAllBymovieId( int  movieid);
//	 public List<Booking> findAllByBookingDate(LocalDate bookingDate);
}





