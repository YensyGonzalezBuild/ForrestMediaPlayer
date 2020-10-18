package co.edu.unbosque.view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.model.persistance.DjManager;
import co.edu.unbosque.view.components.Input;

public class CreateShowModal extends JFrame {

	private static final long serialVersionUID = 1L;

	private Controller controller;
	
	private DjManager djManager;

	private Input nameInput;

	private Input genreInput;

	private JComboBox StartTimeSelector;

	private JComboBox EndTimeSelector;
	
	private JComboBox DjSelector;

	public CreateShowModal(Controller controller) {
		
		this.djManager = new DjManager("../ForrestM/src/data");

		this.controller = controller;

		this.nameInput = new Input();

		this.genreInput = new Input();

		this.render();
	}
	
	public String[] getValues() {
		
		String[] values = new String[5];
		
		values[0] = this.nameInput.getInputContent();
		
		values[1] = this.genreInput.getInputContent();
		
		values[2] = (String) this.StartTimeSelector.getSelectedItem();
		
		values[3] = (String) this.EndTimeSelector.getSelectedItem();
		
		values[4] = (String) this.DjSelector.getSelectedItem();
		
		return values;
		
	}
	
	public void updateDjs(String dj) {
		
		this.DjSelector.addItem(dj);
	}
	
	private void configComboBoxes() {
		String[] TimeStamps = {"8:00am","9:00am","10:00am","11:00am","12:00pm","1:00pm","2:00pm","3:00pm","4:00pm","5:00pm","6:00pm","7:00pm"};
		
		this.StartTimeSelector = new JComboBox(TimeStamps);
		
		this.EndTimeSelector = new JComboBox(TimeStamps);
		
		String[] Djs = this.djManager.getDjs();
		
		this.DjSelector = new JComboBox(Djs);
	}

	private void render() {

		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new GridLayout(7, 1));
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));

		JLabel title = new JLabel("Create Program");

		Font labelFont = title.getFont();

		title.setHorizontalTextPosition(SwingConstants.CENTER);

		title.setFont(new Font(labelFont.getName(), Font.PLAIN, 22));
		
		JButton createButton = new JButton("Create Program");
		
		createButton.setName("createProgram");
		
		createButton.addMouseListener(this.controller);

		JButton createDjButton = new JButton("Create new Dj");
		
		createDjButton.setName("createDj");
		
		createDjButton.addMouseListener(this.controller);
		
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new GridLayout(2, 1));
		
		buttonPanel.add(createDjButton);
		
		buttonPanel.add(createButton);
		
		mainPanel.add(title);

		mainPanel.add(this.nameInput.getInput("Program Name", "programName"));
		
		mainPanel.add(this.genreInput.getInput("Program Genre", "programGenre"));
		
		this.configComboBoxes();
		
		mainPanel.add(this.StartTimeSelector);
		
		mainPanel.add(this.EndTimeSelector);
		
		mainPanel.add(this.DjSelector);
		
		mainPanel.add(buttonPanel);

		this.setSize(500, 650);

		this.setResizable(false);

		this.add(mainPanel);

	}

}
