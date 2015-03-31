/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurityproject;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 *
 * @author 7051665
 */
public class NewPasswordPanel extends JPanel implements KeyListener{
    
    public NewPasswordPanel()
    {
        this.setLayout(new GridLayout(3, 1));
        initComponents();
        this.setVisible(true);
        this.updateUI();
    }
    
    private void initComponents()
    {
        test = new JLabel("Begin typing to create a new password");
        passwordTextBox = new JTextField();
        savePassword = new JButton("Save Password");
        add(test);
        add(passwordTextBox);
        add(savePassword);
    }
    
    //User defined variables
    JLabel test;    
    JTextField passwordTextBox;
    JButton savePassword;

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
