package com.blackpensoftware.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.blackpensoftware.launcher.core.LauncherFrame;
import com.blackpensoftware.logs.LogHandler;

public class FileHandler {
	
	LogHandler log = LauncherFrame.getLog();
	
	/**
	 * Method Name: createFile
	 * 
	 * Method description: 
	 * 
	 * @param fileName the location of the file 
	 * @return a new file
	 * @see File
	 * 
	 **/
	public File createFile(String fileName){
		return new File(fileName);
	}
	
	/**
	 * Method Name: createFileWriter
	 * 
	 * Method description: 
	 * 
	 * @param file the file that the FileWriter will reference 
	 * @param override The boolean that states if the file will be overwritten or appended to 
	 * @return a new FileWriter
	 * @see FileWriter
	 * 
	 **/
	public FileWriter createFileWriter(File file, boolean override) throws IOException{
		return new FileWriter(file, override);
	}
	
	/**
	 * Method Name: createWriter
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public PrintWriter createWriter(FileWriter fileWriter) throws IOException{
		return new PrintWriter(fileWriter); 
	}
	
	/**
	 * Method Name: fileAsStringArrayList
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public ArrayList<String> fileAsStringArrayList(File file){
		ArrayList<String> fileContents = new ArrayList<String>();
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			log.addLogError(e.getMessage());
		}
		
		while(scanner.hasNextLine()){
			fileContents.add(scanner.nextLine());
		}
		scanner.close();
		return fileContents;
	}
	
	/**
	 * Method Name: fileAsStringArray
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public String[] fileAsStringArray(File file){
		ArrayList<String> list = fileAsStringArrayList(file);
		String[] array = new String[list.size()];
		
		for(int i = 0; i < list.size(); i++){
			array[i] = list.get(i);
		}
		
		return array;
	}// End of fileAsStringArray method
	
	/**
	 * Method Name: splitArrayElements
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public String[][] splitArrayElements(String[] array, String splitPoint){
		String[][] newArray = new String[array.length][];
		for(int i = 0; i < array.length; i++){
			newArray[i] = array[i].split(splitPoint);
		}
		return newArray;
	}
	
	/**
	 * Method Name: loadFile
	 * 
	 * Method description: 
	 * 
	 * @param
	 * @return
	 * 
	 **/
	public File loadFile(String fileName){
		File file = createFile(fileName);
		
		if(!file.exists()){
			log.addLogWarning("No file with the name " + fileName + " found");
			try {
				log.addLogText("Generating new file with the name " + fileName);
				file.createNewFile();
			} catch (IOException e) {
				log.addLogError("Can't load / generate " + fileName);
				log.addLogError(e.getMessage());
				return file;
			}
		}
		log.addLogText(fileName + " loaded");
		return file;
	}// End of loadConfig method
}// End of class
