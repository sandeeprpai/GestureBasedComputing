/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.gbc.util.*;
import com.gbc.bean.LastCapturedImageBean;
import com.gbc.gui.MainTray;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.TrayIcon.MessageType;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.Buffer;
import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class CaptureImageTest implements Runnable {

    CaptureDeviceInfo captureDeviceInfo = null;
    MediaLocator mediaLocator = null;
    Player player = null;
    //PreviewFrame mainFrame = null;
    MainTray mainTray = null;
    NewJFrame aThis = null;

    CaptureImageTest(NewJFrame aThis) {
        this.aThis = aThis;
    }

    public void run() {
        try {
            try {
                initilizeWebcam();
                //AppConstants.isWebcamDetected = true;
                // mainTray.enableStartMenu(false);
            } catch (NoPlayerException ex) {
                // mainFrame.showException(ex);
                AppConstants.isWebcamDetected = false;
                //  mainTray.enableStartMenu(true);
                //  mainTray.icon.displayMessage("Error", "Cannot find Webcam - " + ex.getMessage(), MessageType.ERROR);
            } catch (IOException ex) {
                //mainFrame.showException(ex);
                AppConstants.isWebcamDetected = false;
                //  mainTray.enableStartMenu(true);
                //  mainTray.icon.displayMessage("Error", "Cannot find Webcam - " + ex.getMessage(), MessageType.ERROR);
            } catch (CannotRealizeException ex) {
                //mainFrame.showException(ex);
                //   AppConstants.isWebcamDetected = false;
                //   mainTray.enableStartMenu(true);
                //   mainTray.icon.displayMessage("Error", "Cannot find Webcam - " + ex.getMessage(), MessageType.ERROR);
            }
            FrameGrabbingControl fgc = null;
            ImageIcon iicon = null;
            // boolean isPreviousNull = true;
            // boolean isPreviousAClick = false;
            // boolean isPreviousARightClick = false;
            // boolean isPreviousADoubleClick = false;
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            Robot rob = new Robot();
            int i = 1;
            Date prevDate = new Date();
            while (AppConstants.isWebcamDetected) {
                //try {
                fgc = (FrameGrabbingControl) player.getControl(
                        "javax.media.control.FrameGrabbingControl");
                while (true) {
                    Buffer buffer = fgc.grabFrame();
                    BufferToImage bti = new BufferToImage((VideoFormat) buffer.getFormat());
                    Image image = bti.createImage(buffer);
                    // AppConstants.lastImage = new LastCapturedImageBean(image, new Random().nextInt(10000));
                    iicon = new ImageIcon(image);
                    aThis.setImage(iicon);
                    System.out.println("" + i++);
                    long currTime = new Date().getTime();
                    if (currTime - prevDate.getTime() > 10000) {
                        System.err.println("" + (currTime - prevDate.getTime()));
                        System.exit(0);
                    }
                }
//                mainFrame.setPic(iicon, image);
//
//                ArrayList<ColorBean1> colorBeans = ProcessImage.processImage(image);
//
//                if (colorBeans.size() > 0) {
//                    System.out.println("" + colorBeans);
//                    if (colorBeans.size() == 1) {
//                        ColorBean1 cb = colorBeans.get(0);
//                        if (cb.getColor().equalsIgnoreCase("Red")) {
//                            CalculateCoordinate.getXY(cb, dim);
//                            rob.mouseMove(CalculateCoordinate.x, CalculateCoordinate.y);
//                        } else if (cb.getColor().equalsIgnoreCase("Green")) {
//                            if (CalculateCoordinate.isPreviousAClick == false) {
//                                rob.mousePress(InputEvent.BUTTON1_MASK);
//                                rob.mouseRelease(InputEvent.BUTTON1_MASK);
//                                CalculateCoordinate.isPreviousAClick = true;
//                                //for updation
//                                CalculateCoordinate.isContinuousRed = false;
//                                CalculateCoordinate.isPreviousARightClick = false;
//                                //  CalculateCoordinate. = false;
//
//                            }
//                        } else if (cb.getColor().equalsIgnoreCase("Blue")) {
//                            if (CalculateCoordinate.isPreviousARightClick == false) {
//                                rob.mousePress(InputEvent.BUTTON3_MASK);
//                                rob.mouseRelease(InputEvent.BUTTON3_MASK);
//                                CalculateCoordinate.isPreviousARightClick = true;
//                                CalculateCoordinate.isContinuousRed = false;
//                               // CalculateCoordinate.isPreviousARightClick = false;
//                            }
//                        }
//                    } else if (colorBeans.size() == 2) { // code for double click
//                        ColorBean1 greenColorBean = null;
//                        ColorBean1 redColorBean = null;
//                        if (colorBeans.get(0).getColor().equalsIgnoreCase("Red")) {
//                            redColorBean = colorBeans.get(0);
//                        }
//                        if (colorBeans.get(0).getColor().equalsIgnoreCase("Green")) {
//                            greenColorBean = colorBeans.get(0);
//                        }
//                        //----------------------------------------------
//                        if (colorBeans.get(1).getColor().equalsIgnoreCase("Red")) {
//                            redColorBean = colorBeans.get(1);
//                        }
//                        if (colorBeans.get(1).getColor().equalsIgnoreCase("Green")) {
//                            greenColorBean = colorBeans.get(1);
//                        }
//                        //----------------------------------------------
//                        if (greenColorBean != null && redColorBean != null && !CalculateCoordinate.isPreviousADoubleClick) {
//                            rob.mousePress(InputEvent.BUTTON1_MASK);
//                            rob.mouseRelease(InputEvent.BUTTON1_MASK);
//                            rob.mousePress(InputEvent.BUTTON1_MASK);
//                            rob.mouseRelease(InputEvent.BUTTON1_MASK);
//                            CalculateCoordinate.isPreviousARightClick = true;
//                        }
//
//
//                    }
//                } else {
//                    CalculateCoordinate.isPreviousNull = true;
//                    CalculateCoordinate.isPreviousAClick = false;
//                    CalculateCoordinate.isPreviousARightClick = false;
//                    CalculateCoordinate.isContinuousRed = false;
//                }
//                //                } catch (IOException ex) {
//                //                    Logger.getLogger(Final.class.getName()).log(Level.SEVERE, null, ex);
//                //                }
//                //                } catch (IOException ex) {
//                //                    Logger.getLogger(Final.class.getName()).log(Level.SEVERE, null, ex);
//                //                }
                // AppConstants.lastImage = new LastCapturedImageBean(image, new Random().nextInt(10000));
            }
        } catch (AWTException ex) {
            Logger.getLogger(CaptureImageTest.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    private void initilizeWebcam() throws NoPlayerException, IOException, CannotRealizeException {
        //String str = "vfw:Microsoft WDM Image Capture (Win32):1";
        String str = "vfw:Microsoft WDM Image Capture (Win32):0";
        captureDeviceInfo = CaptureDeviceManager.getDevice(AppConstants.DEVICE_URL);
        mediaLocator = captureDeviceInfo.getLocator();
        player = Manager.createRealizedPlayer(mediaLocator);
        player.start();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CaptureImageTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
