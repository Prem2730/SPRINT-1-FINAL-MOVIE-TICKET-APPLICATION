package com.cg.mts;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.mts.entities.*;
import com.cg.mts.exception.MovieNotFoundException;
import com.cg.mts.repository.IMovieRepository;
import com.cg.mts.repository.ITheatreRepository;
import com.cg.mts.service.IMovieService;


	@SpringBootTest
	class IMovieServiceTest {
	
	@Autowired
	IMovieService movieService;
	
	@MockBean
	private IMovieRepository movieRepository;
	
	@MockBean
	private ITheatreRepository theatreRepository;
	
	
		
	static LocalDate date = LocalDate.now();
	
	public Movie getMovie() {
		Movie testMovie = new Movie(1,"TESTMOVIE", "TEST GENRE", "02 hours 05 mins", "ENGLISH", "TEST DESCRIPTION", date);
		return testMovie;
	}
	/*public Theatre getTheatre() {
		List<Movie> movieList = getMovieList();
		List<Screen> screenList = getScreenList();
		Theatre testTheatre = new Theatre(1, "PVR", "Anand", movieList, screenList, "Rakesh", "4546546544");
		return testTheatre;
	}*/
	
	public List<Movie> getMovieList(){
		List<Movie> testMovieList = new ArrayList<Movie>();
		testMovieList.add(new Movie(1, "TESTMOVIE_1", "TEST GENRE_1", "02 hours 05 mins", "ENGLISH", "TEST DESCRIPTION_1", date));
		testMovieList.add(new Movie(2, "TESTMOVIE_2", "TEST GENRE_2", "02 hours 05 mins", "ENGLISH", "TEST DESCRIPTION_2",date));
		return testMovieList;
	}
	
	/*public List<Screen> getScreenList(){
		List<Screen> testScreenList = new ArrayList<Screen>();
		List<Show> showList = getShowList();
		testScreenList.add(new Screen(1, "TESTSCREEN_1", showList , 1, 1));
		testScreenList.add(new Screen(2, "TESTSCREEN_2", showList, 1, 2));
		return testScreenList;
	}
	
	public List<Show> getShowList(){
		Movie movie = getMovie();
		List<Show> testShowList = new ArrayList<Show>();
		testShowList.add(new Show(1, null, null, "TEST SHOW", movie,1,2));
		testShowList.add(new Show(2, null, null, "TEST SHOW", movie,1,2));
		return testShowList;
		
	}*/
		
	@Test  
	void testAddMovie() throws MovieNotFoundException {
	
		Movie testAddMovie = getMovie();	
		Mockito.when(movieRepository.existsById(testAddMovie.getMovieId())).thenReturn(false);
		Mockito.when(movieRepository.save(testAddMovie)).thenReturn(testAddMovie);
		Movie result = movieService.addMovie(testAddMovie);
		assertEquals(result.getMovieId(), testAddMovie.getMovieId());
	
	}

	
	@Test 
	void testRemoveMovie() throws MovieNotFoundException {
	
		Movie deleteMovie = getMovie(); 
		int movieId = deleteMovie.getMovieId(); 
		Mockito.when(movieRepository.existsById(movieId)).thenReturn(true);
		movieService.removeMovie(deleteMovie.getMovieId());
		verify(movieRepository, times(1)).existsById(movieId); 
	  }
	 
	
	
	
	  @Test  
	  void testUpdateMovie() throws MovieNotFoundException { 
		
		 Movie testUpdateMovie = getMovie();	
		 
		 Mockito.when(movieRepository.existsById(testUpdateMovie.getMovieId())).thenReturn(true);
		 Mockito.when(movieRepository.save(testUpdateMovie)).thenReturn(testUpdateMovie);
		 Movie result = movieService.updateMovie(testUpdateMovie);
		 assertEquals(result.getMovieId(), testUpdateMovie.getMovieId());
	 }
	 

	  @Test  
	  void testViewMovie() throws MovieNotFoundException {
		
		Movie viewMovie = getMovie();
		int movieId = viewMovie.getMovieId();
		
		Mockito.when(movieRepository.findByMovieId(movieId)).thenReturn(viewMovie); 
		Movie result = movieService.viewMovie(movieId);
		assertEquals(result, viewMovie);
	}

	  @Test  
	  void testViewMovieList() throws MovieNotFoundException {
		
		List<Movie> movieList = getMovieList();
		
		Mockito.when(movieRepository.findAll()).thenReturn(movieList);
		assertEquals(2, movieService.viewMovieList().size());
	}

	
	/*  @Test 
	  void testViewMovieListByTheatreId() throws MovieNotFoundException { 
	   
	   Theatre theatre = getTheatre();
       Mockito.when(theatreRepository.existsById(theatre.getTheatreId())).thenReturn(true); 
	   Mockito.when(theatreRepository.getOne(theatre.getTheatreId())).thenReturn(theatre);
	   assertEquals(2, movieService.viewMovieList(theatre.getTheatreId()).size()); 
	   }
	 
	  
	  @Test 
	  void testViewMovieListLocalDate() throws MovieNotFoundException {
		
		List<Movie> movieList = getMovieList();
		
		Mockito.when(movieRepository.findByMovieReleaseDate(date)).thenReturn(movieList);
		assertEquals(2, movieService.viewMovieList(date).size());
	}*/

}
