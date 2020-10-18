package co.edu.unbosque.view.components;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.unbosque.view.Constants;

public class Input {
	
	JTextField field;
	
	public JPanel getInput(String Label, String Name) {
		
		Constants assets = new Constants();
		
		JPanel input = new JPanel();
		
		input.setLayout(new GridLayout(2, 1));
		
		JLabel label = new JLabel(Label);
		
		this.field = new JTextField();
		
		field.setName(Name);
		
		field.setBorder(assets.getMainBorder());
		
		input.add(label);
		
		input.add(field);
		
		return input;
	}
	
	public String getInputContent() {
		return this.field.getText();
	}
	
}
