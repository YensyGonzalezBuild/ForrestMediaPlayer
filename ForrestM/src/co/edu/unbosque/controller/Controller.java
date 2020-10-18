package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JFileChooser;

import co.edu.unbosque.model.Song;
import co.edu.unbosque.model.persistance.SongManager;
import co.edu.unbosque.view.AddSongModal;
import co.edu.unbosque.view.View;

public class Controller implements ActionListener, MouseListener {

	private View mainUI;

	private AddSongModal songModal;
	
	private SongManager songManager;
	
	private boolean play;
	
	private boolean stop;
	
	javazoom.jl.player.Player player; 
	
	private File cancion;
	
	public Controller() {

		mainUI = new View(this);
		
		mainUI.setVisible(true);
		
		this.songManager = new SongManager("../ForrestM/src/data");

		this.songModal = new AddSongModal(this);

	}

	// METODO PARA REPRODUCIR LA CANCION ACTUAL Y REGRESAR A MOSTRAR LAS LISTAS

	public void playSong(Song song) {
		System.out.print(song);
		
	    if(!play){
	        try{
	            cancion = new File(song.getUrl());
	            FileInputStream fis = new FileInputStream(cancion);
	            BufferedInputStream bis = new BufferedInputStream(fis);
	            player = new javazoom.jl.player.Player(bis);
	            play = true;
	        }catch(Exception e){
	            System.out.println("Problema reproduciendo la canción! :(  Valida la ruta del archivo mp3 o que el archivo mp3 no este corrupto ");
	            stop = true;
	           
	        }
	        
	        new Thread(){
	        	@Override
	            public void run(){
	                try{
	                    player.play();
	                    System.out.println("Cancion finalizada (hilo) :) ");
	                    
//	                    }
	                }catch (Exception e){}
	            }
		    }.start();
	    }else{
	        try {
				player.close();
				play = false;
				
			} catch (Exception e) { }
	    }
	}

	 //METODO PARA DETENER LA MUSICA
	
	public void stopSong() {
		try {
			play = false;

			player.close();
		} catch (Exception e1) {System.out.println(e1);}   //TERMINA LA REPRODUCCION

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		System.out.println(e.getSource());

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub


//		this.mainUI.setSelectedListSong(UUID.fromString(e.getComponent().getName()));
//		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		Song song = new Song();
		
		String ActionName = e.getComponent().getName();
		
		switch (ActionName) {
		
		case "openCreateSongModal":
			
			this.songModal.setVisible(true);
			
			break;

		case "createSong":

			System.out.println("Starting to create song");

			song = new Song(UUID.randomUUID(), this.songModal.getNameInput().getInputContent(),
					this.songModal.getArtistInput().getInputContent(), this.songModal.getAlbumInput().getInputContent(),
					this.songModal.getGenreInput().getInputContent(), this.songModal.getUrl(), false);
			
			this.songManager.createSong(song);
			
			this.songModal.setVisible(false);
			
			this.mainUI.refreshSongList();

			break;
			
		case "addSongFromListToCueFront":
			
			song = this.songManager.getSongById(this.mainUI.getSelectedListSong());
			
			this.mainUI.cueActions("AddFront", song);
			
			this.mainUI.refreshSongCue();
			
			break;
			
		case "addSongFromListToCueEnd":
			
			song = this.songManager.getSongById(this.mainUI.getSelectedListSong());
			
			this.mainUI.cueActions("AddBack", song);
			
			this.mainUI.refreshSongCue();
			
			break;
			
		case "addSongFromListToCueRandom":
			
			song = this.songManager.getSongById(this.mainUI.getSelectedListSong());
			
			this.mainUI.cueActions("AddRandom", song);
			
			this.mainUI.refreshSongCue();
			
			break;
			
		case "removeSongFromCue":
			
			song = this.songManager.getSongById(this.mainUI.getSelectedCueSong());
			
			this.mainUI.cueActions("Remove", song);
			
			this.mainUI.refreshSongCue();
			
			break;
			
		case "setCueSongUpNext":
			
			song = this.songManager.getSongById(this.mainUI.getSelectedCueSong());
			
			this.mainUI.cueActions("SetUpFront", song);
			
			this.mainUI.refreshSongCue();
			
			break;
			
		case "moveUpCueSong":
			
			song = this.songManager.getSongById(this.mainUI.getSelectedCueSong());
			
			this.mainUI.cueActions("MoveUp", song);
			
			this.mainUI.refreshSongCue();
			
			break;
			
		case "moveBackCueSong":
			
			song = this.songManager.getSongById(this.mainUI.getSelectedCueSong());
			
			this.mainUI.cueActions("MoveBack", song);
			
			this.mainUI.refreshSongCue();
			
			break;
			
		case "findFile":
			
			JFileChooser fc = new JFileChooser();
			
			 fc.showOpenDialog(this.songModal);
			
			File file = fc.getSelectedFile();
			
			this.songModal.setUrl(file.getPath());
			
			break;
			
		case "playSongCue":
			
			ArrayList<Song> Cue = this.mainUI.getSongCue();
			
			Song active = Cue.get(Cue.size()-1);
			
			this.playSong(active);
			
			Cue.remove(active);
			
			this.mainUI.setSongCue(Cue);
			
			this.mainUI.refreshSongCue();
			
			break;
			
		case "skipSongInCue":
			
			this.stopSong();
			
			ArrayList<Song> UpdatedCue = this.mainUI.getSongCue();
			
			Song next = UpdatedCue.get(UpdatedCue.size()-1);
			
			this.playSong(next);
			
			UpdatedCue.remove(next);
			
			this.mainUI.setSongCue(UpdatedCue);
			
			this.mainUI.refreshSongCue();
			
			break;
			
		case "stopSongCue":
			
			this.stopSong();
			
			break;

		default:
			
			String[] data = ActionName.split(":");
			
			if(data[0].equals("List")) {
				
				this.mainUI.setSelectedListSong(UUID.fromString(data[1]));
				
				this.mainUI.refreshSongList();
				
			}else{
				
				this.mainUI.setSelectedCueSong(UUID.fromString(data[1]));
				
				this.mainUI.refreshSongCue();
				
			}
			
			break;

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	
}
