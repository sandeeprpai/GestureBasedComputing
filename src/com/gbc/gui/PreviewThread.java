/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.gui;

/**
 *
 * @author TF-DD
 */
public class PreviewThread implements Runnable{
 PreviewFrame previewFrame = null;
    PreviewThread(LoginInterfaceDialog aThis) {
       previewFrame =   new PreviewFrame(aThis, true);
    }

    @Override
    public void run() {       
        previewFrame.setVisible(true);
    }
    
}
