package co.edu.unbosque.view;

import java.awt.Dimension;

import javax.swing.*;

import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.model.Show;

public class ShowProgramsModal extends JFrame{

	private static final long serialVersionUID = 1L;

	private Controller controller;
	
	private Show SelectedProgram;

	private JPanel mainPane;
	
	public ShowProgramsModal(Controller control) {

		this.controller = control;
		
	}

	public Show getSelectedProgram() {
		return SelectedProgram;
	}

	public void setSelectedProgram(Show selectedProgram) {
		SelectedProgram = selectedProgram;
	}

	private void render() {
		
		this.mainPane = new JPanel();

		this.mainPane.setLayout(new BoxLayout(this.mainPane, BoxLayout.PAGE_AXIS));

		// -- top bar

		JLabel Title = new JLabel("Select Program to start"); 

		JPanel topMargin = new JPanel();

		topMargin.setMaximumSize(new Dimension(1100, 10));

		JPanel bottomMargin = new JPanel();

		bottomMargin.setMaximumSize(new Dimension(1100, 10));

		// -- Programs list panel

		JPanel programsListPanel = this.getProgramsList();

		// -- Main Frame config

		mainPane.add(topMargin);

		mainPane.add(programsListPanel);

		mainPane.add(bottomMargin);

		this.setSize(500, 700);

		this.setResizable(false);

		this.add(mainPane);

	}
	
	private JPanel getProgramsList() {
		
		return new JPanel();
	}
	
//	private JScrollPane renderProgramsList() {
//		
//	}
	
}
