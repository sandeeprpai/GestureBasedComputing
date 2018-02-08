package com.gbc.detector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.gbc.bean.ProcessedImageBean;
import com.gbc.detector.ColorDetectorFrame;
import com.gbc.util.AppConstants;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.RasterFormatException;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
 * @author staff
 */
public class ImageSetting implements Runnable {

    ColorDetectorFrame parent = null;
    FrameGrabbingControl fgc = null;
    Player player = null;

    public ImageSetting(ColorDetectorFrame parent) {
        this.parent = parent;
    }

    public void capture() {


        new Thread(this).start();

    }

    public void saveJPEG(Image image, String filename) throws IOException {
        /*   FileOutputStream fos = null;
        try {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        bufferedImage.createGraphics().drawImage(image, null, null);
        fos = new FileOutputStream(new File(filename));
        
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
        param.setQuality(.7f, false);
        encoder.setJPEGEncodeParam(param);
        try {
        encoder.encode(bufferedImage);
        } catch (IOException ex) {
        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ImageFormatException ex) {
        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (FileNotFoundException ex) {
        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        fos.close();
        }
         */
        ImageIO.write(toBufferedImage(image), "jpg", new File(filename));

    }

    public void run() {
        //   while (true) {

        //ImageIcon icon = new ImageIcon(AppConstants.lastCapturedImage);
        //player.stop();
        if (AppConstants.lastCapturedImage!= null) {
           // ProcessedImageBean pib = AppConstants.lastCapturedImage;

            Image im =  AppConstants.lastCapturedImage;
            //.getImage();
            ImageIcon ic = new ImageIcon(im);
            parent.setPic(ic, im);
        } else {
            System.out.println("Last captured image is null");
        }
        // player.stop();

        // }
    }

