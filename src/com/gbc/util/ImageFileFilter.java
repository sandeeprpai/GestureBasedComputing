package com.gbc.util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author ThinkForce
 */
public class ImageFileFilter implements FileFilter{

    public boolean accept(File pathname) {
        String pathInLowerCase = pathname.getPath().toLowerCase();
       if(pathInLowerCase.endsWith(".jpg") || pathInLowerCase.endsWith(".jpeg")){
           return true;
       }
        return false;
    }

}
