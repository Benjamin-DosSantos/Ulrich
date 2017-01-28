package com.blackpensoftware.entites;

import com.blackpensoftware.core.LWJGE_Window;
import com.blackpensoftware.models.Model;
import com.blackpensoftware.models.ModelLoader;
import com.blackpensoftware.primitives.VectorPoint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Benjamin DosSantos Jr. on 1/25/2017.
 */
public class Player extends Model {
    private ModelLoader modelLoader = new ModelLoader();
    private ArrayList<Model> allModels = new ArrayList<>();
    
    private BufferedImage image;
    
    private Model body;
    private Model leftArm;
    private Model rightArm;
    private Model rightLeg;
    private Model leftLeg;
    
    private int lastAnimationTime = 0;
    
    public Player(){
        try {
            image = ImageIO.read(new File("ulrich_person_nicolas.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        prepareModels();
    }// End of constructor
    
    public void prepareModels(){
        body = modelLoader.loadFileToModel("player_body.txt", image);
        leftArm = modelLoader.loadFileToModel("player_arm_left.txt");
        rightArm = modelLoader.loadFileToModel("player_arm_right.txt");
        rightLeg = modelLoader.loadFileToModel("player_leg_right.txt");
        leftLeg = modelLoader.loadFileToModel("player_leg_left.txt");
        
        allModels.add(body);
        allModels.add(leftArm);
        allModels.add(rightArm);
        allModels.add(rightLeg);
        allModels.add(leftLeg);
    }

    @Override
    public void transformX(int ammount){
        for(Model model: allModels){
            model.transformX(ammount);
        }
    }

    @Override
    public void transformY(int ammount){
        for(Model model: allModels){
            model.transformY(ammount);
        }
    }

    @Override
    public void transformZ(int ammount){
        for(Model model: allModels){
            model.transformZ(ammount);
        }
    }

    @Override
    public void setxSpeed(double xSpeed) {
        for(Model model: allModels){
            model.setxSpeed(xSpeed);   
        }
    }

    @Override
    public void setySpeed(double ySpeed) {
        for(Model model: allModels){
            model.setySpeed(ySpeed);
        }
    }

    @Override
    public void setzSpeed(double zSpeed) {
        for(Model model: allModels){
            model.setzSpeed(zSpeed);
        }
    }
    
    @Override
    public void drawModel(LWJGE_Window lwjgeWindow){
        for(Model model: allModels){
            model.drawModel(lwjgeWindow);
        }
    }
    
    private boolean swapDirection = false;
    
    public void runAnimationLoop(){
        if(lastAnimationTime >= 60 * 5){
            lastAnimationTime = 0;
            if(swapDirection){
                walk(20);
            }else{
                walk(-20);
            }
            swapDirection = !swapDirection;
        }else{
            lastAnimationTime++;
        }
    }
    
    private void walk(int distance){
        leftLeg.transformZ(-distance);
        leftLeg.transformY(-distance);
        
        rightLeg.transformZ(distance);
    }
    
    private void jump(int height){
        transformY(height);
    }
    
    private void punch(float rotateAngle, float rotateX, float rotateY, float rotateZ){
        rightArm.rotate(rotateAngle, rotateX, rotateY , rotateZ);
    }
    
    public ArrayList<Model> getAllModels(){
        return allModels; 
    }

    public Model getBody() {
        return body;
    }

    public void setBody(Model body) {
        this.body = body;
    }

    public Model getLeftArm() {
        return leftArm;
    }

    public void setLeftArm(Model leftArm) {
        this.leftArm = leftArm;
    }

    public Model getRightArm() {
        return rightArm;
    }

    public void setRightArm(Model rightArm) {
        this.rightArm = rightArm;
    }

    public Model getRightLeg() {
        return rightLeg;
    }

    public void setRightLeg(Model rightLeg) {
        this.rightLeg = rightLeg;
    }

    public Model getLeftLeg() {
        return leftLeg;
    }

    public void setLeftLeg(Model leftLeg) {
        this.leftLeg = leftLeg;
    }

}// End of class
