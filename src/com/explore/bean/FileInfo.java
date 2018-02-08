/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.explore.bean;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class FileInfo implements Serializable{
    File fileToBeCopiedFile = null;

    public FileInfo(File fileToBeCopiedFile) {
        this.fileToBeCopiedFile = fileToBeCopiedFile;
    }

    public File getFileToBeCopiedFile() {
        return fileToBeCopiedFile;
    }

    public void setFileToBeCopiedFile(File fileToBeCopiedFile) {
        this.fileToBeCopiedFile = fileToBeCopiedFile;
    }

   
    
}
