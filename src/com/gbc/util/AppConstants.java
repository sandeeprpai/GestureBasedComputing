/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.util;

import com.gbc.bean.LastCapturedImageBean;
import com.gbc.bean.ProcessedImageBean;
import com.gbc.bean.SettingsBean;
import java.awt.Image;
import java.util.ArrayList;


/**
 *
 * @author TF-DD
 */
public class AppConstants {

    public static int selectedPanel = 0;
    public static long userTypedPattern;
    public static String DEVICE_URL = "vfw:Microsoft WDM Image Capture (Win32):0";
    public static boolean isWebcamDetected = true;
    public static LastCapturedImageBean lastImage = null;
    public static ArrayList<ProcessedImageBean> lastProcessedImage = new ArrayList<ProcessedImageBean>();
    public static int RED_COMPONENT_OF_RED_MAX = 220;
    public static int GREEN_COMPONENT_OF_RED_MAX = 70;
    public static int BLUE_COMPONENT_OF_RED_MAX = 90;
    public static int RED_COMPONENT_OF_RED_MIN = 130;
    public static int GREEN_COMPONENT_OF_RED_MIN = 45;
    public static int BLUE_COMPONENT_OF_RED_MIN = 60;
    public static int RED_COMPONENT_OF_GREEN_MAX = 100;
    public static int GREEN_COMPONENT_OF_GREEN_MAX = 150;
    public static int BLUE_COMPONENT_OF_GREEN_MAX = 125;
    public static int RED_COMPONENT_OF_GREEN_MIN = 70;
    public static int GREEN_COMPONENT_OF_GREEN_MIN = 130;
    public static int BLUE_COMPONENT_OF_GREEN_MIN = 105;
    public static int RED_COMPONENT_OF_BLUE_MAX = 85;
    public static int GREEN_COMPONENT_OF_BLUE_MAX = 155;
    public static int BLUE_COMPONENT_OF_BLUE_MAX = 230;
    public static int RED_COMPONENT_OF_BLUE_MIN = 50;
    public static int GREEN_COMPONENT_OF_BLUE_MIN = 120;
    public static int BLUE_COMPONENT_OF_BLUE_MIN = 165;
    public static int THRESHOLD_COLOR_COUNT = 10;
    public static double MULTYPLYING_FACTOR = 1.5;
    public static int MIN_CHANGE = 5;
    public static SettingsBean settingsBean = new SettingsBean();
    public static int noOfImagesPerSecond = 15;
    public static int delayForEachImageInSecond = 1000 / noOfImagesPerSecond;
    public static int MIN_BUFFER_SIZE = 5;
    public static boolean isFingerTipOn = true;
    public static int processedCount = 0;
    public static int supplied = 0;
    public static long startTime;
    public static long passwordPattern = 123;
    public static boolean isTestDialogClosed = false;
    public static int imageLabelHt;
    public static int imageLabelWd;
    public static boolean isImageViewerOn = false;
    public static int WEBCAM_IMAGE_WIDTH =640;
    public static int MIN_DIST_BW_COLORS = 80;
   public static Image lastCapturedImage;
    public static  int CLIENT_SIDE_SERVER_PORT_FOR_FILE_TRANSFER =60000;
    //public static final int CLIENT_SIDE_SERVER_PORT_FOR_FILE_TRANSFER;
    public static int CHUNK_SIZE= 1024;
        public static String SERVER_IP = "192.168.1.25";
          public static final int SERVER_PORT = 50000;
   public static boolean  isPasting = false;    public static boolean isFileTransferCompleted = false;
      public static String FILE_PATH = "F://Dumps";
    public static  boolean isFileTransferAppsStarted = false;
}
