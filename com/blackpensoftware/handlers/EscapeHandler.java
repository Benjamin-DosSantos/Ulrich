package com.blackpensoftware.handlers;

public class EscapeHandler {
	//LogHandler log = LWJGE_Window.getLog();
	
	private boolean isPaused = false;
	
	public void pauseFunction(){
		if(isPaused){
			unPauseGame();
		}else{
			pauseGame();
		}
	}
	
	private void unPauseGame(){
		isPaused = false;
		//	TODO: Fix error on adding Logs 
		
		//log.addLogText("Game was un-paused");
	}
	
	private void pauseGame(){
		isPaused = true;
		//log.addLogText("Game was paused");
	}
	
	private void drawMenu(){
		
	}
}// End of class
