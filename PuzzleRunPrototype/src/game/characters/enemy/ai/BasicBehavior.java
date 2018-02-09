package game.characters.enemy.ai;

import game.board.GameObject;
import game.characters.Enemy;
import game.model.Actions;

/**
 * The Basic Behavior of the enemy.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 2/1/2018
 */
public class BasicBehavior extends Behavior {

	@Override
	public Actions performAction(GameObject[][] theBoard, Enemy enemy) {
		return Actions.MOVE;
	}

}
