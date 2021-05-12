package com.cg.mts.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.Show;

@Repository
public interface IShowRepository extends JpaRepository<Show, Integer> {
	public List<Show> findByTheatreId(int theatreId);
	public boolean existsByShowId(int showId);
//	public List<Show> findByShowStartTime(LocalDateTime showStartTime);
	public void deleteByShowId(int showId);
	public Show findByShowId(int showId);
}
