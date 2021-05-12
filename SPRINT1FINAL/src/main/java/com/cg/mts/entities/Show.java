package com.cg.mts.entities;
//these are the packages

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "show_master")
public class Show {
	@Id
	@GeneratedValue
	private int showId;
	private LocalDateTime showStartTime;
	private LocalDateTime showEndTime;
	private String showName;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="movie_Id")
	private Movie movie;
	private int screenId;
	private int theatreId;
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public LocalDateTime getShowStartTime() {
		return showStartTime;
	}
	public void setShowStartTime(LocalDateTime showStartTime) {
		this.showStartTime = showStartTime;
	}
	public LocalDateTime getShowEndTime() {
		return showEndTime;
	}
	public void setShowEndTime(LocalDateTime showEndTime) {
		this.showEndTime = showEndTime;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public int getScreenId() {
		return screenId;
	}
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}
	public int getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}public Show() {
		// TODO Auto-generated constructor stub
		super();
	}
	public Show(LocalDateTime showStartTime, LocalDateTime showEndTime, String showName, Movie movie, int screenId,
			int theatreId) {
		super();
		this.showStartTime = showStartTime;
		this.showEndTime = showEndTime;
		this.showName = showName;
		this.movie = movie;
		this.screenId = screenId;
		this.theatreId = theatreId;
	}
	public Show(int showId, LocalDateTime showStartTime, LocalDateTime showEndTime, String showName, Movie movie, int screenId,
			int theatreId) {
		super();
		this.showId = showId;
		this.showStartTime = showStartTime;
		this.showEndTime = showEndTime;
		this.showName = showName;
		this.movie = movie;
		this.screenId = screenId;
		this.theatreId = theatreId;
	}
}
//This entity was created by Aruna
