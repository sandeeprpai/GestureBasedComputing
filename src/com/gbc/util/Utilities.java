/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.util;

import com.gbc.bean.RGBBean;
import com.gbc.detector.BlockRGBValueBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Utilities {

    public static void serializeSettingsBean() {
        ObjectOutputStream oos = null;
        try {
            // TODO add your handling code here:
            //        AppConstants.RED_COMPONENT_OF_RED_MAX = ;
            //        AppConstants.GREEN_COMPONENT_OF_RED_MAX = ;
            //        AppConstants.
            File settingsFile = new File(System.getProperty("user.dir") + File.separator + "Settings" + File.separator + "Settings.ser");
            File parentSettings = new File(settingsFile.getParent());
            if (!parentSettings.exists()) {
                parentSettings.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(settingsFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(AppConstants.settingsBean);
            oos.flush();
            fos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();

            } catch (IOException ex) {
                Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void createDirectory() {
        File settingsFile = new File(System.getProperty("user.dir") + File.separator + "Settings");
        if (!settingsFile.exists()) {
            settingsFile.mkdirs();
        }

    }

    public static void loadColorValues(BlockRGBValueBean blockRGBValueBean, String color) {
        // String color = (String) ;
        int redComponentOfTheColorMax = 0;
        int redComponentOfTheColorMin = 0;
        int greenComponentOfTheColorMax = 0;
        int greenComponentOfTheColorMin = 0;
        int blueComponentOfTheColorMax = 0;
        int blueComponentOfTheColorMin = 0;

        int starting = -1;
        int ending = -1;
        for (int i = 0; i < 26; i++) {

            int redData = blockRGBValueBean.getRedCount()[i];
            if (redData > 0 && starting == -1) {
                starting = i * 10;
            }
            if (redData == 0 && ending == -1 && starting != -1) {
                ending = i * 10;
            }
        }
        // System.err.println("RedData length starting:" + starting + "  ending:" + ending);
        redComponentOfTheColorMax = ending;
        redComponentOfTheColorMin = starting;
        //green of red

        starting = -1;
        ending = -1;
        for (int i = 0; i < 26; i++) {

            int redData = blockRGBValueBean.getGreenCount()[i];
            if (redData > 0 && starting == -1) {
                starting = i * 10;
            }
            if (redData == 0 && ending == -1 && starting != -1) {
                ending = i * 10;
            }
        }
        //  System.err.println("RedData length starting:" + starting + "  ending:" + ending);
        greenComponentOfTheColorMax = ending;
        greenComponentOfTheColorMin = starting;

        ////////////////////////////
        starting = -1;
        ending = -1;
        for (int i = 0; i < 26; i++) {
            int redData = blockRGBValueBean.getBlueCount()[i];
            if (redData > 0 && starting == -1) {
                starting = i * 10;
            }
            if (redData == 0 && ending == -1 && starting != -1) {
                ending = i * 10;
            }
        }

        blueComponentOfTheColorMax = ending;
        blueComponentOfTheColorMin = starting;

        if (redComponentOfTheColorMax == -1) {
            redComponentOfTheColorMax = 255;
        }
        if (greenComponentOfTheColorMax == -1) {
            greenComponentOfTheColorMax = 255;
        }
        if (blueComponentOfTheColorMax == -1) {
            blueComponentOfTheColorMax = 255;
        }

        RGBBean colorBean = new RGBBean();
        colorBean.setMaxRed(redComponentOfTheColorMax);
        colorBean.setMaxGreen(greenComponentOfTheColorMax);
        colorBean.setMaxBlue(blueComponentOfTheColorMax);

        colorBean.setMinRed(redComponentOfTheColorMin);
        colorBean.setMinGreen(greenComponentOfTheColorMin);
        colorBean.setMinBlue(blueComponentOfTheColorMin);

        System.err.println("RedData length starting:" + redComponentOfTheColorMax + "  ending:" + redComponentOfTheColorMin);
        System.err.println("green Data length starting:" + greenComponentOfTheColorMax + "  ending:" + greenComponentOfTheColorMin);
        System.err.println("blue Data length starting:" + blueComponentOfTheColorMax + "  ending:" + blueComponentOfTheColorMin);
        if (color.equalsIgnoreCase("Red")) {
            AppConstants.RED_COMPONENT_OF_RED_MAX = redComponentOfTheColorMax;
            AppConstants.RED_COMPONENT_OF_RED_MIN = redComponentOfTheColorMin;

            AppConstants.GREEN_COMPONENT_OF_RED_MAX = greenComponentOfTheColorMax;
            AppConstants.GREEN_COMPONENT_OF_RED_MIN = greenComponentOfTheColorMin;

            AppConstants.BLUE_COMPONENT_OF_RED_MAX = blueComponentOfTheColorMax;
            AppConstants.BLUE_COMPONENT_OF_RED_MIN = blueComponentOfTheColorMin;
            AppConstants.settingsBean.setRedColor(colorBean);
        }
        if (color.equalsIgnoreCase("Green")) {
            AppConstants.RED_COMPONENT_OF_GREEN_MAX = redComponentOfTheColorMax;
            AppConstants.RED_COMPONENT_OF_GREEN_MIN = redComponentOfTheColorMin;

            AppConstants.GREEN_COMPONENT_OF_GREEN_MAX = greenComponentOfTheColorMax;
            AppConstants.GREEN_COMPONENT_OF_GREEN_MIN = greenComponentOfTheColorMin;

            AppConstants.BLUE_COMPONENT_OF_GREEN_MAX = blueComponentOfTheColorMax;
            AppConstants.BLUE_COMPONENT_OF_GREEN_MIN = blueComponentOfTheColorMin;
            AppConstants.settingsBean.setGreenColor(colorBean);
        }
        if (color.equalsIgnoreCase("BLUE")) {
            AppConstants.RED_COMPONENT_OF_BLUE_MAX = redComponentOfTheColorMax;
            AppConstants.RED_COMPONENT_OF_BLUE_MIN = redComponentOfTheColorMin;

            AppConstants.GREEN_COMPONENT_OF_BLUE_MAX = greenComponentOfTheColorMax;
            AppConstants.GREEN_COMPONENT_OF_BLUE_MIN = greenComponentOfTheColorMin;

            AppConstants.BLUE_COMPONENT_OF_BLUE_MAX = blueComponentOfTheColorMax;
            AppConstants.BLUE_COMPONENT_OF_BLUE_MIN = blueComponentOfTheColorMin;
            AppConstants.settingsBean.setBlueColor(colorBean);
        }

    }
}
