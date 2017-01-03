package com.blackpensoftware.drawing;

import org.lwjgl.opengl.GL11;

/**
 * Created by Benjamin DosSantos Jr. on 1/2/17.
 * Copyright Black Pen Software (c) All Rights Reserved
 */
public class LightingHandler {
    public void defaultLightingSetup(){
        GL11.glLightModeli(GL11.GL_LIGHT_MODEL_LOCAL_VIEWER, GL11.GL_TRUE);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_LIGHT0);

        float[] ambiantLight = {0.2f, 0.2f, 0.2f, 1.0f};
        float[] diffuseLight = {0.8f, 0.8f, 0.8f, 1.0f};
        float[] specularLight = {0.4f, 0.4f, 0.4f, 1.0f};

        GL11.glLightfv(GL11.GL_LIGHT0, GL11.GL_AMBIENT, ambiantLight);
        GL11.glLightfv(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, diffuseLight);
        GL11.glLightfv(GL11.GL_LIGHT0, GL11.GL_SPECULAR, specularLight);

        float[] lightPosition = {1000, 1000, 1000, 1.0f};
        GL11.glLightfv(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosition);

        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
    }
}// End of class
