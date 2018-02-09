package game.characters;

import game.model.Direction;

/**
 * The character used by the player.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 11/15/2017
 */
public class PlayerCharacter extends GameCharacter {
	
	/**
	 * A set of images used for the player.
	 */
	private static final String[][] IMAGE_TILE = {{"hero_N_1.png", "hero_N_2.png"},
			{"hero_E_1.png", "hero_E_2.png"}, {"hero_S_1.png", "hero_S_2.png"},
			{"hero_W_1.png", "hero_W_2.png"}};
	
	/**
	 * If the player was killed by the enemy.
	 */
	private boolean myDeath;
	
	/**
	 * The current frame the character is on.
	 */
	private int imageFrame;

	/**
	 * The constructor. Creates a new character.
	 * 
	 * @param theStartDirection The starting direction of the character.
	 */
	public PlayerCharacter(Direction theStartDirection) {
		super('p', theStartDirection, 3);
		myDeath = false;
		this.updateImage();
		imageFrame = 0;
	}
	
	/**
	 * If the player is dead.
	 * 
	 * @param if they are dead.
	 */
	public void setDeath(boolean isDead) {
		myDeath = isDead;
	}
	
	/**
	 * Tells if the character is dead.
	 * 
	 * @return Tells if the character dead.
	 */
	public boolean isDead() {
		return myDeath;
	}
	
	/**
	 * Tells if the game is over.
	 */
	public void gameOver() {
		myDeath = true;
	}
	
	@Override
	public void animate() {
		if(imageFrame == 1) {
			imageFrame = 0;
		} else {
			imageFrame = 1;
		}
		updateImage();
	}
	
	@Override
	public void updateImage() {
		String filePath = "player/";
		if(getFacingDirection() == Direction.NORTH) {
			filePath += IMAGE_TILE[0][imageFrame];
		} else if(getFacingDirection() == Direction.EAST) {
			filePath += IMAGE_TILE[1][imageFrame];
		} else if(getFacingDirection() == Direction.SOUTH) {
			filePath += IMAGE_TILE[2][imageFrame];
		} else if(getFacingDirection() == Direction.WEST) {
			filePath += IMAGE_TILE[3][imageFrame];
		} else {
			filePath = this.getImage().getPath();
		}
		this.setImage(filePath);
	}
}
