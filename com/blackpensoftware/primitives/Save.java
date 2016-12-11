package com.blackpensoftware.primitives;

import com.blackpensoftware.buffer.LiveBuffer;

public class Save {
	// THIS IS THE PRIMITIVE TYPE FOR SAVE INSTANCES 
	
	private String visibleName;
	private int saveID;
	
	public Save(String visibleName, int saveID){
		this.visibleName = visibleName;
		this.saveID = saveID;
	}// End of constructor
	
	public void populateFile(LiveBuffer buffer){
		
	}// End of populateFile method
	
	public LiveBuffer getSaveInformation(){
		
		return new LiveBuffer();
	}
}// End of class
