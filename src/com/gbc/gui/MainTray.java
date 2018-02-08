/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.gui;

import com.gbc.bean.RGBBean;
import com.gbc.bean.SettingsBean;
import com.gbc.detector.ColorDetectorFrame;
import com.gbc.image.process.ProcessImage;
import com.gbc.util.AppConstants;
import com.gbc.util.CaptureImage;
import com.gbc.util.Utilities;
import com.gbc.viewer.ImageViewerFrame;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Administrator
 */
public class MainTray implements ActionListener {

    PopupMenu popupMenu = null;
//    static MainFrame mainFrame = null;
//    static PreviewFrame newMainFrame = null;
//    Settings settings = null;
    MenuItem login = null;
    // SettingsBean settingsBean = null;
    LoginInterfaceDialog loginInterfaceDialog = null;
    PreviewFrame previewFrame = null;
    MenuItem start = null;
    public TrayIcon icon = null;
    MenuItem testFrame = null;
    MenuItem settings = null;
    MenuItem imageViewer;

    public MainTray() {

//        AppConstants.settingsBean = new SettingsBean();
        loadSettings();
//        settings = new Settings(AppConstants.settingsBean);
        init();
        initInitialFrames();

        if (SystemTray.isSupported()) {

            SystemTray tray = SystemTray.getSystemTray();
            Image image = new ImageIcon(this.getClass().getResource("/icon/icon16.png")).getImage();
            icon = new TrayIcon(image, "GBC", popupMenu);
            try {
                tray.add(icon);
            } catch (AWTException ex) {
                Logger.getLogger(MainTray.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void main(String[] args) {
        try {
//            try {
//                UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(MainTray.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (InstantiationException ex) {
//                Logger.getLogger(MainTray.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IllegalAccessException ex) {
//                Logger.getLogger(MainTray.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (UnsupportedLookAndFeelException ex) {
//                Logger.getLogger(MainTray.class.getName()).log(Level.SEVERE, null, ex);
//            }
            SplashScreen sc = new SplashScreen();
            sc.setLocationRelativeTo(null);
            sc.setVisible(true);

            Thread.sleep(1000);
            sc.setBackgroundImage("1");

            Thread.sleep(1000);
            sc.setBackgroundImage("2");
            
            Thread.sleep(1000);
            sc.setBackgroundImage("3");
            Thread.sleep(1000);
            sc.dispose();
            new MainTray();
        } catch (InterruptedException ex) {
            Logger.getLogger(MainTray.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void init() {
        popupMenu = new PopupMenu();
        testFrame = new MenuItem("Preview");
        testFrame.setActionCommand("TestFrame");
        testFrame.addActionListener(this);
        testFrame.setEnabled(false);


        settings = new MenuItem("Settings");
        settings.setActionCommand("Settings");
        settings.addActionListener(this);
        settings.setEnabled(false);
        ////////////////
        imageViewer = new MenuItem("Image Viewer");
        imageViewer.setActionCommand("viewer");
        imageViewer.addActionListener(this);
        imageViewer.setEnabled(false);
        /////////////////

        start = new MenuItem("Start");
        start.setActionCommand("Start");
        start.addActionListener(this);


        login = new MenuItem("Login");
        login.setActionCommand("Login");
        login.addActionListener(this);
        login.setEnabled(false);

        MenuItem exit = new MenuItem("Exit");
        exit.setActionCommand("Exit");
        exit.addActionListener(this);

        MenuItem editSettings = new MenuItem("Edit Settings");
        editSettings.setActionCommand("Edit Settings");
        editSettings.addActionListener(this);

        popupMenu.add(imageViewer);
        popupMenu.add(editSettings);
        popupMenu.add(testFrame);
        popupMenu.add(settings);
        popupMenu.add(login);
        popupMenu.add(start);
        popupMenu.add(exit);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("" + e.getActionCommand());
        if (e.getActionCommand().equals("TestFrame")) {
            new PreviewFrame(new javax.swing.JFrame(), false).setVisible(true);
        } else if (e.getActionCommand().equals("Exit")) {
            System.out.println("Supplied:" + AppConstants.supplied + "  consumed:" + AppConstants.processedCount + " Time:" + (AppConstants.startTime - new Date().getTime()));
            long inSecond = ((new Date().getTime() - AppConstants.startTime)) / 1000;
            if (inSecond > 0) {
                System.out.println("Rate:" + AppConstants.processedCount / inSecond + " sec" + inSecond + "image count:" + AppConstants.processedCount);
            }
            System.exit(0);
        } else if (e.getActionCommand().equals("Login")) {

            loginInterfaceDialog.setLocationRelativeTo(null);
            loginInterfaceDialog.setVisible(true);
        } else if (e.getActionCommand().equals("Settings")) {
            new Thread(new ColorSettingsThread(new Frame())).start();
        } else if (e.getActionCommand().equals("Start")) {
            new Thread(new CaptureImage(this)).start();
            enableStartMenu(false);
            login.setEnabled(true);

        } else if (e.getActionCommand().equals("viewer")) {
            new ImageViewerFrame().setVisible(true);

        } else if (e.getActionCommand().equals("Edit Settings")) {
            System.out.println("edit settinfgs");
           new ColorDetectorFrame(new JFrame(), true).setVisible(true);

        }
    }

    private void loadSettings() {
        File settingsFile = new File(System.getProperty("user.dir")
                + File.separator + "Settings" + File.separator + "Settings.ser");
        if (settingsFile.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(settingsFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                SettingsBean tempBean = (SettingsBean) ois.readObject();
                AppConstants.settingsBean = tempBean;
                RGBBean red = AppConstants.settingsBean.getRedColor();
                RGBBean green = AppConstants.settingsBean.getGreenColor();
                RGBBean blue = AppConstants.settingsBean.getBlueColor();
                AppConstants.RED_COMPONENT_OF_RED_MAX = red.getMaxRed();
                AppConstants.GREEN_COMPONENT_OF_RED_MAX = red.getMaxGreen();
                AppConstants.BLUE_COMPONENT_OF_RED_MAX = red.getMaxBlue();
                AppConstants.RED_COMPONENT_OF_RED_MIN = red.getMinRed();
                AppConstants.GREEN_COMPONENT_OF_RED_MIN = red.getMinGreen();
                AppConstants.BLUE_COMPONENT_OF_RED_MIN = red.getMinBlue();
                AppConstants.RED_COMPONENT_OF_GREEN_MAX = green.getMaxRed();
                AppConstants.GREEN_COMPONENT_OF_GREEN_MAX = green.getMaxGreen();
                AppConstants.BLUE_COMPONENT_OF_GREEN_MAX = green.getMaxBlue();
                AppConstants.RED_COMPONENT_OF_GREEN_MIN = green.getMinRed();
                AppConstants.GREEN_COMPONENT_OF_GREEN_MIN = green.getMinGreen();
                AppConstants.BLUE_COMPONENT_OF_GREEN_MIN = green.getMinBlue();
                AppConstants.RED_COMPONENT_OF_BLUE_MAX = blue.getMaxRed();
                AppConstants.GREEN_COMPONENT_OF_BLUE_MAX = blue.getMaxGreen();
                AppConstants.BLUE_COMPONENT_OF_BLUE_MAX = blue.getMaxBlue();
                AppConstants.RED_COMPONENT_OF_BLUE_MIN = blue.getMinRed();
                AppConstants.GREEN_COMPONENT_OF_BLUE_MIN = blue.getMinGreen();
                AppConstants.BLUE_COMPONENT_OF_BLUE_MIN = blue.getMinBlue();
            } catch (IOException ex) {
                Logger.getLogger(MainTray.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainTray.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainTray.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            RGBBean red = new RGBBean();
            red.setMaxRed(AppConstants.RED_COMPONENT_OF_RED_MAX);
            red.setMaxGreen(AppConstants.GREEN_COMPONENT_OF_RED_MAX);
            red.setMaxBlue(AppConstants.BLUE_COMPONENT_OF_RED_MAX);

            red.setMinRed(AppConstants.RED_COMPONENT_OF_RED_MIN);
            red.setMinGreen(AppConstants.GREEN_COMPONENT_OF_RED_MIN);
            red.setMinBlue(AppConstants.BLUE_COMPONENT_OF_RED_MIN);

            RGBBean green = new RGBBean();
            green.setMaxRed(AppConstants.RED_COMPONENT_OF_GREEN_MAX);
            green.setMaxGreen(AppConstants.GREEN_COMPONENT_OF_GREEN_MAX);
            green.setMaxBlue(AppConstants.BLUE_COMPONENT_OF_GREEN_MAX);

            green.setMinRed(AppConstants.RED_COMPONENT_OF_GREEN_MIN);
            green.setMinGreen(AppConstants.GREEN_COMPONENT_OF_GREEN_MIN);
            green.setMinBlue(AppConstants.BLUE_COMPONENT_OF_GREEN_MIN);


            RGBBean blue = new RGBBean();
            blue.setMaxRed(AppConstants.RED_COMPONENT_OF_BLUE_MAX);
            blue.setMaxGreen(AppConstants.GREEN_COMPONENT_OF_BLUE_MAX);
            blue.setMaxBlue(AppConstants.BLUE_COMPONENT_OF_BLUE_MAX);

            blue.setMinRed(AppConstants.RED_COMPONENT_OF_BLUE_MIN);
            blue.setMinGreen(AppConstants.GREEN_COMPONENT_OF_BLUE_MIN);
            blue.setMinBlue(AppConstants.BLUE_COMPONENT_OF_BLUE_MIN);

            AppConstants.settingsBean.setBlueColor(blue);
            AppConstants.settingsBean.setGreenColor(green);
            AppConstants.settingsBean.setRedColor(red);
            Utilities.serializeSettingsBean();
        }
    }

    private void initInitialFrames() {
        // previewFrame = new PreviewFrame(loginInterfaceDialog, true)

        loginInterfaceDialog = new LoginInterfaceDialog(this);

    }

    public void enableStartMenu(boolean b) {
        start.setEnabled(b);
    }

    void setEnableComponents() {
        login.setEnabled(false);
        testFrame.setEnabled(true);
        settings.setEnabled(true);
        start.setEnabled(false);
        imageViewer.setEnabled(true);
    }

    public void enableLogin(boolean b) {
        login.setEnabled(b);
    }
}
