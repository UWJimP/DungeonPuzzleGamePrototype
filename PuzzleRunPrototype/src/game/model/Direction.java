package game.model;

import java.util.ArrayList;
import java.util.List;

import game.board.GameObject;

/**
 * The static values of the directions.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 11/15/17
 */
public enum Direction {
	
	/**
	 * The North direction.
	 */
	NORTH,
	
	/**
	 * The East direction.
	 */
	EAST, 
	
	/**
	 * The South direction.
	 */
	SOUTH,
	
	/**
	 * The West direction.
	 */
	WEST,
	
	/**
	 * The Center direction.
	 */
	CENTER;
	
	/**
	 * The value used to check if a location is empty.
	 */
	private static final char PASSABLE = ' ';
	
	/**
	 * Returns a list of directions the character can move.
	 * 
	 * @param theBoard The game board.
	 * @param theX the current location's X-axis.
	 * @param theY the current location's Y-axis.
	 * @return A List of possible directions.
	 */
	public static List<Direction> getDirectionalOptions(GameObject[][] theBoard, 
			int theX, int theY) {
		ArrayList<Direction> directions = new ArrayList<>();
		int maxX = theBoard[0].length, maxY = theBoard.length;
		
		if(checkArray(theX - 1, theY, maxX, maxY) 
				&& theBoard[theY][theX - 1].getSymbol() == PASSABLE) {
			directions.add(WEST);
		}
		if(checkArray(theX + 1, theY, maxX, maxY) 
				&& theBoard[theY][theX + 1].getSymbol() == PASSABLE) {
			directions.add(EAST);
		}
		if(checkArray(theX, theY - 1, maxX, maxY)
				&& theBoard[theY - 1][theX].getSymbol() == PASSABLE) {
			directions.add(NORTH);
		}
		if(checkArray(theX, theY + 1, maxX, maxY)
				&& theBoard[theY + 1][theX].getSymbol() == PASSABLE) {
			directions.add(SOUTH);
		}
		
		//Adds this method if there is no possible movement for the character.
		if(directions.isEmpty()) {
			directions.add(CENTER);
		}
		
		return directions;
	}
	
	/**
	 * Returns the opposite facing position.
	 * 
	 * @param theDirection The Direction.
	 * @return The opposite of that Direction.
	 */
	public static Direction getOppositeDirection(Direction theDirection) {
		Direction opposite = theDirection;
		
		if(theDirection == NORTH) {
			opposite = SOUTH;
		} else if(theDirection == SOUTH) {
			opposite = NORTH;
		} else if(theDirection == WEST) {
			opposite = EAST;
		} else if(theDirection == EAST) {
			opposite = WEST;
		}
		
		return opposite;
	}
	
	/**
	 * The Position if the facing direction were to turn right.
	 * 
	 * @param theDirection The facing Direction.
	 * @return The facing direction after turning right.
	 */
	public static Direction getRightDirection(Direction theDirection) {
		Direction opposite = theDirection;
		
		if(theDirection == NORTH) {
			opposite = EAST;
		} else if(theDirection == SOUTH) {
			opposite = WEST;
		} else if(theDirection == WEST) {
			opposite = SOUTH;
		} else if(theDirection == EAST) {
			opposite = NORTH;
		}
		
		return opposite;
	}
	
	/**
	 * The Position if the facing direction were to turn left.
	 * 
	 * @param theDirection The facing Direction.
	 * @return The facing direction after turning left.
	 */
	public static Direction getLeftDirection(Direction theDirection) {
		Direction opposite = theDirection;
		
		if(theDirection == NORTH) {
			opposite = WEST;
		} else if(theDirection == SOUTH) {
			opposite = EAST;
		} else if(theDirection == WEST) {
			opposite = SOUTH;
		} else if(theDirection == EAST) {
			opposite = NORTH;
		}
		
		return opposite;
	}
	
	/**
	 * Provides the location after using the Direction.
	 * 
	 * @param theDirection The moving direction.
	 * @param theX The X location.
	 * @param theY The Y location.
	 * @return An array for the new position.
	 */
	public static int[] newPosition(Direction theDirection, int theX, int theY) {
		int[] position = {theY, theX};
		
		if(theDirection == NORTH) {
			position[0]--;
		} else if(theDirection == SOUTH) {
			position[0]++;
		} else if(theDirection == EAST) {
			position[1]++;
		} else if(theDirection == WEST) {
			position[1]--;
		}
		
		return position;
	}	
	
	/**
	 * Converts a symbol to a direction.
	 * 
	 * @param theDirection The symbol's direction.
	 * @return The converted value to direction.
	 */
	public static Direction getDirection(char theDirection) {
		Direction direction;
		
		if(theDirection == 'n') {
			direction = NORTH;
		} else if(theDirection == 'e') {
			direction = EAST;
		} else if(theDirection == 's') {
			direction = SOUTH;
		} else {
			direction = WEST;
		}
		
		return direction;
	}
	/**
	 * Checks if the values are within the array's parameters.
	 * 
	 * @param theX The value to check for x.
	 * @param theY The value to check for y.
	 * @param theMaxX The max x value of the array.
	 * @param theMaxY The max y value of the array.
	 * @return If the values are within the parameter.
	 */
	private static boolean checkArray(int theX, int theY, int theMaxX, int theMaxY) {
		boolean withinValue = true;
		
		if(theX < 0 || theY < 0) {
			withinValue = false;
		} else if(theX > theMaxX || theY > theMaxY) {
			withinValue = false;
		}
		
		return withinValue;
	}
}
