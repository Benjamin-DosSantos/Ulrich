package com.blackpensoftware.entites;

import com.blackpensoftware.models.Model;
import com.blackpensoftware.models.ModelLoader;
import com.blackpensoftware.primitives.VectorPoint;

import java.util.ArrayList;

/**
 * Created by Benjamin DosSantos Jr. on 1/25/2017.
 */
public class Player extends Model{
    ModelLoader modelLoader = new ModelLoader();
    ArrayList<Model> allModels = new ArrayList<>();
    
    Model body = modelLoader.loadFileToModel("player_body.txt");
    Model leftArm = modelLoader.loadFileToModel("player_arm_left.txt");
    Model rightArm = modelLoader.loadFileToModel("player_arm_right.txt");
    Model rightLeg = modelLoader.loadFileToModel("player_leg_right.txt");
    Model leftLeg = modelLoader.loadFileToModel("player_leg_left.txt");
    
    public Player(){
        prepareModels();
    }// End of constructor
    
    public void prepareModels(){
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
