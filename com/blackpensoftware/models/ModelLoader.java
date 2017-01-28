package com.blackpensoftware.models;

import com.blackpensoftware.fileIO.FileHandler;
import com.blackpensoftware.primitives.VectorPoint;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.awt.*;
import java.awt.image.BufferedImage;
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
    double[] texturePoints;
    int[] textureOrder;
    
    boolean swap = false;

    public ModelLoader(){

    }// End of constructor


    public Model loadFileToModel(String fileName){
        File file = fileHandler.loadFile(fileName);
        String[] fileInfo = fileHandler.fileAsStringArray(file);

        parseModelData(fileInfo);

        return new Model(modelPoints, modelOrder, normalOrder);
    }// End of loadFileToModel method
    
    public Model loadFileToModel(String fileName, BufferedImage image){
        File file = fileHandler.loadFile(fileName);
        String[] fileInfo = fileHandler.fileAsStringArray(file);

        parseModelData(fileInfo);
        return new Model(modelPoints, modelOrder, normalOrder, texturePoints, textureOrder, image);
    }// End of loadFileToModel method

    public void parseModelData(String[] textPoints){
        ArrayList<VectorPoint> vectorPoints = new ArrayList<>();
        ArrayList<Integer> normalPoints = new ArrayList<>();
        ArrayList<Integer> orderPoints = new ArrayList<>();
        ArrayList<Double> textureCords = new ArrayList<>();
        ArrayList<Integer> textureOrderAL = new ArrayList<>();
        
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
                case "vt":
                    parseTexturePoints(currentLineSplit, textureCords);
                    break;
                case "f":
                    parseOrder(currentLineSplit, orderPoints, textureOrderAL);
                    break;
            }
        }

        modelPoints = arrayListToArray(vectorPoints);
        modelOrder = intArrayListToArray(orderPoints);
        normalOrder = intArrayListToArray(normalPoints);
        texturePoints = doubleArrayListToArray(textureCords);
        textureOrder = intArrayListToArray(textureOrderAL);
    }// End of parseModelData method
    
    private void parseTexturePoints(String[] currentLineSplit, ArrayList<Double> texturePoints){
        double textureXPos = Double.parseDouble(currentLineSplit[1]);
        double textureYPos = Double.parseDouble(currentLineSplit[2]);
        
        texturePoints.add(textureXPos);
        texturePoints.add(textureYPos);
    }
    
    public void parseNormals(String[] currentLineSplit, ArrayList<Integer> normalPoints){
        int xNormal = (int) Double.parseDouble(currentLineSplit[1]);
        int yNormal = (int) Double.parseDouble(currentLineSplit[2]);
        int zNormal = (int) Double.parseDouble(currentLineSplit[3]);
        
        normalPoints.add(xNormal);
        normalPoints.add(yNormal);
        normalPoints.add(zNormal);
    }
    
    public VectorPoint parseVectorPoints(String[] currentLineSplit){
        int xPos = (int) Double.parseDouble(currentLineSplit[1]) + 300;
        int yPos = (int) Double.parseDouble(currentLineSplit[2]);
        int zPos = (int) Double.parseDouble(currentLineSplit[3]);

        return new VectorPoint(xPos, yPos, zPos, Color.RED);
    }// End of parseVectorPoints method

    public void parseOrder(String[] currentLineSplit, ArrayList<Integer> points, ArrayList<Integer> textureOrder){
        for(int point = 1; point < currentLineSplit.length; point++){
            String[] currentPoint = currentLineSplit[point].split("/");
            points.add(Integer.parseInt(currentPoint[0]) - 1);
            
            if(!currentPoint[1].equalsIgnoreCase("")){
                textureOrder.add(Integer.parseInt(currentPoint[1]) - 1);   
            }
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

    public double[] doubleArrayListToArray(ArrayList<Double> intPoints){
        double[] points  = new double[intPoints.size()];
        for(int i = 0; i < points.length; i++){
            points[i] = intPoints.get(i);
        }
        return points;
    }
}// End of class





