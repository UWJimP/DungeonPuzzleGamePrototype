package game.controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.board.Board;

public class FireKey extends KeyAdapter {

	private int myKeyEvent;
	
	private Board myBoard;
	
	public FireKey(int theKey, Board theBoard) {
		myKeyEvent = theKey;
		myBoard = theBoard;
	}
	
    /**
     * Calls and moves the character in the key's set direction.
     */
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        if (theEvent.getKeyCode() == myKeyEvent && !myBoard.hasRemainingBullets() &&
        		myBoard.isGameOver() != true) {
            myBoard.playerShoot();
        }
    }
}
