package game.controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The Key used to escape the game.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 1/9/2018
 */
public class ExitKey extends KeyAdapter {
	
	/**
	 * The keyboard event.
	 */
	private int myKeyEvent;
	
	/**
	 * The constructor. Stores the key value for the event.
	 * 
	 * @param theKey The Event;
	 */
	public ExitKey(int theKey) {
		myKeyEvent = theKey;
	}
	
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        if (theEvent.getKeyCode() == myKeyEvent) {
            System.exit(0);
        }
    }	
}
