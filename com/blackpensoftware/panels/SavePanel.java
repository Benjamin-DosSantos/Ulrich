package com.blackpensoftware.panels;

import java.awt.Color;

import javax.swing.JPanel;

public class SavePanel extends JPanel{
	private boolean isVisible = false;
	
	public SavePanel(){
		this.setBackground(Color.RED);
		this.setVisible(false);
	}// End of constructor
	
	public void toggleVisible(){
		isVisible = !isVisible;
		this.setVisible(isVisible);
	}
}// End of class
