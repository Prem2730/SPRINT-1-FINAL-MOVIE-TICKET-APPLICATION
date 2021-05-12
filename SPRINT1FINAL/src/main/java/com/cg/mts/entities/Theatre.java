package com.cg.mts.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "theatre_master")
public class Theatre {
	
	@Id
	@GeneratedValue
	private int theatreId;
	

	private String theatreName;
	

	private String theatreCity;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "movie_theatre_master")
	private List<Movie> listOfMovies;
	
	@OneToMany

	@JoinTable(name = "screen_theatre_master")
	private List<Screen> listOfScreens;
	private String managerName;
	private String managerContact;
	
	
	
	public int getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public String getTheatreCity() {
		return theatreCity;
	}
	public void setTheatreCity(String theatreCity) {
		this.theatreCity = theatreCity;
	}
	public List<Movie> getListOfMovies() {
		return listOfMovies;
	}
	public void setListOfMovies(List<Movie> listOfMovies) {
		this.listOfMovies = listOfMovies;
	}

	
	public List<Screen> getListOfScreens() {
		return listOfScreens;
		} 
	
	public void setListOfScreens(List<Screen> listOfScreens){
		this.listOfScreens = listOfScreens;
	}
	 
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerContact() {
		return managerContact;
	}
	public void setManagerContact(String managerContact) {
		this.managerContact = managerContact;
	}
	
	
	public Theatre() {
		super();
	}
	public Theatre( String theatreName,  String theatreCity, List<Movie> listOfMovies,
			List<Screen> listOfScreens, String managerName, String managerContact) {
		super();
		this.theatreName = theatreName;
		this.theatreCity = theatreCity;
		this.listOfMovies = listOfMovies;
		this.listOfScreens = listOfScreens;
		this.managerName = managerName;
		this.managerContact = managerContact;
	}
	public Theatre(int theatreId, String theatreName,String theatreCity, List<Movie> listOfMovies,
			List<Screen> listOfScreens, String managerName, String managerContact) {
		super();
		this.theatreId = theatreId;
		this.theatreName = theatreName;
		this.theatreCity = theatreCity;
		this.listOfMovies = listOfMovies;
		this.listOfScreens = listOfScreens;
		this.managerName = managerName;
		this.managerContact = managerContact;
	}
	
	
	@Override
	public String toString() {
		return "Theatre [theatreId=" + theatreId + ", theatreName=" + theatreName + ", theatreCity=" + theatreCity
				+ ", listOfMovies=" + listOfMovies+ ", listOfScreens=" + listOfScreens + ", managerName="  + managerName
				+ ", managerContact=" + managerContact + "]";
	}
	
}
