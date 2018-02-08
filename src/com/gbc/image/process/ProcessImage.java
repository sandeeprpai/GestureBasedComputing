/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.image.process;

import com.gbc.bean.ColorBean;
import com.gbc.bean.ProcessedImageBean;
import com.gbc.util.AppConstants;
import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author TF-PRG
 */
public class ProcessImage implements Runnable {

    Image image = null;

    public static void main(String[] args) {
        try {
            File file = new File("35.jpg");
            Image image = ImageIO.read(file);
            // processImage(image);

        } catch (IOException ex) {
            Logger.getLogger(ProcessImage.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public ProcessImage(Image image) {
        this.image = image;
    }

    public static ProcessedImageBean processImage(Image image) {
        if (image != null) {
            try {
                //BufferedImage bImage = toBufferedImage(image);
                PixelGrabber pg = new PixelGrabber(image, 0, 0, image.getWidth(null), image.getHeight(null), true);
                int[] nextImgData = null;
                int currentPixelWidth = 0;
                int previousRedPixelWidth = -1;
                int previousGreenPixelWidth = -1;
                int previousBluePixelWidth = -1;
                int currentpixelHeight = 0;
                int previousRedPixelHeight = -1;
                int previousGreenPixelHeight = -1;
                int previousBluePixelHeight = -1;
                int redPixelCount = 0;
                int greenPixelCount = 0;
                int bluePixelCount = 0;
                int width = 0;
                int height = 0;
                ColorBean redColorBean = null;
                ColorBean greenColorBean = null;
                ColorBean blueColorBean = null;
                ArrayList<ColorBean> colorBeans = new ArrayList<ColorBean>();

                if (pg.grabPixels()) {
                    width = pg.getWidth();
                    height = pg.getHeight();
                    nextImgData = new int[width * height];
                    nextImgData = (int[]) pg.getPixels();
                }
                int i;

                int red = 0;
                int green = 0;
                int blue = 0;
                ProcessedImageBean processedImageBean = new ProcessedImageBean();
                // System.out.println("b4 processing:" + processedImageBean);
                for (i = 0; i < (width * height); i++) {

                    red = (nextImgData[i] >> 16) & 0xff;
                    green = (nextImgData[i] >> 8) & 0xff;
                    blue = (nextImgData[i] & 0xff);
                    //Detects Red
                    //System.out.println("" + AppConstants.RED_COMPONENT_OF_RED_MAX);
                    if (red <= AppConstants.RED_COMPONENT_OF_RED_MAX && red >= AppConstants.RED_COMPONENT_OF_RED_MIN
                            && green <= AppConstants.GREEN_COMPONENT_OF_RED_MAX && green >= AppConstants.GREEN_COMPONENT_OF_RED_MIN
                            && blue <= AppConstants.BLUE_COMPONENT_OF_RED_MAX && blue >= AppConstants.BLUE_COMPONENT_OF_RED_MIN
                            && redColorBean == null) {
//                    int rand= new Random().nextInt(6);
//                    System.out.println("Random value" + rand);
//                    if(rand==1) {
//                        System.err.println("Red:Max:" + AppConstants.RED_COMPONENT_OF_RED_MAX + " Min:"+AppConstants.RED_COMPONENT_OF_RED_MIN+":" 
//                               + red);
//                        System.err.println("Green:Max:" + AppConstants.GREEN_COMPONENT_OF_RED_MAX + " Min:"+AppConstants.GREEN_COMPONENT_OF_RED_MIN+":" 
//                               + red);
//                        System.err.println("Blue:Max:" + AppConstants.BLUE_COMPONENT_OF_RED_MAX+ " Min:"+AppConstants.BLUE_COMPONENT_OF_RED_MIN+":" 
//                               + red);
//                    }
                        greenPixelCount = 0;
                        bluePixelCount = 0;
                        currentpixelHeight = i / width;
                        currentPixelWidth = i % width;
                        if (currentpixelHeight != previousRedPixelHeight) {
                            redPixelCount = 1;
                        } else if (currentPixelWidth == previousRedPixelWidth + 1) {
                            redPixelCount++;
                        } else {
                            redPixelCount = 1;
                        }

                        if (redPixelCount >= AppConstants.THRESHOLD_COLOR_COUNT) {
                            redColorBean = new ColorBean();
                            redColorBean.setColor("RED");
                            redColorBean.setStartingPixelWidth((currentPixelWidth
                                    - AppConstants.THRESHOLD_COLOR_COUNT) + 1);
                            redColorBean.setEndingPixelWidth(currentPixelWidth);
                            redColorBean.setPixelHeight(currentpixelHeight);
                            processedImageBean.setRedColorBean(redColorBean);

                            colorBeans.add(redColorBean);
                            //   return colorBeans;
                        }

                        previousRedPixelHeight = currentpixelHeight;
                        previousRedPixelWidth = currentPixelWidth;


                    } else if (red >= AppConstants.RED_COMPONENT_OF_GREEN_MIN && red <= AppConstants.RED_COMPONENT_OF_GREEN_MAX
                            && green >= AppConstants.GREEN_COMPONENT_OF_GREEN_MIN && green <= AppConstants.GREEN_COMPONENT_OF_GREEN_MAX
                            && blue >= AppConstants.BLUE_COMPONENT_OF_GREEN_MIN && blue <= AppConstants.BLUE_COMPONENT_OF_GREEN_MAX
                            && greenColorBean == null) {
                        //Detects black
                        redPixelCount = 0;
                        bluePixelCount = 0;
                        currentpixelHeight = i / width;
                        currentPixelWidth = i % width;
                        if (currentpixelHeight != previousGreenPixelHeight) {
                            greenPixelCount = 1;
                        } else if (currentPixelWidth == previousGreenPixelWidth + 1) {
                            greenPixelCount++;
                        } else {
                            greenPixelCount = 1;
                        }

                        if (greenPixelCount >= AppConstants.THRESHOLD_COLOR_COUNT) {
                            greenColorBean = new ColorBean();
                            greenColorBean.setColor("GREEN");
                            greenColorBean.setStartingPixelWidth((currentPixelWidth
                                    - AppConstants.THRESHOLD_COLOR_COUNT) + 1);
                            greenColorBean.setEndingPixelWidth(currentPixelWidth);
                            greenColorBean.setPixelHeight(currentpixelHeight);

                            //ArrayList<ColorBean1> colorBeans = new ArrayList<ColorBean1>();
                            colorBeans.add(greenColorBean);
                            //return colorBeans;
                            processedImageBean.setGreenColorBean(greenColorBean);
                        }

                        previousGreenPixelHeight = currentpixelHeight;
                        previousGreenPixelWidth = currentPixelWidth;
                        ////////////////
                    } else if (red >= AppConstants.RED_COMPONENT_OF_BLUE_MIN && red <= AppConstants.RED_COMPONENT_OF_BLUE_MAX
                            && green >= AppConstants.GREEN_COMPONENT_OF_BLUE_MIN && green <= AppConstants.GREEN_COMPONENT_OF_BLUE_MAX
                            && blue >= AppConstants.BLUE_COMPONENT_OF_BLUE_MIN && blue <= AppConstants.BLUE_COMPONENT_OF_BLUE_MAX
                            && blueColorBean == null) {
                        //Detects black
                        redPixelCount = 0;
                        greenPixelCount = 0;
                        currentpixelHeight = i / width;
                        currentPixelWidth = i % width;
                        if (currentpixelHeight != previousBluePixelHeight) {
                            bluePixelCount = 1;
                        } else if (currentPixelWidth == previousBluePixelWidth + 1) {
                            bluePixelCount++;
                        } else {
                            bluePixelCount = 1;
                        }

                        if (bluePixelCount >= AppConstants.THRESHOLD_COLOR_COUNT) {
                            blueColorBean = new ColorBean();
                            //System.out.println(" continous Blue Detected");
                            blueColorBean.setColor("BLUE");
                            blueColorBean.setStartingPixelWidth((currentPixelWidth
                                    - AppConstants.THRESHOLD_COLOR_COUNT) + 1);
                            blueColorBean.setEndingPixelWidth(currentPixelWidth);
                            blueColorBean.setPixelHeight(currentpixelHeight);

                            // ArrayList<ColorBean1> colorBeans = new ArrayList<ColorBean1>();
                            colorBeans.add(blueColorBean);
                            processedImageBean.setBlueColorBean(blueColorBean);
                            //return colorBeans;
                        }

                        previousBluePixelHeight = currentpixelHeight;
                        previousBluePixelWidth = currentPixelWidth;
                        ////////////////
                    } /*else if (red >= AppConstants.RED_COMPONENT_OF_BLUE_MIN && red <= AppConstants.RED_COMPONENT_OF_BLUE_MAX
                    && green >= AppConstants.GREEN_COMPONENT_OF_BLUE_MIN && green <= AppConstants.GREEN_COMPONENT_OF_BLUE_MAX
                    && blue >= AppConstants.BLUE_COMPONENT_OF_BLUE_MIN && blue <= AppConstants.BLUE_COMPONENT_OF_BLUE_MAX
                    && blueColorBean == null) {
                    //Detects black
                    redPixelCount = 0;
                    currentpixelHeight = i / width;
                    currentPixelWidth = i % width;
                    if (currentpixelHeight != previousGreenPixelHeight) {
                    greenPixelCount = 1;
                    } else if (currentPixelWidth == previousGreenPixelWidth + 1) {
                    greenPixelCount++;
                    } else {
                    greenPixelCount = 1;
                    }
                    
                    if (greenPixelCount >= AppConstants.THRESHOLD_COLOR_COUNT) {
                    greenColorBean = new ColorBean1();
                    greenColorBean.setColor("GREEN");
                    greenColorBean.setStartingPixelWidth((currentPixelWidth
                    - AppConstants.THRESHOLD_COLOR_COUNT) + 1);
                    greenColorBean.setEndingPixelWidth(currentPixelWidth);
                    greenColorBean.setPixelHeight(currentpixelHeight);
                    
                    // ArrayList<ColorBean1> colorBeans = new ArrayList<ColorBean1>();
                    colorBeans.add(greenColorBean);
                    //return colorBeans;
                    }
                    
                    previousGreenPixelHeight = currentpixelHeight;
                    previousGreenPixelWidth = currentPixelWidth;
                    ////////////////
                    }*/
                }

                // ArrayList<ColorBean1> colorBeans = new ArrayList<ColorBean1>();
                processedImageBean.setImageId(10);
                processedImageBean.setNoOfColorsDetected(colorBeans.size());
                processedImageBean.setDate(new Date());
                processedImageBean.setImage(image);

                //  System.out.println("after processing:" + processedImageBean);
                return processedImageBean;
                //return null;

            } catch (InterruptedException ex) {
                //Logger.getLogger(Finger.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            //return null;
        } else {
            return null;
        }
    }

    @Override
    public void run() {
//        ProcessedImageBean processImage = processImage(10);
//        if (AppConstants.lastProcessedImage.size() > 15) {
//            AppConstants.lastProcessedImage.remove(0);
////            if (AppConstants.delayForEachImageInSecond > 20) {
////                AppConstants.delayForEachImageInSecond = AppConstants.delayForEachImageInSecond - 5;
////            }
//        }
//        System.gc();
//        AppConstants.lastProcessedImage.add(processImage);
//        System.out.println("frm process image:" + AppConstants.lastProcessedImage.size() + " delay " + AppConstants.delayForEachImageInSecond);
//        AppConstants.processedCount++;
//
////        if(AppConstants.lastProcessedImage.size() <5) {
////            AppConstants.delayForEachImageInSecond++;
////        } else if(AppConstants.lastProcessedImage.size()>10) {
////            AppConstants.delayForEachImageInSecond--;
////        }
//
////        System.out.println("Entered into Image Processing");
////        int prevImageId = 0;
////        while (true) {
////            if (AppConstants.lastImage != null) {
////                int currentImageId = AppConstants.lastImage.getId();
////                if (prevImageId != currentImageId) {
////                    Image image = AppConstants.lastImage.getImage();
////                    int imageId = AppConstants.lastImage.getId();
////                    AppConstants.lastProcessedImage = processImage(image, imageId);
////                    prevImageId = imageId;
////                   // System.out.println("processing" + imageId + "  " + AppConstants.lastProcessedImage);
////                }
////            }
////        }
    }
}
