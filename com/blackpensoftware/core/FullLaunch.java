package com.blackpensoftware.core;

import com.blackpensoftware.launcher.core.LauncherFrame;

public class FullLaunch {
	static LauncherFrame frame = new LauncherFrame();
	public static void main(String[] args){
		
	}// End of main method
	
	public static LauncherFrame getFrame() {
		return frame;
	}
	public void setFrame(LauncherFrame frame) {
		this.frame = frame;
	}
}// End of class
