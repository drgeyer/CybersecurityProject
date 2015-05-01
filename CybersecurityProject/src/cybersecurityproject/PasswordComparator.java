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
public class PasswordComparator {
    final int MISS = 40;
    
    public PasswordComparator()
    {
        
    }
    
    public int ComparePasswords(Password p1, Password p2)
    {
        Password tempP1 = (Password) p1.clone();
        Password tempP2 = (Password) p2.clone();
        
        //make sure they are the same length
        if(PasswordLengthCheck(tempP1, tempP2) == false)
        {
            return 1;
        }
        
        //make sure they contain all of the same exact events
        if(PasswordEventCheck(tempP1, tempP2) == false)
        {
            return 2;
        }
        
        //make sure the final password string is correct
        if(FinalStringCheck(tempP1, tempP2) == false)
        {
            return 4;
        }
        
        if(PasswordTimeCompare(tempP1, tempP2) == false)
        {
            return 3;
        }
        return 0;
    }

    private boolean PasswordLengthCheck(Password tempP1, Password tempP2) {
        return !((tempP1.events.size() != tempP2.events.size()) || tempP1.events.isEmpty());
    }

    private boolean PasswordEventCheck(Password tempP1, Password tempP2) {
        Password p1 = (Password) tempP1.clone();
        Password p2 = (Password) tempP2.clone();
        
        while(!p1.events.isEmpty())
        {
            boolean found = false;
            for(int i = 0; i < p2.events.size(); i++)
            {
                if(EventCompare(p1.events.get(0), p2.events.get(i)))
                {
                    p1.events.remove(0);
                    p2.events.remove(i);
                    found = true;
                    break;
                }
            }
            if(found == false)
            {
                return false;
            }
        }
        return true;        
    }

    private boolean PasswordTimeCompare(Password tempP1, Password tempP2) {
        Password p1 = tempP1.clone();
        Password p2 = tempP2.clone();
        
        int differences[] = CalculateDifferenceArray(p1, p2);
        
        if(differences == null)
            return false;
        
        //now that we have the diff array we can compare them all
        differences = MinimizeDifferences(differences);
        
        //if the sum of the differrences is over a certain threshold then we need to
        //fail the password
        int sum_of_diffs = 0;
        for(int i : differences)
            sum_of_diffs += Math.abs(i);
        
        //allow a miss of +/- 50 ms per key event
        return sum_of_diffs <= (p1.events.size() * p1.events.size() + p1.events.size() * MISS);
    }
    
    private boolean EventCompare(PasswordEvent e1, PasswordEvent e2) {
        return e1.KeyCode == e2.KeyCode &&
                e1.KeyLocation == e2.KeyLocation &&
                e1.KeyPressed == e2.KeyPressed;
    }
    
    private int[] CalculateDifferenceArray(Password p1, Password p2) {
        int p1period = p1.events.get(p1.events.size()-1).TimeSinceFirst;
        int p2period = p2.events.get(p2.events.size()-1).TimeSinceFirst;
        double ratio = (double)p1period / (double)p2period;
        
        if(ratio > 1.25 || ratio < 0.75)
            return null;
        
        int differences[] = new int[p1.events.size()];
        for(int i = 0; i < p1.events.size(); i++)
        {
            for(int j = 0; j < p2.events.size(); j++)
            {
                if(EventCompare(p1.events.get(i), p2.events.get(j)))
                {
                    //found it so set the difference and move to next
                    differences[i] = (int) (p1.events.get(i).TimeSinceFirst - (p2.events.get(j).TimeSinceFirst * ratio));
                    p2.events.remove(j);
                    break;
                }
            }
        }
        
        return differences;
    }
    
    private int[] MinimizeDifferences(int[] differences) {
        int average = 0;
        
        average = SumDifferences(differences);
        average = average / differences.length;
        
        int temp[] = differences.clone();
        for(int i = 0; i < temp.length; i++)
        {
            temp[i] += (-average);
        }
        
        return temp;
    }
    
    private int SumDifferences(int[] differences) {
        int sum = 0;
        for(int i : differences)
        {
            sum += Math.abs(i);
        }
        return sum;
    }

    private boolean FinalStringCheck(Password tempP1, Password tempP2) {
        return tempP1.final_password.equals(tempP2.final_password);
    }
}

