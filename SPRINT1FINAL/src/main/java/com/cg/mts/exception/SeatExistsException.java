package com.cg.mts.exception;
//this is the exception if existing seat not found
public class SeatExistsException extends RuntimeException {
	public SeatExistsException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
}
