/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExplorerFrame.java
 *
 * Created on Dec 16, 2011, 9:45:02 PM
 */
package com.gbc.transfer.exp;

import com.exp.rendr.ComboRenderer;

import com.exp.rendr.TreeRenderer;
import com.explore.bean.CopyFileListBean;
import com.gbc.transfer.exp.comm.ServerCommunicator;
import com.gbc.transfer.exp.rendr.TableRenderer;
import com.gbc.util.AppConstants;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Deepak
 */
public class ExplorerFrame extends javax.swing.JFrame {

    Desktop desktop = Desktop.getDesktop();
     ServerCommunicator serverCommunicator;

    /** Creates new form ExplorerFrame */
    public ExplorerFrame() {

        initComponents();
        AppConstants.isFileTransferAppsStarted = true;
        createDefaultDirectory();
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //SwingUtilities.updateComponentTreeUI(this);
        this.setExtendedState(MAXIMIZED_BOTH);
        //setResizable(false);
        explorerTable.setRowHeight(75);
        TableRenderer renderer = new TableRenderer();
        explorerTable.getColumnModel().getColumn(0).setCellRenderer(renderer);
        explorerTable.getColumnModel().getColumn(1).setCellRenderer(renderer);
        explorerTable.getColumnModel().getColumn(2).setCellRenderer(renderer);
        explorerTable.getColumnModel().getColumn(3).setCellRenderer(renderer);
        explorerTable.getColumnModel().getColumn(4).setCellRenderer(renderer);
        explorerTree.setCellRenderer(new TreeRenderer());
        explorerComboBox.setRenderer(new ComboRenderer());
        loadContents();
        loadContentsForADirectory(new File(System.getProperty("user.dir") + File.separator + "Transfer"));
        serverCommunicator= new ServerCommunicator();
//explorerTable.setSelectionMode(Selection);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        explorerTree = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        explorerTable = new javax.swing.JTable();
        explorerComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Back.png"))); // NOI18N
        jButton2.setText("Back");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Right.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Foldersmall.png"))); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);

        jButton5.setText("Close");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton5);

        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setFocusCycleRoot(true);

        explorerTree.setAlignmentX(1.0F);
        explorerTree.setAlignmentY(1.0F);
        explorerTree.setAutoscrolls(true);
        explorerTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                explorerTreeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(explorerTree);

        jSplitPane1.setLeftComponent(jScrollPane1);

        explorerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        explorerTable.setColumnSelectionAllowed(true);
        explorerTable.setDragEnabled(true);
        explorerTable.setDropMode(javax.swing.DropMode.INSERT_COLS);
        explorerTable.setFillsViewportHeight(true);
        explorerTable.setSelectionBackground(new java.awt.Color(204, 204, 255));
        explorerTable.setSelectionForeground(new java.awt.Color(255, 0, 102));
        explorerTable.setShowHorizontalLines(false);
        explorerTable.setShowVerticalLines(false);
        explorerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                explorerTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(explorerTable);
        explorerTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jSplitPane1.setRightComponent(jScrollPane2);

        explorerComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Address");

        jButton1.setText("GO");

        jLabel2.setText("jLabel2");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(explorerComboBox, 0, 629, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel2)
                .addContainerGap(641, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(explorerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void explorerTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_explorerTreeValueChanged
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) explorerTree.getLastSelectedPathComponent();
        Object userObject = selectedNode.getUserObject();
        if (userObject instanceof File && ((File) userObject).isDirectory()) {
            loadTreeNodes((File) userObject, selectedNode);
        } else if (userObject instanceof File) {
            runFile((File) userObject);
        }
    }//GEN-LAST:event_explorerTreeValueChanged

    private void explorerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_explorerTableMouseClicked
        System.out.println("column" + explorerTable.getSelectedColumn() + "row:" +explorerTable.getSelectedRow());
        System.out.println("" + explorerTable.rowAtPoint(evt.getPoint()));
        int column = explorerTable.getSelectedColumn();
            int row = explorerTable.getSelectedRow();
            explorerTable.getSelectionModel();
           // ListSelectionModel.
            if(explorerTable.rowAtPoint(evt.getPoint()) == -1) {
              //  explorerTable.setSelectionMode(JTable.);
                explorerTable.clearSelection();
            }
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
            
            if (row != -1) {
                Object value = explorerTable.getValueAt(row, column);
                if (value instanceof File && ((File) value).isDirectory()) {
                    loadTableContents(((File) value).listFiles());
                } else if (value instanceof File) {
                    runFile((File) value);
                     serverCommunicator.sendToServer(new CopyFileListBean((File) value));
                }
            }
        }
    }//GEN-LAST:event_explorerTableMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ExplorerFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox explorerComboBox;
    private javax.swing.JTable explorerTable;
    private javax.swing.JTree explorerTree;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    private void loadContents() {
        File[] files = File.listRoots();
       // loadTableContents(files);
        loadTreeContents(files);
        loadComboboxContents(files);
    }

    private void loadContentsForADirectory(File root) {
        File[] files = root.listFiles();
        loadTableContents(files);
        //loadTreeContents(files);
        //loadComboboxContents(files);
    }

    private void loadTableContents(File[] files) {
        DefaultTableModel tableModel = (DefaultTableModel) explorerTable.getModel();
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        // explorerTable.setLayout(new FlowLayout());
        int length = 0;
        if (files != null) {
            length = files.length;
        }
        int rows = length / 4;
        if ((length % 5) > 0) {
            rows++;
        }
        for (int i = 0; i < rows; i++) {
            int first = (i * 5) + 0;
            int second = (i * 5) + 1;
            int third = (i * 5) + 2;
            int fourth = (i * 5) + 3;
            int fifth = (i * 5) + 4;
            Object[] data = new Object[5];
            if (first < length) {
                data[0] = files[first];
            }
            if (second < length) {
                data[1] = files[second];
            }
            if (third < length) {
                data[2] = files[third];
            }
            if (fourth < length) {
                data[3] = files[fourth];
            }
            if (fifth < length) {
                data[4] = files[fifth];
            }
            tableModel.addRow(data);
            if (first == length || second == length || third == length
                    || fourth == length || fifth == length) {
                break;
            }
        }
    }

    private void loadTreeContents(File[] files) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("MyComputer", true);
        for (File file : files) {
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file, true);
            rootNode.add(childNode);
        }
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        explorerTree.setModel(treeModel);
    }

    private void loadTreeNodes(File file, DefaultMutableTreeNode selectedNode) {
        File[] files = file.listFiles();
        loadTableContents(files);
        for (File file1 : files) {
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(file1);
            selectedNode.add(treeNode);
        }
    }

    private void loadComboboxContents(File[] files) {
        explorerComboBox.removeAllItems();
        for (File file : files) {
            explorerComboBox.addItem(file);
        }

    }

    private void runFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Can not launch this file");
        }
    }

    private void createDefaultDirectory() {
        File f = new File(System.getProperty("user.dir") + File.separator + "Transfer");
        if (!f.exists()) {
            f.mkdirs();
        }
    }
}
