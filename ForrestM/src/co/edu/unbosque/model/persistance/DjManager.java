package co.edu.unbosque.model.persistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import co.edu.unbosque.model.Song;

public class DjManager {

	private String MainRoute;

	private String FileRoute;

	private FileManager Manager;
	
	public DjManager(String fileRoute) {
		
		this.MainRoute = fileRoute;

		this.Manager = new FileManager(fileRoute);

		this.FileRoute = "/Djs.txt";
		
	}
	
	public void createDj(String Name) {
		
		String entry = UUID.randomUUID() + ";" + Name + ";";

		this.Manager.addFileEntry(entry, this.FileRoute);
		
	}
	
	public String[] getDjs() {
		
		ArrayList<String> Djs = new ArrayList<String>();

		String line = "";

		File f = new File(this.MainRoute + this.FileRoute);

		try {

			FileReader fr = new FileReader(f);

			BufferedReader br = new BufferedReader(fr);

			while (line != null) {

				Song song = new Song();

				line = br.readLine();

				if (line != null) {

					String[] data = line.split(";");

					Djs.add(data[1]);
				}

			}

			fr.close();
		} catch (IOException e) {
			System.out.print(e);
		}
		
		String[] djNames = new String[Djs.size()];
		
		int i = 0;
		
		for(String name: Djs) {
			djNames[i] = name;
		}

		return djNames;
		
	}
	
}
