package com.blackpensoftware.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.blackpensoftware.core.FullLaunch;
import com.blackpensoftware.core.LWJGE_Window;
import com.blackpensoftware.launcher.core.LauncherFrame;
import com.blackpensoftware.launcher.core.LauncherPanel;
import com.blackpensoftware.logs.LogHandler;

public class StartListener implements ActionListener{
	LogHandler log = LauncherFrame.getLog();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		log.addLogText("User started a new game instance");
		LWJGE_Window mainWindow = LauncherFrame.getGameView();
		FullLaunch.getFrame().toggleVisible();
		mainWindow.run(log, LauncherFrame.getConfig(), LauncherFrame.getSettings());
		FullLaunch.getFrame().toggleVisible();
	}// End of actionPerformed method
}// End of class
