package com.blackpensoftware.core;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.system.MemoryUtil.NULL;

import com.blackpensoftware.buffer.ExitBuffer;
import com.blackpensoftware.physics.GravityHandler;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.blackpensoftware.buffer.LiveBuffer;
import com.blackpensoftware.buffer.PreBuffer;
import com.blackpensoftware.drawing.BasicObjectHandler;
import com.blackpensoftware.drawing.DrawHandler;
import com.blackpensoftware.fileIO.ConfigurationHandler;
import com.blackpensoftware.fileIO.LogHandler;
import com.blackpensoftware.fileIO.Settings;
import com.blackpensoftware.input.KeyHandler;

public class LWJGE_Window {
	// The window handle
	private long window;
	
	private LogHandler logHandler;
	private ConfigurationHandler config; 
	private Settings settings;
	

	private DrawHandler drawHandler;
	private KeyHandler keys = new KeyHandler();

	private PreBuffer preBuffer;
    private LiveBuffer liveBuffer;
	private ExitBuffer exitBuffer;

	private GravityHandler gravityHandler = new GravityHandler();

	private String gameName = "Ulrich";
	
	int windowWidth = 0;
	int windowHeight = 0;
	
	public void run(LogHandler logHandler, ConfigurationHandler config, Settings settings) {
		this.logHandler = logHandler;
		this.config = config;
		this.settings = settings;
		
		this.logHandler.addLogText("Running LWJGL " + Version.getVersion());

		this.config = new ConfigurationHandler();
		
		windowWidth = settings.getWidth();
		windowHeight = settings.getHeight();

		preBuffer = new PreBuffer(logHandler);
		liveBuffer = preBuffer.populateLiveBuffer();
		drawHandler = new DrawHandler(liveBuffer);

		new BasicObjectHandler(drawHandler, liveBuffer);

		
		try {
			init();
			loop();

			// Free the window callbacks and destroy the window
			glfwFreeCallbacks(window);
			glfwDestroyWindow(window);
		} finally {
			// Terminate GLFW and free the error callback
			glfwTerminate();
			glfwSetErrorCallback(null).free();

			exitBuffer = liveBuffer.populateExitBuffer();
			exitBuffer.fullExit();
		}
	}

	private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (!glfwInit()){
			logHandler.addLogError("Unable to initialize GLFW");
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

		gravityHandler.createGravity(liveBuffer);
	}
	
	private void validateWindow(long windowToCheck) {
		if (windowToCheck == NULL){
			logHandler.addLogError("Failed to create the GLFW window");
			throw new RuntimeException("Failed to create the GLFW window");
		}else{
			logHandler.addLogText("GLFW window opened");
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

	public static void main(String[] args){
		new LWJGE_Window().run(new LogHandler(), new ConfigurationHandler(), new Settings());
	}


	public void clear(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
	}

	public LogHandler getLogHandler() {
		return logHandler;
	}

	public void setLogHandler(LogHandler logHandler) {
		this.logHandler = logHandler;
	}

    public LiveBuffer getLiveBuffer() {
        return liveBuffer;
    }

    public void setLiveBuffer(LiveBuffer liveBuffer) {
        this.liveBuffer = liveBuffer;
    }
}// End of class
