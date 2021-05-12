package com.cg.mts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.mts.entities.Movie;
import com.cg.mts.entities.Show;
import com.cg.mts.exception.ShowExistsException;
import com.cg.mts.exception.ShowNotFoundException;
import com.cg.mts.repository.IShowRepository;
import com.cg.mts.service.IShowService;

@SpringBootTest
public class ShowTest2 {

	@Autowired
	IShowService service;
	
	@MockBean
	IShowRepository repository;
	
	public Show getShow() {
		Movie movie=new Movie("Avengers","Action","120 mins","English","Nice",LocalDate.now());
		Show show = new Show();
		show.setShowStartTime(LocalDateTime.of(2021,03,25, 02,00,00));
		show.setShowEndTime(LocalDateTime.of(2021,03,25, 03,00,00));
		show.setShowName("evening");
		show.setMovie(movie);
		show.setScreenId(2);
		show.setTheatreId(3);
		return show;
	}
	
	public List<Show> getShowList(){
		List<Show> list = new ArrayList<>();
		list.add(getShow());
		return list;
	}
	
	@Test
	void testAddShow() throws ShowExistsException {
		Show showData = getShow();
		Mockito.when(repository.existsByShowId(showData.getShowId())).thenReturn(false);
		Mockito.when(repository.save(showData)).thenReturn(showData);
		Show res = service.addShow(showData);
		assertEquals(res.getShowId(), showData.getShowId());
	}
	
	@Test
	void testUpdateShow() throws ShowNotFoundException {
		Show showData = getShow();
		Mockito.when(repository.existsByShowId(showData.getShowId())).thenReturn(true);
		Mockito.when(repository.save(showData)).thenReturn(showData);
		Show res = service.updateShow(showData);
		assertEquals(res.getShowId(), showData.getShowId());
	}
	
	@Test
	void testRemoveShow() throws ShowNotFoundException {
		Show showData = getShow();
		int id = showData.getShowId();
		Mockito.when(repository.existsByShowId(id)).thenReturn(true);
		service.removeShow(id);
		verify(repository,times(1)).existsByShowId(id);
	}
	
	@Test
	void testViewShowByShowId() throws ShowNotFoundException {
		Show showData = getShow();
		Mockito.when(repository.existsByShowId(showData.getShowId())).thenReturn(true);
		Mockito.when(repository.findByShowId(showData.getShowId())).thenReturn(showData);
		Show res = service.viewShow(showData.getShowId());
		assertEquals(res.getShowId(), showData.getShowId());
	}
	
	@Test
	void testGetAllShow() throws ShowNotFoundException {
		List<Show> list = getShowList();
		Mockito.when(repository.findAll()).thenReturn(list);
		assertEquals(1, service.viewAllShows().size());
	}
	
	/*@Test
	void testViewShowByTheatreId() throws ShowNotFoundException {
		Show showData = getShow();
		List<Show> list = getShowList();
		Mockito.when(repository.findByTheatreId(showData.getTheatreId())).thenReturn(list);
		assertEquals(1,service.viewShowList(showData.getTheatreId()).size());
	}
	
	@Test
	void testViewShowByDate() throws ShowNotFoundException {
		Show showData = getShow();
		List<Show> list = getShowList();
		Mockito.when(repository.findByShowStartTime(showData.getShowStartTime())).thenReturn(list);
		assertEquals(1,service.viewShowList("2021-03-25 02:00:00").size());
	}*/
}
