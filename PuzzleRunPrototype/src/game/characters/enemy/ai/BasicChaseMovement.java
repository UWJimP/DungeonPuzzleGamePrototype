package game.characters.enemy.ai;

import game.board.GameObject;
import game.characters.Enemy;
import game.model.Direction;

/**
 * The basic movement for the enemy chasing the player.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 11/26/2017
 */
public class BasicChaseMovement implements Movement {

	private int knownPlayerX;
	
	private int knownPlayerY;
	
	public BasicChaseMovement(int thePlayerX, int thePlayerY) {
		knownPlayerX = thePlayerX;
		knownPlayerY = thePlayerY;
	}
	
	@Override
	public Direction move(GameObject[][] theBoard, Enemy theEnemy) {

		//Sets a variable to be returned by the method.
		Direction moveDirection = theEnemy.getFacingDirection();

		if(theEnemy.getLocationX() == knownPlayerX) {
			if(theEnemy.getLocationY() < knownPlayerY) {
				return Direction.SOUTH;
			} else {
				return Direction.NORTH;
			}
		}
		
		if(theEnemy.getLocationY() == knownPlayerY) {
			if(theEnemy.getLocationX() < knownPlayerY) {
				return Direction.EAST;
			} else {
				return Direction.WEST;
			}
		}
		
		
		/**
		//East
		for(int x = theEnemy.getLocationX(); x < theBoard[0].length; x++) {
			if(theBoard[theEnemy.getLocationY()][x].getSymbol() == 'p') {
				return Direction.EAST;
			} else if(theBoard[theEnemy.getLocationY()][x].getSymbol() == 'e' ||
					theBoard[theEnemy.getLocationY()][x].getSymbol() == 'x') {
				x = theBoard[0].length;
			}
		}
		//West
		for(int x = theEnemy.getLocationX(); x > 0; x--) {
			if(theBoard[theEnemy.getLocationY()][x].getSymbol() == 'p') {
				return Direction.WEST;
			} else if(theBoard[theEnemy.getLocationY()][x].getSymbol() == 'e' ||
					theBoard[theEnemy.getLocationY()][x].getSymbol() == 'x') {
				x = 0;
			}
		}
		//South
		for(int y = theEnemy.getLocationY(); y < theBoard[0].length; y++) {
			if(theBoard[y][theEnemy.getLocationX()].getSymbol() == 'p') {
				return Direction.SOUTH;
			} else if(theBoard[y][theEnemy.getLocationX()].getSymbol() == 'e' ||
					theBoard[y][theEnemy.getLocationX()].getSymbol() == 'x') {
				y = theBoard[0].length;
			}
		}
		//North
		for(int y = theEnemy.getLocationY(); y > 0; y--) {
			if(theBoard[y][theEnemy.getLocationX()].getSymbol() == 'p') {
				return Direction.NORTH;
			} else if(theBoard[y][theEnemy.getLocationX()].getSymbol() == 'e' ||
					theBoard[y][theEnemy.getLocationX()].getSymbol() == 'x') {
				y = theBoard[0].length;
			}
		}
		*/
		
		//If no line of sight, 
		
		return moveDirection;
	}

}
