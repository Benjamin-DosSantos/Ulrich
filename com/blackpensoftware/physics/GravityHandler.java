package com.blackpensoftware.physics;

import com.blackpensoftware.buffer.LiveBuffer;
import com.blackpensoftware.models.Model;
import com.blackpensoftware.primitives.VectorPoint;

import java.util.Timer;
import java.util.TimerTask;

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

	public void createGravity(LiveBuffer liveBuffer){
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run(){
				for (Model model: liveBuffer.getActiveModels()){
					if(model.isAffectedByGravity()){
						applyGravity(model);
					}// End of if the model is affected by gravity
				}// End of for all of the models in the scene
			}
		}, 0, 1000 / 60);
	}// End of createGravity
}// End of class
