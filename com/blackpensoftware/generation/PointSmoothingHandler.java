package com.blackpensoftware.generation;

import com.blackpensoftware.models.Model;
import com.blackpensoftware.primitives.VectorPoint;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Benjamin DosSantos on 12/16/2016.
 */
public class PointSmoothingHandler {
    public void cleanUpPoints(ArrayList<Model> models, int subDivs, int max, int min){
        int[] lastPoints = populateRandomPoints(subDivs, max, min);

        for(Model currentModel: models){
            VectorPoint[] allPoints = currentModel.getPoints();

            for(int currentPoint = 0; currentPoint < allPoints.length; currentPoint++){
                if(currentPoint < subDivs){
                    // First set: 0 -> (subDivs - 1)
                    allPoints[currentPoint].setyPos(lastPoints[currentPoint]);
                }else{
                    // Second set: subDivs -> ((subDivs * 2) - 1)
                    int newPoint = newPoint(max, min);
                    allPoints[currentPoint].setyPos(newPoint);
                    lastPoints[currentPoint - subDivs] = newPoint;
                }// End of if past the half way point
            }// End of for the vectorPoints in the model
        }// End of for the models in the terrain
    }// End of cleanUpPoints method

    public int[] populateRandomPoints(int size, int max, int min){
        int[] newArray = new int[size];
        for(int i = 0; i < newArray.length; i++){
            newArray[i] = newPoint(max, min);
        }// End of for the size of the array to be populated

        return newArray;
    }// End of populateRandomPoints method

    public int newPoint(int max, int min){
        Random ran = new Random();
        return ran.nextInt(max - min) + min;
    }// End of newPoint method

    public boolean checkEven(int numberToCheck){
        return (numberToCheck % 2 == 0);
    }// End of checkEven method
}// End of class
