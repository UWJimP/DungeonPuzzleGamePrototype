package game.controls;

import java.awt.event.KeyAdapter;

/**
 * The Abstract class that sets up the keyboard controls.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 12/14/2017
 */
public abstract class AbstractKeyboard extends KeyAdapter {

	/**
	 * The Key Event that sees if the action should be performed.
	 */
	private int myKeyEvent;
	
	public AbstractKeyboard(final int theKeyEvent) {
		super();
		myKeyEvent = theKeyEvent;
	}
	
    /**
     * Sets the KeyEvent.
     * 
     * @param theKeyEvent The new KeyEvent.
     */
    public void setKeyEvent(final int theKeyEvent) {
        myKeyEvent = theKeyEvent;
    }
    
    /**
     * Return the current Key Event.
     * 
     * @return The Key Event.
     */
    public int getKeyEvent() {
        return myKeyEvent;
    }
}
