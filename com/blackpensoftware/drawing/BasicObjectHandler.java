package com.blackpensoftware.drawing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.blackpensoftware.buffer.LiveBuffer;
import com.blackpensoftware.entites.Player;
import com.blackpensoftware.generation.LandGenerator;
import com.blackpensoftware.models.Model;
import com.blackpensoftware.models.ModelLoader;
import com.blackpensoftware.physics.GravityHandler;
import com.blackpensoftware.primitives.VectorPoint;

public class BasicObjectHandler {
	ArrayList<Model> objects = new ArrayList<Model>();
	private DrawHandler drawHandler;
	private LiveBuffer liveBuffer;

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
	public BasicObjectHandler(DrawHandler drawHandler, LiveBuffer liveBuffer){
		this.drawHandler = drawHandler;
		this.liveBuffer = liveBuffer;

		createBasicObjects();
	}// End of constructor

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
		
	    VectorPoint point1 = new VectorPoint(xPos, yPos, zPos, Color.RED);
	    VectorPoint point2 = new VectorPoint(xPos + width, yPos, zPos, Color.GREEN);
	    VectorPoint point3 = new VectorPoint(xPos + (width / 2), yPos - height, zPos, Color.BLUE);
	    VectorPoint point4 = new VectorPoint(xPos, yPos - height, zPos + depth, Color.RED);
	    
	    VectorPoint[] pointArray = {point1, point2, point3, point4};
	    int[] pointOrder = {0,1,2,2,3,1};
	    
		final Model model = new Model(pointArray, pointOrder, false);
		liveBuffer.addModel(model);
		
		LandGenerator landGeneration = new LandGenerator(0, 0, -500, 1024, 1024, 64, 50, 0);
		ArrayList<Model> models = landGeneration.getModels();

		liveBuffer.addModel(models);

<<<<<<< HEAD

        Player player = new Player();
        player.transformY(200);
        liveBuffer.addModel(player);
=======
<<<<<<< HEAD

        Player player = new Player();
        player.transformY(200);
        liveBuffer.addModel(player.getAllModels());
>>>>>>> origin/master

        Player player_2 = new Player();
        player_2.transformY(200);
        player_2.transformX(300);
<<<<<<< HEAD
        liveBuffer.addModel(player_2);
=======
        liveBuffer.addModel(player_2.getAllModels());
>>>>>>> origin/master

        Player player_3 = new Player();
        player_3.transformY(200);
        player_3.transformX(600);
<<<<<<< HEAD
        liveBuffer.addModel(player_3);
=======
        player_3.getLeftArm().setRotateSpeed(1);
        liveBuffer.addModel(player_3.getAllModels());
>>>>>>> origin/master

        Player player_4 = new Player();
        player_4.transformY(200);
        player_4.transformX(900);
<<<<<<< HEAD
        liveBuffer.addModel(player_4);
        
        liveBuffer.convertAllTextures();
=======
        liveBuffer.addModel(player_4.getAllModels());
=======
		ModelLoader modelLoader = new ModelLoader();
		liveBuffer.addModel(modelLoader.loadFileToModel("test.txt"));
>>>>>>> origin/master
>>>>>>> origin/master
	}// End of createBasicObjects method
}// End of class
