package co.edu.unbosque.model.persistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import co.edu.unbosque.model.Show;
import co.edu.unbosque.model.Song;

public class ShowManager {

	private String MainRoute;

	private String FileRoute;

	private FileManager Manager;

	public ShowManager(String fileRoute) {
		this.MainRoute = fileRoute;

		this.Manager = new FileManager(fileRoute);

		this.FileRoute = "/Shows.txt";
	}

	public boolean createShow(String[] values) {
		
		String entry = UUID.randomUUID() + ";" + values[0]+ ";" + values[1] + ";" + values[2] + ";"
				+ values[3] + ";"+ values[4] + ";";

		return this.Manager.addFileEntry(entry, this.FileRoute);
	}
	
	public Show getShowById(UUID id) {
		ArrayList<Show> shows = this.listShows();

		for (Show show : shows) {
			if (show.getId().equals(id)) {
				return show;
			}
		}
		
		return null;
	}

	public boolean deleteShow() {

		return true;
	}

	public ArrayList<Show> listShows() {

		ArrayList<Show> Shows = new ArrayList<Show>();

		String line = "";

		File f = new File(this.MainRoute + this.FileRoute);

		try {

			FileReader fr = new FileReader(f);

			BufferedReader br = new BufferedReader(fr);

			int i = 0;

			while (line != null) {

				line = br.readLine();

				if (line != null) {

					String[] data = line.split(";");

					Shows.add(new Show(UUID.fromString(data[0]), data[1], data[2], data[3], data[4], data[5]));

					i++;

				}

			}

			fr.close();
		} catch (IOException e) {
			System.out.print(e);
		}

		return Shows;
	}

}
