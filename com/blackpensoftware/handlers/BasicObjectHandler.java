package com.blackpensoftware.handlers;

import java.awt.Color;
import java.util.ArrayList;

import com.blackpensoftware.generation.LandGenerator;
import com.blackpensoftware.models.Model;
import com.blackpensoftware.primitives.VectorPoint;

public class BasicObjectHandler {
	ArrayList<Model> objects = new ArrayList<Model>();
	private DrawHandler drawHandler;
	
	public BasicObjectHandler(DrawHandler drawHandler){
		this.drawHandler = drawHandler;
	}
	
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
	    
		Model model = new Model(pointArray, pointOrder);
		objects.add(model);
		
		LandGenerator landGeneration = new LandGenerator(0, 0, 0, 1024, 1024, 32);
		ArrayList<Model> models = landGeneration.getModels();
		for(Model currentModel: models){
			objects.add(currentModel);
		}
		
	}
	
	public void pushBasicObjects(){
		for(Model currentObject: objects){
			drawHandler.getObjectsToDraw().add(currentObject);
		}
	}
}
