/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gbc.bean;

/**
 *
 * @author ThinkForce
 */
public class ColorBean {
    private String color = "";
    private int startingPixelWidth = -1;
    private int endingPixelWidth = -1;
    private int pixelHeight = -1;

    public ColorBean() {
    }
    

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getEndingPixelWidth() {
        return endingPixelWidth;
    }

    public void setEndingPixelWidth(int endingPixelWidth) {
        this.endingPixelWidth = endingPixelWidth;
    }

    public int getPixelHeight() {
        return pixelHeight;
    }

    public void setPixelHeight(int pixelHeight) {
        this.pixelHeight = pixelHeight;
    }

    public int getStartingPixelWidth() {
        return startingPixelWidth;
    }

    public void setStartingPixelWidth(int startingPixelWidth) {
        this.startingPixelWidth = startingPixelWidth;
    }

    @Override
    public String toString() {
        return "ColorBean1{" + "color=" + color + '}';
    }


        
}
