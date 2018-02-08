/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.bean;

import java.awt.Image;

/**
 *
 * @author TF-DD
 */
public class LastCapturedImageBean {

    private int id;
    private Image image = null;

    public LastCapturedImageBean(Image image, int id) {
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
