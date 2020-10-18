package co.edu.unbosque.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.*;
import javax.swing.border.Border;

import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.model.Show;
import co.edu.unbosque.model.Song;
import co.edu.unbosque.model.persistance.SongManager;

public class View extends JFrame {

	// -- Variables

	private static final long serialVersionUID = 1L;

	private Constants constants;

	private Controller controller;

	private UUID selectedListSong;

	private UUID selectedCueSong;

	private JPanel mainPane;

	private JPanel songList;

	private JPanel songCuePane;

	private JLabel Title;

	private JLabel Genre;

	private JLabel Time;

	private SongManager songManager;

	private ArrayList<Song> songCue;

	private Show activeProgram;

	// -- Constructor

	public View(Controller control, Show initialProgram) {

		this.controller = control;

		this.activeProgram = initialProgram;

		this.songCue = new ArrayList<Song>();

		this.songManager = new SongManager("../ForrestM/src/data");

		constants = new Constants();

		this.mainPane = new JPanel();

		this.mainPane.setLayout(new BoxLayout(this.mainPane, BoxLayout.PAGE_AXIS));

		this.render();

	}

	// -- Data managing functions

	public void setSelectedListSong(UUID song) {
		this.selectedListSong = song;
	}

	public UUID getSelectedListSong() {
		return this.selectedListSong;
	}

	public void setSelectedCueSong(UUID song) {
		this.selectedCueSong = song;
	}

	public UUID getSelectedCueSong() {
		return this.selectedCueSong;
	}

	public Show getActiveProgram() {
		return activeProgram;
	}

	public void setActiveProgram(Show activeProgram) {
		this.activeProgram = activeProgram;

		this.Title.setText(activeProgram.getName());

		this.Genre.setText(activeProgram.getGenre());

		this.Time.setText(activeProgram.getStartTime() + " - " + activeProgram.getEndTime());
		
		this.selectedListSong = UUID.randomUUID();
		
		this.refreshSongList();
		
		this.songCue.clear();
		
		this.refreshSongCue();

	}

	public void setSongCue(ArrayList<Song> Songs) {
		this.songCue = Songs;
	}

	public ArrayList<Song> getSongCue() {
		return this.songCue;
	}

	public void cueActions(String Action, Song song) {
		switch (Action) {

		case "AddFront":

			this.songCue.add(song);

			break;

		case "AddBack":

			this.songCue.add(0, song);

			break;

		case "AddRandom":

			int index = (int) Math.ceil(Math.random() * this.songCue.size());

			this.songCue.add(index, song);

			break;

		case "Remove":

			for (Song cueSong : this.songCue) {
				if (cueSong.getId().equals(song.getId())) {

					songCue.remove(cueSong);
					break;
				}
			}

			break;

		case "SetUpFront":

			for (Song cueSong : this.songCue) {
				if (cueSong.getId().equals(song.getId())) {

					songCue.remove(cueSong);

					this.songCue.add(this.songCue.size(), cueSong);

					break;
				}
			}

			break;

		case "MoveUp":

			for (Song cueSong : this.songCue) {
				if (cueSong.getId().equals(song.getId())) {

					int position = this.songCue.indexOf(cueSong);

					if (position < this.songCue.size() - 1) {

						songCue.remove(cueSong);

						this.songCue.add(position + 1, cueSong);

					}

					break;
				}
			}

			break;

		case "MoveBack":

			for (Song cueSong : this.songCue) {
				if (cueSong.getId().equals(song.getId())) {

					int position = this.songCue.indexOf(cueSong);

					if (position > 0) {

						songCue.remove(cueSong);

						this.songCue.add(position - 1, cueSong);

					}

					break;
				}
			}

			break;

		}
	}

	// -- rendering functions

	public void refreshSongList() {
		this.songList.removeAll();

		this.songList.add(this.renderSongList());

		this.songList.repaint();

		this.songList.revalidate();
	}

	public void refreshSongCue() {
		this.songCuePane.removeAll();

		this.songCuePane.add(this.renderSongCue());

		this.songCuePane.repaint();

		this.songCuePane.revalidate();
	}

