package com.blackpensoftware.input;

import static org.lwjgl.glfw.GLFW.*;

import com.blackpensoftware.fileIO.FileHandler;
import org.lwjgl.opengl.GL11;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class KeyHandler{
    File keyMasterFile;

	EscapeHandler escHandler = new EscapeHandler();
    FileHandler fileHandler = new FileHandler();

    HashMap<String, Integer> keyMap = new HashMap<>();

	public KeyHandler(){
	    this.keyMasterFile = fileHandler.loadFile("KeySelections.txt");
	    writeDefaultsToFile();
    }

	public void handleKeys(long window) {
        glfwSetKeyCallback(window, (window1, key, scancode, action, mods) -> {
            switch (key) {
                case GLFW_KEY_W:
                    GL11.glTranslatef(0, 0, 100);
                    break;
                case GLFW_KEY_S:
                    GL11.glTranslatef(0, 0, -100);
                    break;
                case GLFW_KEY_A:
                    GL11.glTranslatef(1, 0, 0);
                    break;
                case GLFW_KEY_D:
                    GL11.glTranslatef(-1, 0, 0);
                    break;
                case GLFW_KEY_SPACE:
                    GL11.glTranslatef(0, 5, 0);
                    break;
                case GLFW_KEY_LEFT_SHIFT:
                    GL11.glTranslatef(0, -5, 0);
                    break;
                case GLFW_KEY_Q:
                    GL11.glTranslatef(-5, 0, 0);
                    break;
                case GLFW_KEY_E:
                    GL11.glTranslatef(5, 0, 0);
                    break;
                case GLFW_KEY_R:
                    GL11.glRotatef(1, 20, 0, 0);
                    break;
                case GLFW_KEY_F:
                    GL11.glRotatef(1, -20, 0, 0);
                    break;
                case GLFW_KEY_ESCAPE:
                    if (action == GLFW_RELEASE) {
                        escHandler.pauseFunction();
                    }
                    break;
            }
        });
    }

    private void populateKeyMap(){
        keyMap.put("Forward", GLFW_KEY_W);
        keyMap.put("Back", GLFW_KEY_S);
        keyMap.put("Left", GLFW_KEY_A);
        keyMap.put("Right", GLFW_KEY_D);
    }

    private void writeDefaultsToFile(){

    }

	private void parseKeysFromFile(){

    }
}// End of class
