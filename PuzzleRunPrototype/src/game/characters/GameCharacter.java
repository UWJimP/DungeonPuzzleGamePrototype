package game.characters;

import game.board.GameObject;
import game.exceptions.NegativeErrorException;
import game.model.Direction;

/**
 * The abstract class used by the game characters.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 11/15/2017
 */
public abstract class GameCharacter extends GameObject {
	
	/**
	 * The location of the character on the board.
	 */
	private int[] myLocation;
	
	/**
	 * The direction the character is facing.
	 */
	private Direction myFacingDirection;
	
	/**
	 * The current amount of ammo the character has.
	 */
	private int myCurrentAmmo;
	
	/**
	 * The max amount of ammo a character can have.
	 */
	private int myMaxAmmo;
	
	/**
	 * The constructor for the characters in the game.
	 * 
	 * @param theCharacter The 'character' letter represented by the game.
	 * @param theStartDirection the starting direction.
	 * @param theAmmo The max amount of ammo a character can have.
	 */
	public GameCharacter(char theCharacter, Direction theStartDirection, int theAmmo) {
		super(theCharacter);
		myLocation = new int[]{0,0};
		myFacingDirection = theStartDirection;
		myCurrentAmmo = theAmmo;
		myMaxAmmo = theAmmo;
	}
	
	/**
	 * Sets the character's location.
	 * 
	 * @param theLocation The x,y location on the board.
	 */
	public void setLocation(int theX, int theY) {
		myLocation[0] = theY;
		myLocation[1] = theX;
	}
	
	/**
	 * Set the character's current facing direction.
	 * 
	 * @param theDirection The Direction.
	 */
	public void setFacingDirection(Direction theDirection){
		myFacingDirection = theDirection;
	}

	/**
	 * Set the amount of ammo a character has.
	 * 
	 * @param theAmount The amount of ammo a character has.
	 */
	public void setAmmo(int theAmount) {
		if (theAmount > -1) {
			myCurrentAmmo = theAmount;
		} else {
			try {
				throw new NegativeErrorException();
			} catch (NegativeErrorException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Updates the Character's image with any necessary changes.
	 */
	public abstract void updateImage();
	
	/**
	 * Refreshes a character ammo supply.
	 */
	public void reload() {
		myCurrentAmmo = myMaxAmmo;
	}
	
	/**
	 * Tells if the character can shoot at the location.
	 * 
	 * @param theCharacter
	 * @return If the location can be shot.
	 */
	public boolean canShoot(GameObject[][] theBoard) {
		int x = this.myLocation[1], y = myLocation[0];
		boolean shot = true;
		
		if(myFacingDirection == Direction.NORTH) {
			y--;
		} else if(myFacingDirection == Direction.SOUTH) {
			y++;
		} else if(myFacingDirection == Direction.EAST) {
			x++;
		} else { //If West.
			x--;
		}
		
		GameObject temp = theBoard[y][x];
		if(temp.getSymbol() == 'x' || myCurrentAmmo <= 0) {
			shot = false;
		}
		
		return shot;
	}
	
	/**
	 * Returns the array of the character's location.
	 * 
	 * @return The character's location as an array.
	 */
	public int[] getLocation() {
		return myLocation.clone();
	}

	/**
	 * Returns the character's x location.
	 * 
	 * @return The character's x location.
	 */
	public int getLocationX() {
		return myLocation[1];
	}
	
	/**
	 * Returns the character's y location.
	 * 
	 * @return The character's y location.
	 */
	public int getLocationY() {
		return myLocation[0];
	}
	
	/**
	 * Returns the facing direction of the character.
	 * 
	 * @return The direction the character is facing.
	 */
	public Direction getFacingDirection() {
		return myFacingDirection;
	}
	
	/**
	 * Returns the amount of ammo remaining for the character.
	 * 
	 * @return The amount of ammo remaining for the character.
	 */
	public int getCurrAmmo() {
		return myCurrentAmmo;
	}
	
	/**
	 * Returns the maximum amount of ammo a character can carry.
	 * 
	 * @return The maximum amount of ammo a character can carry.
	 */
	public int getMaxAmmo() {
		return myMaxAmmo;
	}
	
	/**
	 * Animate the character by changing their frame.
	 */
	public abstract void animate();
}
