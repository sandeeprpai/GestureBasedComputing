/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.transfer.exp.comm;


import com.gbc.util.AppConstants;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class FileTransferServer implements Runnable {

    ServerSocket ss = null;

    public FileTransferServer() {
        try {
            ss = new ServerSocket(AppConstants.CLIENT_SIDE_SERVER_PORT_FOR_FILE_TRANSFER);
        } catch (IOException ex) {
            Logger.getLogger(FileTransferServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        try {
            // boolean allFileTransferCompleted = false;
            // while (!allFileTransferCompleted) {
            while (true) {
                Socket s = ss.accept();
                new Thread(new FileTransfer(s)).start();
            }
            // }
        } catch (IOException ex) {
            Logger.getLogger(FileTransferServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
