/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.fingerip;

/**
 *
 * @author Administrator
 */
public class CoordinateBean {

    private int height = 0;
    private int width = 0;

    CoordinateBean(int ht, int wid) {
        height = ht;
        width = wid;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
