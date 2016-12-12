package com.blackpensoftware.handlers;

import com.blackpensoftware.models.Model;
import com.blackpensoftware.primitives.VectorPoint;

public class GravityHandler {
	private double gravityAmount;
	
	public GravityHandler(){
		this.gravityAmount = 9.81;
	}// End of constructor
	
	public GravityHandler(double gravityAmount){
		this.gravityAmount = gravityAmount;
	}// End of constructor 
	
	public void applyGravity(Model model){
		double newAcceleration = (gravityAmount / 60) + model.getyAcceleration();
		model.setyAcceleration(newAcceleration);
		
		VectorPoint[] modelPoints = model.getPoints();
		for(VectorPoint currentPoint: modelPoints){
			currentPoint.setyPos((int) (currentPoint.getyPos() - newAcceleration));
		}
	}// End of applyGravity method
}// End of class
