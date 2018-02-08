/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.transfer.exp.comm;

import com.explore.bean.AllFileTransferCompleted;
import com.explore.bean.FileInfo;
import com.explore.bean.FilePacket;
import com.explore.bean.FileTransferCompleted;

import com.gbc.util.AppConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class FileTransfer implements Runnable {

    Socket socket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;

    FileTransfer(Socket s) {
        socket = s;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(FileTransfer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        boolean status = true;
        Object obj = null;
        long fileSize = 0;
        long noOfPackets = 0;
        FileInputStream fis = null;
        byte[] filechunk = null;
        int currentChunkSize = 0;
        FilePacket filePacket = null;
        boolean allFileTransferCompleted = false;
        try {
            while (!allFileTransferCompleted) {

                obj = ois.readObject();
                if (obj instanceof FileInfo) {
                    /////////sending file/////////////////
                    File myFile = ((FileInfo) obj).getFileToBeCopiedFile();
                    fileSize = myFile.length();
                    noOfPackets = fileSize / AppConstants.CHUNK_SIZE;
                    fis = new FileInputStream(myFile);
                    ///////////////
                    //   dummyOut = new FileOutputStream(dummy);

                    //////////////
                    for (int i = 0; i < noOfPackets; i++) {
                        filechunk = new byte[AppConstants.CHUNK_SIZE];
                        currentChunkSize = fis.read(filechunk);
                        // System.out.println(new String(filechunk));
                        filePacket = new FilePacket(filechunk, currentChunkSize);
                        oos.writeObject(filePacket);
                        // System.out.println("---------" + filePacket.getLength() +"-----------");
                        // System.out.println(new String(filePacket.getPayLoad()));
                        //   dummyOut.write(filePacket.getPayLoad());
                        //   dummyOut.flush();
                        oos.flush();
                    }
                    if (fis.available() > 0) {
                        currentChunkSize = fis.available();
                        //  dummyOut.write(filechunk,0,currentChunkSize);
                        //  dummyOut.flush();
                        filechunk = new byte[AppConstants.CHUNK_SIZE];
                        fis.read(filechunk);
                        filePacket = new FilePacket(filechunk, currentChunkSize);
                        // System.out.println(filePacket.getLength() + new String(filePacket.getPayLoad(),0, currentChunkSize));
                        oos.writeObject(filePacket);
                        oos.flush();
                    }
                     oos.writeObject(new FileTransferCompleted());
                        oos.flush();
                    /////////////////////////////////////
                } else if (obj instanceof AllFileTransferCompleted) {
                    allFileTransferCompleted = true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FileTransfer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileTransfer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
