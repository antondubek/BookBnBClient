/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Dialogs;

import javax.swing.JFrame;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author amakepeace
 */
public class AddBookDialogTest {
    
    AddBookDialog dialog;
   
    

    @Test
    public void testCheckBookInput() {
        
        JFrame defaultFrame = new JFrame();
        dialog = new AddBookDialog(defaultFrame, true);
        
        assertTrue(dialog.checkBookInput("1905026595", "Darren Brown"
                , "Confessions of a Conjuror", "1st"));
        
        assertFalse(dialog.checkBookInput(null, "Darren Brown"
                , "Confessions of a Conjuror", "1st"));
        
        assertFalse(dialog.checkBookInput("", "Darren Brown"
                , "Confessions of a Conjuror", "1st"));
        
        assertFalse(dialog.checkBookInput("ABC", "Darren Brown"
                , "Confessions of a Conjuror", "1st"));
        
        assertFalse(dialog.checkBookInput("????*^%", "Darren Brown"
                , "Confessions of a Conjuror", "1st"));
        
        
        
    }
    
}
