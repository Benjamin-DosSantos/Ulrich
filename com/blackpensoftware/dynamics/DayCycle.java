package com.blackpensoftware.dynamics;

import org.lwjgl.opengl.GL11;

/**
 * Created by Benjamin DosSantos Jr. on 1/2/17.
 * Copyright Black Pen Software (c) All Rights Reserved
 */
public class DayCycle {
    private double currentTime = 12;
    private double clockCycle = 24;
    private double timeWarp = 0.01; // 1 is 24 real time hours, 0.01 is 24 real time minutes
    private double minuteLength = 1;
    private double minutesInAnHour = 60;
    private double hourLength = minuteLength * minutesInAnHour;
    private double assumedFPS = 60;
    private double totalTimeInFrames = timeToFrames(clockCycle);

    int redPart = 0;
    int greenPart = 0;
    int bluePart = 0;

    public DayCycle(){

    }

    public void setBackground(){
        GL11.glClearColor(redPart, greenPart, bluePart, 1);
    }

    private double timeToFrames(double time){
        return (time * hourLength * assumedFPS);
    }// End of timeToFrames method

    private double framesToTime(double frames){
        return ((frames / hourLength) / assumedFPS);
    }// End of framesToTime method
}// End of class
