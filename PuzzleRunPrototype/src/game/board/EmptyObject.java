package game.board;

/**
 * This objects represents empty space on the board.
 * 
 * @author Jim
 *
 */
public class EmptyObject extends GameObject {

	private static final char SYMBOL = ' ';
	
	public EmptyObject() {
		super(SYMBOL);
		this.setImage("floor.png");
	}

}
