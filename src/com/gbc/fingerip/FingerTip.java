/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.fingerip;

import com.gbc.bean.ColorBean;
import com.gbc.bean.ProcessedImageBean;
import com.gbc.image.process.ProcessImage;
import com.gbc.util.AppConstants;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author prasanth
 */
public class FingerTip implements Runnable {

    @Override
    public void run() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Robot rob = null;
        try {
            rob = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(FingerTip.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            if (AppConstants.isFingerTipOn) {
               // System.out.println("Inside finger tip");
                    System.gc();
                   ProcessedImageBean pib =   ProcessImage.processImage(AppConstants.lastCapturedImage);
                    if (pib != null) {
                        if (pib.getNoOfColorsDetected() > 0) {
                            //  System.out.println("" + colorBeans); C
                            ColorBean rcb = pib.getRedColorBean();
                            ColorBean gcb = pib.getGreenColorBean();
                            ColorBean bcb = pib.getBlueColorBean();
                            if (pib.getNoOfColorsDetected() == 1) {
                                if (rcb != null) {
                                    CalculateCoordinate.getXY(rcb, dim);
                                    rob.mouseMove(CalculateCoordinate.x, CalculateCoordinate.y);
                                } else if (gcb != null) {
                                    if (CalculateCoordinate.isPreviousAClick == false) {
                                        rob.mousePress(InputEvent.BUTTON1_MASK);
                                        rob.mouseRelease(InputEvent.BUTTON1_MASK);
                                        CalculateCoordinate.isPreviousAClick = true;
                                        //for updation
                                        CalculateCoordinate.isContinuousRed = false;
                                        CalculateCoordinate.isPreviousARightClick = false;
                                        //  CalculateCoordinate. = false;

                                    }
                                } else if (bcb != null) {
                                    if (CalculateCoordinate.isPreviousARightClick == false) {
                                        rob.mousePress(InputEvent.BUTTON3_MASK);
                                        rob.mouseRelease(InputEvent.BUTTON3_MASK);
                                        CalculateCoordinate.isPreviousARightClick = true;
                                        CalculateCoordinate.isContinuousRed = false;
                                    }
                                }
                            } else if (pib.getNoOfColorsDetected() == 2) { // code for double click

                                //----------------------------------------------
                                if (gcb != null && rcb != null && !CalculateCoordinate.isPreviousADoubleClick) {
                                    rob.mousePress(InputEvent.BUTTON1_MASK);
                                    rob.mouseRelease(InputEvent.BUTTON1_MASK);
                                    rob.mousePress(InputEvent.BUTTON1_MASK);
                                    rob.mouseRelease(InputEvent.BUTTON1_MASK);
                                    CalculateCoordinate.isPreviousADoubleClick = true;
                                }


                            }
                        } else {
                            CalculateCoordinate.isPreviousNull = true;
                            CalculateCoordinate.isPreviousAClick = false;
                            CalculateCoordinate.isPreviousARightClick = false;
                            CalculateCoordinate.isContinuousRed = false;
                        }
                    }
                    try {
                        if (AppConstants.lastProcessedImage.size() >= 1&& AppConstants.lastProcessedImage.size()<=3) {
                            Thread.sleep(1000);
                        } else {
                            Thread.sleep(AppConstants.delayForEachImageInSecond );
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FingerTip.class.getName()).log(Level.SEVERE, null, ex);
                    }

              //  }
            }
        }
    }
}
