package com.blackpensoftware.primitives;

import java.awt.Color;

public class VectorPoint {
	private int xPos;
	private int yPos;
	private int zPos;
	private Color color;
	
	public VectorPoint(){
		this.xPos = 0;
		this.yPos = 0;
		this.zPos = 0; 
		this.color = Color.RED;
	}
	
	public VectorPoint(int xPos, int yPos, int zPos){
		this();
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
	}
	
	public VectorPoint(int xPos, int yPos, int zPos, Color color){
		this(xPos, yPos, zPos);
		this.color = color;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public int getzPos() {
		return zPos;
	}

	public Color getColor() {
		return color;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public void setzPos(int zPos) {
		this.zPos = zPos;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}// End of class
