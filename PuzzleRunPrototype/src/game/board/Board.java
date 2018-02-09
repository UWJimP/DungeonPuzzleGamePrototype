package game.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.Timer;

import game.characters.Enemy;
import game.characters.GameCharacter;
import game.characters.PlayerCharacter;
import game.characters.enemy.BasicEnemy;
import game.model.Actions;
import game.model.Direction;

/**
 * The board program that creates boards based on
 * text files created in the system. test
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 11/15/2017
 */
public class Board extends Observable {

	/**
	 * Empty space on the board.
	 */
	private static final GameObject EMPTY_SPACE = new EmptyObject();
	
	/**
	 * Wall space on the board. It is not passable and blocks line of sight.
	 */
	private static final GameObject WALL_SPACE = new WallObject();
	
	private static final GameObject WALL_OPEN_SPACE = new WallOpenObject();
	
	private static final GameObject WALL_OPEN_EAST_SPACE = new WallOpenEastObject();
	
	private static final GameObject WALL_OPEN_WEST_SPACE = new WallOpenWestObject();
	
	/**
	 * The level's game character.
	 */
	private PlayerCharacter myPlayer;
	
	/**
	 * If the board has been cleared or not.
	 */
	private boolean myClear;
	
	/**
	 * The 2D array of the board. It follows width by height.
	 */
	private GameObject[][] myBoard;
	
	/**
	 * The Width of the board.
	 */
	private int myWidth;
	
	/**
	 * The Height of the board.
	 */
	private int myHeight;
	
	/**
	 * Stores a list of enemy waiting to move.
	 */
	private List<Enemy> waitingEnemy;

	/**
	 * Stores a list of enemy alive.
	 */
	private List<Enemy> livingEnemy;
	
	/**
	 * stores a list of bullets waiting to move.
	 */
	private List<BulletObject> waitingBullet;
	
	/**
	 * List of move options the player has.
	 */
	private List<Direction> myPlayerMoveOptions;
	
	/**
	 * List of Enemies performing the shoot action.
	 */
	private List<Enemy> myShootActors;
	
	/**
	 * List of Enemies performing the move action.
	 */
	private List<Enemy> myMoveActors;
	
	/**
	 * Timer used to handle with the projectiles.
	 */
	private AnimationTimer myBulletTimer;
	
	/**
	 * Timer used to handle with the Character animation.
	 */
	private AnimationTimer myCharacterTimer;
	
	/**
	 * Tells if the game is over.
	 */
	private boolean gameOver;
	
