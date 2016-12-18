package com.blackpensoftware.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener {

    /**
     * Method Name: actionPerformed
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Called when the user presses the exit button on the main launch panel
     *
     * @param
     *      e: Action event called on button pressed
     *
     **/

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO: display confirmation screen
		System.exit(0);
	}// End of actionPerformed method
}// End of class
