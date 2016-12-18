package com.blackpensoftware.core;

import com.blackpensoftware.frames.LauncherFrame;

public class FullLaunch {
	static LauncherFrame frame = new LauncherFrame();
	public static void main(String[] args){
		
	}// End of main method

    /**
     * Method Name: getFrame
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      returns the current LauncherFrame
     * @return
     *      LauncherFrame: The current LauncherFrame
     *
     **/
	public static LauncherFrame getFrame() {
		return frame;
	}

    /**
     * Method Name: setFrame
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Sets the class variable for the current LauncherFrame to a custom value
     * @param
     *      frame: LauncherFrame for the class variable to be set to
     *
     **/
	public void setFrame(LauncherFrame frame) {
		this.frame = frame;
	}
}// End of class
