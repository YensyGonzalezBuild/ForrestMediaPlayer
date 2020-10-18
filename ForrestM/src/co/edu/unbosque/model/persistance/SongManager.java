package co.edu.unbosque.model.persistance;

import java.io.*;
import java.util.*;

import co.edu.unbosque.model.Song;

public class SongManager {

	private String MainRoute;

	private String FileRoute;

	private FileManager Manager;

	public SongManager(String fileRoute) {
		this.MainRoute = fileRoute;

		this.Manager = new FileManager(fileRoute);

		this.FileRoute = "/Songs.txt";
	}

	public boolean createSong(Song song) {

		String entry = song.getId() + ";" + song.getName() + ";" + song.getAlbum() + ";" + song.getArtist() + ";"
				+ song.getGenre() + ";"+ song.getUrl() + ";";

		return this.Manager.addFileEntry(entry, this.FileRoute);
	}

	public Song getSongById(UUID id) {
		ArrayList<Song> songs = this.listSongs("any");

		for (Song song : songs) {
			if (song.getId().equals(id)) {
				return song;
			}
		}
		
		return null;
	}

	public boolean deleteSong(UUID id) {

		ArrayList<Song> songs = listSongs("all");

		this.Manager.fileWipe(this.FileRoute);

		songs.forEach((song) -> {
			if (id != song.getId()) {

				String entry = song.getId() + "; " + song.getName() + "; " + song.getAlbum() + "; " + song.getArtist()
						+ "; " + song.getGenre();

				Manager.addFileEntry(entry, this.FileRoute);

			}
		});

		return true;
	}

	public ArrayList<Song> listSongs(String genre) {

		ArrayList<Song> Songs = new ArrayList<Song>();

		String line = "";

		File f = new File(this.MainRoute + "/Songs.txt");

		try {

			FileReader fr = new FileReader(f);

			BufferedReader br = new BufferedReader(fr);

			int i = 0;

			while (line != null) {

				Song song = new Song();

				line = br.readLine();

				if (line != null) {

					String[] data = line.split(";");

					song.setId(UUID.fromString(data[0]));

					song.setName(data[1]);

					song.setAlbum(data[2]);

					song.setArtist(data[3]);

					song.setGenre(data[4]);
					
					song.setUrl(data[5]);

					if (genre == "any") {

						song.setActive(true);

					} else {

						song.setActive(data[4].toLowerCase().equals(genre.toLowerCase()));

					}

					Songs.add(song);

					i++;

				}

			}

			fr.close();
		} catch (IOException e) {
			System.out.print(e);
		}

		return Songs;
	}

}