    RGB getRGB(int x, int y, Image image) {
        if (image != null) {
            try {
                PixelGrabber pixcellGraber = new PixelGrabber(image, x, y, 1, 1, true);
                int[] pixcellBuffer = new int[pixcellGraber.getWidth() * pixcellGraber.getHeight()];
                if (pixcellGraber.grabPixels()) {
                    pixcellBuffer = (int[]) pixcellGraber.getPixels();
                }

                int red = 0;
                int green = 0;
                int blue = 0;

                red = (pixcellBuffer[0] >> 16) & 0xff;
                green = (pixcellBuffer[0] >> 8) & 0xff;
                blue = (pixcellBuffer[0] & 0xff);
                RGB rgb = new RGB();
                rgb.setRed(red);
                rgb.setGreen(green);
                rgb.setBlue(blue);
                return rgb;
            } catch (InterruptedException ex) {
                Logger.getLogger(ImageSetting.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        return null;
    }

    Image getNewImage(Image image, int firstPosX, int firstPosY, int lastPosX, int lastPosY) {
        int tempX;
        int tempY;
        if (firstPosY > lastPosY) {
            tempX = firstPosX;
            tempY = firstPosY;

            firstPosX = lastPosX;
            firstPosY = lastPosY;

            lastPosX = tempX;
            lastPosY = tempY;

        }
        //    Rectangle r = new Rectangle(firstPosX, firstPosY, lastPosX - firstPosX, lastPosY - firstPosY);
        int width = Math.abs(lastPosX - firstPosX);
        int height = Math.abs(lastPosY - firstPosY);

        image.getGraphics().drawRect(firstPosX, firstPosY, width, height);
        return image;
    }

    Image cloneThisImage(Image image) {
        Image newImage = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(new ImageIcon(image));
            oos.flush();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            newImage = ((ImageIcon) ois.readObject()).getImage();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ImageSetting.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageSetting.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newImage;


    }

    Image newCloneThisImage(Image image) {

        ColorModel cm = toBufferedImage(image).getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = toBufferedImage(image).copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public static BufferedImage toBufferedImage(Image image) {
          try {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        // This code ensures that all the pixels in the image are loaded
        //image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels; for this method's
        // implementation, see Determining If an Image Has Transparent Pixels
        boolean hasAlpha = true;

        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
            if (hasAlpha) {
                transparency = Transparency.BITMASK;
            }

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                    image.getWidth(null), image.getHeight(null), transparency);
       

        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            if (hasAlpha) {
                type = BufferedImage.TYPE_INT_ARGB;
            }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
         } catch (HeadlessException e) {
            // The system does not have a screen
             return null;
        } catch (NullPointerException e) {
            System.out.println("No Image to display");
            return null;
        } catch (Exception e) {
            System.out.println("Image Settings");
            return null;
        }

    }

    public static boolean hasAlpha(Image image) {
        // If buffered image, the color model is readily available
        if (image instanceof BufferedImage) {
            BufferedImage bimage = (BufferedImage) image;
            return bimage.getColorModel().hasAlpha();
        }

        // Use a pixel grabber to retrieve the image's color model;
        // grabbing a single pixel is usually sufficient
        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
        }

        // Get the image's color model
        ColorModel cm = pg.getColorModel();
        return cm.hasAlpha();
    }

    Image getImageFromFile() {
        Image image = null;

        // Read from a file
        File file = new File("image.jpg");
        if (file.exists()) {
            try {
                image = ImageIO.read(file);
            } catch (IOException ex) {
                Logger.getLogger(ImageSetting.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return image;
    }

    Image clipImage(Image image, int firstPosX, int firstPosY, int lastPosX, int lastPosY) {
        PixelGrabber pixcellGraber = new PixelGrabber(image, firstPosX, firstPosY, 100, 100, true);
        System.out.println("pixel grabber " + pixcellGraber.getHeight() + " wd" + pixcellGraber.getWidth());
        int[] pixcellBuffer = new int[pixcellGraber.getWidth() * pixcellGraber.getHeight()];
        Image newImage = newGetImageFromArray(pixcellBuffer, 100, 100);
        return newImage;



    }

    public Image getImageFromArray(int[] pixels, int width, int height) {
        MemoryImageSource mis = new MemoryImageSource(width, height, pixels, 0, width);
        Toolkit tk = Toolkit.getDefaultToolkit();
        return tk.createImage(mis);
    }

    public static Image newGetImageFromArray(int[] pixels, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = (WritableRaster) image.getData();
        //   raster.setPixels(0,0,width,height,pixels);
        raster.setPixels(0, 0, 100, 100, pixels);
        return image;
    }

    Image sliceImage(Image image, int firstPosX, int firstPosY, int lastPosX, int lastPosY) {
        //BufferedImage dst = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

        // Graphics2D g = dst.createGraphics();
        //  System.out.println(" draw image status: " + g.drawImage(image, 0, 0, 10, 10, null));
        // g.
        //g.dispose();
        //image.getGraphics().clipRect(100, 100, 100, 100);
        int tempX;
        int tempY;
        if (firstPosY > lastPosY) {
            tempX = firstPosX;
            tempY = firstPosY;

            firstPosX = lastPosX;
            firstPosY = lastPosY;

            lastPosX = tempX;
            lastPosY = tempY;

        }
        //    Rectangle r = new Rectangle(firstPosX, firstPosY, lastPosX - firstPosX, lastPosY - firstPosY);
        int width = Math.abs(lastPosX - firstPosX);
        int height = Math.abs(lastPosY - firstPosY);
        BufferedImage bi = null;
        try {
            bi = cropMyImage(toBufferedImage(image), width, height, firstPosX, firstPosY);
        } catch (Exception ex) {
            Logger.getLogger(ImageSetting.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bi;

    }

    public static BufferedImage cropMyImage(BufferedImage img, int cropWidth,
            int cropHeight, int cropStartX, int cropStartY) throws Exception {
        BufferedImage clipped = null;
        Dimension size = new Dimension(cropWidth, cropHeight);
        Rectangle clip = null;
        clip = createClip(img, size, cropStartX, cropStartY, clip);

        try {
            int w = clip.width;
            int h = clip.height;

            System.out.println("Crop Width " + w);
            System.out.println("Crop Height " + h);
            System.out.println("Crop Location " + "(" + clip.x + "," + clip.y
                    + ")");

            clipped = img.getSubimage(clip.x, clip.y, w, h);

            System.out.println("Image Cropped. New Image Dimension: "
                    + clipped.getWidth() + "w X " + clipped.getHeight() + "h");
        } catch (RasterFormatException rfe) {
            System.out.println("Raster format error: " + rfe.getMessage());
            return null;
        }
        return clipped;
    }

    private static Rectangle createClip(BufferedImage img, Dimension size,
            int clipX, int clipY, Rectangle clip) throws Exception {
        /**
         * Some times clip area might lie outside the original image,
         * fully or partially. In such cases, this program will adjust
         * the crop area to fit within the original image.
         *
         * isClipAreaAdjusted flas is usded to denote if there was any
         * adjustment made.
         */
        boolean isClipAreaAdjusted = false;


        /**Checking for negative X Co-ordinate**/
        if (clipX < 0) {
            clipX = 0;
            isClipAreaAdjusted = true;
        }
        /**Checking for negative Y Co-ordinate**/
        if (clipY < 0) {
            clipY = 0;
            isClipAreaAdjusted = true;
        }

        /**Checking if the clip area lies outside the rectangle**/
        if ((size.width + clipX) <= img.getWidth()
                && (size.height + clipY) <= img.getHeight()) {

            /**
             * Setting up a clip rectangle when clip area
             * lies within the image.
             */
            clip = new Rectangle(size);
            clip.x = clipX;
            clip.y = clipY;
        } else {

            /**
             * Checking if the width of the clip area lies outside the image.
             * If so, making the image width boundary as the clip width.
             */
            if ((size.width + clipX) > img.getWidth()) {
                size.width = img.getWidth() - clipX;
            }

            /**
             * Checking if the height of the clip area lies outside the image.
             * If so, making the image height boundary as the clip height.
             */
            if ((size.height + clipY) > img.getHeight()) {
                size.height = img.getHeight() - clipY;
            }

            /**Setting up the clip are based on our clip area size adjustment**/
            clip = new Rectangle(size);
            clip.x = clipX;
            clip.y = clipY;

            isClipAreaAdjusted = true;

        }
        if (isClipAreaAdjusted) {
            System.out.println("Crop Area Lied Outside The Image."
                    + " Adjusted The Clip Rectangle\n");
        }
        return clip;
    }

    BlockRGBValueBean getRGBComponentsOfSlicedImage(Image testImage) {
        int[] redCount = new int[26];
        int[] greenCount = new int[26];
        int[] blueCount = new int[26];
        try {
            PixelGrabber pixcellGraber = new PixelGrabber(testImage, 1, 1, testImage.getWidth(null) - 1, testImage.getHeight(null) - 1, true);
            int[] pixcellBuffer = new int[pixcellGraber.getWidth() * pixcellGraber.getHeight()];
            if (pixcellGraber.grabPixels()) {
                pixcellBuffer = (int[]) pixcellGraber.getPixels();
            }
            int red = 0;
            int green = 0;
            int blue = 0;
            int range = 0;
            for (int i = 0; i < pixcellBuffer.length; i++) {
                red = (pixcellBuffer[i] >> 16) & 0xff;
                green = (pixcellBuffer[i] >> 8) & 0xff;
                blue = (pixcellBuffer[i] & 0xff);

                range = (red / 10);
                redCount[range]++;

                range = (green / 10);
                greenCount[range]++;

                range = (blue / 10);
                blueCount[range]++;
            }
            BlockRGBValueBean blockRGBValueBean = new BlockRGBValueBean();
            blockRGBValueBean.setBlueCount(blueCount);
            blockRGBValueBean.setGreenCount(greenCount);
            blockRGBValueBean.setRedCount(redCount);
            return blockRGBValueBean;
        } catch (InterruptedException ex) {
            Logger.getLogger(ImageSetting.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
