/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.util;

import com.gbc.gui.PreviewFrame;
import java.awt.Color;

/**
 *
 * @author prasanth
 */
public class SetColorPanel implements Runnable {

    PreviewFrame previewFrame = null;

    public SetColorPanel(PreviewFrame previewFrame) {
        this.previewFrame = previewFrame;
    }

    @Override
    public void run() {
        while (!previewFrame.isClosed && AppConstants.lastImage != null) {
//            if (AppConstants.lastProcessedImage.getRedColorBean() != null) {
//                previewFrame.setBackgroundOfRedpanel(Color.red);
//            } else {
//                previewFrame.setBackgroundOfRedpanel(Color.white);
//            }
//            if (AppConstants.lastProcessedImage.getGreenColorBean() != null) {
//                previewFrame.setBackgroundOfGreenpanel(Color.green);
//            } else {
//                previewFrame.setBackgroundOfGreenpanel(Color.white);
//            }
//            if (AppConstants.lastProcessedImage.getBlueColorBean() != null) {
//                  previewFrame.setBackgroundOfBluepanel(Color.blue);
//            } else {
//                  previewFrame.setBackgroundOfBluepanel(Color.white);
//            }
        }
    }
}
