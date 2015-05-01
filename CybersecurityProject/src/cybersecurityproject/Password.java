/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurityproject;

import java.util.ArrayList;

/**
 *
 * @author 7051665
 */
public class Password {
    ArrayList<PasswordEvent> events;
    String final_password;
    
    public Password()
    {
         events = new ArrayList<>();
         final_password = null;
    }
    
    @Override
    public Password clone()
    {
        Password temp = new Password();
        temp.events = (ArrayList<PasswordEvent>) this.events.clone();
        temp.final_password = this.final_password;
        
        return temp;
    }
    
    public void clear()
    {
        this.events.clear();
        this.final_password = null;
    }
}
