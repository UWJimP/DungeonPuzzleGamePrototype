package game.characters;

import game.board.GameObject;
import game.characters.enemy.ai.Behavior;
import game.characters.enemy.ai.Movement;
import game.model.Actions;
import game.model.Direction;

/**
 * Methods used by the enemy.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 11/17/2017
 */
public abstract class Enemy extends GameCharacter {
	
	private static final int AMMO = 2;
	
	private Movement myMovement;
	
	private Behavior myBehavior;
	
	public Enemy(char theCharacter, Direction theStartDirection, Movement theMovement,
			Behavior theBehavior) {
		super(theCharacter, theStartDirection, AMMO);
		myMovement = theMovement;
		myBehavior = theBehavior;
	}
	
	/**
	 * Changes the enemy's behavior.
	 * 
	 * @param theBehavior The behavior.
	 */
	public void changeBehavior(Behavior theBehavior) {
		myBehavior = theBehavior;
	}

	/**
	 * Changes the enemy's movement path.
	 * 
	 * @param theMovement The movement.
	 */
	public void changeMovement(Movement theMovement) {
		myMovement = theMovement;
	}
	
	/**
	 * The move function performed by the character.
	 * 
	 * @param theBoard The game Board.
	 * @return The character location after moving.
	 */
	public Direction move(GameObject[][] theBoard) {
		Direction direction = myMovement.move(theBoard.clone(), this);
		
		if(direction != Direction.CENTER) {
			this.setFacingDirection(direction);
		}
		
		return direction;
	}
	
	/**
	 * Returns an action to the board to determine how the enemy should act.
	 * 
	 * @param theBoard The game board.
	 * @return An action from the enemy.
	 */
	public Actions performAction(GameObject[][] theBoard) {
		return myBehavior.performAction(theBoard, this);
	}
	
	/**
	 * A method used to notify the enemy and tell if the behavior changes.
	 * 
	 * @param chaseTarget Tells if the enemy should be notified or not.
	 * @param theX The player's x location.
	 * @param theY The player's y location.
	 */
	public abstract void notifyEnemy(boolean chaseTarget, int theX, int theY);
	
	/**
	 * Method used for the enemy to scan for the player.
	 * 
	 * @param theBoard Scans the board.
	 * @return If the player is within range of the player.
	 */
	public boolean scanBoard(GameObject[][] theBoard) {
		int theX = this.getLocationX(), theY = this.getLocationY();
		
		//Check North
		for(int y = theY - 1; y > 0; y--) {
			if(theBoard[y][theX].getSymbol() == 'p') {
				this.setFacingDirection(Direction.NORTH);
				return true;
			} else if(theBoard[y][theX].getSymbol() != ' ') {
				y = 0;
			}
		}
		//Check South
		for(int y = theY + 1; y < theBoard.length; y++) {
			if(theBoard[y][theX].getSymbol() == 'p') {
				this.setFacingDirection(Direction.SOUTH);
				return true;
			} else if(theBoard[y][theX].getSymbol() != ' ') {
				y = theBoard.length;
			}
		}
		//Check West
		for(int x = theX - 1; x > 0; x--) {
			if(theBoard[theY][x].getSymbol() == 'p') {
				this.setFacingDirection(Direction.EAST);
				return true;
			} else if(theBoard[theY][x].getSymbol() != ' ') {
				x = 0;
			}
		}
		//Check East
		for(int x = theX + 1; x < theBoard[0].length; x++) {
			if(theBoard[theY][x].getSymbol() == 'p') {
				this.setFacingDirection(Direction.EAST);
				return true;
			} else if(theBoard[theY][x].getSymbol() != ' ') {
				x = theBoard.length;
			}
		}
		return false;
	}
	
	@Override
	public abstract void updateImage();
}
