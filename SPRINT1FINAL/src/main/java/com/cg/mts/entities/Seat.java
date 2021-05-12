package com.cg.mts.entities;
//these are packages.
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "seat_master") //table 
public class Seat {
	@Id
	@GeneratedValue
	private int seatId;


	private String seatNumber;

	private String type;

	private double price;

	private BookingState state;

	public BookingState getState() {
		return state;
	}
	public void setState(BookingState state) {
		this.state = state;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Seat() {
		super();
	}
	public Seat(String seatNumber, String type, double price, BookingState state) {
		super();
		this.seatNumber = seatNumber;
		this.type = type;
		this.price = price;
		this.state = state;
	}
	public Seat(int seatId, String seatNumber, String type, double price,BookingState state) {
		super();
		this.seatId = seatId;
		this.seatNumber = seatNumber;
		this.type = type;
		this.price = price;
		this.state = state;
	}
	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", seatNumber=" + seatNumber + ", type=" + type + ", price=" + price
				+ ", state=" + state + "]";
	}
}
