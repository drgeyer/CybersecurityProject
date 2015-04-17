/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurityproject;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
/**
 *
 * @author 7051665
 */
public class PasswordTextBox extends JTextField implements KeyListener{
    public ArrayList<PasswordEvent> createdPassword = new ArrayList<>();
    public long CurrentEventTime, PreviousEventTime;
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
        createdPassword.clear();
        CurrentEventTime = 0;
        PreviousEventTime = 0;
        CurrentEventPosition = 0;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //extra event we dont care about
        //must be overriden to implement key listener
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        PreviousEventTime = CurrentEventTime;
        CurrentEventTime = System.currentTimeMillis();
        if(ke.getKeyCode() == KeyEvent.VK_ENTER)
        {
            NewPasswordForm parent = (NewPasswordForm)this.getParent();
            parent.FireSubmitButton();
            return;
        }
        PasswordEvent temp  = new PasswordEvent();
        temp.KeyCode = ke.getKeyCode();
        temp.KeyLocation = ke.getKeyLocation();
        temp.KeyPressed = KeyEvent.KEY_PRESSED;
        temp.EventPosition = CurrentEventPosition;
        CurrentEventPosition += 1;
        if(PreviousEventTime == 0)
            temp.TimeSincePrev = 0;
        else
            temp.TimeSincePrev = (int)(CurrentEventTime - PreviousEventTime);
        
        if(createdPassword.size() >= 1)
        {
            //if we have a previous event we can calculate time from previous
            //to this new one we are evaluating currently
            createdPassword.get(createdPassword.size()-1).TimeToNext = temp.TimeSincePrev;
        }
        createdPassword.add(temp);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        PreviousEventTime = CurrentEventTime;
        CurrentEventTime = System.currentTimeMillis();
        if(ke.getKeyCode() == KeyEvent.VK_ENTER)
        {
            NewPasswordForm parent = (NewPasswordForm)this.getParent();
            parent.FireSubmitButton();
            return;
        }
        PasswordEvent temp  = new PasswordEvent();
        temp.KeyCode = ke.getKeyCode();
        temp.KeyLocation = ke.getKeyLocation();
        temp.KeyPressed = KeyEvent.KEY_RELEASED;
        temp.EventPosition = CurrentEventPosition;
        CurrentEventPosition += 1;
        if(PreviousEventTime == 0)
            temp.TimeSincePrev = 0;
        else
            temp.TimeSincePrev = (int)(CurrentEventTime - PreviousEventTime);
        
        if(createdPassword.size() >= 1)
        {
            //if we have a previous event we can calculate time from previous
            //to this new one we are evaluating currently
            createdPassword.get(createdPassword.size()-1).TimeToNext = temp.TimeSincePrev;
        }
        createdPassword.add(temp);
    }
}
