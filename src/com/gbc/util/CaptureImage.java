/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.util;

import com.gbc.bean.LastCapturedImageBean;
import com.gbc.bean.ProcessedImageBean;
import com.gbc.fingerip.FingerTip;
import com.gbc.gui.MainTray;
import com.gbc.image.process.ProcessImage;
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
public class CaptureImage implements Runnable {

    CaptureDeviceInfo captureDeviceInfo = null;
    MediaLocator mediaLocator = null;
    Player player = null;
    //PreviewFrame mainFrame = null;
    MainTray mainTray = null;

    public CaptureImage(MainTray mainTray) {
        this.mainTray = mainTray;
        //mainFrame = aThis;
    }

    public void run() {
        try {
            try {
                initilizeWebcam();
                AppConstants.isWebcamDetected = true;
                mainTray.enableStartMenu(false);
                new Thread(new FingerTip()).start();
            } catch (NoPlayerException ex) {
                // mainFrame.showException(ex);
                AppConstants.isWebcamDetected = false;
                mainTray.enableStartMenu(true);
                mainTray.enableLogin(false);
                mainTray.icon.displayMessage("Error", "Cannot find Webcam - " + ex.getMessage(), MessageType.ERROR);
            } catch (IOException ex) {
                //mainFrame.showException(ex);
                AppConstants.isWebcamDetected = false;
                mainTray.enableStartMenu(true);
                mainTray.enableLogin(false);
                mainTray.icon.displayMessage("Error", "Cannot find Webcam - " + ex.getMessage(), MessageType.ERROR);
            } catch (CannotRealizeException ex) {
                //mainFrame.showException(ex);
                AppConstants.isWebcamDetected = false;
                mainTray.enableStartMenu(true);
                mainTray.enableLogin(false);
                mainTray.icon.displayMessage("Error", "Cannot find Webcam - " + ex.getMessage(), MessageType.ERROR);
            }
            FrameGrabbingControl fgc = null;
            ImageIcon iicon = null;
            // boolean isPreviousNull = true;
            // boolean isPreviousAClick = false;
            // boolean isPreviousARightClick = false;
            // boolean isPreviousADoubleClick = false;
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            Robot rob = new Robot();
            Buffer buffer = null;
            BufferToImage bti = null;
            Image image = null;
            fgc = (FrameGrabbingControl) player.getControl(
                    "javax.media.control.FrameGrabbingControl");
            // int i = 1;
            buffer = fgc.grabFrame();
            VideoFormat vf = (VideoFormat) buffer.getFormat();
            System.out.println("" + buffer.getFormat());
            AppConstants.startTime = new Date().getTime();
            while (AppConstants.isWebcamDetected) {               //try {
                buffer = fgc.grabFrame();
                //System.out.println("" + buffer.getFormat());
                // System.out.println("" + buffer.getFormat());
                bti = new BufferToImage(vf);
                image = bti.createImage(buffer);
                AppConstants.lastCapturedImage = image;
                //new Thread(new ProcessImage(image)).start();
                AppConstants.supplied++;

            }
        } catch (AWTException ex) {
            Logger.getLogger(CaptureImage.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CaptureImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
