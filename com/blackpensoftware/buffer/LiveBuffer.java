package com.blackpensoftware.buffer;

import java.util.ArrayList;

import com.blackpensoftware.models.Model;

public class LiveBuffer {
	private ArrayList<Model> activeModels = new ArrayList<Model>();

	public LiveBuffer(){
		this(new ArrayList<Model>());
	}// End of constructor
	
	public LiveBuffer(ArrayList<Model> activeModels){
		this.activeModels = activeModels;
	}// End of constructor
	
	public void addModel(Model... model){
		for(Model nextToAdd: model){
			activeModels.add(nextToAdd);
		}
	}// End of addModel method
	
	public void addModel(ArrayList<Model> models){
		for(Model model: models){
			activeModels.add(model);
		}
	}// End of addModel method
	
	public void removeModel(Model model){
		activeModels.add(model);
	}// End of removeModel method
	
	public void removeAllModels(){
		for(Model model: activeModels){
			activeModels.remove(model);
		}
	}// End of removeAllModels method
}// End of class
