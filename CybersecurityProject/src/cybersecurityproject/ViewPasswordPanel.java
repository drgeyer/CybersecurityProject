/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurityproject;

import java.io.File;
import javax.swing.*;
/**
 *
 * @author 7051665
 */
public class ViewPasswordPanel extends JPanel{
    
    public ViewPasswordPanel(File infile)
    {
        pwdfile = infile;
        initComponents();
    }
    
    public ViewPasswordPanel()
    {
        this(null);
    }
    
    private void initComponents()
    {
        test = new JLabel("You chose to open: " + pwdfile.getName());
        add(test);
    }
    JLabel test;
    File pwdfile;
}
