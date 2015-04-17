/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurityproject;

/**
 *
 * @author 7051665
 */
public class PasswordEvent {
    public int KeyLocation;
    public int KeyPressed;
    public int KeyCode;
    public int TimeToNext;
    public int TimeSincePrev;
    public int EventPosition;
    public int EventSTDEV;
    
    PasswordEvent()
    {
    }
    
    public boolean Equals(PasswordEvent comp)
    {
        if(this.KeyLocation == comp.KeyLocation && this.KeyPressed == comp.KeyPressed &&
           this.KeyCode == comp.KeyCode)
        {
            return true;
        }
        return false;
    }
    
    public int PositionDiff(PasswordEvent comp)
    {
        return (this.EventPosition - comp.EventPosition);
    }
    
}
