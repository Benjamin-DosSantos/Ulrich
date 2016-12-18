package com.blackpensoftware.fileIO;

import com.blackpensoftware.buffer.LiveBuffer;
import com.blackpensoftware.models.Model;

import java.util.ArrayList;

public class Save {
	// THIS IS THE PRIMITIVE TYPE FOR SAVE INSTANCES 
	
	private String visibleName;

	private ArrayList<Model> allGameObjects = new ArrayList<>();

	public Save(String visibleName){
		this.visibleName = visibleName;
	}// End of constructor

	public ArrayList<Model> loadSaveData(){
		ArrayList<Model> allModels = new ArrayList<>();

		return allModels;
	}// End of loadSaveData

	public void writeSaveData(){

	}// End of writeSaveData
}// End of class
