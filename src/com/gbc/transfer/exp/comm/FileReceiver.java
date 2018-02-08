/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbc.transfer.exp.comm;

import com.explore.bean.AllFileTransferCompleted;
import com.explore.bean.CopyFileListBean;
import com.explore.bean.FileInfo;
import com.explore.bean.FilePacket;
import com.explore.bean.FileTransferCompleted;


import com.gbc.util.AppConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class FileReceiver implements Runnable {

    Socket socket = null;
    CopyFileListBean copyFileListBean = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
   // FileExpFrame mainFrame = null;

    public FileReceiver(CopyFileListBean copyFileListBean) {
        try {

            this.copyFileListBean = copyFileListBean;
           // this.mainFrame = mainFrame;
            socket = new Socket(copyFileListBean.getSourceIp(),
                    AppConstants.CLIENT_SIDE_SERVER_PORT_FOR_FILE_TRANSFER);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException ex) {
            Logger.getLogger(FileReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        File file = copyFileListBean.getFileToBeCopied();
        Object obj = null;
        FileOutputStream fos = null;
        FilePacket packet = null;
        File localFile = null;
        File copyingPath = null;//mainFrame.getSelectedPathOfExplorer();
        if (copyingPath == null) {
            copyingPath = new File(AppConstants.FILE_PATH);
        }
        if(!copyingPath.exists())  {
            copyingPath.mkdirs();
        }
        System.out.println("Copying path " + copyingPath.getAbsolutePath());
        // pd.seMaximum(files.size());
        try {
            int i = 0;
            boolean status = false;
           // for (File f : files) {

                localFile = new File(copyingPath.getAbsolutePath() + File.separator + file.getName());
                if (!localFile.exists()) {
                    status = true;
                    AppConstants.isPasting = true;
                    System.out.println("Copying file " + localFile.getAbsolutePath());
                    oos.writeObject(new FileInfo(file));
                    oos.flush();
                    fos = new FileOutputStream(localFile.getAbsolutePath());

                    ////////////////File Transfer
                    AppConstants.isFileTransferCompleted = false;
                    while (!AppConstants.isFileTransferCompleted) {
                        obj = ois.readObject();
                        if (obj instanceof FilePacket) {
                            packet = (FilePacket) obj;
                            fos.write(packet.getPayLoad(), 0, packet.getLength());
                            fos.flush();
                        } else if (obj instanceof FileTransferCompleted) {
                            System.out.println("File Transfer Completed");
                            AppConstants.isFileTransferCompleted = true;
                        }
                        ////////////////////
                        i++;

                    }
                } else {
                    System.out.println("copy Skipped ,File already exists");
                }
           // }
            if (status) {
                JOptionPane.showMessageDialog(null, "File Transfer Completed",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                //mainFrame.loadTree();
            }


            System.out.println("App Constants status:" + AppConstants.isPasting);
            AppConstants.isPasting = false;
            oos.writeObject(new AllFileTransferCompleted());
            oos.flush();
            AppConstants.isPasting = false;
            socket.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileReceiver.class.getName()).log(Level.SEVERE, null, ex);
            AppConstants.isPasting = false;
        } catch (IOException ex) {
            Logger.getLogger(FileReceiver.class.getName()).log(Level.SEVERE, null, ex);
            AppConstants.isPasting = false;
        }

        AppConstants.isPasting = false;
    }
}
