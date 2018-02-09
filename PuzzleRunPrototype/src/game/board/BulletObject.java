package game.board;

import game.model.Direction;

/**
 * The bullet object of the game.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 1/3/2018
 */
public class BulletObject extends GameObject {

	/**
	 * The Direction of the bullet is moving.
	 */
	private Direction myDirection;
	
	/**
	 * The location of the bullet.
	 */
	private int[] myLocation;
	
	/**
	 * Check to see if the bullet has acted in the array.
	 */
	private boolean myActed;
	
	public BulletObject(Direction theDirection, int theX, int theY) {
		super('b');
		myDirection = theDirection;
		myLocation = new int[]{theY, theX};
		myActed = false;
		this.directionImage(theDirection);
	}

	public Direction getDirection() {
		return myDirection;
	}
	
	public void setLocation(int theX, int theY) {
		myLocation[0] = theY;
		myLocation[1] = theX;
	}
	
	public void setActed(boolean acted) {
		myActed = acted;
	}
	
	public int getX() {
		return myLocation[1];
	}
	
	public int getY() {
		return myLocation[0];
	}
	
	public boolean hasActed() {
		return myActed;
	}
	
	private void directionImage(Direction theDirection) {
		if(theDirection == Direction.NORTH) {
			this.setImage("kunai/kunaiNorth.png");
		} else if(theDirection == Direction.EAST) {
			this.setImage("kunai/kunaiEast.png");
		} else if(theDirection == Direction.WEST) {
			this.setImage("kunai/kunaiWest.png");
		}else {
			this.setImage("kunai/kunaiSouth.png");
		}
	}	
}
