package com.blackpensoftware.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.blackpensoftware.core.FullLaunch;
import com.blackpensoftware.core.LWJGE_Window;
import com.blackpensoftware.frames.LauncherFrame;
import com.blackpensoftware.fileIO.LogHandler;

public class StartListener implements ActionListener{
	LogHandler log = LauncherFrame.getLog();

    /**
     * Method Name: actionPerformed
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Called when the user presses the Start button on the main launch panel
     *      Logs that a new game instance has been created
     *      Creates a new game window
     *      Stops showing the selection screen
     *      Displays the game window
     *      Shows the selection screen when the game is closed
     *
     * @param
     *      e: Action event called on button pressed
     *
     **/

	@Override
	public void actionPerformed(ActionEvent e) {
		log.addLogText("User started a new game instance");
		LWJGE_Window mainWindow = LauncherFrame.getGameView();
		FullLaunch.getFrame().toggleVisible();
		mainWindow.run(log, LauncherFrame.getConfig(), LauncherFrame.getSettings());
		FullLaunch.getFrame().toggleVisible();
	}// End of actionPerformed method
}// End of class
