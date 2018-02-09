package game.board;

public class WallOpenEastObject extends GameObject {

	private static final char SYMBOL = 'x';
	
	public WallOpenEastObject() {
		super(SYMBOL);
		this.setImage("brick_cont_E.png");
	}
}
