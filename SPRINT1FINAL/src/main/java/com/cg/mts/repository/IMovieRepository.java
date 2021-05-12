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
	
	
	/*
	public Movie addMovie(Movie movie);
	public Movie removeMovie(int movieid) throws MovieNotFoundException;
	public Movie updateMovie(Movie movie) throws MovieNotFoundException;
	public Movie viewMovie(int movieid) throws MovieNotFoundException;
	public List<Movie> viewMovieList();
	public List<Movie> viewMovieList(int theatreid);
	public List<Movie> viewMovieList(LocalDate date);
	*/
	
}
