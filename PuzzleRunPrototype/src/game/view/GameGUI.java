package game.view;

import javax.swing.JFrame;

public class GameGUI {

    /**
     * The default width of the board.
     */
    private static final int BOARD_WIDTH = 10;
    
    /**
     * The default height of the board.
     */
    private static final int BOARD_HEIGHT = 20;
    
    /**
     * The Frame that holds the Tetris game data.
     */
    private final JFrame myFrame;
	
    private final GameSystem mySystem;
    
    /**
     * Sets up the Frame and its attributes.
     */
    public GameGUI() {        
        myFrame = new GameFrame(BOARD_WIDTH, BOARD_HEIGHT);
        mySystem = new GameSystem(myFrame);
    }
    
    public void start() {
    	mySystem.setUp();
    }
}
