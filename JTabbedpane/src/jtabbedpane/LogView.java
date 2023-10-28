/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jtabbedpane;

import java.awt.EventQueue;
import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author mxtur
 */
public class LogView extends JFrame {

    /**
     * Creates new form LogView
     */
    public LogView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.textArea1 = new TextArea();

        setDefaultCloseOperation(3);

        this.textArea1.setEditable(false);
        this.jScrollPane1.setViewportView(this.textArea1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(this.jScrollPane1, -1, 734, 32767)
                        .addContainerGap()));

        layout.setVerticalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(this.jScrollPane1, -1, 582, 32767)
                        .addContainerGap()));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void loadLogs(String subsystemName) {

        try {
            Scanner s = new Scanner(new File("logs.log"));
            try {
                while (s.hasNextLine()) {
                    String line = s.nextLine();
                    if (line.contains(subsystemName)) {
                        this.textArea1.append(line + "\n");
                    }
                }

                s.close();
            } catch (Throwable throwable) {
                try {
                    s.close();
                } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                }
                throw throwable;
            }
        } catch (IOException iOException) {
        }
    }

    public FileHandler editLogs(FileHandler fileHandler, String oldName, String newName) {
        List<String> logLines = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("logs.log"));
            try {
                while (scanner.hasNextLine()) {
                    logLines.add(scanner.nextLine());
                }
                scanner.close();
            } catch (Throwable throwable) {
                try {
                    scanner.close();
                } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                }
                throw throwable;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SystemPanel.class.getName()).log(Level.SEVERE, (String) null, ex);
        }

        for (int i = 0; i < logLines.size(); i++) {
            String line = logLines.get(i);
            line = line.replace(oldName,newName);
            logLines.set(i, line);
        }
        fileHandler.close();

        try {
            PrintWriter writer = new PrintWriter(new File("logs.log"));
            try {
                for (String line : logLines) {
                    writer.println(line);
                }
                writer.close();
            } catch (Throwable throwable) {
                try {
                    writer.close();
                } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                }
                throw throwable;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SystemPanel.class.getName()).log(Level.SEVERE, (String) null, ex);
        }

        try {
            fileHandler = new FileHandler("logs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            
        } catch (IOException ex) {
            Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, (String) null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        
        return fileHandler;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LogView.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        // </editor-fold>

        // </editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LogView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JScrollPane jScrollPane1;
    private TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}
