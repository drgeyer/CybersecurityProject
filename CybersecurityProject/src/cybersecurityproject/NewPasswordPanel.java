/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurityproject;
import javax.swing.*;

/**
 *
 * @author 7051665
 */
public class NewPasswordPanel extends JPanel {
    
    public NewPasswordPanel()
    {
        initComponents();
        this.setVisible(true);
        this.updateUI();
    }
    
    private void initComponents()
    {
        test = new JLabel("New Password Pane");
        add(test);
    }
    
    //User defined variables
    JLabel test;    
}
