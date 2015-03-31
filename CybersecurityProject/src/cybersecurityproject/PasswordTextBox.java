/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurityproject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
/**
 *
 * @author 7051665
 */
public class PasswordTextBox extends JTextField implements KeyListener{
    
    public PasswordTextBox()
    {
        addKeyListener(this);
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("Key Typed");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println("Key Pressed");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println("Key Released");
    }
}
