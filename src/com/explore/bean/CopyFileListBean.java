/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.explore.bean;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class CopyFileListBean implements Serializable {

    private File fileToBeCopied = null;
    private String sourceIp = null;

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public File getFileToBeCopied() {
        return fileToBeCopied;
    }

    public void setFileToBeCopied(File fileToBeCopied) {
        this.fileToBeCopied = fileToBeCopied;
    }

    public CopyFileListBean(File fileToBeCopied) {
        this.fileToBeCopied = fileToBeCopied;
    }

  



}
