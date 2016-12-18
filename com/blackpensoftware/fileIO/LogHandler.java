package com.blackpensoftware.fileIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.blackpensoftware.fileIO.FileHandler;

public class LogHandler {
    private String fileName = "GameLog.txt";
	FileHandler fileHandler = new FileHandler();
	File logFile;
	
	public LogHandler(){
		createLogFile();
	}// End of constructor
	
	/**
	 * Method Name: createLogFile
	 *
     * Author: Benjamin DosSantos Jr.
     *
	 * Method description: 
	 *      Creates a Log file for all information to be collected in to
     *      Sets the class logFile variable to the new created file.
     *      If there is an existing log file, it is deleted and a new one is created to replace it
	 * 
	 **/
	public void createLogFile(){
		try{
			File file = new File(fileName);
			
			if(file.exists()){
				file.delete();
			}
			
			file.createNewFile();
			
			logFile = file;
		}catch (IOException exception){
			exception.printStackTrace();
		}
	}// End of createLogFile method
	
	/**
	 * Method Name: addTimeStamp
	 *
     * Author: Benjamin DosSantos Jr.
     *
	 * Method description: 
	 *      The method creates and returns a custom time stamp for the second that the method was called in
     *
	 * @return
     *      String: Time stamp for the time that the method was called in
	 * 
	 **/
	public String addTimeStamp(){
		String date = new SimpleDateFormat("MM/dd/yy HH:mm:ss").format(Calendar.getInstance().getTime());
		return "[" + date + "] ";
	}
	
	/**
	 * Method Name: addMessageToLog
	 *
     * Author: Benjamin DosSantos Jr.
     *
	 * Method description: 
	 *      Adds a basic text message to the log file, the base for all other types of log messages
	 * @param
     *      log: a string of text to be written to the log file
	 * 
	 **/
	private void addMessageToLog(String log){
		fileHandler.writeStringToFile(logFile, log);
	} // End of addMessageToLog method
	
	/**
	 * Method Name: addLogText
	 *
     * Author: Benjamin DosSantos Jr.
     *
	 * Method description: 
	 *      A log event that is for informational content
     * @see addMessageToLog
     *
	 * @param
     *      message: String to be written to the log file
	 * 
	 **/
	public void addLogText(String message){
		String logText = addTimeStamp() + "[INFO] " + message;
		addMessageToLog(logText);
	}// End of addLogText method
	
	/**
	 * Method Name: addLogWarning
	 *
     * Author: Benjamin DosSantos Jr.
     *
	 * Method description: 
	 *      A log event that is important but not necessarily a cause for concern
     *      just information that should be presented to the user but can be ignored without issue
	 * @param
     *      message: String to be written to the log file
	 * 
	 **/
	public void addLogWarning(String message){
		String logText = addTimeStamp() + "[WARNING] " + message;
		addMessageToLog(logText);
	}// End of addLogWarning method
	
	/**
	 * Method Name: addLogError
	 *
     * Author: Benjamin DosSantos Jr.
     *
	 * Method description: 
	 *      A log event that is for error messages
     *      messages that are not crash events but are important for debugging
     *      examples include: files or images not being loaded
	 * @param
     *      message: String to be written to the log file
	 * 
	 **/
	public void addLogError(String message){
		String logText = addTimeStamp() + "[ERROR] " + message;
		addMessageToLog(logText);
	}// End of addLogError method
}// End of class
