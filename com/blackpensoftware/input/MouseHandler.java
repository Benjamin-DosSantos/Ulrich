package com.blackpensoftware.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MouseHandler extends GLFWCursorPosCallback {
	private double mouseX;
	private double mouseY;
	private double mouseDeltaX;
	private double mouseDeltaY;
	private boolean wasFirst;
	
	@Override
	public void invoke(long window, double xPos, double yPos) {
		
	}// End of invoke
}// End of class
