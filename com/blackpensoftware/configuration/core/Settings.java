package com.blackpensoftware.configuration.core;

import com.blackpensoftware.launcher.core.LauncherFrame;

public class Settings {
	
	private static int width = 300;
	private static int height = 300;
	private static int minDimention = 300;
	
	/**
	 * Method Name: routeValues
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public static void routeValues(String[][] settings) {
		for(int i = 0; i < settings.length; i++){
			switch (settings[i][0]){
				case "width":
					width = checkValue(Integer.parseInt((settings[i][1]).trim()));;
					break;
				case "height":
					height = checkValue(Integer.parseInt((settings[i][1]).trim()));
					break;
			}
		}
	}// End of routeValues method

	/**
	 * Method Name: checkValue
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	private static int checkValue(int value) {
		if(value < minDimention){
			LauncherFrame.getLog().addLogError("Settings value was out of range, returned to default value");
			return minDimention; 
		} else { 
			return value; 
		}
	}

	/**
	 * Method Name: getWidth
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public static int getWidth() {
		return width;
	}

	/**
	 * Method Name: setWidth
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public static void setWidth(int width) {
		Settings.width = width;
	}

	/**
	 * Method Name: getHeight
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public static int getHeight() {
		return height;
	}

	/**
	 * Method Name: setHeight
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public static void setHeight(int height) {
		Settings.height = height;
	}
}// End of class
