package com.blackpensoftware.configuration.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.blackpensoftware.handlers.FileHandler;
import com.blackpensoftware.launcher.core.LauncherFrame;
import com.blackpensoftware.logs.LogHandler;

public class ConfigurationHandler {
	String[] settings = {"width", "height"};
	
	FileHandler fileHandler = new FileHandler();
	LogHandler log = LauncherFrame.getLog();
	
	File config;
	
	public ConfigurationHandler(){
		loadConfig();
		parseSettings();
	}// End of constructor
	
	/**
	 * Method Name: loadConfig
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public void loadConfig(){
		File config = fileHandler.createFile("Settings.txt");
		
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
	 * 
	 * @param
	 * @return
	 * 
	 **/
	private void populateSettings(File settingsFile) {
		FileWriter fileWriter;
		PrintWriter writer = null;
		try {
			fileWriter = fileHandler.createFileWriter(settingsFile, true);
			writer = fileHandler.createWriter(fileWriter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.println("width: 700");
		writer.println("height: 500");
		writer.close();
	}

	/**
	 * Method Name: parseSettings
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public void parseSettings(){
		File settingsFile = fileHandler.createFile("Settings.txt");
		
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
