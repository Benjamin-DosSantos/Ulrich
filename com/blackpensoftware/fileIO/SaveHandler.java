package com.blackpensoftware.fileIO;

import com.blackpensoftware.buffer.LiveBuffer;
import sun.rmi.runtime.Log;

public class SaveHandler {
	private LogHandler logHandler;

	public SaveHandler(LogHandler logHandler){
		this.logHandler = logHandler;
	}// End of constructor
	
	public LiveBuffer loadSaveToLiveBuffer(Save save){
		
		return new LiveBuffer(logHandler);
	}// End of loadSaveToLiveBuffer method
	
	public void saveLiveBufferToSave(LiveBuffer buffer, Save save){
		
	}// End of saveLiveBufferToSave method
}// End of class
