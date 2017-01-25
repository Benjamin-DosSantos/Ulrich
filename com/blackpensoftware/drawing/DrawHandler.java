package com.blackpensoftware.drawing;

import java.util.ArrayList;

import com.blackpensoftware.buffer.LiveBuffer;
import com.blackpensoftware.core.LWJGE_Window;
import com.blackpensoftware.dynamics.DayCycle;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.blackpensoftware.models.Model;

public class DrawHandler {

	private LiveBuffer liveBuffer;
	private DayCycle dayCycle = new DayCycle();
	private LightingHandler lightingHandler = new LightingHandler();
    private LWJGE_Window lwjgeWindow;

	public DrawHandler(LWJGE_Window lwjgeWindow, LiveBuffer liveBuffer){
		this.lwjgeWindow = lwjgeWindow;
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
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glOrtho(45, windowWidth, 0, windowHeight, 20000, -20000);
		GL11.glClearDepth(1.0f);
		
		GL11.glViewport(0, 0, windowWidth, windowHeight);
		lightingHandler.defaultLightingSetup();
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
	public void drawAllObjects() {
		//dayCycle.setBackground();
		liveBuffer.drawAllModels(lwjgeWindow);
	}// End of drawAllObjects method
}// End of class
