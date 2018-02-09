package game.characters.enemy.ai;

import game.board.GameObject;
import game.characters.Enemy;
import game.model.Actions;

/**
 * The abstract behavior template performed by the enemy.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 2/1/2018
 */
public abstract class Behavior {
	
	/**
	 * The constructor. Creates the Behavior object.
	 */
	public Behavior() {
	}
	
	/**
	 * Returns an action that will be performed by the enemy.
	 * 
	 * @param theBoard The game board.
	 * @return The Action performed by the enemy.
	 */
	public abstract Actions performAction(GameObject[][] theBoard, Enemy enemy);
}
