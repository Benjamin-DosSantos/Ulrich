package com.blackpensoftware.primitives;

import java.awt.Color;

public class VectorPoint {
	private int xPos;
	private int yPos;
	private int zPos;
	private Color color;

	/**
	 * Method Name: VectorPoint
	 *
	 * Author: Benjamin DosSantos Jr.
	 *
	 * Method description:
	 *      Default VectorPoint constructor, populates all vector point information to the default value
	 *
	 **/
	public VectorPoint(){
		this.xPos = 0;
		this.yPos = 0;
		this.zPos = 0; 
		this.color = Color.RED;
	}// End of constructor

	/**
	 * Method Name: VectorPoint
	 *
	 * Author: Benjamin DosSantos Jr.
	 *
	 * Method description:
	 *      Creates a new VectorPoint instance with an x, y, and z position
	 * @param
	 *      xPos: X Position for the vector point in 3D space
	 * @param
	 * 		yPos: Y Position for the vector point in 3D space
	 * @param
	 * 		zPos: Z Position for the vaector point in 3D space
	 *
	 **/
	public VectorPoint(int xPos, int yPos, int zPos){
		this();
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
	}// End of constructor

	/**
	 * Method Name: VectorPoint
	 *
	 * Author: Benjamin DosSantos Jr.
	 *
	 * Method description:
	 *      Creates a new VectorPoint instance with an x, y, z position, and a Color value
	 * @param
	 *      xPos: X Position for the vector point in 3D space
	 * @param
	 * 		yPos: Y Position for the vector point in 3D space
	 * @param
	 * 		zPos: Z Position for the vaector point in 3D space
	 * @param
	 * 		color: Color for the point to be drawn in
	 *
	 **/
	public VectorPoint(int xPos, int yPos, int zPos, Color color){
		this(xPos, yPos, zPos);
		this.color = color;
	}// End of constructor

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
