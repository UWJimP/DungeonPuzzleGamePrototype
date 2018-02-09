package game.board;

public class WallOpenWestObject extends GameObject {

	private static final char SYMBOL = 'x';
	
	public WallOpenWestObject() {
		super(SYMBOL);
		this.setImage("brick_cont_W.png");
	}	
}
