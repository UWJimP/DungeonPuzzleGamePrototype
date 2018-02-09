package game.controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.board.Board;

public class ReloadKey extends KeyAdapter {

	/**
	 * The keyboard event.
	 */
	private int myKeyEvent;
	
	private Board myBoard;
	
	public ReloadKey(int theKey, Board theBoard) {
		myKeyEvent = theKey;
		myBoard = theBoard;
	}
	
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        if (theEvent.getKeyCode() == myKeyEvent) {
            myBoard.playerReload();
        }
    }
	
}
