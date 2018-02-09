package game.characters.enemy.ai;

import game.board.GameObject;
import game.characters.Enemy;
import game.model.Actions;
import game.model.Direction;

public class AlertBehavior extends Behavior {

	private int knownPlayerLocationX;
	
	private int knownPlayerLocationY;
	
	public AlertBehavior(int playerLocationX, int playerLocationY) {
		knownPlayerLocationX = playerLocationX;
		knownPlayerLocationY = playerLocationY;
	}
	
	@Override
	public Actions performAction(GameObject[][] theBoard, Enemy enemy) {
		Actions action = Actions.MOVE;
		
		if(enemy.getCurrAmmo() == 0) {
			action = Actions.RELOAD;
		} else if(enemy.getFacingDirection() == Direction.NORTH) {
			for(int y = enemy.getLocationY() - 1; y > 0; y--) {
				if(theBoard[y][enemy.getLocationX()].getSymbol() == 'p') {
					action = Actions.SHOOT;
					y = 0;
				} else if(theBoard[y][enemy.getLocationX()].getSymbol() != ' ') {
					y = 0;
				}
			}
		} else if(enemy.getFacingDirection() == Direction.SOUTH) {
			for(int y = enemy.getLocationY() + 1; y < theBoard.length; y++) {
				if(theBoard[y][enemy.getLocationX()].getSymbol() == 'p') {
					action = Actions.SHOOT;
					y = theBoard.length;
				} else if(theBoard[y][enemy.getLocationX()].getSymbol() != ' ') {
					y = theBoard.length;
				}
			}
		} else if(enemy.getFacingDirection() == Direction.EAST) {
			for(int x = enemy.getLocationX() + 1; x < theBoard[0].length; x++) {
				if(theBoard[enemy.getLocationY()][x].getSymbol() == 'p') {
					action = Actions.SHOOT;
					x = theBoard[0].length;
				} else if(theBoard[enemy.getLocationY()][x].getSymbol() != ' ') {
					x = theBoard[0].length;
				}
			}
		} else if(enemy.getFacingDirection() == Direction.WEST) {
			for(int x = enemy.getLocationX() - 1; x > 0; x--) {
				if(theBoard[enemy.getLocationY()][x].getSymbol() == 'p') {
					action = Actions.SHOOT;
					x = 0;
				} else if(theBoard[enemy.getLocationY()][x].getSymbol() != ' ') {
					x = 0;
				}
			}
		}
		if(enemy.getLocationX() == knownPlayerLocationX &&
				enemy.getLocationY() == knownPlayerLocationY &&
				action != Actions.SHOOT){
			action = Actions.ARRIVED;
		}
		
		return action;
	}

}
