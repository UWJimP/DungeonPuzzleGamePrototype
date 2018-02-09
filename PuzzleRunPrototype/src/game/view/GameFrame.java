package game.view;

//import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * The Frame used by the game.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 12/14/2017
 */
public class GameFrame extends JFrame {
	
	/**
	 * The serial code of the class.
	 */
	private static final long serialVersionUID = 6727656709509878024L;
	
	/**
	 * The size of each pixel.
	 */
	//private static final int PIXEL_SIZE = 10;

	/**
	 * The constructor of the frame.
	 * 
	 * @param theWidth The width of the frame.
	 * @param theHeight The height of the frame.
	 */
	public GameFrame(final int theWidth, final int theHeight) {
		super("Puzzle Run");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(new Dimension(theWidth * PIXEL_SIZE, theHeight * PIXEL_SIZE));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		setVisible(true);
	}
}
