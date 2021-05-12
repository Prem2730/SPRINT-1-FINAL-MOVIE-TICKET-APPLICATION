package com.cg.mts.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.entities.Movie;
import com.cg.mts.entities.Theatre;
import com.cg.mts.repository.IMovieRepository;
import com.cg.mts.repository.ITheatreRepository;
import com.cg.mts.service.IMovieService;

import com.cg.mts.exception.MovieNotFoundException;

@Service
@Transactional
public class MovieService implements IMovieService{
	
	@Autowired
	IMovieRepository repository;
	
	@Autowired
	ITheatreRepository theatreRepository;
	
	Logger logger = LoggerFactory.getLogger(MovieService.class);
	
	
	@Override
	public Movie addMovie(Movie movie) throws MovieNotFoundException {
		logger.debug("addMovie method is accessed in MovieService");
		
		if(!repository.existsById(movie.getMovieId()))
		{
			Movie movieAdd = repository.save(movie);
			return movieAdd;	
		}
		else
		{
			logger.error("Movie Not Found Exception thrown");
			throw new MovieNotFoundException("Movie cannot be added as id "+movie.getMovieId()+ " already exist in Database.");
		}
	}

	
	
	@Override
	public String removeMovie(int movieid) throws MovieNotFoundException {
		logger.debug("removeMovie method is accessed in MovieService");
		
		if(repository.existsById(movieid))
			{
				Movie movieDelete = repository.getOne(movieid);
				repository.delete(movieDelete);
				return "Movie Deleted";
			}
		else 
			{
				logger.error("Movie Not Found Exception thrown");
				throw new MovieNotFoundException("Movie with Id "+movieid+ " cannot be deleted. Not Found in DB.");
			}			
		
	}

	
	
	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		logger.debug("updateMovie method is accessed in MovieService");
		
		if(repository.existsById(movie.getMovieId()))
		{
			Movie movieUpdate = repository.save(movie);
			return movieUpdate;
		}
		else
		{
			logger.error("Movie Not Found Exception thrown");
			throw new MovieNotFoundException("Movie with Id "+movie.getMovieId()+ " cannot be updated as it does not exist in DB");
		}
	}

	
	
	@Override
	public Movie viewMovie(int movieid) throws MovieNotFoundException {
			logger.debug("viewMovie method is accessed in MovieService");
			Movie movieView = repository.findByMovieId(movieid);
			if(!(movieView==null))
			{
				return movieView;
			}
				else
			{	
				logger.error("Movie Not Found Exception thrown");
				throw new MovieNotFoundException("Movie with "+movieid+" does not exist.");
			}
	}

	
	
	@Override
	public List<Movie> viewMovieList() throws MovieNotFoundException{
		logger.debug("viewMovieList method is accessed in MovieService");
		List<Movie> movieList = repository.findAll();
	
		if(!movieList.isEmpty())
			{
				return movieList;
			}
		else
		{
			logger.error("Movie Not Found Exception thrown");
			throw new MovieNotFoundException("Movie List does not exist.");
		}
	}

	
	

}	
	
	