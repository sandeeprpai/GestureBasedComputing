/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iv.utils;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author ThinkForce
 */
public class ImageFielFilterClass extends FileFilter {

    @Override
    public boolean accept(File f) {
        String pathInLowerCase = f.getPath().toLowerCase();
        if (f.isDirectory() || pathInLowerCase.endsWith(".jpg") || pathInLowerCase.endsWith(".jpeg")) {
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
       return "";
    }
}
