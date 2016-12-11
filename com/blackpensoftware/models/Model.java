package com.blackpensoftware.models;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.blackpensoftware.primitives.VectorPoint;

public class Model {
	private VectorPoint[] points;
	private int[] pointOrder;
	
	public Model(VectorPoint[] points, int[] pointOrder){
		this.points = points;
		this.pointOrder = pointOrder;
	}// End of constructor
	
	public void drawModel(){	
		GL11.glBegin(GL11.GL_LINE_STRIP);
		for(int point: pointOrder){
			VectorPoint currentPoint = points[point];
			Color colorMaster = currentPoint.getColor();
			GL11.glColor3f(colorMaster.getRed(), colorMaster.getGreen(), colorMaster.getBlue());
			GL11.glVertex3f(currentPoint.getxPos(), currentPoint.getyPos(), currentPoint.getzPos());
		}
		GL11.glEnd();
	}
	
	public static void rotate(float angle, float xDif, float yDif, float zDif){
		GL11.glRotatef(angle, xDif, yDif, zDif);
	}
}// End of class
