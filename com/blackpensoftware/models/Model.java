package com.blackpensoftware.models;

import java.awt.Color;

import com.blackpensoftware.core.LWJGE_Window;
import org.lwjgl.opengl.GL11;

import com.blackpensoftware.primitives.VectorPoint;

public class Model {
	private VectorPoint[] points;
	private int[] pointOrder;
	private boolean isAffectedByGravity;
	private double weight;
	
	private double xSpeed;
	private double ySpeed;
	private double zSpeed;
	
	private double xAcceleration;
	private double yAcceleration;
	private double zAcceleration;
	
	public Model(){
		this.points = new VectorPoint[0];
		this.pointOrder = new int[0];
		this.isAffectedByGravity = false;
		this.weight = 0.0;
	}// End of constructor
	
	public Model(VectorPoint[] points, int[] pointOrder){
		this();
		this.points = points;
		this.pointOrder = pointOrder;
	}// End of constructor
	
	public Model(VectorPoint[] points, int[] pointOrder, boolean isAffectedByGravity){
		this(points, pointOrder);
		this.isAffectedByGravity = isAffectedByGravity;
	}// End of constructor
	
	public Model(VectorPoint[] points, int[] pointOrder, boolean isAffectedByGravity, double weight){
		this(points, pointOrder, isAffectedByGravity);
		this.weight = weight;
	}// End of constructor
	
	public void drawModel(LWJGE_Window lwjgeWindow){
	    if(lwjgeWindow.getDebugMode()){
            GL11.glBegin(GL11.GL_LINE_STRIP);
        }else {
            GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        }

		for(int point: pointOrder){
			VectorPoint currentPoint = points[point];
			Color colorMaster = currentPoint.getColor();

			float[] green = {colorMaster.getRed(), colorMaster.getGreen(), colorMaster.getBlue(), 1.0f};
			float[] white = {0.3f, 0.3f, 0.3f, 1.0f};
			float[] emissionColor = {0.0f, 0.0f, 0.0f, 1.0f};

			GL11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT, green);
			GL11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, green);
			GL11.glMaterialfv(GL11.GL_FRONT, GL11.GL_SPECULAR, white);
			GL11.glMaterialfv(GL11.GL_FRONT, GL11.GL_EMISSION, emissionColor);
			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 0.0f);

			GL11.glVertex3f(currentPoint.getxPos(), currentPoint.getyPos(), currentPoint.getzPos());
		}
		GL11.glEnd();
	}// End of drawModel method
	
	public static void rotate(float angle, float xDif, float yDif, float zDif){
		GL11.glRotatef(angle, xDif, yDif, zDif);
	}// End of rotate method

	public VectorPoint[] getPoints() {
		return points;
	}

	public void setPoints(VectorPoint[] points) {
		this.points = points;
	}

	public int[] getPointOrder() {
		return pointOrder;
	}

	public void setPointOrder(int[] pointOrder) {
		this.pointOrder = pointOrder;
	}

	public boolean isAffectedByGravity() {
		return isAffectedByGravity;
	}

	public void setAffectedByGravity(boolean isAffectedByGravity) {
		this.isAffectedByGravity = isAffectedByGravity;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public double getySpeed() {
		return ySpeed;
	}

	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	public double getzSpeed() {
		return zSpeed;
	}

	public void setzSpeed(double zSpeed) {
		this.zSpeed = zSpeed;
	}

	public double getxAcceleration() {
		return xAcceleration;
	}

	public void setxAcceleration(double xAcceleration) {
		this.xAcceleration = xAcceleration;
	}

	public double getyAcceleration() {
		return yAcceleration;
	}

	public void setyAcceleration(double yAcceleration) {
		this.yAcceleration = yAcceleration;
	}

	public double getzAcceleration() {
		return zAcceleration;
	}

	public void setzAcceleration(double zAcceleration) {
		this.zAcceleration = zAcceleration;
	}
}// End of class
