package game.characters.enemy;

import game.characters.Enemy;
import game.characters.enemy.ai.AlertBehavior;
import game.characters.enemy.ai.BasicBehavior;
import game.characters.enemy.ai.BasicChaseMovement;
import game.characters.enemy.ai.BasicMovement;
import game.model.Direction;

/**
 * The basic Enemy type.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 11/17/2017
 */
public class BasicEnemy extends Enemy {	
	
	/**
	 * The Image set of the enemy.
	 */
	private static final String[][] IMAGE_TILE = {{"enemyNorth1.png","enemyNorth2.png"},
			{"enemyEast1.png","enemyEast2.png"}, {"enemySouth1.png", "enemySouth2.png"},
			{"enemyWest1.png", "enemyWest2.png"}};
	
	private int imageFrame;
			
	public BasicEnemy(Direction theStartDirection) {
		super('e', theStartDirection, new BasicMovement(), new BasicBehavior());
		imageFrame = 0;
		this.updateImage();
	}

	@Override
	public void notifyEnemy(boolean chaseTarget, int theX, int theY) {
		if(chaseTarget) {
			this.changeBehavior(new AlertBehavior(theX, theY));
			this.changeMovement(new BasicChaseMovement(theX, theY));
		} else {
			this.changeBehavior(new BasicBehavior());
			this.changeMovement(new BasicMovement());
		}
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
		String filePath = "enemy/";
		
		if (this.getFacingDirection() == Direction.NORTH) {
			filePath += IMAGE_TILE[0][imageFrame];
		} else if (this.getFacingDirection() == Direction.EAST) {
			filePath += IMAGE_TILE[1][imageFrame];
		} else if (this.getFacingDirection() == Direction.SOUTH) {
			filePath += IMAGE_TILE[2][imageFrame];
		} else if (this.getFacingDirection() == Direction.WEST) {
			filePath += IMAGE_TILE[3][imageFrame];
		} else {
			filePath = getImage().getPath();
		}
		
		setImage(filePath);
	}

	
	
}
