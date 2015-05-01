/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurityproject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
/**
 *
 * @author 7051665
 */
public class PasswordTextBox extends JTextField implements KeyListener{
    public Password createdPassword = new Password();
    public long CurrentEventTime, PreviousEventTime, FirstEventTime;
    public int CurrentEventPosition;
    
    
    public PasswordTextBox()
    {
        addKeyListener(this);
        CurrentEventTime = 0;
        PreviousEventTime = 0;
        CurrentEventPosition = 0;
        
    }
    
    public void ResetPasswordData()
    {
        this.setText("");
        createdPassword.clear();
        CurrentEventTime = 0;
        PreviousEventTime = 0;
        FirstEventTime = 0;
        CurrentEventPosition = 0;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //must be overriden to implement key listener
        //this function is completely useless
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        PreviousEventTime = CurrentEventTime;
        CurrentEventTime = System.currentTimeMillis();
        if(this.getText().length() <= 1 && ((ke.getKeyCode() == KeyEvent.VK_DELETE) || (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE)))
        {
            this.ResetPasswordData();
            return;
        }
        
        PasswordEvent temp  = new PasswordEvent();
        if(createdPassword.events.isEmpty())
        {
            FirstEventTime = CurrentEventTime;
            temp.TimeSinceFirst = 0;
        }
        else
        {
            temp.TimeSinceFirst = (int)(CurrentEventTime - FirstEventTime);
        }
        
        temp.KeyCode = ke.getKeyCode();
        temp.KeyLocation = ke.getKeyLocation();
        temp.KeyPressed = KeyEvent.KEY_PRESSED;
        temp.EventPosition = CurrentEventPosition;
        CurrentEventPosition += 1;
        if(PreviousEventTime == 0)
            temp.TimeSincePrev = 0;
        else
            temp.TimeSincePrev = (int)(CurrentEventTime - PreviousEventTime);
        
        if(createdPassword.events.size() >= 1)
        {
            //if we have a previous event we can calculate time from previous
            //to this new one we are evaluating currently
            createdPassword.events.get(createdPassword.events.size()-1).TimeToNext = temp.TimeSincePrev;
        }
        createdPassword.events.add(temp);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        PreviousEventTime = CurrentEventTime;
        CurrentEventTime = System.currentTimeMillis();
        if(this.getText().length() <= 1 && ((ke.getKeyCode() == KeyEvent.VK_DELETE) || (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE)))
        {
            return;
        }
        
        PasswordEvent temp  = new PasswordEvent();
        if(createdPassword.events.isEmpty())
        {
            FirstEventTime = CurrentEventTime;
            temp.TimeSinceFirst = 0;
        }
        else
        {
            temp.TimeSinceFirst = (int)(CurrentEventTime - FirstEventTime);
        }
        
        
        temp.KeyCode = ke.getKeyCode();
        temp.KeyLocation = ke.getKeyLocation();
        temp.KeyPressed = KeyEvent.KEY_RELEASED;
        temp.EventPosition = CurrentEventPosition;
        CurrentEventPosition += 1;
        if(PreviousEventTime == 0)
            temp.TimeSincePrev = 0;
        else
            temp.TimeSincePrev = (int)(CurrentEventTime - PreviousEventTime);
        
        if(createdPassword.events.size() >= 1)
        {
            //if we have a previous event we can calculate time from previous
            //to this new one we are evaluating currently
            createdPassword.events.get(createdPassword.events.size()-1).TimeToNext = temp.TimeSincePrev;
        }
        createdPassword.events.add(temp);
    }
}
