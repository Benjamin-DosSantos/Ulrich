package com.blackpensoftware.primitives;

import java.awt.*;
import java.io.File;

import javax.imageio.*;
import javax.swing.*;

public class GameButton extends JButton{
	public GameButton(String buttonText) {
		this.setPreferredSize(new Dimension(500, 75));
		this.setText(buttonText);
		this.setHorizontalTextPosition(JButton.CENTER);
		this.setVerticalTextPosition(JButton.CENTER);
		
		try{
            ImageIcon image = new ImageIcon(getClass().getResource("/resources/ulrich_button.png"));
            this.setIcon(image);    
        }catch (Exception ex) {
            ex.printStackTrace();
        }
	}// End of constructor
}// End of class
