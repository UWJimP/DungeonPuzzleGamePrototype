package game.characters.enemy.ai;

import java.util.List;

import game.board.GameObject;
import game.characters.Enemy;
import game.model.Direction;

/**
 * The Basic Movement for the enemy.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 11/17/2017
 */
public class BasicMovement implements Movement{
	
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
			//Will go forward is possible.
			if(myOptions.contains(theEnemy.getFacingDirection())) {
				moveDirection = theEnemy.getFacingDirection();
				
			//Will go right if cannot go forward.
			} else if(myOptions.contains(Direction.getRightDirection(theEnemy.getFacingDirection()))) {
				moveDirection = Direction.getRightDirection(theEnemy.getFacingDirection());
				
			//Will go left if cannot go right.
			} else if(myOptions.contains(Direction.getLeftDirection(theEnemy.getFacingDirection()))) {
				moveDirection = Direction.getLeftDirection(theEnemy.getFacingDirection());
				
			//Will go back if no other possible movement.
			} else {
				moveDirection = Direction.getOppositeDirection(theEnemy.getFacingDirection());
			}
		}
		
		//Returns which ever direction the ai decides.
		return moveDirection;
	}
}
