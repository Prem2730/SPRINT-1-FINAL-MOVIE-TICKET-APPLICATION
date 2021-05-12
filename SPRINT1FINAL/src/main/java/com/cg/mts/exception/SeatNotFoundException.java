package com.cg.mts.exception;
//Exception occurs if seat not found
public class SeatNotFoundException extends Exception {
	public SeatNotFoundException(String message) {
		super(message);
	}
}
