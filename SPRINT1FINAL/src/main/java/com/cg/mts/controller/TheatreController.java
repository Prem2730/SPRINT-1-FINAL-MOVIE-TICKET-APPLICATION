package com.cg.mts.controller;

//import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.cg.mts.entities.Theatre;
import com.cg.mts.repository.ITheatreRepository;

@RestController
@RequestMapping("/mts/theatre")
public class TheatreController {

	@Autowired
	ITheatreRepository repository;
	
	Logger logger=LoggerFactory.getLogger(TheatreController.class);
	
	@PostMapping("/addTheatre")
	public ResponseEntity<Theatre> addTheatre(@Valid @RequestBody Theatre theatre){
		logger.info("addTheatre method is accessed from the MovieController");
		Theatre theatreAdd = repository.save(theatre);
		return new ResponseEntity<Theatre>(theatreAdd, HttpStatus.OK);
	}
	
}
