package com.cg.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.BookingState;
import com.cg.mts.entities.Seat;

@Repository
public interface ISeatRepository extends JpaRepository<Seat, Integer> {
	public Seat findBySeatId(int seatId);
	
	public boolean existsBySeatId(int seatId);
	
}
