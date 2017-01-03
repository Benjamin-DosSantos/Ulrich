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
	HeightGenerator heightGenerator = new HeightGenerator();

	Random ran = new Random();
	
	public LandGenerator(int xPos, int yPos, int zPos, int width, int depth, int subDivs, int max, int min){
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		this.width = width;
		this.depth = depth;
		this.subDivs = subDivs;

		generateMapPoints();
		heightGenerator.genHeightMap(models, subDivs, max, min);
	}// End of LandGenerator constructor

	public void generateMapPoints(){
		for(int row = 0; row < subDivs; row++){
			VectorPoint[] stripPoints = generateStripPoints(row);
			int[] pointOrder = orderStripPoints(stripPoints);
			Model newStrip = new Model(stripPoints, pointOrder);
			models.add(newStrip);
		}// End of for the strips in the land
	}// End of generateMapPoints method

	public VectorPoint[] generateStripPoints(int currentRow){
		VectorPoint[] col = new VectorPoint[subDivs * 2];
		boolean swap = false;

		for(int currentCol = 0; currentCol < subDivs * 2; currentCol++){
			int zoffset = 0;
			int xOffset = 0;

			if(currentCol >= subDivs){
				zoffset = -(subDivs * (depth / subDivs));
				xOffset = (width / subDivs);
			}

			int xPos = this.xPos + xOffset + currentRow * (width / subDivs);
			int yPos = this.yPos;
			int zPos = this.zPos + zoffset + currentCol * (depth / subDivs);
		
			if(swap){
				col[currentCol] = new VectorPoint(xPos, yPos, zPos, Color.BLUE);
			}else{
				col[currentCol] = new VectorPoint(xPos, yPos, zPos, Color.GREEN);
			}
			swap = !swap;
		}// End of for the number of points in the strip
		return col;
	}// End of generateStripPoints method
	
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
