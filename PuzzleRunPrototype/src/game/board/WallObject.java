package game.board;

public class WallObject extends GameObject {

	private static final char SYMBOL = 'x';
	
	public WallObject() {
		super(SYMBOL);
		this.setImage("brick_closed.png");
	}
}
