package com.blackpensoftware.data;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

/**
 * Created by Benjamin DosSantos Jr. on 1/6/2017.
 */
public class FPSHandler {
    private double lastTime = glfwGetTime();
    private int minFPS = 300000;
    private int maxFPS = 0;
    private int passedFrames = 0;
    private long totalFrames = 0;
    private int totalSeconds = 0;

    public FPSHandler(){

    }// End of constructor

    public void captureFPS(){
        double currentTime = glfwGetTime();
        passedFrames++;

        if (currentTime - lastTime >= 1.0 ){
            System.out.println("Frames per Second:" + passedFrames);

            if(minFPS > passedFrames){ minFPS = passedFrames; }
            if(maxFPS < passedFrames){ maxFPS = passedFrames; }

            totalFrames += passedFrames;
            passedFrames = 0;
            lastTime += 1.0;
            totalSeconds++;
        }// End of if at least one second has passed
    }// End of captureFPS method

    public void printFinalInformation(){
        System.out.println("Max FPS: " + maxFPS);
        System.out.println("Min FPS: " + minFPS);
        System.out.println("Average FPS: " + (totalFrames / totalSeconds));
    }// End of printFinalInformation method
}// End of class
