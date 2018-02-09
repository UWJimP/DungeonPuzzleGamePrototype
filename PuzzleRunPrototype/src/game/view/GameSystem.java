package game.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import game.board.Board;
import game.controls.ExitKey;
import game.controls.FireKey;
import game.controls.MovementKey;
import game.controls.ReloadKey;
import game.model.Direction;

/**
 * Runs the game's functions and set up.
 * 
 * @author Jim phanjim2@hotmail.com
 * @version 12/14/2017
 */
public class GameSystem {

	private JFrame myFrame;
	
	private Board myBoard;
	
	private GamePanel panel;
	
	public GameSystem(JFrame theFrame) {
		myFrame = theFrame;
		myBoard = new Board("test5.txt");
		panel = new GamePanel();
		myFrame.add(panel);
		myBoard.addObserver(panel);
		myBoard.update();
	}
	
	public void newGame() {
		//TODO
	}
	
	public void setUp() {
		deleteListeners();
		setControls();
	}
	
	private void setControls() {
		for(final KeyListener key : this.createKeyListeners()) {
			myFrame.addKeyListener(key);
		}
	}
	
	private void deleteListeners() {
		for(final KeyListener key : myFrame.getKeyListeners()) {
			myFrame.removeKeyListener(key);
		}
	}
	
	private List<KeyListener> createKeyListeners() {
		ArrayList<KeyListener> listeners = new ArrayList<>();
		listeners.add(new MovementKey(KeyEvent.VK_W, Direction.NORTH, myBoard));
		listeners.add(new MovementKey(KeyEvent.VK_S, Direction.SOUTH, myBoard));
		listeners.add(new MovementKey(KeyEvent.VK_D, Direction.EAST, myBoard));
		listeners.add(new MovementKey(KeyEvent.VK_A, Direction.WEST, myBoard));
		listeners.add(new FireKey(KeyEvent.VK_SPACE, myBoard));
		listeners.add(new ExitKey(KeyEvent.VK_ESCAPE));
		listeners.add(new ReloadKey(KeyEvent.VK_R, myBoard));
		return listeners;
	}
}
