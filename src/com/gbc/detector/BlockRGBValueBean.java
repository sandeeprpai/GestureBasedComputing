package com.gbc.detector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Administrator
 */
public class BlockRGBValueBean {
 int[] redCount = new int[26];
        int[] greenCount = new int[26];
        int[] blueCount = new int[26];

    public int[] getBlueCount() {
        return blueCount;
    }

    public void setBlueCount(int[] blueCount) {
        this.blueCount = blueCount;
    }

    public int[] getGreenCount() {
        return greenCount;
    }

    public void setGreenCount(int[] greenCount) {
        this.greenCount = greenCount;
    }

    public int[] getRedCount() {
        return redCount;
    }

    public void setRedCount(int[] redCount) {
        this.redCount = redCount;
    }
        
}
