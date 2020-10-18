package co.edu.unbosque.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

public class Constants {
	
	private Color main;
	
	private Color ligthGray;
	
	private Color gray;
	
	private Color darkGray;
	
	private Border mainBorder;
	
	private JPanel mainInput;
	
	public Constants() {
		
		this.main = new Color(111,133,63);
		
		this.ligthGray = new Color(238,238,238);
		
		this.gray = new Color(204,204,204);
		
		this.darkGray = new Color(196,196,196);
		
		this.mainBorder = BorderFactory.createLineBorder(this.main, 2, true);
	}

	public Color getMain() {
		return main;
	}

	public Color getLigthGray() {
		return ligthGray;
	}

	public Color getGray() {
		return gray;
	}

	public Color getDarkGray() {
		return darkGray;
	}

	public Border getMainBorder() {
		return mainBorder;
	}
}
