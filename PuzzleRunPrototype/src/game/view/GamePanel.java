package game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import game.board.EmptyObject;
import game.board.GameObject;

public class GamePanel extends JPanel implements Observer {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4608208034634573375L;
	
	/**
	 * A copy of the game board which needs to be drawn.
	 */
	private GameObject[][] myBoard;
	
	public GamePanel() {
		super();
        this.setBackground(Color.WHITE);
	}
	
	@Override
	public void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);
		
		drawBoard(theGraphics);
	}

	private void drawBoard(final Graphics theGraphics) {
		for(int row = 0; row < myBoard.length; row++) {
			for(int column = 0; column < myBoard[0].length; column++) {
				try {
					if(myBoard[row][column].getSymbol() == 'p') {
						BufferedImage image1 = ImageIO.read(new EmptyObject().getImage());
						theGraphics.drawImage(image1, column * 50, row * 50, null);
						BufferedImage image2 = ImageIO.read(myBoard[row][column].getImage());
						theGraphics.drawImage(image2, column * 50, row * 50, null);
					} else {
						BufferedImage image = ImageIO.read(myBoard[row][column].getImage());
						theGraphics.drawImage(image, column * 50, row * 50, null);
					}					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}		
//		System.out.println();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg1 instanceof GameObject[][]) {
			myBoard = (GameObject[][]) arg1;
			repaint();
		}
	}
}
