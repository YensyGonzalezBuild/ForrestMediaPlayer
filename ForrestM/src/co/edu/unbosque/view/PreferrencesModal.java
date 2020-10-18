package co.edu.unbosque.view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import co.edu.unbosque.controller.Controller;

public class PreferrencesModal extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private Controller controller;

	public PreferrencesModal(Controller control) {
		
		this.controller = control;
		
		this.render();
		
	}
	
	private void render() {
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new GridLayout(2, 1));
		
		JLabel title = new JLabel("Select Playback Type");

		Font labelFont = title.getFont();

		title.setHorizontalTextPosition(SwingConstants.CENTER);

		title.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
		
		JPanel buttonBar = new JPanel();
		
		buttonBar.setLayout(new GridLayout(1, 3));
		
		buttonBar.add(this.optionSelector("Streaming", "SetStreamingPlayback", this.controller.getPlaybackType().equals("Streaming")));
		
		buttonBar.add(this.optionSelector("AM Radio", "SetAmPlayback", this.controller.getPlaybackType().equals("AM")));
		
		buttonBar.add(this.optionSelector("FM Radio", "SetFmPlayback", this.controller.getPlaybackType().equals("FM")));
		
		mainPanel.add(title);
		
		mainPanel.add(buttonBar);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));

		this.setSize(300, 250);

		this.setResizable(false);

		this.add(mainPanel);
		
	}
	
	private JPanel optionSelector(String label, String action, boolean isActive) {
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(2, 1));
		
		JLabel title = new JLabel(label);
		
		JButton actionButton = new JButton(isActive ? "Active" : "Activate");
		
		actionButton.setEnabled(!isActive);
		
		actionButton.setName(action);
		
		actionButton.addMouseListener(this.controller);
		
		panel.add(title);
		
		panel.add(actionButton);
		
		return panel;
		
	}
	
}
