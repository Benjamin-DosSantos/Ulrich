package com.blackpensoftware.handlers;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL11;

public class KeyHandler{
	
	EscapeHandler escHandler = new EscapeHandler();
	
	public void handleKeys(long window){
		glfwSetKeyCallback(window, (window1, key, scancode, action, mods) -> {
			switch(key){
				case GLFW_KEY_W:
					GL11.glRotatef(1, 1, 0, 0);
					break;
				case GLFW_KEY_S:
					GL11.glRotatef(1, -1, 0, 0);
					break;
				case GLFW_KEY_A:
					GL11.glRotatef(1, 0, 1, 0);
					break;
				case GLFW_KEY_D:
					GL11.glRotatef(1, 0, -1, 0);
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
				case GLFW_KEY_ESCAPE:
					if(action == GLFW_RELEASE){
						escHandler.pauseFunction();
					}
					break;
			}
		});
	}
}// End of class
