package com.blackpensoftware.fileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.blackpensoftware.frames.LauncherFrame;

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
	}	// End of createFile method
	
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
	}// End of createFileWriter method
	
	/**
	 * Method Name: createWriter
	 * 
	 * Method description: 
	 * 		creates a PrintWriter for a FileWriter
	 * @param
	 * 		fileWriter: A FileWriter used to create a PrintWriter
	 * @return
	 * 		PrintWriter: for writing to a file
	 * 
	 **/
	public PrintWriter createWriter(FileWriter fileWriter) throws IOException{
		return new PrintWriter(fileWriter); 
	}// End of createWriter
	
	/**
	 * Method Name: fileAsStringArrayList
	 * 
	 * Method description:
	 *		Takes in a File and returns a new ArrayList of the contents of that File
	 * @param
	 * 		file: File to make into an ArrayList
	 * @return
	 * 		ArrayList of Strings
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
		}// End of while there are more lines in the file
		scanner.close();
		return fileContents;
	}// End of fileAsStringArrayList method
	
	/**
	 * Method Name: fileAsStringArray
	 * 
	 * Method description: 
	 *		Takes in a file and returns the file's contents as a String[]
	 * @param
	 * 		file: File to make into String Array
	 * @return
	 * 		String[] with all of the file's information
	 * 
	 **/
	public String[] fileAsStringArray(File file){
		ArrayList<String> list = fileAsStringArrayList(file);
		String[] array = new String[list.size()];
		
		for(int i = 0; i < list.size(); i++){
			array[i] = list.get(i);
		}// End of for the size of the list
		
		return array;
	}// End of fileAsStringArray method
	
	/**
	 * Method Name: splitArrayElements
	 * 
	 * Method description: 
	 * 		Takes in an array of information and a split point to make a new 2D array of resources
	 * @param
	 * 		array: The master array of information to be split into a 2D array
	 * @param
	 * 		splitPoint: The point that the 1D array will be split into a 2D array
	 * @return
	 * 		The new 2D String array with the contents of the master array
	 * 
	 **/
	public String[][] splitArrayElements(String[] array, String splitPoint){
		String[][] newArray = new String[array.length][];
		for(int i = 0; i < array.length; i++){
			newArray[i] = array[i].split(splitPoint);
		}// End of for the length of the array
		return newArray;
	}// End of splitArrayElements method
	
	/**
	 * Method Name: loadFile
	 * 
	 * Method description: 
	 * 		Takes in a file name and checks to see if that file is real.
	 * 		If the file doesn't exist it is created, if it does the file is loaded to a new File object
	 * @param
	 * 		fileName: Name of the file to be loaded
	 * @return
	 * 		File: The file object that references the file at the fileName location
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
		}// End of if the file doesn't exist
		log.addLogText(fileName + " loaded");
		return file;
	}// End of loadConfig method

	/**
	 * Method Name: writeStringToFile
	 *
	 * Method description:
	 *		Writes a string to the file being referenced
	 * @param
	 * 		fileToWrite: The file that the string will be added to
	 * @param
	 * 		data: The string of resources that is going to be written
	 **/
    public void writeStringToFile(File fileToWrite, String data){
        PrintWriter writer = createCustomWriter(fileToWrite);
        writer.println(data);
        writer.close();
    }// End of write StringToFile method

	/**
	 * Method Name: writeArrayToFile
	 *
	 * Method description:
	 *		Writes an array of resources to a file
	 * @param
	 * 		fileToWrite: The file referance that the resources will be written to
	 * @param
	 * 		data: String array of resources that will be written to the array
	 **/
    public void writeArrayToFile(File fileToWrite, String[] data){
        PrintWriter writer = createCustomWriter(fileToWrite);

        for(String dataPart: data){
            writer.println(dataPart);
        }// End of for every String in the string array of resources
        writer.close();
    }// End of writeArrayToFile

	/**
	 * Method Name: createCustomWriter
	 *
	 * Method description:
	 *		Takes in a file and creates a printWriter for the file
	 * @param
	 * 		fileToWrite: The file that the PrintWriter will reference
	 * @return
	 * 		New PrintWriter that references the file
	 *
	 **/
    public PrintWriter createCustomWriter(File fileToWrite){
        FileWriter fileWriter;
        PrintWriter writer = null;
        try {
            fileWriter = createFileWriter(fileToWrite, true);
            writer = createWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer;
    }// End of createCustomWriter method
}// End of class
