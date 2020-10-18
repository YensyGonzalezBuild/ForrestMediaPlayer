package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.*;

import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.model.Show;
import co.edu.unbosque.model.Song;
import co.edu.unbosque.model.persistance.ShowManager;

public class ShowProgramsModal extends JFrame {

	private static final long serialVersionUID = 1L;

	private Controller controller;

	private Constants constants;

	private ShowManager showManager;

	private UUID SelectedProgram;

	private JPanel mainPane;

	private JPanel programsListPanel;

	public ShowProgramsModal(Controller control) {

		this.controller = control;

		this.constants = new Constants();

		this.showManager = new ShowManager("../ForrestM/src/data");

		this.render();

	}

	public UUID getSelectedProgram() {
		return SelectedProgram;
	}

	public void setSelectedProgram(UUID selectedProgram) {
		SelectedProgram = selectedProgram;
	}
	
	public void refreshProgramList() {
		this.programsListPanel.removeAll();

		this.programsListPanel.add(this.renderProgramsList());

		this.programsListPanel.repaint();

		this.programsListPanel.revalidate();
	}

	private void render() {

		this.mainPane = new JPanel();

		this.mainPane.setLayout(new BoxLayout(this.mainPane, BoxLayout.PAGE_AXIS));

		this.mainPane.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

		this.mainPane.setBackground(Color.WHITE);

		// -- top bar

		JLabel Title = new JLabel("Select Program to start");

		Font labelFont = Title.getFont();

		Title.setFont(new Font(labelFont.getName(), Font.PLAIN, 22));

		Title.setHorizontalTextPosition(SwingConstants.CENTER);

		Title.setPreferredSize(new Dimension(650, 60));

		JPanel topMargin = new JPanel();

		topMargin.setBackground(Color.WHITE);

		topMargin.setPreferredSize(new Dimension(650, 10));

		JPanel bottomMargin = new JPanel();

		bottomMargin.setBackground(Color.WHITE);

		bottomMargin.setPreferredSize(new Dimension(650, 10));

		// -- Programs list panel

		this.programsListPanel = new JPanel();

		this.programsListPanel.setPreferredSize(new Dimension(650, 600));

		this.programsListPanel.setBackground(this.constants.getLigthGray());

		this.programsListPanel.setBorder(this.constants.getMainBorder());

		this.programsListPanel.add(this.renderProgramsList());

		// -- Button Bar

		JPanel buttonBar = new JPanel();

		buttonBar.setBackground(Color.WHITE);

		buttonBar.setPreferredSize(new Dimension(650, 50));

		buttonBar.setLayout(new GridLayout(1, 2));

		JButton addNewButton = new JButton("Create New Program");
		
		addNewButton.setName("openCreateProgram");
		
		addNewButton.addMouseListener(this.controller);

		JButton selectButton = new JButton("Select Program");
		
		selectButton.setName("selectInitialProgram");
		
		selectButton.addMouseListener(this.controller);

		buttonBar.add(addNewButton);

		buttonBar.add(selectButton);

		// -- Main Frame config

		mainPane.add(Title);

		mainPane.add(topMargin);

		mainPane.add(programsListPanel);

		mainPane.add(bottomMargin);

		mainPane.add(buttonBar);

		this.setSize(650, 700);

		this.setResizable(false);

		this.add(mainPane);

	}

	private JScrollPane renderProgramsList() {

		JPanel listContainer = new JPanel();

		listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.PAGE_AXIS));

		ArrayList<Show> Shows = this.showManager.listShows();

		Shows.forEach((Show) -> {
			listContainer.add(ProgramCard(Show));
		});

		JScrollPane songScrollPana = new JScrollPane(listContainer);
		songScrollPana.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		songScrollPana.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		return songScrollPana;

	}

	private JPanel ProgramCard(Show show) {

		JPanel card = new JPanel();
		
		card.setPreferredSize(new Dimension(590, 60));

		card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		card.setLayout(new GridLayout(1, 4));

		JLabel Name = new JLabel(show.getName());

		Name.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

		Name.setHorizontalTextPosition(SwingConstants.CENTER);

		JLabel Genre = new JLabel(show.getGenre());

		Name.setHorizontalTextPosition(SwingConstants.CENTER);

		JLabel Times = new JLabel(show.getStartTime() +" - "+ show.getEndTime());

		Name.setHorizontalTextPosition(SwingConstants.CENTER);

		JLabel Dj = new JLabel(show.getDj());

		Name.setHorizontalTextPosition(SwingConstants.CENTER);

		Color background;

		Color fontColor;

		if (show.getId().equals(this.SelectedProgram)) {

			background = this.constants.getMain();

			fontColor = Color.WHITE;

		} else {

			background = Color.WHITE;

			fontColor = Color.BLACK;

		}

		card.setBackground(background);

		Name.setForeground(fontColor);

		Genre.setForeground(fontColor);

		Times.setForeground(fontColor);

		Dj.setForeground(fontColor);

		card.add(Name);

		card.add(Genre);

		card.add(Times);

		card.add(Dj);
		
		card.setName("Show:" + show.getId().toString());

		card.addMouseListener(controller);

		return card;
	}

}
