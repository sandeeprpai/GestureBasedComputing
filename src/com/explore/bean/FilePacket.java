/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.explore.bean;



import com.gbc.util.AppConstants;
import java.io.Serializable;

/**
 *
 * @author ThinkForce
 */
public class FilePacket implements Serializable{
    byte[] payLoad = new byte[AppConstants.CHUNK_SIZE];
    int length = 0;

    public FilePacket(byte[] payLoad, int length) {
        this.payLoad = payLoad;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(byte[] payLoad) {
        this.payLoad = payLoad;
    }


}
