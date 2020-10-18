package co.edu.unbosque.model.persistance;

import java.io.*;

public class FileManager {
	
	private String FileRoute;
	
	public FileManager(String fileRoute) {
		this.FileRoute = fileRoute;
	}
	
	public void fileWipe(String fileName) {
		
		File f = new File(this.FileRoute+fileName);
		
		f.delete();
		
	}
	
	public boolean addFileEntry(String entry, String fileName) {
		File f = new File(this.FileRoute+fileName);
		
		try {
			
			if(!f.exists()) {
				
				f.createNewFile();
				
			}
			
			FileWriter fw = new FileWriter(f, true);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(entry);
			
			fw.close();
			
		} catch (IOException e) {
			
			System.out.println(e);
			
			return false;
		
		} 
		
		return true;

	}

}
