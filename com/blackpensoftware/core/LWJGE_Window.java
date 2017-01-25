package com.blackpensoftware.core;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.system.MemoryUtil.NULL;

import com.blackpensoftware.buffer.ExitBuffer;
import com.blackpensoftware.data.FPSHandler;
import com.blackpensoftware.dynamics.DayCycle;
import com.blackpensoftware.input.MouseHandler;
import com.blackpensoftware.physics.GravityHandler;
import org.lwjgl.BufferUtils;
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

import java.nio.DoubleBuffer;

public class LWJGE_Window {
	// The window handle
	private long window;
	
	private LogHandler logHandler;
	private ConfigurationHandler config; 
	private Settings settings;
	

	private DrawHandler drawHandler;
	private KeyHandler keys = new KeyHandler(this);
    private MouseHandler mouseHandler;

	private PreBuffer preBuffer;
    private LiveBuffer liveBuffer;
	private ExitBuffer exitBuffer;

	private GravityHandler gravityHandler = new GravityHandler();

	private String gameName = "Ulrich";
	
	int windowWidth = 0;
	int windowHeight = 0;

    public boolean debugMode = false;

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
		drawHandler = new DrawHandler(this, liveBuffer);

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
		glfwSwapInterval(0); // 0: Unlimited, 1: 60 FPS, 2: 30 FPS, 3: 15 FPS
		// Make the window visible
		glfwShowWindow(window);

        mouseHandler = new MouseHandler(window, windowWidth, windowHeight);
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

<<<<<<< HEAD
        FPSHandler fpsHandler = new FPSHandler(liveBuffer);
=======
        FPSHandler fpsHandler = new FPSHandler();
>>>>>>> origin/master

        drawHandler.defaultAttribs(windowWidth, windowHeight);
		
		while (!glfwWindowShouldClose(window)) {
			clear();

			fpsHandler.captureFPS();
            mouseHandler.captureInput();
			drawHandler.drawAllObjects();
			
			glfwSwapBuffers(window); // swap the color buffers
			glfwPollEvents();
		}
		fpsHandler.printFinalInformation();
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

    public boolean getDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}// End of class
