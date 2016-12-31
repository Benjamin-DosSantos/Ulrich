package com.blackpensoftware.fileIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.blackpensoftware.fileIO.Settings;
import com.blackpensoftware.fileIO.FileHandler;
import com.blackpensoftware.fileIO.LogHandler;
import com.blackpensoftware.frames.LauncherFrame;

public class ConfigurationHandler {
    private String fileName = "Settings.txt";	// Name for the config file

	// All of the settings to be added to the file
	String[][] settings = {
            {"width", "700"},
            {"height", "500"}
	};

	FileHandler fileHandler = new FileHandler();
	LogHandler log = LauncherFrame.getLog();
	
	File config;

	/**
	 * Method Name: ConfigurationHandler
	 *
	 * Author: Benjamin DosSantos Jr.
	 *
	 * Method description:
	 *      Constructor that loads and parses the config on call
	 *
	 **/
	public ConfigurationHandler(){
		loadConfig();
		parseSettings();
	}// End of constructor
	
	/**
	 * Method Name: loadConfig
	 * 
	 * Method description:
	 * 		Checks to see if the config file exists.
	 * 		If it does, loads all of the infomation from the file
	 * 		If it doesn't, it creates a new file and populates it with the default settings
	 **/
	public void loadConfig(){
		File config = fileHandler.createFile(fileName);
		
		if(!config.exists()){
			log.addLogWarning("No settings file found");
			try {
				log.addLogText("Generating new settings file");
				config.createNewFile();
				populateSettings(config);
			} catch (IOException e) {
				log.addLogError("Can't load / generate settings file");
				e.printStackTrace();
			}
		}else{
			this.config = config;
			log.addLogText("Settings file loaded");
		}
	}// End of loadConfig method
	
	/**
	 * Method Name: populateSettings
	 * 
	 * Method description: 
	 * 		Takes in a settings file and loads the custom user settings
	 * @param
	 * 		settingsFile: File for the settings to be parsed from
	 **/
	private void populateSettings(File settingsFile) {
		FileWriter fileWriter;
		PrintWriter writer = null;
		try {
			fileWriter = fileHandler.createFileWriter(settingsFile, true);
			writer = fileHandler.createWriter(fileWriter);
		} catch (IOException e) {
            e.printStackTrace();
        }
		writeDefaultInforamtion(writer);
	}// End of populateSettings method

	/**
	 * Method Name: writeDefaultInforamtion
	 *
	 * Method description:
	 *		Writes all of the default information to the settings file
	 * @param
	 * 		writer: Takes in a PrintWriter that writes the default settings to the settings file
	 *
	 **/
	private void writeDefaultInforamtion(PrintWriter writer){
        for(String[] settingsItem: settings){
            writer.println(settingsItem[0] + ":" + settingsItem[1]);
        }// End of for all of the String arrays in the settings 2D array
        writer.close();
    }// End of WriteDefaultInformation method

	/**
	 * Method Name: parseSettings
	 * 
	 * Method description:
	 * 		Loads all of the file settings and routes the values to the Settings system
	 *
	 * @see
	 * 		Settings
	 * 
	 **/
	public void parseSettings(){
		File settingsFile = fileHandler.createFile(fileName);
		
		if(!settingsFile.exists()){
			log.addLogError("No settings file");
		}else{
			String[] settingsSingle = fileHandler.fileAsStringArray(settingsFile);
			String[][] settings = fileHandler.splitArrayElements(settingsSingle, ":");
			log.addLogText("Settings file parsed");
			
			Settings.routeValues(settings);
		}
		log.addLogText("Custom settings applied");
	}// End of parseSettings method
}// End of class
