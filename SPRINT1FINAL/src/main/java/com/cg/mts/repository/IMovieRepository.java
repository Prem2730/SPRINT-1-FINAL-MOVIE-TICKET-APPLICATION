package com.cg.mts.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.exception.MovieNotFoundException;
import com.cg.mts.entities.Movie;


@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer> {

	//public List<Movie> findByMovieReleaseDate(LocalDate movieReleaseDate) throws MovieNotFoundException;
	public Movie findByMovieId(int movieId) throws MovieNotFoundException;	 
	
	
	
}
//This repository is implemented by Ratna Kumari
