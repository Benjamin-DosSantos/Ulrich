package com.blackpensoftware.buffer;

import com.blackpensoftware.fileIO.LogHandler;
import sun.rmi.runtime.Log;

/**
 * Created by Benjamin DosSantos Jr. on 12/17/2016.
 */
public class ExitBuffer {
    private LogHandler logHandler;


    public ExitBuffer(LogHandler logHandler){
        this.logHandler = logHandler;

        logHandler.addLogText("Exit Buffer Created");
    }

    public void fullExit(){
        logHandler.addLogText("Full Exit started");
    }// End of fullExit method
}// End of ExitBuffer class
