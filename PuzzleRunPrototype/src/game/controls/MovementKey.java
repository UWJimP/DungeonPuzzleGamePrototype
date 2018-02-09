package game.controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.board.Board;
import game.model.Direction;

/**
 * The key event that moves the player.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 12/14/2017
 */
public class MovementKey extends KeyAdapter {

	/**
	 * The Key Event that sees if the action should be performed.
	 */
	private int myKeyEvent;
	
	/**
	 * The direction the character moves in.
	 */
	private Direction myMoveDirection;
	
	/**
	 * The board the keyboard interacts with.
	 */
	private Board myBoard;
	
	public MovementKey(final int theKeyEvent, final Direction theDirection, 
			final Board theBoard) {
		myKeyEvent = theKeyEvent;
		myMoveDirection = theDirection;
		myBoard = theBoard;
	}
	
    /**
     * Calls and moves the character in the key's set direction.
     */
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        if (theEvent.getKeyCode() == myKeyEvent && !myBoard.hasRemainingBullets() &&
        		myBoard.isGameOver() != true) {
            myBoard.movePlayer(myMoveDirection);
            System.out.println(myBoard.toString());
        }
    }
}
