/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.fingerip;


import com.gbc.bean.ColorBean;
import com.gbc.util.AppConstants;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;

/**
 *
 * @author Administrator
 */
public class CalculateCoordinate {

    public static boolean isPreviousNull = true;
    public static boolean isPreviousAClick = false;
    public static boolean isPreviousARightClick = false;
    public static boolean isPreviousADoubleClick = false;
    public static int x = 0;
    public static int y = 0;
    public static CoordinateBean prevCoordinate = new CoordinateBean(0, 0);
    public static boolean isContinuousRed = true;

    static void getXY(ColorBean cb, Dimension dim) {
        if (cb != null) {
            if(isContinuousRed) {
            if (cb.getColor().equalsIgnoreCase("Red")) {
                Point point = MouseInfo.getPointerInfo().getLocation();
                x = (int) point.getX();
                y = (int) point.getY();
                
                isContinuousRed = true;
                int effectiveChangeInY = prevCoordinate.getHeight() - cb.getPixelHeight();
                int effectiveChangeInX = prevCoordinate.getWidth() - cb.getStartingPixelWidth();
                int effectiveChangeInXAbs = Math.abs(effectiveChangeInX);
                int effectiveChangeInYAbs = Math.abs(effectiveChangeInY);

                // Write Your code here

                if (effectiveChangeInXAbs > AppConstants.MIN_CHANGE) {
                    x = x + (int) (effectiveChangeInX * AppConstants.MULTYPLYING_FACTOR);
                }
                if (effectiveChangeInYAbs > AppConstants.MIN_CHANGE) {
                    effectiveChangeInY = -effectiveChangeInY;
                    y = y + (int) (effectiveChangeInY * AppConstants.MULTYPLYING_FACTOR);
                }

                if (y > 800) {
                    y = 800;
                }
                if (y > dim.getHeight()) {
                    y = (int) dim.getHeight();
                }
                if (x > dim.getWidth()) {
                    x = (int) dim.getWidth();
                }

                if (y < 0) {
                    y = 0;
                }
                if (x < 0) {
                    x = 0;
                }

            }
            } else {
                
            }
            isPreviousNull = false;
            prevCoordinate.setHeight(cb.getPixelHeight());
            prevCoordinate.setWidth(cb.getStartingPixelWidth());
            isContinuousRed = true;
        } else {
            isPreviousNull = true;
            isPreviousAClick = false;
            isPreviousARightClick = false;
        }
    }
}
