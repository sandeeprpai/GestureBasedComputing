/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.transfer.exp.rendr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import sun.awt.shell.ShellFolder;

/**
 *
 * @author Deepak
 */
public class TableRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        try {
            super.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);
            // FileSystemView fsv = FileSystemView.getFileSystemView();
            if (value instanceof File) {
                File file = (File) value;
                if (file.exists()) {
                    ShellFolder sf = ShellFolder.getShellFolder(file);
                    String name = sf.getDisplayName();
                    Image image = getScaledImage(sf.getIcon(true), 40, 40);
                    this.setIcon(new ImageIcon(image));
                    if (file.isDirectory()) {
                        this.setText("<html>" + name + "</html>");
                    } else {
                        String length = "";
                        long l = file.length();
                        if (l > 1024 * 1024) {
                            length = (l / (1024 * 1024)) + " MB";
                        } else if (l > 1024) {
                            length = (l / 1024) + " KB";
                        } else {
                            length = l + " Bytes";
                        }
                        if (name != null && name.length() > 10) {
                            name = name.substring(0, 10) + "...";
                        }
                        this.setText("<html>" + name + "<br/>" + sf.getFolderType() +
                                "<br/>" + length + "</html>");
                    }
                    this.setHorizontalAlignment(JLabel.LEADING);
                    this.setVerticalAlignment(JLabel.CENTER);
                    this.setHorizontalTextPosition(JLabel.TRAILING);
                    this.setVerticalTextPosition(JLabel.CENTER);
                    this.setIconTextGap(10);
                    //this.setToolTipText(fsv.getSystemTypeDescription(file));
                    return this;
                } else {
                    FileSystemView fsv = FileSystemView.getFileSystemView();
                    if (fsv.isDrive(file)) {
                        Image image = ((ImageIcon)fsv.getSystemIcon(file)).getImage();
                        this.setIcon(new ImageIcon(getScaledImage(image, 32, 32)));
                        this.setText("DVD Drive");
                        return this;
                    }else {
                       return null;
                    }
                    
                }
            } else {

                return null;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TableRenderer.class.getName()).log(Level.SEVERE,
                    null, ex);
            return null;
        }

    }

    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, Color.WHITE, null);
        g2.dispose();
        return resizedImg;
    }
}
