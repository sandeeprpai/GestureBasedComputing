/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.transfer.exp.comm;

import com.explore.bean.CopyFileListBean;

import com.gbc.util.AppConstants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class ServerCommunicator implements Runnable {

    private Socket socket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;

    public ServerCommunicator() {

    }

    public void sendToServer(Object obj) {
        try {
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerCommunicator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CopyFileListBean getResponse() {
        try {
            sendToServer("GetFileList");
            return (CopyFileListBean) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ServerCommunicator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerCommunicator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void run() {
        try {
            socket = new Socket(AppConstants.SERVER_IP, AppConstants.SERVER_PORT);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerCommunicator.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Server Not Found", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(ServerCommunicator.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Server Not Found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
