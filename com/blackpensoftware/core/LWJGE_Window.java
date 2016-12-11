package com.blackpensoftware.core;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import com.blackpensoftware.configuration.core.ConfigurationHandler;
import com.blackpensoftware.configuration.core.Settings;
import com.blackpensoftware.handlers.BasicObjectHandler;
import com.blackpensoftware.handlers.DrawHandler;
import com.blackpensoftware.handlers.KeyHandler;
import com.blackpensoftware.logs.LogHandler;

public class LWJGE_Window {
	// The window handle
	private long window;
	private boolean isRunning = false;
	
	private LogHandler log;
	private ConfigurationHandler config; 
	private Settings settings;
	

	private DrawHandler drawHandler = new DrawHandler();
	private BasicObjectHandler baseObjects = new BasicObjectHandler(drawHandler);
	private KeyHandler keys = new KeyHandler();
	
	private String gameName = "Ulrich";
	
	int windowWidth = 0;
	int windowHeight = 0;
	
	public void run(LogHandler log, ConfigurationHandler config, Settings settings) {
		this.log = log;
		this.config = config;
		this.settings = settings;
		
		this.log.addLogText("Running LWJGL " + Version.getVersion());

		this.config = new ConfigurationHandler();
		
		windowWidth = settings.getWidth();
		windowHeight = settings.getHeight();
		
		baseObjects.createBasicObjects();
		baseObjects.pushBasicObjects();
		
		try {
			isRunning = true;
			init();
			loop();

			// Free the window callbacks and destroy the window
			glfwFreeCallbacks(window);
			glfwDestroyWindow(window);
		} finally {
			// Terminate GLFW and free the error callback
			glfwTerminate();
			glfwSetErrorCallback(null).free();
			isRunning = false;
		}
	}

	private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (!glfwInit()){
			log.addLogError("Unable to initialize GLFW");
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		// Configure our window
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		// Create the window
		window = glfwCreateWindow(windowWidth, windowHeight, gameName, NULL, NULL);
		validateWindow(window);
		
		keys.handleKeys(window);
		
		
		// Get the resolution of the primary monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		// Center our window
		glfwSetWindowPos(window, (vidmode.width() - windowWidth) / 2, (vidmode.height() - windowHeight) / 2);
	
		// Make the OpenGL context current 
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(0);
		// Make the window visible
		glfwShowWindow(window);
	}
	
	private void validateWindow(long windowToCheck) {
		if (windowToCheck == NULL){
			log.addLogError("Failed to create the GLFW window");
			throw new RuntimeException("Failed to create the GLFW window");
		}else{
			log.addLogText("GLFW window opened");
		}
	}
	
	private void loop() {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();
		// Set the clear color
		
		drawHandler.defaultAttribs(windowWidth, windowHeight);
		
		while (!glfwWindowShouldClose(window)) {
			clear();
			
			drawHandler.drawAllObjects();
			
			glfwSwapBuffers(window); // swap the color buffers
			glfwPollEvents();
		}
	}

	public void clear(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
	}
	
	public boolean isRunning() {
		return isRunning;
	}

	public LogHandler getLog() {
		return log;
	}

	public void setLog(LogHandler log) {
		this.log = log;
	}
}// End of class
