package com.cg.mts.controller;
//these are packages.
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entities.Seat;
import com.cg.mts.exception.SeatExistsException;
import com.cg.mts.exception.SeatNotFoundException;
import com.cg.mts.service.ISeatService;

@RestController
@RequestMapping("/mts/seat")
public class SeatController {
//autowired with seat service
	@Autowired
	ISeatService service;
//logger - to see output.
	Logger logger = LoggerFactory.getLogger(SeatController.class);

// This implementation is for booking seat & if not throw exception.
	@PostMapping("/bookSeat")
	public ResponseEntity<Object> bookSeat(@RequestParam int seatId)  {
		logger.info("Inside bookSeat method"); //logger helps to see is message.
		Seat seatData = null;
		try {  //check the exception.
			seatData = service.bookSeat(seatId);
			return new ResponseEntity<Object>(seatData, HttpStatus.OK);
		} catch (SeatNotFoundException e) { //if exception excute this block.
			// TODO Auto-generated catch block
			logger.error("Seat Not Found Exception");
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
// This implementation is for  cancel seat & if not throw exception.
	@PutMapping("/cancelSeat")
	public ResponseEntity<Object> cancelSeat(@RequestParam int seatId) {
		logger.info("Inside cancelSeat method");
		Seat seatData;
		try {//To check the exception.
			seatData = service.cancelSeatBooking(seatId);
			return new ResponseEntity<Object>(seatData, HttpStatus.OK);
		} catch (SeatNotFoundException e) { //if exception excute this block.
			// TODO Auto-generated catch block
			logger.error("Seat Not Found Exception");
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
// This implementation is for block seat - if not throw exception
	@PutMapping("/blockSeat")
	public ResponseEntity<Object> blockSeat(@RequestParam int seatId) { //check in data
		Seat seatData;
		logger.info("Inside blockSeat method");
		try {//To check the exception.
			seatData = service.blockSeat(seatId);
			return new ResponseEntity<Object>(seatData, HttpStatus.OK);
//if exception  catch blocks catches the exception and print exception message.
		} catch (SeatNotFoundException e) {//if exception excute this block.
			// TODO Auto-generated catch block
			logger.error("Seat Not Found Exception"); //this message prints.
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

// This implementation is for add new seat - if not throw exception	
	@PostMapping("/addNewSeat")
	public ResponseEntity<Object> addNewSeat(@Valid @RequestBody Seat seat){
		Seat seatData=null;
		try {//To check the exception.
			seatData = service.addSeat(seat);
			return new ResponseEntity<Object>(seatData, HttpStatus.OK);
			//if exception  catch blocks catches the exception and print exception message.
		} catch (SeatExistsException e) {//if exception excute this block.
			// TODO Auto-generated catch block
			logger.error("Seat Exists Exception");//this message prints.
			//e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	// This implementation is for get avaliable seats - if not throw exception
	@GetMapping("/getAvailableSeats")
	public ResponseEntity<Object> getAllSeats() {
		List<Seat> seatList;
		try {//To check the exception.
			seatList = service.getAll();
			return new ResponseEntity<Object>(seatList,HttpStatus.OK);
		} catch (SeatNotFoundException e) {//if exception excute this block.
			// TODO Auto-generated catch block
			logger.error("Seat Not Found Exception");
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

//this done by vishnu
}
