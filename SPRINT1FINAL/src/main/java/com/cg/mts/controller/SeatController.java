package com.cg.mts.controller;

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
	@Autowired
	ISeatService service;

	Logger logger = LoggerFactory.getLogger(SeatController.class);

	@PostMapping("/bookSeat")
	public ResponseEntity<Object> bookSeat(@RequestParam int seatId)  {
		logger.info("Inside bookSeat method");
		Seat seatData = null;
		try {
			seatData = service.bookSeat(seatId);
			return new ResponseEntity<Object>(seatData, HttpStatus.OK);
		} catch (SeatNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Seat Not Found Exception");
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}

	@PutMapping("/cancelSeat")
	public ResponseEntity<Object> cancelSeat(@RequestParam int seatId) {
		logger.info("Inside cancelSeat method");
		Seat seatData;
		try {
			seatData = service.cancelSeatBooking(seatId);
			return new ResponseEntity<Object>(seatData, HttpStatus.OK);
		} catch (SeatNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Seat Not Found Exception");
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}

	@PutMapping("/blockSeat")
	public ResponseEntity<Object> blockSeat(@RequestParam int seatId) {
		Seat seatData;
		logger.info("Inside blockSeat method");
		try {
			seatData = service.blockSeat(seatId);
			return new ResponseEntity<Object>(seatData, HttpStatus.OK);

		} catch (SeatNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Seat Not Found Exception");
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	
	@PostMapping("/addNewSeat")
	public ResponseEntity<Object> addNewSeat(@Valid @RequestBody Seat seat){
		Seat seatData=null;
		try {
			seatData = service.addSeat(seat);
			return new ResponseEntity<Object>(seatData, HttpStatus.OK);
		} catch (SeatExistsException e) {
			// TODO Auto-generated catch block
			logger.error("Seat Exists Exception");
			//e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getAvailableSeats")
	public ResponseEntity<Object> getAllSeats() {
		List<Seat> seatList;
		try {
			seatList = service.getAll();
			return new ResponseEntity<Object>(seatList,HttpStatus.OK);
		} catch (SeatNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Seat Not Found Exception");
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}


}