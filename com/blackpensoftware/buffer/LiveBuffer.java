package com.blackpensoftware.buffer;

import java.util.ArrayList;

import com.blackpensoftware.models.Model;

public class LiveBuffer {
	private ArrayList<Model> activeModels = new ArrayList<Model>();

    /**
     * Method Name: LiveBuffer
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Creates a new ArrayList for all active models in the view
     *
     **/
	public LiveBuffer(){
		this(new ArrayList<Model>());
	}// End of constructor

    /**
     * Method Name: LiveBuffer
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Takes in an ArrayList of models that will act as the new master list for active models in the scene
     *
     * @param
     *      activeModels: ArrayList of Models that will act as the master list
     *
     **/
	public LiveBuffer(ArrayList<Model> activeModels){
		this.activeModels = activeModels;
	}// End of constructor

    /**
     * Method Name: addModel
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Takes in models to add to the master active model ArrayList
     *
     * @param
     *      model: Any number of models to add to the ArrayList
     *
     **/
	public void addModel(Model... model){
		for(Model nextToAdd: model){
			activeModels.add(nextToAdd);
		}// End of for the Models in the signature
	}// End of addModel method

    /**
     * Method Name: addModel
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Takes in an ArrayList of Models to add to the master active model ArrayList
     *
     * @param
     *      models: ArrayList of models to add to the ArrayList
     *
     **/
	public void addModel(ArrayList<Model> models){
		for(Model model: models){
			activeModels.add(model);
		}// End of for the number of models in the ArrayList
	}// End of addModel method

    /**
     * Method Name: removeModel
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Removes a specific Model from the activeModels ArrayList
     *
     * @param
     *      model: The model to remove from the activeModels ArrayList
     *
     **/
	public void removeModel(Model model){
		activeModels.add(model);
	}// End of removeModel method

    /**
     * Method Name: removeAllModels
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Removes all models from the current activeModels ArrayList
     **/
	public void removeAllModels(){
		for(Model model: activeModels){
			activeModels.remove(model);
		}// End of for all of the models in the activeModels ArrayList
	}// End of removeAllModels method
}// End of class
