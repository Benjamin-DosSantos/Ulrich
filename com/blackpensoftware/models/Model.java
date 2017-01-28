package com.blackpensoftware.models;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import com.blackpensoftware.core.LWJGE_Window;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.blackpensoftware.primitives.VectorPoint;
import org.lwjgl.opengl.GL12;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengles.EXTSparseTexture.GL_TEXTURE_2D;

public class Model {
	private VectorPoint[] points;
	private int[] pointOrder;
	private int[] normals;
	private double[] textures;
	private int[] textureOrder;
	private BufferedImage image;
	private int imageID;
	private boolean loadTexture = false;

	private boolean isAffectedByGravity;
	private double weight;
	
	private double xSpeed;
	private double ySpeed;
	private double zSpeed;
	
	private double xAcceleration;
	private double yAcceleration;
	private double zAcceleration;
	
	private float rotateAngle;
    private float rotateX;
    private float rotateY;
    private float rotateZ;
	
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

	public Model(VectorPoint[] points, int[] pointOrder, int[] normals){
		this();
		this.points = points;
		this.pointOrder = pointOrder;
		this.normals = normals;
	}// End of constructor

	public Model(VectorPoint[] points, int[] pointOrder, int[] normals, double[] textures, int[] textureOrder, BufferedImage image){
		this.points = points;
		this.pointOrder = pointOrder;
		this.normals = normals;
		this.textures = textures;
		this.textureOrder = textureOrder;
		this.image = image;
		this.loadTexture = true;
	}// End of constructor

	public Model(VectorPoint[] points, int[] pointOrder, boolean isAffectedByGravity){
		this(points, pointOrder);
		this.isAffectedByGravity = isAffectedByGravity;
	}// End of constructor
	
	public Model(VectorPoint[] points, int[] pointOrder, boolean isAffectedByGravity, double weight){
		this(points, pointOrder, isAffectedByGravity);
		this.weight = weight;
	}// End of constructor
	
    public void convertTexture(){
        if(loadTexture){
            convertImage(image);   
        }
    }
    
	public void drawModel(LWJGE_Window lwjgeWindow){
	    if(lwjgeWindow.getDebugMode()){
            GL11.glBegin(GL11.GL_LINE_STRIP);
        }else {
            GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        }
        
        transformX((int) (xSpeed));
		transformY((int) (ySpeed));
		transformZ((int) (zSpeed));
		
		xSpeed += xAcceleration;
		ySpeed += yAcceleration;
		zSpeed += zAcceleration;
		
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

			if(loadTexture){
				GL11.glTexCoord2d(textures[textureOrder[point]], textures[textureOrder[point + 1]]);
			}
			
			GL11.glVertex3f(currentPoint.getxPos(), currentPoint.getyPos(), currentPoint.getzPos());
		}
		
        GL11.glPushMatrix();
        GL11.glRotatef(rotateAngle, rotateX, rotateY, rotateZ);
		GL11.glPopMatrix();
		GL11.glEnd();
	}// End of drawModel method
	
	private void convertImage(BufferedImage image){
	    System.out.println("TGIF!!");
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4); //4 for RGBA, 3 for RGB

		for(int y = 0; y < image.getHeight(); y++){
			for(int x = 0; x < image.getWidth(); x++){
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
				buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
				buffer.put((byte) (pixel & 0xFF));               // Blue component
				buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
			}
		}

		buffer.flip(); //FOR THE LOVE OF GOD DO NOT FORGET THIS
        
		imageID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, imageID); //Bind texture ID

        //Setup wrap mode
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        //Setup texture scaling filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        
		GL11.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
	}
	
	public void transformX(int ammount){
		for(VectorPoint point: points){
			int currentX = point.getxPos();
			point.setxPos(currentX + ammount);
		}
	}

	public void transformY(int ammount){
		for(VectorPoint point: points){
			int currentY = point.getyPos();
			point.setyPos(currentY + ammount);
		}
	}

	public void transformZ(int ammount){
		for(VectorPoint point: points){
			int currentZ = point.getzPos();
			point.setzPos(currentZ + ammount);
		}
	}
	
	public void rotate(float rotateAngle, float rotateX, float rotateY, float rotateZ){
	    this.rotateAngle = rotateAngle;
	    this.rotateX = rotateX;
	    this.rotateY = rotateY;
	    this.rotateZ = rotateZ;
    }
    
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
