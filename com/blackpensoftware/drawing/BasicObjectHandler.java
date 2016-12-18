package com.blackpensoftware.drawing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.blackpensoftware.generation.LandGenerator;
import com.blackpensoftware.models.Model;
import com.blackpensoftware.physics.GravityHandler;
import com.blackpensoftware.primitives.VectorPoint;

public class BasicObjectHandler {
	ArrayList<Model> objects = new ArrayList<Model>();
	private DrawHandler drawHandler;
	private GravityHandler gravityHandler = new GravityHandler(0.5);

    /**
     * Method Name: BasicObjectHandler
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Sets the class variable for the drawHandler to a custom value
     * @param
     *      drawHandler: The current instance DrawHandler
     *
     **/
	public BasicObjectHandler(DrawHandler drawHandler){
		this.drawHandler = drawHandler;
	}

    /**
     * Method Name: createBasicObjects
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      This is the method that adds all of the core needed models to the scene
     *      Used for basic objects, complex objects should have their own classes
     *      and can be called here for convenience
     **/

	public void createBasicObjects(){
		int xPos = 200;
		int yPos = 700;
		int zPos = -50;
	    int width = 1000;
	    int height = width / 2;
	    int depth = height;
		
	    VectorPoint point1 = new VectorPoint(xPos              , yPos         , zPos, Color.RED);
	    VectorPoint point2 = new VectorPoint(xPos + width      , yPos         , zPos, Color.GREEN);
	    VectorPoint point3 = new VectorPoint(xPos + (width / 2), yPos - height, zPos, Color.BLUE);
	    VectorPoint point4 = new VectorPoint(xPos              , yPos - height, zPos + depth, Color.RED);
	    
	    VectorPoint[] pointArray = {point1, point2, point3, point4};
	    int[] pointOrder = {0,1,2,2,3,1};
	    
		final Model model = new Model(pointArray, pointOrder);
		objects.add(model);
		
		LandGenerator landGeneration = new LandGenerator(0, 0, 0, 1024, 1024, 32);
		ArrayList<Model> models = landGeneration.getModels();
		for(Model currentModel: models){
			objects.add(currentModel);
		}
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run(){
				gravityHandler.applyGravity(model);
			}	
		}, 0, 1000 / 60);
		
	}// End of createBasicObjects method

    /**
     * Method Name: pushBasicObjects
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Sends all of the basic objects to the objects to draw array
     *      //TODO: Replace with a send to the LiveBuffer
     *
     **/
	public void pushBasicObjects(){
		for(Model currentObject: objects){
			drawHandler.getObjectsToDraw().add(currentObject);
		}// End of for the models in the currentObjectsArray
	}// End of pushBasicObjects method
}// End of class
