package com.blackpensoftware.logs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.blackpensoftware.handlers.FileHandler;

public class LogHandler {
	FileHandler fileHandler = new FileHandler();
	File logFile;
	
	public LogHandler(){
		createLogFile();
	}// End of constructor
	
	/**
	 * Method Name: createLogFile
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public void createLogFile(){
		try{
			File file = new File("GameLog.txt");
			
			if(file.exists()){
				file.delete();
			}
			
			file.createNewFile();
			
			logFile = file;
		}catch (IOException exception){
			exception.printStackTrace();
		}
	}
	
	/**
	 * Method Name: addTimeStamp
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public String addTimeStamp(){
		String date = new SimpleDateFormat("MM/dd/yy HH:mm:ss").format(Calendar.getInstance().getTime());
		return "[" + date + "] ";
	}
	
	/**
	 * Method Name: addMessageToLog
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	private void addMessageToLog(String log){
		try {
			FileWriter fileWriter = fileHandler.createFileWriter(logFile, true);
			PrintWriter writer = fileHandler.createWriter(fileWriter);
			writer.println(log);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method Name: addLogText
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public void addLogText(String message){
		String logText = addTimeStamp() + "[INFO] " + message;
		addMessageToLog(logText);
	}
	
	/**
	 * Method Name: addLogWarning
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public void addLogWarning(String message){
		String logText = addTimeStamp() + "[WARNING] " + message;
		addMessageToLog(logText);
	}
	
	/**
	 * Method Name: addLogError
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public void addLogError(String message){
		String logText = addTimeStamp() + "[ERROR] " + message;
		addMessageToLog(logText);
	}
}// End of class
