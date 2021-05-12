package com.cg.mts.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.entities.Show;
import com.cg.mts.exception.ShowExistsException;
import com.cg.mts.exception.ShowNotFoundException;
import com.cg.mts.repository.IShowRepository;

@Service
@Transactional
public class ShowService implements IShowService {
	
	@Autowired
	IShowRepository repository;
	
	Logger logger = LoggerFactory.getLogger(ShowService.class);
	//This is for adding new show
	@Override
	public Show addShow(Show show) throws ShowExistsException{
		logger.info("Inside addShow method");
		if(repository.existsByShowId(show.getShowId()))
			throw new ShowExistsException("Cannot add show, Show Id already exists");
		else {
			Show showData = repository.save(show);
			return showData;
		}	
	}

	@Override
	public Show updateShow(Show show) throws ShowNotFoundException {
		logger.info("Inside updateShow method");
		if(repository.existsByShowId(show.getShowId())) {
			Show showData = repository.save(show);
			return showData;
		}
		else
			throw new ShowNotFoundException("Show not found");
		
	}

	@Override
	public void removeShow(int showId) throws ShowNotFoundException {
		logger.info("Inside removeShow method");
		if(repository.existsByShowId(showId)) {
			repository.deleteByShowId(showId);
		}
		else
			throw new ShowNotFoundException("Invalid showid, cannot delete show");
		
	}

	@Override
	public Show viewShow(int showid) throws ShowNotFoundException {
		logger.info("Inside viewShow method");
		if(repository.existsByShowId(showid)) {
			return repository.findByShowId(showid);
		}
		else {
			throw new ShowNotFoundException("Invalid Show Id, no shows found");
		}
	}

	@Override
	public List<Show> viewAllShows() throws ShowNotFoundException {
		logger.info("Inside viewAllShows method");
		List<Show> showList = repository.findAll();
		if(showList.isEmpty()) {
			throw new ShowNotFoundException("No Show in Database");
		}
		else
			return showList;
		
	}

	@Override
	public List<Show> viewShowList(int theatreid) throws ShowNotFoundException {
		// TODO Auto-generated method stub
		logger.info("Inside findByTheatreId method");
		List<Show> showData =  repository.findByTheatreId(theatreid);
		if(showData.isEmpty()) {
			logger.error("ShowNotFoundException in getShowByTheatreId method");	
			throw new ShowNotFoundException("No show found");
		}
		else
			return showData;
	}

	

}
