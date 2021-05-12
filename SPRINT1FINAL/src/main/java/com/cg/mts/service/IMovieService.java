package com.cg.mts.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.mts.entities.Movie;
import com.cg.mts.exception.MovieNotFoundException;

public interface IMovieService {
	public Movie addMovie(Movie movie) throws MovieNotFoundException;
	public String removeMovie(int movieid) throws MovieNotFoundException;
	public Movie updateMovie(Movie movie) throws MovieNotFoundException;
	public Movie viewMovie(int movieid) throws MovieNotFoundException;
	public List<Movie> viewMovieList() throws MovieNotFoundException;

	
}
