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

    VectorPoint[] modelPoints;
    int[] modelOrder;
    int[] normalOrder;

    boolean swap = false;

    public ModelLoader(){

    }// End of constructor

    public Model loadFileToModel(String fileName){
        File file = fileHandler.loadFile(fileName);
        String[] fileInfo = fileHandler.fileAsStringArray(file);

        splitArrayIntoMultipleObjects(fileInfo);
        parseModelData(fileInfo);

        return new Model(modelPoints, modelOrder, normalOrder);
    }// End of loadFileToModel method

    public void parseModelData(String[] textPoints){
        ArrayList<VectorPoint> vectorPoints = new ArrayList<>();
        ArrayList<Integer> normalPoints = new ArrayList<>();
        ArrayList<Integer> orderPoints = new ArrayList<>();
        
        for(String currentLine: textPoints){
            String[] currentLineSplit = currentLine.split(" ");
            String infoType = currentLineSplit[0];

            switch (infoType){
                case "v":
                    vectorPoints.add(parseVectorPoints(currentLineSplit));
                    break;
                case "vn":
                    parseNormals(currentLineSplit, normalPoints);
                    break;
                case "f":
                    parseOrder(currentLineSplit, orderPoints);
                    break;
            }
        }

        modelPoints = arrayListToArray(vectorPoints);
        modelOrder = intArrayListToArray(orderPoints);
        normalOrder = intArrayListToArray(normalPoints);
    }// End of parseModelData method

<<<<<<< HEAD
    public void parseNormals(String[] currentLineSplit, ArrayList<Integer> normalPoints){
        int xNormal = (int) Double.parseDouble(currentLineSplit[1]);
        int yNormal = (int) Double.parseDouble(currentLineSplit[2]);
        int zNormal = (int) Double.parseDouble(currentLineSplit[3]);
        
        normalPoints.add(xNormal);
        normalPoints.add(yNormal);
        normalPoints.add(zNormal);
    }
    
=======
    public void splitArrayIntoMultipleObjects(String[] fileInfo){
        int numberOfObjects = calculateNumberOfObjects(fileInfo);
        
        System.out.println("Number of Objects: " + numberOfObjects);
    }
    
    public int calculateNumberOfObjects(String[] objectArray){
        int numberOfObjects = 0;
        for(String currentLine: objectArray){
            String[] currentLineSplit = currentLine.split(" ");
            String infoType = currentLineSplit[0];
            if(infoType.equals("o")){
                numberOfObjects++;
            }// End of if there is new object
        }// End of for the number of lines in the array
        return numberOfObjects;
    }// End of calculateNumberOfObjects method

>>>>>>> origin/master
    public VectorPoint parseVectorPoints(String[] currentLineSplit){
        int xPos = (int) Double.parseDouble(currentLineSplit[1]) + 300;
        int yPos = (int) Double.parseDouble(currentLineSplit[2]);
        int zPos = (int) Double.parseDouble(currentLineSplit[3]);

        return new VectorPoint(xPos, yPos, zPos, Color.GREEN);
    }// End of parseVectorPoints method

    public void parseOrder(String[] currentLineSplit, ArrayList<Integer> points){
        for(int point = 1; point < currentLineSplit.length; point++){
            String[] currentPoint = currentLineSplit[point].split("//");
            points.add(Integer.parseInt(currentPoint[0]) - 1);
        }
    }

    public VectorPoint[] arrayListToArray(ArrayList<VectorPoint> intPoints){
        VectorPoint[] points  = new VectorPoint[intPoints.size()];
        for(int i = 0; i < points.length; i++){
            points[i] = intPoints.get(i);
        }
        return points;
    }

    public int[] intArrayListToArray(ArrayList<Integer> intPoints){
        int[] points  = new int[intPoints.size()];
        for(int i = 0; i < points.length; i++){
            points[i] = intPoints.get(i);
        }
        return points;
    }
}// End of class





