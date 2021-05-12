package com.cg.mts.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ticket_master")
public class Ticket {
	@Id
	@GeneratedValue
	private int ticketId;
	private int noOfSeats;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "ticket_id")
	private List<Seat> seatNumber;
	
	@OneToOne
	 @JoinColumn(name="booking_Id") 
	 private Booking bookingRef;
	 
	private boolean ticketStatus;
	private String screenName;
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public List<Seat> getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(List<Seat> seatNumber) {
		this.seatNumber = seatNumber;
	}


	 
	public boolean isTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(boolean ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public Ticket() {
		super();
	}
	public Ticket(int noOfSeats, List<Seat> seatNumber, boolean ticketStatus, String screenName) {
		super();
		this.noOfSeats = noOfSeats;
		this.seatNumber = seatNumber;
		this.ticketStatus = ticketStatus;
		this.screenName = screenName;
	}
	public Ticket(int ticketId, int noOfSeats, List<Seat> seatNumber, boolean ticketStatus,
			String screenName) {
		super();
		this.ticketId = ticketId;
		this.noOfSeats = noOfSeats;
		this.seatNumber = seatNumber;
		this.ticketStatus = ticketStatus;
		this.screenName = screenName;
	}

}
