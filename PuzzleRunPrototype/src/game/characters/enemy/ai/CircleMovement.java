package game.characters.enemy.ai;

import java.util.List;

import game.board.GameObject;
import game.characters.Enemy;
import game.model.Direction;

/**
 * The movement for the enemy circling an area.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 11/20/2017
 */
public class CircleMovement implements Movement{

	@Override
	public Direction move(GameObject[][] theBoard, Enemy theEnemy) {
		//Sets a variable to be returned by the method.
		Direction moveDirection = theEnemy.getFacingDirection();
		
		//Gets a list of possible directions.
		List<Direction> myOptions = Direction.getDirectionalOptions(theBoard, theEnemy.getLocationX(),
				theEnemy.getLocationY());

		//If the option of Center is possible then no other possible directions exist.
		if(myOptions.contains(Direction.CENTER)) {
			moveDirection = Direction.CENTER;
			
		//Otherwise check through the array for an option.
		} else {
			
			//Will turn right if possible.
			if(myOptions.contains(Direction.getRightDirection(theEnemy.getFacingDirection()))) {
				moveDirection = Direction.getRightDirection(theEnemy.getFacingDirection());
			
			//Will go forward if possible.
			} else if(myOptions.contains(theEnemy.getFacingDirection())) {
				moveDirection = theEnemy.getFacingDirection();
				
			//Will go left is possible.
			} else if(myOptions.contains(Direction.getLeftDirection(theEnemy.getFacingDirection()))) {		
				moveDirection = Direction.getLeftDirection(theEnemy.getFacingDirection());
			
			//Will go opposite otherwise.
			} else {
				moveDirection = Direction.getOppositeDirection(theEnemy.getFacingDirection());
			}
		}
		
		//Returns which ever direction is decided.
		return moveDirection;
	}

}
