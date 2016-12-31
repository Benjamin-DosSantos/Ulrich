package com.blackpensoftware.models;

import com.blackpensoftware.fileIO.FileHandler;
import com.blackpensoftware.primitives.VectorPoint;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Benjamin DosSantos Jr. on 12/31/16.
 * Copyright Black Pen Software (c) All Rights Reserved
 */
public class ModelLoader {
    FileHandler fileHandler = new FileHandler();

    public ModelLoader(){

    }// End of constructor

    public Model loadFileToModel(String fileName){
        File file = fileHandler.loadFile(fileName);

        String[] fileInfo = fileHandler.fileAsStringArray(file);

        VectorPoint[] modelPoints = parseVectorPoints(fileInfo);
        int[] modelOrder = parseOrder(modelPoints, fileInfo);

        return new Model(modelPoints, modelOrder);
    }// End of loadFileToModel method

    public VectorPoint[] parseVectorPoints(String[] textPoints){
        ArrayList<VectorPoint> vectorPoints = new ArrayList<>();

        boolean swap = false;

        for(String currentLine: textPoints){
            String[] currentLineSplit = currentLine.split(" ");
            if(currentLineSplit[0].equalsIgnoreCase("v")){
                int xPos = (int) Double.parseDouble(currentLineSplit[1]) * 100 + 300;
                int yPos = (int) Double.parseDouble(currentLineSplit[2]) * 100;
                int zPos = (int) Double.parseDouble(currentLineSplit[3]) * 100;
                if(swap){
                    vectorPoints.add(new VectorPoint(xPos, yPos, zPos, Color.GREEN));
                }else{
                    vectorPoints.add(new VectorPoint(xPos, yPos, zPos, Color.CYAN));
                }

                swap = !swap;
            }
        }

        return arrayListToArray(vectorPoints);
    }// End of parseVectorPoints method

    public int[] parseOrder(VectorPoint[] modelPoints, String[] textPoints){
        ArrayList<Integer> orderPoints = new ArrayList<>();

        for(String currentLine: textPoints){
            String[] currentLineSplit = currentLine.split(" ");
            if(currentLineSplit[0].equalsIgnoreCase("f")){
                for(int point = 1; point < currentLineSplit.length; point++){
                    orderPoints.add(Integer.parseInt(currentLineSplit[point]) - 1);
                }
            }
        }

        return intArrayListToArray(orderPoints);
    }

    public int[] intArrayListToArray(ArrayList<Integer> intPoints){
        int[] points  = new int[intPoints.size()];
        for(int i = 0; i < points.length; i++){
            points[i] = intPoints.get(i);
        }
        return points;
    }

    public VectorPoint[] arrayListToArray(ArrayList<VectorPoint> vectorPoints){
        VectorPoint[] newArray = new VectorPoint[vectorPoints.size()];
        for(int i = 0; i < newArray.length; i++){
            newArray[i] = vectorPoints.get(i);
        }// End of for the size of the array

        return newArray;
    }// End of arrayListToArray method
}// End of class


