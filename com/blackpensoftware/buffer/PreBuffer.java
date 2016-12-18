package com.blackpensoftware.buffer;

import com.blackpensoftware.fileIO.Save;

/**
 * Created by Benjamin DosSantos on 12/17/2016.
 */
public class PreBuffer {
    private String saveName;
    private Save currentSave;

    /**
     * Method Name: PreBuffer
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Create default values for the information to be loaded from the save file
     *
     **/
    public PreBuffer(){
        saveName = "Game_1";
        this.currentSave = new Save(saveName);
    }// End of constructor

    /**
     * Method Name: PreBuffer
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Sets the class variables for the save file to custom values
     * @param
     *      saveName: String of the name of the save file to load all data from
     *
     **/
    public PreBuffer(String saveName){
        this.saveName = saveName;
        this.currentSave = new Save(saveName);
    }// End of constructor

    /**
     * Method Name: LiveBuffer
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      populates the LiveBuffer with all old Models from the save
     *
     **/
    public LiveBuffer populateLiveBuffer(){
        return new LiveBuffer();
    }// End of populateLiveBuffer Method
}// End of class
