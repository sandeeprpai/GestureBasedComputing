/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.bean;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class SettingsBean implements Serializable {

    RGBBean redColor;
    RGBBean greenColor;
    RGBBean blueColor;

    public RGBBean getBlueColor() {
        return blueColor;
    }

    public void setBlueColor(RGBBean blueColor) {
        this.blueColor = blueColor;
    }

    public RGBBean getGreenColor() {
        return greenColor;
    }

    public void setGreenColor(RGBBean greenColor) {
        this.greenColor = greenColor;
    }

    public RGBBean getRedColor() {
        return redColor;
    }

    public void setRedColor(RGBBean redColor) {
        this.redColor = redColor;
    }

    
}
