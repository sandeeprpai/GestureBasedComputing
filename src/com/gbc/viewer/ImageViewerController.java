/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.viewer;

import com.gbc.bean.ProcessedImageBean;
import com.gbc.image.process.ProcessImage;
import com.gbc.util.AppConstants;
import com.gbc.viewer.ImageViewerFrame;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TF-DD
 */
public class ImageViewerController implements Runnable {

    ImageViewerFrame ivf = null;

    public ImageViewerController(ImageViewerFrame ivf) {
        this.ivf = ivf;
    }

    public void run() {
        int redHeight = 0;
        int greenHeight = 0;
        int blueHeight = 0;
        int redWidth = 0;
        int greenWidth = 0;
        int blueWidth = 0;

        boolean isForward = false;
        boolean isReverse = false;
        while (true) {
            if (AppConstants.isImageViewerOn) {
               // System.out.println("Inside image viewer tip");
                ProcessedImageBean pib = ProcessImage.processImage(AppConstants.lastCapturedImage);
                // if (AppConstants.lastProcessedImage.size() > 2) {
//                    for (int j = 0; AppConstants.lastProcessedImage.size() > AppConstants.MIN_BUFFER_SIZE; j++) {
//                        AppConstants.lastProcessedImage.remove(0);
//                    }
                // System.out.println("Consuming from finger tip frame");
                //}
                System.gc();
                // pib = AppConstants.lastProcessedImage.remove(0);
                if (pib != null) {
                    if (pib.getNoOfColorsDetected() > 0) {
                        ///////////////////copied-started
                        if (pib.getNoOfColorsDetected() > 0) {
                            if (pib.getBlueColorBean() != null) {
                                blueHeight = pib.getBlueColorBean().getPixelHeight();
                                blueWidth = pib.getBlueColorBean().getStartingPixelWidth();
                            }
                            if (pib.getGreenColorBean() != null) {
                                greenHeight = pib.getGreenColorBean().getPixelHeight();
                                greenWidth = pib.getGreenColorBean().getStartingPixelWidth();
                            }
                            if (pib.getRedColorBean() != null) {
                                redHeight = pib.getRedColorBean().getPixelHeight();
                                redWidth = pib.getRedColorBean().getStartingPixelWidth();
                            }
                            if (pib.getNoOfColorsDetected() == 1 && pib.getRedColorBean() != null) {

                                if (pib.getRedColorBean().getStartingPixelWidth() > AppConstants.WEBCAM_IMAGE_WIDTH / 2) {
                                    isForward = true;
                                }
                                if (pib.getRedColorBean().getStartingPixelWidth() < AppConstants.WEBCAM_IMAGE_WIDTH / 2) {
                                    isReverse = true;
                                }

                                if (pib.getRedColorBean().getStartingPixelWidth() < 50 && isForward) {
                                    ivf.forwardImage();
                                    System.out.println("Forward");
                                    isForward = false;
                                }

                                if (pib.getRedColorBean().getStartingPixelWidth() > AppConstants.WEBCAM_IMAGE_WIDTH - 50 && isReverse) {
                                    ivf.backwardImage();
                                    System.out.println("Reverse");
                                    isReverse = false;
                                }

                            }
                        }
                        if (pib.getNoOfColorsDetected() == 2 && pib.getBlueColorBean() != null && pib.getGreenColorBean() != null) {
                            //   System.out.print(" Diff1 " + (firstHeight - secondHeight));
                            int effectiveHt = Math.abs(greenHeight - blueHeight)
                                    - AppConstants.MIN_DIST_BW_COLORS;
                            System.out.print("  effective ht :" + effectiveHt);
                            if (effectiveHt > 0) {
                                ivf.showImage(effectiveHt);
                            }
                        }

                        System.out.println("");


                    }
                    ///////////////////copied-ended

                }
                try {
                    if (AppConstants.lastProcessedImage.size() >= 1 && AppConstants.lastProcessedImage.size() <= 3) {
                        Thread.sleep(1000);
                    } else {
                        Thread.sleep(AppConstants.delayForEachImageInSecond);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(ImageViewerController.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        }
    }
}
