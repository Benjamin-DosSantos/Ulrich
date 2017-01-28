package com.blackpensoftware.generation;

import com.blackpensoftware.models.Model;
import com.blackpensoftware.primitives.VectorPoint;

import java.util.ArrayList;
import java.util.Random;

public class HeightGenerator {

    public void genHeightMap(ArrayList<Model> models, int subDivs, int max, int min){
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
    }// End of genHeightMap method

    public int[] populateRandomPoints(int size, int max, int min){
        int[] newArray = new int[size];
        for(int i = 0; i < newArray.length; i++){
            newArray[i] = newPoint(max, min);
        }// End of for the size of the array to be populated

        return newArray;
    }// End of populateRandomPoints method

    public int newPoint(int max, int min){
        if(max == 0 || max == -1){
            return 0;
        }else{
            Random ran = new Random();
            return ran.nextInt(max - min) + min;
        }// End of if the new point has a 0 maximum height
    }// End of newPoint method

    public boolean checkEven(int numberToCheck){
        return (numberToCheck % 2 == 0);
    }// End of checkEven method
}// End of class
