package com.cg.mts.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entities.Movie;
import com.cg.mts.exception.MovieNotFoundException;
import com.cg.mts.service.IMovieService;


@RestController
@RequestMapping("/mts/movie")
public class MovieController {

	@Autowired
	IMovieService service;
	
	Logger logger=LoggerFactory.getLogger(MovieController.class);
	
	
	
	
	@PostMapping("/addMovie")
	public ResponseEntity<Object> addMovie(@Valid @RequestBody Movie movie){
		logger.debug("addMovie method is accessed from the MovieController");
		
		Movie movieAdd = new Movie();
		try {
			movieAdd = service.addMovie(movie);
			return new ResponseEntity<Object>(movieAdd, HttpStatus.CREATED);
		} 
		catch (MovieNotFoundException e) 
		{
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	@DeleteMapping("/deleteMovie/{id}")
	public ResponseEntity<String> deleteMovie(@PathVariable Integer id){
		logger.debug("deleteMovie method is accessed from the MovieController");
		
		String deleteString = null;
		try {
			deleteString = service.removeMovie(id);
			return new ResponseEntity<String>(deleteString, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);	
		}
			
	}
	
	
	
	@PostMapping("/updateMovie")
	public ResponseEntity<Object> updateMovie(@Valid @RequestBody Movie movie){
		logger.debug("updateMovie method is accessed from the MovieController");
		
		Movie movieUpdate = new Movie();
		try {
			movieUpdate = service.updateMovie(movie);
			return new ResponseEntity<Object>(movieUpdate, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
		

	@GetMapping("/viewMovie/{id}")
	public ResponseEntity<Object> viewMovie(@PathVariable int id){
		logger.debug("viewMovie method is accessed from the MovieController");
		
		Movie movieView = new Movie();
		try {
			movieView = service.viewMovie(id);
			return new ResponseEntity<Object>(movieView, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}	
	
		
	
	@GetMapping("/viewMovieList")
	public ResponseEntity<Object> viewMovieList(){
		logger.debug("viewMovieList method is accessed from the MovieController");
		
		List<Movie> movieList = new ArrayList<Movie>();
		try {
			movieList = service.viewMovieList();
			return new ResponseEntity<Object>(movieList, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage() , HttpStatus.BAD_REQUEST);
		}
		
	}
		
	
}	
		
	

