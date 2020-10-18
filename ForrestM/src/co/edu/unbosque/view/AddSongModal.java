package co.edu.unbosque.view;

import java.awt.*;

import javax.swing.*;

import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.view.components.Input;

public class AddSongModal extends JFrame {

	private static final long serialVersionUID = 1L;

	private Controller controller;

	private Input nameInput;

	private Input artistInput;

	private Input albumInput;

	private Input genreInput;
	
	private String url;

	public AddSongModal(Controller controller) {

		this.controller = controller;

		this.nameInput = new Input();

		this.artistInput = new Input();

		this.albumInput = new Input();

		this.genreInput = new Input();

		this.render();
	}
	
	
	
	public Input getNameInput() {
		return nameInput;
	}



	public Input getArtistInput() {
		return artistInput;
	}



	public Input getAlbumInput() {
		return albumInput;
	}



	public Input getGenreInput() {
		return genreInput;
	}
	
	public String getUrl() {
		return this.url;
	}


	public void setUrl(String Url) {
		this.url = Url;
	}


	private void render() {

		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new GridLayout(9, 1));
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));

		JLabel title = new JLabel("Create Song");

		Font labelFont = title.getFont();

		title.setHorizontalTextPosition(SwingConstants.CENTER);

		title.setFont(new Font(labelFont.getName(), Font.PLAIN, 22));
		
		JButton createButton = new JButton("Create Song");
		
		createButton.addMouseListener(controller);
		
		createButton.setName("createSong");
		
		JButton selectFileButton = new JButton("Seleccionar Archivo");
		
		selectFileButton.setName("findFile");
		
		selectFileButton.addMouseListener(this.controller);

		mainPanel.add(title);

		mainPanel.add(this.nameInput.getInput("Song Name", "songName"));
		
		mainPanel.add(this.artistInput.getInput("Song Artist", "songArtist"));
		
		mainPanel.add(this.albumInput.getInput("Song Album", "songAlbum"));
		
		mainPanel.add(this.genreInput.getInput("Song Genre", "songGenre"));
		
		mainPanel.add(new JPanel());
		
		mainPanel.add(selectFileButton);
		
		mainPanel.add(new JPanel());
		
		mainPanel.add(createButton);

		this.setSize(500, 650);

		this.setResizable(false);

		this.add(mainPanel);

	}

}
