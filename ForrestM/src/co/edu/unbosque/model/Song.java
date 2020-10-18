package co.edu.unbosque.model;

import java.util.*;

public class Song{
	
	private UUID id;
	
	private String Name;
	
	private String Album;
	
	private String Artist; 
	
	private String Genre;
	
	private String Url;
	
	private boolean isActive;
	
	public Song() {
		
	}
	
	
	public Song(UUID id, String name, String album, String artist, String genre, String url, boolean isActive) {
		super();
		this.id = id;
		Name = name;
		Album = album;
		Artist = artist;
		Genre = genre;
		Url = url;
		this.isActive = isActive;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}



	public UUID getId() {
		return id;
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

	public String getAlbum() {
		return this.Album;
	}

	public void setAlbum(String album) {
		this.Album = album;
	}

	public String getArtist() {
		return this.Artist;
	}

	public void setArtist(String artist) {
		this.Artist = artist;
	}

	public String getGenre() {
		return this.Genre;
	}

	public void setGenre(String genre) {
		this.Genre = genre;
	}

	public boolean isActive() {
		return this.isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	

}
