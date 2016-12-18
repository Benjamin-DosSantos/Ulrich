package com.blackpensoftware.generation;

import com.blackpensoftware.models.Model;
import com.blackpensoftware.primitives.VectorPoint;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Benjamin DosSantos on 12/16/2016.
 */
public class PointSmoothingHandler {

    Random ran = new Random();

    public void cleanUpPoints(ArrayList<Model> models, int hightestPoint, int maxDiff){
        for(Model model: models){
            VectorPoint[] points = model.getPoints();
            for(int point = 1; point < points.length; point++){
                int lastYPos = points[point - 1].getyPos();
                int changeAmount = ran.nextInt(maxDiff * 2) - maxDiff;  // from -highest to highest
                int nextYPos = lastYPos + changeAmount;
                points[point].setyPos(nextYPos);
            }// End of for all of the points in the model
        }// End of for Models in the terrain
    }// End of cleanUpPoints
}// End of class
