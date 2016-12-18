package com.blackpensoftware.drawing;

import java.util.ArrayList;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.blackpensoftware.generation.LandGenerator;
import com.blackpensoftware.models.Model;

public class DrawHandler {
	
	private ArrayList<Model> objectsToDraw = new ArrayList<Model>();
	
	private int windowWidth;
	private int windowHeight;

    /**
     * Method Name: defaultAttribs
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Does the default needed steps for the setup of the game instance window
     * @param
     *      windowWidth: The width of the window to be created
     * @param
     *      windowHeight: The height of the window to be created
     *
     **/
	public void defaultAttribs(int windowWidth, int windowHeight){
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		GL.createCapabilities();
		GL11.glMatrixMode(GL11.GL_RENDER_MODE);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, windowWidth, 0, windowHeight, 20000, -20000);
		GL11.glClearDepth(1.0f);
		GL11.glViewport(0, 0, windowWidth, windowHeight);
	}

    /**
     * Method Name: drawAllObjects
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Draws all of the obects in the objects to draw
     *      //TODO: Replace with LiveBuffer system
     *
     **/
	public void drawAllObjects() {
		GL11.glClearColor(0, 0, 0, 1);                        
		for(Model object: objectsToDraw){
			object.drawModel();
		}
	}// End of drawAllObjects method

    /**
     * Method Name: getObjectsToDraw
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Gets the ArrayList of all objects to draw
     * @return
     *      ArrayList of models to draw
     *
     **/
	public ArrayList<Model> getObjectsToDraw() {
		return objectsToDraw;
	}
}// End of class
