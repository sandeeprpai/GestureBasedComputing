/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.gui;

import com.gbc.util.AppConstants;
import java.awt.Frame;

/**
 *
 * @author TF-DD
 */
public class ColorSettingsThread implements Runnable {
Frame lid;
    ColorSettingsThread(Frame aThis) {
        lid =aThis;
    }

    @Override
    public void run() {
        new ColorSettingsDialog(lid,true,AppConstants.settingsBean).setVisible(true);
    }
    
}
