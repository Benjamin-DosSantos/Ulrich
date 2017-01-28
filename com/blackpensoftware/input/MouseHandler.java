package com.blackpensoftware.input;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.opengl.GL11;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class MouseHandler {

    private boolean mouseLocked = false;
    private double newX = 400;
    private double newY = 300;

    private double prevX = 0;
    private double prevY = 0;

    private boolean rotX = false;
    private boolean rotY = false;

    long window;
    int windowWidth;
    int windowHeight;
    int middleXPos;
    int middleYPos;

    public MouseHandler(long window, int windowWidth, int windowHeight){
        this.window = window;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.middleXPos = windowWidth / 2;
        this.middleYPos = windowHeight / 2;
    }// End of constructor

    public void captureInput(){
        if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == GLFW_PRESS) {
            glfwSetCursorPos(window, middleXPos, middleYPos);

            prevX = middleXPos;
            prevY = middleYPos;

            mouseLocked = !mouseLocked;

        }

        if (mouseLocked){
            DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
            DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

            glfwGetCursorPos(window, x, y);

            x.rewind();
            y.rewind();

            newX = x.get();
            newY = y.get();

            double xDif = prevX - newX;
            double yDif = prevY - newY;



            GL11.glTranslated(0.0, 0.0, 0.0);
            GL11.glRotated(yDif / 4, 0.1, 0.0, 0);
            GL11.glRotated(xDif / 4, 0.0, 0.1, 0.0);
            GL11.glTranslated(0.0, 0.0, 0.0);

            glfwSetCursorPos(window,middleXPos,middleYPos);
        }
    }// End of captureInput method
}// End of class
