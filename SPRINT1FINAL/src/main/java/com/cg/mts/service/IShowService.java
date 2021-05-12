package com.cg.mts.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.cg.mts.entities.Show;
import com.cg.mts.exception.ShowExistsException;
import com.cg.mts.exception.ShowNotFoundException;

public interface IShowService {
//unimplemented methods

	public Show addShow(Show show) throws ShowExistsException;
	public Show updateShow(Show show) throws ShowNotFoundException;
	public void removeShow(int showId) throws ShowNotFoundException;
	public Show viewShow(int showid) throws ShowNotFoundException;
	public List<Show> viewAllShows() throws ShowNotFoundException;
	public List<Show> viewShowList(int theatreid)throws ShowNotFoundException;
//This is implemented by Aruna

}
