package com.cg.mts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.entities.Ticket;
import com.cg.mts.repository.ITicketRepository;

@Service
@Transactional
public class TicketService implements ITicketService{
	
	@Autowired
	ITicketRepository repository;
	
	Logger logger = LoggerFactory.getLogger(TicketService.class);
	
	@Override
	public Ticket addTicket(Ticket ticket) {
		logger.info("Inside addTicket method");
		Ticket ticketData = repository.save(ticket);
		return ticketData;
	}

}
