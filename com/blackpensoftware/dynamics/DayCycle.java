package com.blackpensoftware.dynamics;

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

    public DayCycle(){
        double time = timeToFrames(12);
        System.out.println(time);
        time = framesToTime(time);
        System.out.println(time);

    }

    private double timeToFrames(double time){
        return (time * hourLength * assumedFPS);
    }// End of timeToFrames method

    private double framesToTime(double frames){
        return ((frames / hourLength) / assumedFPS);
    }// End of framesToTime method
}// End of class
