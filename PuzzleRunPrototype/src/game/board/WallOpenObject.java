package game.board;

public class WallOpenObject extends GameObject {

	private static final char SYMBOL = 'x';
	
	public WallOpenObject() {
		super(SYMBOL);
		this.setImage("brick_cont.png");
	}
}