	private void render() {

		// -- top bar

		JPanel topPanel = this.getProgramInfo();

		JPanel topMargin = new JPanel();

		topMargin.setMaximumSize(new Dimension(1100, 10));

		JPanel bottomMargin = new JPanel();

		bottomMargin.setMaximumSize(new Dimension(1100, 10));

		// -- Song list panel

		JPanel songListPanel = this.getSongList();

		// -- Song cue panel

		JPanel songCuePanel = this.getSongCuePanel();

		// -- Center Panel

		JPanel centerPane = new JPanel();

		centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.LINE_AXIS));

		JPanel centerSepparator = new JPanel();

		centerSepparator.setMaximumSize(new Dimension(10, 10));

		songListPanel = this.getSongList();

		centerPane.add(songListPanel);

		centerPane.add(centerSepparator);

		centerPane.add(songCuePanel);

		// -- Main Frame config

		mainPane.add(topMargin);

		mainPane.add(topPanel);

		mainPane.add(bottomMargin);

		mainPane.add(centerPane);

		this.setSize(1100, 735);

		this.setResizable(false);

		this.add(mainPane);

	}

	private JPanel getProgramInfo() {

		JPanel panel = new JPanel();

		panel.setMaximumSize(new Dimension(1065, 65));

		panel.setBackground(constants.getLigthGray());

		panel.setBorder(constants.getMainBorder());

		panel.setLayout(new GridLayout(1, 6));

		this.Title = new JLabel(this.activeProgram.getName());

		this.Genre = new JLabel(this.activeProgram.getGenre());

		this.Time = new JLabel(this.activeProgram.getStartTime() + " - " + this.activeProgram.getEndTime());

		JLabel Type = new JLabel("Streaming");

		JButton Program = new JButton("Program Select");
		
		Program.setName("openProgramSelector");
		
		Program.addMouseListener(controller);

		JButton Config = new JButton("Preferences");

		Config.setMaximumSize(new Dimension(65, 65));

		panel.add(Title);

		panel.add(Genre);

		panel.add(Time);

		panel.add(Type);

		panel.add(Program);

		panel.add(Config);

		return panel;

	}

	private JPanel getSongList() {

		// -- main Panel

		JPanel panel = new JPanel();

		panel.setMaximumSize(new Dimension(600, 615));

		panel.setMinimumSize(new Dimension(600, 615));

		panel.setBackground(this.constants.getLigthGray());

		panel.setBorder(this.constants.getMainBorder());

		panel.setLayout(new BorderLayout());

		// -- song actions

		JPanel mainActions = new JPanel();

		mainActions.setLayout(new GridLayout(1, 2));

		mainActions.setPreferredSize(new Dimension(600, 50));

		mainActions.setMaximumSize(new Dimension(600, 50));

		mainActions.setMinimumSize(new Dimension(600, 50));

		JButton addButton = new JButton("Create Song");

		addButton.setName("openCreateSongModal");

		addButton.addMouseListener(this.controller);

		JButton removeButton = new JButton("Delete Song");

		addButton.setBackground(this.constants.getMain());

		mainActions.add(addButton);

		mainActions.add(removeButton);

		// -- cue actions

		JPanel cueActions = new JPanel();

		cueActions.setLayout(new GridLayout(1, 4));

		cueActions.setPreferredSize(new Dimension(600, 50));

		cueActions.setMaximumSize(new Dimension(600, 50));

		cueActions.setMinimumSize(new Dimension(600, 50));

		JButton toEndButton = new JButton("At The End");

		toEndButton.setName("addSongFromListToCueEnd");

		toEndButton.addMouseListener(this.controller);

		JButton upNextButton = new JButton("Up Next");

		upNextButton.setName("addSongFromListToCueFront");

		upNextButton.addMouseListener(this.controller);

		JButton shuffleButton = new JButton("Shuffle");

		shuffleButton.setName("addSongFromListToCueRandom");

		shuffleButton.addMouseListener(this.controller);

		cueActions.add(toEndButton);

		cueActions.add(upNextButton);

		cueActions.add(shuffleButton);

		// -- Song List

		this.songList = new JPanel();

		this.songList.add(this.renderSongList());

		// -- Final config

		panel.add(mainActions, BorderLayout.NORTH);

		panel.add(songList, BorderLayout.CENTER);

		panel.add(cueActions, BorderLayout.SOUTH);

		return panel;

	}

	private JScrollPane renderSongList() {

		JPanel listContainer = new JPanel();

		listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.PAGE_AXIS));

		ArrayList<Song> Songs = this.songManager.listSongs(this.activeProgram.getGenre());

		Songs.forEach((Song) -> {
			listContainer.add(SongCard(Song));
		});

		JScrollPane songScrollPana = new JScrollPane(listContainer);
		songScrollPana.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		songScrollPana.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		return songScrollPana;

	}

	private JPanel getSongCuePanel() {

		JPanel panel = new JPanel();

		panel.setMaximumSize(new Dimension(450, 615));

		panel.setMinimumSize(new Dimension(450, 615));

		panel.setBackground(this.constants.getLigthGray());

		panel.setBorder(this.constants.getMainBorder());

		panel.setLayout(new BorderLayout());

		// -- cue actions

		JPanel cueActions = new JPanel();

		cueActions.setLayout(new GridLayout(1, 4));

		cueActions.setPreferredSize(new Dimension(600, 50));

		JButton removeButton = new JButton("Remove");

		removeButton.setName("removeSongFromCue");

		removeButton.addMouseListener(this.controller);

		JButton backButton = new JButton("Back");

		backButton.setName("moveBackCueSong");

		backButton.addMouseListener(this.controller);

		JButton nextButton = new JButton("Next");

		nextButton.setName("moveUpCueSong");

		nextButton.addMouseListener(this.controller);

		JButton upNextButton = new JButton("Up Next");

		upNextButton.setName("setCueSongUpNext");

		upNextButton.addMouseListener(this.controller);

		cueActions.add(removeButton);

		cueActions.add(backButton);

		cueActions.add(nextButton);

		cueActions.add(upNextButton);

		// -- cue actions

		JPanel mainActions = new JPanel();

		mainActions.setLayout(new GridLayout(1, 3));

		mainActions.setPreferredSize(new Dimension(600, 50));

		JButton playButton = new JButton("Play");

		playButton.setName("playSongCue");

		playButton.addMouseListener(this.controller);

		JButton stopButton = new JButton("Stop");

		stopButton.setName("stopSongCue");

		stopButton.addMouseListener(this.controller);

		JButton skipButton = new JButton("Skip");

		skipButton.setName("skipSongInCue");

		skipButton.addMouseListener(this.controller);

		// -- Song List

		this.songCuePane = new JPanel();

		this.songCuePane.add(this.renderSongCue());

		mainActions.add(playButton);

		mainActions.add(stopButton);

		mainActions.add(skipButton);

		panel.add(cueActions, BorderLayout.NORTH);

		panel.add(this.songCuePane, BorderLayout.CENTER);

		panel.add(mainActions, BorderLayout.SOUTH);

		return panel;

	}

	private JScrollPane renderSongCue() {
		JPanel listContainer = new JPanel();

		listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.PAGE_AXIS));

		this.songCue.forEach((Song) -> {
			listContainer.add(this.CueCard(Song));
		});

		JScrollPane songCue = new JScrollPane(listContainer);
		songCue.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		songCue.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		return songCue;
	}

	private JPanel SongCard(Song song) {

		Color background;

		Color fontColor;

		JPanel card = new JPanel();

		card.setPreferredSize(new Dimension(600, 85));

		card.setMaximumSize(new Dimension(600, 85));

		card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		card.setLayout(new GridLayout(2, 1));

		JLabel Title = new JLabel(song.getName());

		Title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

		JPanel description = new JPanel();

		description.setLayout(new GridLayout(1, 4));

		JLabel Artist = new JLabel(song.getArtist());

		JLabel Album = new JLabel(song.getAlbum());

		JLabel Genre = new JLabel(song.getGenre());

		JLabel Time = new JLabel("00:00");

		Artist.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

		if (song.getId().equals(this.selectedListSong)) {

			background = this.constants.getMain();

			fontColor = Color.WHITE;

			card.setBackground(background);

			Title.setForeground(fontColor);

			description.setBackground(background);

			Artist.setForeground(fontColor);

			Album.setForeground(fontColor);

			Genre.setForeground(fontColor);

			Time.setForeground(fontColor);

		} else {

			if (song.isActive()) {

				background = Color.WHITE;

				fontColor = Color.BLACK;

				card.setBackground(background);

				Title.setForeground(fontColor);

				description.setBackground(background);

				Artist.setForeground(fontColor);

				Album.setForeground(fontColor);

				Genre.setForeground(fontColor);

				Time.setForeground(fontColor);

				card.addMouseListener(controller);

			} else {

				background = this.constants.getGray();

				fontColor = this.constants.getDarkGray();

				card.setBackground(background);

				Title.setForeground(fontColor);

				description.setBackground(background);

				Artist.setForeground(fontColor);

				Album.setForeground(fontColor);

				Genre.setForeground(fontColor);

				Time.setForeground(fontColor);

			}

		}

		description.add(Artist);

		description.add(Album);

		description.add(Genre);

		description.add(Time);

		// -----------------------------------------------------------------

		card.setName("List:" + song.getId().toString());

		card.add(Title);

		card.add(description);

		return card;

	}

	private JPanel CueCard(Song song) {

		Color background;

		Color fontColor;

		JPanel card = new JPanel();

		card.setPreferredSize(new Dimension(450, 50));

		card.setMaximumSize(new Dimension(450, 50));

		card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		card.setLayout(new GridLayout(1, 2));

		JLabel Title = new JLabel(song.getName());

		Title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

		JLabel Time = new JLabel("00:00");

		if (song.getId().equals(this.selectedCueSong)) {

			background = this.constants.getMain();

			fontColor = Color.WHITE;

			card.setBackground(background);

			Title.setForeground(fontColor);

			Time.setForeground(fontColor);

		} else {

			if (song.isActive()) {

				background = Color.WHITE;

				fontColor = Color.BLACK;

				card.setBackground(background);

				Title.setForeground(fontColor);

				Time.setForeground(fontColor);

			} else {

				background = this.constants.getGray();

				fontColor = this.constants.getDarkGray();

				card.setBackground(background);

				Title.setForeground(fontColor);

				Time.setForeground(fontColor);

			}

		}

		// -----------------------------------------------------------------

		card.setName("Cue:" + song.getId().toString());

		card.addMouseListener(controller);

		card.add(Title);

		card.add(Time);

		return card;

	}

}
