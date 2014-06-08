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
    public KeyCheck()
    {
        keyEvents = new ArrayList<KeyEvent>();
        keyEvents.add(KeyEvent.VK_UP);
        keyEvents.add(KeyEvent.VK_RIGHT);
        keyEvents.add(KeyEvent.VK_LEFT);
        keyEvents.add(KeyEvent.VK_DOWN);
    }
    
    public int InvalidKeyCheck(KeyEvent e)
    {   
        // return 1 if on these keys are present
        if(keyEvents.contains(e.getKeyCode()))
            return 1;
        else
            return 0;
        
        
    }
    
    
    
}
