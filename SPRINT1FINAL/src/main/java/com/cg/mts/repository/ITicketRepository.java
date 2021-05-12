package com.cg.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entities.Ticket;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Integer>{

}
//done by raviteja
