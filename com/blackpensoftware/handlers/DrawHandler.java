package com.blackpensoftware.handlers;

import java.util.ArrayList;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.blackpensoftware.generation.LandGenerator;
import com.blackpensoftware.models.Model;

public class DrawHandler {
	
	private ArrayList<Model> objectsToDraw = new ArrayList<Model>();
	
	private int windowWidth;
	private int windowHeight; 
	
	public void defaultAttribs(int windowWidth, int windowHeight){
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		GL.createCapabilities();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, windowWidth, 0, windowHeight, 20000, -20000);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glClearDepth(1.0f);
		GL11.glViewport(0, 0, windowWidth, windowHeight);
	}
	
	public void drawAllObjects() {
		GL11.glClearColor(0, 0, 0, 1);                        
		for(Model object: objectsToDraw){
			object.drawModel();
		}
	}// End of drawAllObjects method

	public ArrayList<Model> getObjectsToDraw() {
		return objectsToDraw;
	}
}// End of class
