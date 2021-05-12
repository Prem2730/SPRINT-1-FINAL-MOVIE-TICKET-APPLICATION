package com.cg.mts.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entities.Show;
import com.cg.mts.exception.ShowExistsException;
import com.cg.mts.exception.ShowNotFoundException;
import com.cg.mts.service.IShowService;

@RestController
@RequestMapping("/mts/show")
public class ShowController {

	@Autowired
	IShowService service;

	Logger logger = LoggerFactory.getLogger(ShowController.class);

	@PostMapping("/addShow")
	public ResponseEntity<Object> addShow(@RequestBody Show show){
		logger.info("Inside addShow method");
		Show showData;
		try {
			showData = service.addShow(show);
			return new ResponseEntity<Object>(showData, HttpStatus.OK);

		} catch (ShowExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Object>("Cannot add show, Show Id exists", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getAllShows")
	public ResponseEntity<Object> getAllShows(){
		logger.info("Inside getAllShows method");
		List<Show> showList;
		try {
			showList = service.viewAllShows();
			return new ResponseEntity<Object>(showList, HttpStatus.OK);
		} catch (ShowNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Object>("No show in database", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deleteShow")
	public ResponseEntity<String> deleteShow(@RequestParam int showId) {
		logger.info("Inside deleteShow method");
		try {
			service.removeShow(showId);
			return new ResponseEntity<String>("Show deleted", HttpStatus.OK);
		} catch (ShowNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Show Id not found, cannot delete show", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getShowByShowId/{showId}")
	public ResponseEntity<Object> getShowByShowId(@PathVariable("showId") int id){
		logger.info("Inside getShowByShowId method");
		Show showData;
		try {
			showData = service.viewShow(id);
			return new ResponseEntity<Object>(showData, HttpStatus.OK);
		} catch (ShowNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Object>("No show found, Invalid showId", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getShowByTheatreId/{theatreId}")
	public ResponseEntity<Object> getShowsByTheatreId(@PathVariable("theatreId") int id){
		logger.info("Inside getShowByTheatreId method");
		List<Show> showList;
		try {
			showList = service.viewShowList(id);
			return new ResponseEntity<Object>(showList, HttpStatus.OK);

		} catch (ShowNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Object>("No show found with given Theatre Id: "+id, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/updateShow")
	public ResponseEntity<Object> updateShow(@RequestBody Show show){
		Show showData;
		try {
			showData = service.updateShow(show);
			return new ResponseEntity<Object>(showData, HttpStatus.OK);
		} catch (ShowNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Object>("Cannot update show, Showid invalid", HttpStatus.BAD_REQUEST);
	}
}
