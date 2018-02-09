package game.characters.enemy.ai;

import game.board.GameObject;
import game.characters.Enemy;
import game.model.Direction;

/**
 * The methods used by the AI movement.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 11/17/2017
 */
public interface Movement {

	public abstract Direction move(GameObject[][] theBoard, Enemy theEnemy);
	
}
