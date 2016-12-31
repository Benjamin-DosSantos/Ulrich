package com.blackpensoftware.drawing;

import java.util.ArrayList;

import com.blackpensoftware.buffer.LiveBuffer;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.blackpensoftware.models.Model;

public class DrawHandler {

	private LiveBuffer liveBuffer;

	public DrawHandler(LiveBuffer liveBuffer){
		this.liveBuffer = liveBuffer;
	}

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
	}// End of defaultAttribs method

    /**
     * Method Name: drawAllObjects
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Draws all of the obects in the objects to draw
     *
     **/
	int translateMax = 500;
	int rotateMax = 1000;
	int currentTranslate = 0;
	int currentRotate = 0;

	public void drawAllObjects() {
		GL11.glClearColor(0, 0, 0, 1);                        
		liveBuffer.drawAllModels();

		if(currentTranslate < translateMax){
			GL11.glTranslatef(0, 1, 0);
			currentTranslate++;
		}else{
			GL11.glRotatef(0.5f, 1, 0, 0);
		}

	}// End of drawAllObjects method
}// End of class
