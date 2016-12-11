package com.blackpensoftware.primitives;

import java.awt.Dimension;

import javax.swing.JButton;

public class GameButton extends JButton{

	public GameButton(String buttonText) {
		this.setPreferredSize(new Dimension(500, 75));
		this.setText(buttonText);
	}// End of constructor
}// End of class
