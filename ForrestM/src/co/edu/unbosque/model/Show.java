package co.edu.unbosque.model;

import java.util.*;

public class Show {
	
	private UUID id;
	
	private String Name;
	
	private String Genre;
	
	private String StartTime; 
	
	private String EndTime;
	
	
	public Show () {
		
	}

	public Show(UUID id, String name, String genre, String startTime, String endTime) {
		this.id = id;
		this.Name = name;
		this.Genre = genre;
		this.StartTime = startTime;
		this.EndTime = endTime;
	}

	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return this.Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getGenre() {
		return this.Genre;
	}

	public void setGenre(String genre) {
		this.Genre = genre;
	}

	public String getStartTime() {
		return this.StartTime;
	}

	public void setStartTime(String startTime) {
		this.StartTime = startTime;
	}

	public String getEndTime() {
		return this.EndTime;
	}

	public void setEndTime(String endTime) {
		this.EndTime = endTime;
	}
	
	
	

}
