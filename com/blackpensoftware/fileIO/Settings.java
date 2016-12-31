package com.blackpensoftware.fileIO;

import com.blackpensoftware.frames.LauncherFrame;

public class Settings {
	
	private static int width = 300;
	private static int height = 300;
	private static int minDimention = 300;
	
	/**
	 * Method Name: routeValues
	 * 
	 * Method description:
	 * 		Takes in a 2D array of settings and parses the information into variables to be called later
	 * 
	 * @param
	 * 		settings: 2D array of settings that is parsed
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
			}// End of switch case of settings
		}// End of for the size of the settings array
	}// End of routeValues method

	/**
	 * Method Name: checkValue
	 * 
	 * Method description: 
	 * 		Checks to see if the value in the settings array is within range of the minimum values
	 * @param
	 * 		value: The value to check
	 * @return
	 * 		int: Either the value that was entered or, if the value is too small, the minimum value
	 * 
	 **/
	private static int checkValue(int value) {
		if(value < minDimention){
			LauncherFrame.getLog().addLogError("Settings value was out of range, returned to default value");
			return minDimention; 
		} else { 
			return value; 
		}
	}// End of checkValue method

	/**
	 * Method Name: getWidth
	 * 
	 * Method description: 
	 * 		Gets the width setting for the width of the screen
	 * @return
	 * 		int: The width setting for the screen
	 * 
	 **/
	public static int getWidth() {
		return width;
	}

	/**
	 * Method Name: setWidth
	 * 
	 * Method description: 
	 * 		Takes in a value and sets the value to the width setting
	 * @param
	 * 		width: new Value for the width setting of the screen
	 **/
	private static void setWidth(int width) {
		Settings.width = width;
	}

	/**
	 * Method Name: getHeight
	 *
	 * Method description:
	 * 		Gets the height setting for the height of the screen
	 * @return
	 * 		int: The height setting for the screen
	 *
	 **/
	public static int getHeight() {
		return height;
	}

	/**
	 * Method Name: setHeight
	 *
	 * Method description:
	 * 		Takes in a value and sets the value to the width setting
	 * @param
	 * 		height: new Value for the height setting of the screen
	 **/
	private static void setHeight(int height) {
		Settings.height = height;
	}
}// End of class
