package com.blackpensoftware.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO: display confirmation screen
		System.exit(0);
	}// End of actionPerformed method
}// End of class
