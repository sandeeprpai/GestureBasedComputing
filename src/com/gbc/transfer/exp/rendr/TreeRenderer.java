/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.exp.rendr;

import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Deepak
 */
public class TreeRenderer extends DefaultTreeCellRenderer {
FileSystemView fsv = FileSystemView.getFileSystemView();
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                row, hasFocus);
        if (value instanceof DefaultMutableTreeNode) {
            Object userObject = ((DefaultMutableTreeNode)value).getUserObject();
            if (userObject instanceof File) {
                File file = (File)userObject;
                this.setIcon(fsv.getSystemIcon(file));
                this.setText(fsv.getSystemDisplayName(file));
            }else if(userObject instanceof String) {
                System.out.println("" + value);
                 Image image = Toolkit.getDefaultToolkit().createImage(
                         this.getClass().getResource("/com/gbc/transfer/exp/rendr/Icon.png"));
                 this.setIcon(new ImageIcon(image));
            }
            return this;
        }else {
            return null;
        }
    }

}
