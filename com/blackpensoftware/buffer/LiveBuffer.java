package com.blackpensoftware.buffer;

import java.util.ArrayList;

import com.blackpensoftware.core.LWJGE_Window;
import com.blackpensoftware.entites.Player;
import com.blackpensoftware.fileIO.LogHandler;
import com.blackpensoftware.models.Model;

public class LiveBuffer {
	private LogHandler logHandler;	// Log handler that is passed down from the calling class
	private ArrayList<Model> activeModels = new ArrayList<Model>();		// ArrayList of active models in the scene

    /**
     * Method Name: LiveBuffer
     *
     * Author: Benjamin DosSantos Jr.
     *
     * Method description:
     *      Creates a new ArrayList for all active models in the view
     *
     **/
	public LiveBuffer(LogHandler logHandler){
		this(logHandler, new ArrayList<Model>());
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
	 * 		logHandler: A LogHandler instance that is passed down from the calling class
     * @param
     *      activeModels: ArrayList of Models that will act as the master list
     *
     **/
	public LiveBuffer(LogHandler logHandler, ArrayList<Model> activeModels){
		this.logHandler = logHandler;
		this.activeModels = activeModels;
		logHandler.addLogText("Live Buffer started");
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
			logHandler.addLogText("Model added to live buffer");
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

	/**
	 * Method Name: drawAllModels
	 *
	 * Author: Benjamin DosSantos Jr.
	 *
	 * Method description:
	 *      parses through the activeModels array and draws all of the active models in the scene
	 *
	 **/
	public void drawAllModels(LWJGE_Window lwjgeWindow){
		for(Model nextModel: activeModels){
			nextModel.drawModel(lwjgeWindow);
		}
	}

	/**
	 * Method Name: populateExitBuffer
	 *
	 * Author: Benjamin DosSantos Jr.
	 *
	 * Method description:
	 *      creates and populates the ExitBuffer for the current game instance
	 * @return:
	 * 		return a new, populated, ExitBuffer
	 *
	 **/
	public ExitBuffer populateExitBuffer(){
		return new ExitBuffer(logHandler);
	}

	/**
	 * Method Name: getActiveModels
	 *
	 * Author: Benjamin DosSantos Jr.
	 *
	 * Method description:
	 *      returns the an ArrayList of the current models in the scene
	 * @return:
	 * 		returns a ArrayList of current active models
	 *
	 **/
	public ArrayList<Model> getActiveModels() {
		return activeModels;
	}

	/**
	 * Method Name: setActiveModels
	 *
	 * Author: Benjamin DosSantos Jr.
	 *
	 * Method description:
	 *      Takes in an ArrayList of models and replaces the current activeModels array with the new arrayList
	 * @param
	 *      activeModels: ArrayList of models that will replace the current activeModels ArrayList
	 *
	 **/
	public void setActiveModels(ArrayList<Model> activeModels) {
		this.activeModels = activeModels;
	}
	
	public int activeModelCount(){
	    return activeModels.size();
    }
    
    public void runObjectAnimations(){
		for(Model model: activeModels){
			if(model instanceof Player){
			    ((Player) model).runAnimationLoop();
			}
		}
	}
	
	public void convertAllTextures(){
        for(Model model: activeModels){
            model.convertTexture();
        }
    }
}// End of class
