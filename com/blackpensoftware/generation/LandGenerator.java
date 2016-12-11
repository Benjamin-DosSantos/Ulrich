package com.blackpensoftware.generation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import com.blackpensoftware.models.Model;
import com.blackpensoftware.primitives.VectorPoint;

public class LandGenerator {
	private int xPos;
	private int yPos;
	private int zPos;
	private int width; 
	private int depth;
	private int subDivs;
	
	private ArrayList<Model> models = new ArrayList<Model>();
	Random ran = new Random();
	
	public LandGenerator(int xPos, int yPos, int zPos, int width, int depth, int subDivs){
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		this.width = width;
		this.depth = depth;
		this.subDivs = subDivs;
		
		generateMapPoints();
		
	}

	public void generateMapPoints(){
		for(int row = 0; row < subDivs; row++){
			VectorPoint[] stripPoints = generateStripPoints(row);
			int[] pointOrder = orderStripPoints(stripPoints);
			Model newStrip = new Model(stripPoints, pointOrder);
			models.add(newStrip);
		}
	}
	
	public VectorPoint[] generateStripPoints(int currentRow){
		VectorPoint[] col = new VectorPoint[subDivs * 2];
			
		for(int currentCol = 0; currentCol < subDivs * 2; currentCol++){
			int zoffset = 0;
			int xOffset = 0;
			if(currentCol >= subDivs){
				zoffset = -(subDivs * (depth / subDivs));
				xOffset = (width / subDivs);
			}
			
			int xPos = this.xPos + xOffset + currentRow * (width / subDivs);
			int yPos = this.yPos + ran.nextInt(50);
			int zPos = this.zPos + zoffset + currentCol * (depth / subDivs);
		
			col[currentCol] = new VectorPoint(xPos, yPos, zPos, Color.BLUE);
		}
		return col;
	}
	
	public int[] orderStripPoints(VectorPoint[] pointsToOrder){
		ArrayList<Integer> pointOrder = new ArrayList<Integer>();
		for(int currentPoint = 0; currentPoint < subDivs; currentPoint++){
			pointOrder.add(currentPoint);
			pointOrder.add(currentPoint + subDivs);
		}
		return toIntArray(pointOrder);
	}
	
	private int[] toIntArray(ArrayList<Integer> integerArrayList){
		int[] newIntArray = new int[integerArrayList.size()];
		for(int i = 0; i < integerArrayList.size(); i++){
			newIntArray[i] = integerArrayList.get(i);
		}
		return newIntArray;
	}
	
	public ArrayList<Model> getModels() {
		return models;
	}
}// End of class
