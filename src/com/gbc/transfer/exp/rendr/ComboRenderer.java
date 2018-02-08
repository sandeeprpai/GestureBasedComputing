/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.exp.rendr;

import java.awt.Component;
import java.io.File;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Deepak
 */
public class ComboRenderer extends DefaultListCellRenderer {
FileSystemView fsv = FileSystemView.getFileSystemView();
    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected,
                cellHasFocus);
        if(value instanceof File) {
            File file = (File) value;
            this.setText(fsv.getSystemDisplayName(file));
            this.setIcon(fsv.getSystemIcon(file));
            return this;
        }else {
            return null;
        }
    }

}