	/**
	 * Constructor designed to build a level from a textfile.
	 * 
	 * @param fileName Path to the file to be read.
	 */
	public Board(String fileName) {
		this.waitingEnemy = new ArrayList<Enemy>();
		this.livingEnemy = new ArrayList<Enemy>();
		this.waitingBullet = new ArrayList<BulletObject>();
		this.myBulletTimer = new AnimationTimer(this, 30);
		this.myCharacterTimer = new AnimationTimer(this, 1000);
		this.myMoveActors = new ArrayList<Enemy>();
		this.myShootActors = new ArrayList<Enemy>();
		gameOver = false;
		
		try {
			FileReader file = new FileReader("levels/" + fileName);
			BufferedReader reader = new BufferedReader(file);
	
			//Read the board size from the file.
			char[] size = reader.readLine().toCharArray();
			int index = 0;
			
			//Sets the board's width from the file.
			myWidth = 0;			
			while(size[index] != ',') {
				myWidth = myWidth * 10; //Moves the current digits a place over.
				myWidth += size[index] - '0'; //Converts the char ascii to int.
				index++;
			}
			
			//Sets the board's height from the file.
			index++; //Increase the index by 1 from the comma.
			myHeight = 0;
			//Sorts through the remainder of the values.
			while(index < size.length) {
				myHeight = myHeight * 10; //Moves the current digits a place over.
				myHeight += size[index] - '0'; //Converts the char ASCII to int.
				index++;
			}
			
			//Once the board size has been determined, draw it out.
			myBoard = new GameObject[myHeight][myWidth];
			String line;
			int row = 0;
			while((line = reader.readLine()) != null) {
				char[] characters = line.toCharArray();
				int column = 0;
				for(int entry = 0; entry < characters.length; entry += 2) {
					if(characters[entry] == 'x') {
						if(characters[entry + 1] == '0') {
							myBoard[row][column] = WALL_SPACE;
						} else if(characters[entry + 1] == '1') {
							myBoard[row][column] = WALL_OPEN_SPACE;
						} else if(characters[entry + 1] == '2') {
							myBoard[row][column] = WALL_OPEN_WEST_SPACE;
						} else if(characters[entry + 1] == '3') {
							myBoard[row][column] = WALL_OPEN_EAST_SPACE;
						}
					} else if(characters[entry] == ' ') {
						myBoard[row][column] = EMPTY_SPACE;
					} else if(characters[entry] == 'p') {
						myPlayer = new PlayerCharacter(
								Direction.getDirection(characters[entry + 1]));
						myBoard[row][column] = myPlayer;
						myPlayer.setLocation(column, row);
					} else if(characters[entry] == 'e') {
						Enemy temp = new BasicEnemy(Direction.getDirection(characters[entry + 1]));
						myBoard[row][column] = temp;
						temp.setLocation(column, row);
						this.livingEnemy.add(temp);
					}
					column++;
				}				
				row++;
			}
			
			reader.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(this.toString()); //Debug value for testing.
		myPlayerMoveOptions = Direction.getDirectionalOptions(myBoard, myPlayer.getLocationX(),
				myPlayer.getLocationY());
		myBulletTimer.start();
		myCharacterTimer.start();
	}
	
	/**
	 * Returns the char value of the location.
	 * 
	 * @param theX The location on the x-axis.
	 * @param theY The location on the y-axis.
	 * @return The char value of that location.
	 */
	public char getLocationChar(int theX, int theY) {
		return myBoard[theY][theX].getSymbol();
	}
	
	/**
	 * Sets an object to the location on the board.
	 * 
	 * @param theObject The game object.
	 * @param theX The location on the x-axis.
	 * @param theY The location on the y-axis.
	 */
	public void setLocationObject(GameObject theObject, int theX, int theY) {
		myBoard[theY][theX] = theObject;
	}
	
	/**
	 * Move the player in a direction.
	 * 
	 * @param theDirection The direction the player is facing.
	 * @return If the player moved.
	 */
	public boolean movePlayer(Direction theDirection) {
		boolean moved = false;
		int[] location = {myPlayer.getLocationY(), myPlayer.getLocationX()};
		
		/**
		 * If the player input exist in my options, perform the movement.
		 */
		if(this.myPlayerMoveOptions.contains(theDirection)) {
			moved = true;
			this.moveObject(theDirection, location);
		}
		
		//If the player had moved perform all the secondary actions.
		if(moved) {
			this.setActions();
			//Player must move first.
			this.moveCharacter(myPlayer, location[1], location[0]);
			myPlayer.setFacingDirection(theDirection);
			myPlayer.animate();			
			this.performAllMove();
			this.performAllShoot();
			this.moveBullets();
			if(!gameOver) {
				this.notifyEnemies();
			}
			this.update();
		}

		//Final player based functions.
		myPlayer.setLocation(location[1], location[0]);
		myPlayerMoveOptions = Direction.getDirectionalOptions(myBoard, myPlayer.getLocationX(),
				myPlayer.getLocationY());
		return moved;
	}
	
	public void playerShoot() {
		boolean shot = myPlayer.canShoot(myBoard.clone());

		if (shot) {
			this.setActions();
			this.performAllMove();
			shoot(myPlayer);
			this.performAllShoot();
			this.moveBullets();
			this.update();
		}
	}
	
	/**
	 * If the game is cleared.
	 * 
	 * @return If the player reached the goal.
	 */
	public boolean isClear() {
		return myClear;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public void playerReload() {
		if(myPlayer.getCurrAmmo() < myPlayer.getMaxAmmo()) {
			myPlayer.reload();
			this.setActions();
			this.performAllMove();
			this.performAllShoot();
			this.moveBullets();
			this.update();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder map = new StringBuilder();
		
		for(int row = 0; row < myHeight; row++) {			
			for(int column = 0; column < myWidth; column++) {			
				if(myBoard[row][column] == null) {
					map.append("n");
				} else {
					map.append(myBoard[row][column].getSymbol());
				}
				
			}
			
			map.append("\n");
		}
		
		return map.toString();
	}	
	
	/**
	 * Moves all the bullets on the board.
	 * 
	 * @return If there are remaining bullets.
	 */
	private boolean moveBullets() {
		boolean remaining = true;
		List<BulletObject> movingBullets = new ArrayList<BulletObject>();
		for(BulletObject bullet : waitingBullet) {
			if(!bullet.hasActed()) {
				
				//Bullet's location is removed.
				myBoard[bullet.getY()][bullet.getX()] = EMPTY_SPACE;
				GameObject target = getTarget(bullet);
				bullet.setActed(true);
				
				if (target.getSymbol() == ' ') {
					int[] location = Direction.newPosition(bullet.getDirection(),
							bullet.getX(), bullet.getY());					
					myBoard[location[0]][location[1]] = bullet;
					bullet.setLocation(location[1], location[0]);
					movingBullets.add(bullet);
					bullet.setActed(false);
				} else { //If the bullet cannot move, check the target.					
					if (target.getSymbol() == 'p') {
						System.out.print("YOU ARE DEAD");
						myPlayer.gameOver();
						myBoard[myPlayer.getLocationY()][myPlayer.getLocationX()] = EMPTY_SPACE;
						this.gameOver = true;
					} else if (target.getSymbol() == 'e') { //Remove enemy.
						livingEnemy.remove(target);
						myBoard[((Enemy)target).getLocationY()][((Enemy)target).getLocationX()]
								= EMPTY_SPACE;
					} else if (target.getSymbol() == 'b') { //Remove bullet.
						myBoard[((BulletObject)target).getY()][((BulletObject)target).getX()]
								= EMPTY_SPACE;
						((BulletObject)target).setActed(true);
					}
					//Note: x is not included as it has no unique features.
				}
				
				this.setChanged();
				this.notifyObservers(myBoard.clone());				
			}
		}
		
		waitingBullet = movingBullets;
		if(movingBullets.isEmpty()) {
			remaining = false;
		}
		
		return remaining;
	}
	
	public boolean hasRemainingBullets() {
		return !waitingBullet.isEmpty();
	}
	
	/**
	 * Returns a symbol for the bullet object.
	 * 
	 * @param theBoard The Game Board.
	 * @param theBullet The bullet being moved.
	 * @return The target if any.
	 */
	private GameObject getTarget(BulletObject theBullet) {
		GameObject target;
		int x = theBullet.getX(), y = theBullet.getY();
		
		//Shifts the values for a direction.
		if (theBullet.getDirection() == Direction.NORTH) {
			y--;
		} else if (theBullet.getDirection() == Direction.SOUTH) {
			y++;
		} else if (theBullet.getDirection() == Direction.EAST) {
			x++;
		} else if (theBullet.getDirection() == Direction.WEST) {
			x--;
		}
		
		//Checks to ensure the values are within array index range.
		if(x >= 0 && y >= 0 && x < myWidth && y < myHeight) {
			target = myBoard[y][x];
		} else {
			throw new IndexOutOfBoundsException();
		}
		
		return target;
	}

	/**
	 * Moves a character from one location on the board to another.	
	 * 
	 * @param theMovingCharacter The moving character.
	 * @param theX The new location's x value.
	 * @param theY The new location's y value.
	 */
	private void moveCharacter(GameCharacter theMovingCharacter, int theX, int theY) {
		int[] currentLocation = theMovingCharacter.getLocation();
		myBoard[currentLocation[0]][currentLocation[1]] = EMPTY_SPACE;
		myBoard[theY][theX] = theMovingCharacter;
		theMovingCharacter.setLocation(theMovingCharacter.getLocationX(),
				theMovingCharacter.getLocationY());
	}

	private void moveObject(Direction theDirection, int[] theLocation) {
		if(theDirection == Direction.NORTH) {
			theLocation[0]--;
		} else if(theDirection == Direction.SOUTH) {
			theLocation[0]++;
		} else if(theDirection == Direction.EAST) {
			theLocation[1]++;
		} else if(theDirection == Direction.WEST) {
			theLocation[1]--;
		}
	}

	private void notifyEnemies() {
		int x = myPlayer.getLocationX(), y = myPlayer.getLocationY();
		
		//Notify everything to the west.
		for(int checkX = x + 1; checkX < myBoard[0].length; checkX++) {
			if(myBoard[y][checkX].getSymbol() == 'x') {
				checkX = myBoard[0].length;
			} else if(myBoard[y][checkX].getSymbol() == 'e') {
				if(((Enemy)myBoard[y][checkX]).getFacingDirection() == Direction.WEST) {
					((Enemy)myBoard[y][checkX]).notifyEnemy(true, myPlayer.getLocationX(),
							myPlayer.getLocationY());
				}
				checkX = myBoard[0].length;
			}
		}
		//Notify everything to the east.
		for(int checkX = x - 1; checkX > 0; checkX--) {
			if(myBoard[y][checkX].getSymbol() == 'x') {
				checkX = 0;
			} else if(myBoard[y][checkX].getSymbol() == 'e') {
				if(((Enemy)myBoard[y][checkX]).getFacingDirection() == Direction.EAST) {
					((Enemy)myBoard[y][checkX]).notifyEnemy(true, myPlayer.getLocationX(),
							myPlayer.getLocationY());
				}
				checkX = 0;
			}
		}
		//Check North
		for(int checkY = y - 1; checkY > 0; checkY--) {
			if(myBoard[checkY][x].getSymbol() == 'x') {
				checkY = 0;
			} else if(myBoard[checkY][x].getSymbol() == 'e') {
				if(((Enemy)myBoard[checkY][x]).getFacingDirection() == Direction.SOUTH) {
					((Enemy)myBoard[checkY][x]).notifyEnemy(true, myPlayer.getLocationX(),
							myPlayer.getLocationY());
				}
				checkY = 0;
			}
		}
		//Check South
		for(int checkY = y + 1; checkY < myBoard.length; checkY++) {
			if(myBoard[checkY][x].getSymbol() == 'x') {
				checkY = myBoard.length;
			} else if(myBoard[checkY][x].getSymbol() == 'e') {
				if(((Enemy)myBoard[checkY][x]).getFacingDirection() == Direction.NORTH) {
					((Enemy)myBoard[checkY][x]).notifyEnemy(true, myPlayer.getLocationX(),
							myPlayer.getLocationY());
				}
				checkY = myBoard.length;
			}
		}
	}

	/**
	 * Perform all background actions.
	 */
	private void performAllMove() {
		for(Enemy enemy : this.myMoveActors) {
			Direction direction = enemy.move(myBoard);
			
			if(direction == Direction.CENTER) {
				waitingEnemy.add(enemy);
			} else {
				int[] location = new int[]{enemy.getLocationY(), enemy.getLocationX()};
				
				myBoard[enemy.getLocationY()][enemy.getLocationX()] = EMPTY_SPACE;
				moveObject(direction, location);
				myBoard[location[0]][location[1]] = enemy;
				enemy.setLocation(location[1], location[0]);
				enemy.animate();
			}
		}
		for(Enemy enemy : this.waitingEnemy) {
			Direction direction = enemy.move(myBoard.clone());
			
			int[] location = new int[]{enemy.getLocationY(), enemy.getLocationX()};
			
			myBoard[enemy.getLocationY()][enemy.getLocationX()] = EMPTY_SPACE;
			moveObject(direction, location);
			myBoard[location[0]][location[1]] = enemy;
			enemy.setLocation(location[1], location[0]);
			enemy.animate();
		}
		
		waitingEnemy.clear();
	
		this.myMoveActors.clear();
	}

	private void performAllShoot() {
		for(Enemy enemy : this.myShootActors) {
			this.shoot(enemy);
		}
		myShootActors.clear();
	}

	private void setActions() {
		for(Enemy enemy : this.livingEnemy) {
			Actions action = enemy.performAction(myBoard);
			if(action == Actions.RELOAD) {
				enemy.reload();
			} else if(action == Actions.MOVE) {
				myMoveActors.add(enemy);
			} else if(action == Actions.SHOOT) {
				myShootActors.add(enemy);
			} else if(action == Actions.ARRIVED) {
				boolean scanPlayer = enemy.scanBoard(myBoard);
				if(scanPlayer) {
					enemy.notifyEnemy(true, myPlayer.getLocationX(), myPlayer.getLocationY());
				} else {
					enemy.notifyEnemy(false, 0, 0);
				}
			}
		}
	}

	/**
	 * Method used by the character to perform a range attack.
	 * 
	 * @param theCharacter The Game Character performing the shoot attack.
	 */
	private void shoot(GameCharacter theCharacter) {
		int x = theCharacter.getLocationX(), y = theCharacter.getLocationY();
		theCharacter.setAmmo(theCharacter.getCurrAmmo() - 1);
		
		if (theCharacter.getFacingDirection() == Direction.NORTH) {
			y--;
		} else if(theCharacter.getFacingDirection() == Direction.SOUTH) {
			y++;
		} else if(theCharacter.getFacingDirection() == Direction.EAST) {
			x++;
		} else { //If West.
			x--;
		}
		
		GameObject temp = myBoard[y][x];
		
		if (temp.getSymbol() == ' ') {
			GameObject bullet = new BulletObject(theCharacter.getFacingDirection(), x, y);
			myBoard[y][x] = bullet;
			this.waitingBullet.add((BulletObject) bullet);
			setChanged();
			notifyObservers(myBoard.clone());
		} else if (temp.getSymbol() == 'p') {
			((PlayerCharacter) myPlayer).gameOver();
			myBoard[myPlayer.getLocationY()][myPlayer.getLocationX()] = EMPTY_SPACE;
			this.gameOver = true;
		} else if (temp.getSymbol() == 'e') {
			myBoard[((Enemy) temp).getLocationY()][((Enemy) temp).getLocationX()] = EMPTY_SPACE;
			this.livingEnemy.remove(temp);
		} else if (temp.getSymbol() == 'x') {

		}
	}

	public void update() {
		this.setChanged();
		this.notifyObservers(myBoard.clone());
	}
	
	public void animateAll() {
		myPlayer.animate();
		for(Enemy enemy : livingEnemy) {
			enemy.animate();
		}
		update();
	}
	
	private class AnimationTimer {
		
		private Timer myTimer;
		
		public AnimationTimer(Board theBoard, int delay) {
			if(delay == 30) {
				myTimer = new Timer(delay, new MoveBulletAction());
			} else {
				myTimer = new Timer(delay, new AnimateCharacterAction());
			}
		}
		
		public void start() {
			myTimer.start();
		}
	}
	
	private class AnimateCharacterAction implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			animateAll();
		}
	}
	
	private class MoveBulletAction implements ActionListener {		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(hasRemainingBullets()) {
				moveBullets();
			}
		}
	}
}
