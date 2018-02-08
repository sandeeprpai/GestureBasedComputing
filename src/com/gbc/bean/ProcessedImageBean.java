/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.bean;

import java.awt.Image;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author TF-DD
 */
public class ProcessedImageBean implements Serializable {

    ColorBean redColorBean;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    private Image image = null;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    ColorBean greenColorBean;
    ColorBean blueColorBean;
    int noOfColorsDetected = 0;
    int imageId;
    Date date = null;

    public ColorBean getBlueColorBean() {
        return blueColorBean;
    }

    public void setBlueColorBean(ColorBean blueColorBean) {
        this.blueColorBean = blueColorBean;
    }

    public ColorBean getGreenColorBean() {
        return greenColorBean;
    }

    public void setGreenColorBean(ColorBean greenColorBean) {
        this.greenColorBean = greenColorBean;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getNoOfColorsDetected() {
        return noOfColorsDetected;
    }

    public void setNoOfColorsDetected(int noOfColorsDetected) {
        this.noOfColorsDetected = noOfColorsDetected;
    }

    public ColorBean getRedColorBean() {
        return redColorBean;
    }

    public void setRedColorBean(ColorBean redColorBean) {
        this.redColorBean = redColorBean;
    }

    @Override
    public String toString() {

        String toString = "Detected Colors: ";
        if (redColorBean != null) {
            toString = toString + " Red";
        }
        if (greenColorBean != null) {
            toString = toString + " Green";
        }
        if (blueColorBean != null) {
            toString = toString + " Blue";
        }

        return "ProcessedImageBean{" + toString + '}' + noOfColorsDetected + "," + imageId;
    }
}
