/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package features;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amit
 */
public class KeyCheck {
    
    List keyEvents; 
    
    List CtrlPlusSomeKey; // checking for shortcuts key pressed
    
    public KeyCheck()
    {
        keyEvents = new ArrayList<KeyEvent>();
        keyEvents.add(KeyEvent.VK_UP);
        keyEvents.add(KeyEvent.VK_RIGHT);
        keyEvents.add(KeyEvent.VK_LEFT);
        keyEvents.add(KeyEvent.VK_DOWN);
        keyEvents.add(KeyEvent.VK_ESCAPE);
        
        //for shortcuts
        CtrlPlusSomeKey = new ArrayList<KeyEvent>();
        CtrlPlusSomeKey.add(KeyEvent.VK_F);
        
        
    }
    
  
    
    public int InvalidKeyCheck(KeyEvent e)
    {   
        // return 1 if on these keys are present
        if(keyEvents.contains(e.getKeyCode()))
            return 1;
        else
            return 0;
        
        
    }
    
    public int ValidateShortcut(KeyEvent e,javax.swing.JFrame comp)
    {
        
        if(CtrlPlusSomeKey.contains(e.getKeyCode()))
        {   
            return e.getKeyCode();
        }
        else
            return -1;
    }
    
}           
 