/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurityproject;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author 7051665
 */
public class ViewPasswordForm extends javax.swing.JPanel {

    File pwdfile;
    Password LoadedPassword = new Password();
    /**
     * Creates new form ViewPasswordForm
     */
    public ViewPasswordForm() {
        this(null);
    }
    public ViewPasswordForm(File infile)
    {
        pwdfile = infile;
        initComponents();
        LoadPassword();
        initUserComponents();
        drawPassword1.LoadedPassword = (Password) LoadedPassword.clone();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileLabel = new javax.swing.JLabel();
        drawPassword1 = new cybersecurityproject.DrawPassword();

        FileLabel.setText("jLabel1");

        javax.swing.GroupLayout drawPassword1Layout = new javax.swing.GroupLayout(drawPassword1);
        drawPassword1.setLayout(drawPassword1Layout);
        drawPassword1Layout.setHorizontalGroup(
            drawPassword1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        drawPassword1Layout.setVerticalGroup(
            drawPassword1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(drawPassword1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(FileLabel)
                        .addGap(0, 846, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FileLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(drawPassword1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    private void initUserComponents()
    {
        this.FileLabel.setText("Viewing: " + pwdfile.getName());
        this.drawPassword1.setBackground(Color.WHITE);
    }
    
    private void LoadPassword()
    {
        if(pwdfile == null)
            return;
        Scanner inFileScanner;
        try {
            inFileScanner = new Scanner(pwdfile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AttemptPasswordForm.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        int event_num = 0;
        LoadedPassword.final_password = inFileScanner.next();
        while(inFileScanner.hasNextInt())
        {
            PasswordEvent tempEvent = new PasswordEvent();
            tempEvent.KeyLocation = inFileScanner.nextInt();
            tempEvent.KeyPressed = inFileScanner.nextInt();
            tempEvent.KeyCode = inFileScanner.nextInt();
            tempEvent.TimeSincePrev = inFileScanner.nextInt();
            tempEvent.TimeToNext = inFileScanner.nextInt();
            tempEvent.TimeSinceFirst = inFileScanner.nextInt();
            tempEvent.EventPosition = event_num;
            LoadedPassword.events.add(tempEvent);
            event_num += 1;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FileLabel;
    private cybersecurityproject.DrawPassword drawPassword1;
    // End of variables declaration//GEN-END:variables
}
