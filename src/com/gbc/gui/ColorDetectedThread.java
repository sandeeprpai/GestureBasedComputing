/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.gui;

import com.gbc.detector.ColorDetectorFrame;

/**
 *
 * @author TF-DD
 */
public class ColorDetectedThread implements Runnable{
LoginInterfaceDialog lid = null;
    ColorDetectedThread(LoginInterfaceDialog aThis) {
        lid = aThis;
    }

    @Override
    public void run() {
        ColorDetectorFrame colorDetectorFrame = new ColorDetectorFrame(lid,true);
        colorDetectorFrame.setVisible(true);
    }
    
}
