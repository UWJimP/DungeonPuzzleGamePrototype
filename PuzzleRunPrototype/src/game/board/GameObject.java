package game.board;

import java.io.File;

/**
 * The objects placed on the game board.
 * 
 * @author Jim Phan phanjim2@hotmail.com
 * @version 11/28/2017
 */
public abstract class GameObject {

	/**
	 * The character representation of the game object on the board.
	 */
	private char mySymbol;
	
	/**
	 * The File used to draw the image.
	 */
	private File myImage;
	
	/**
	 * The constructor. Sets up the object's symbol.
	 * 
	 * @param theSymbol The character's symbol.
	 */
	public GameObject(final char theSymbol) {
		mySymbol = theSymbol;
	}
	
	/**
	 * Returns the character of the object.
	 * 
	 * @return The char value of the object.
	 */
	public char getSymbol() {
		return mySymbol;
	}
	
	/**
	 * Set the String file path for the object.
	 * 
	 * @param theImage The string of the file path.
	 */
	public void setImage(String theImage) {
		myImage = new File("images/" + theImage);
	}
	
	/**
	 * Return the File of the image.
	 * 
	 * @return The File object of the image.
	 */
	public File getImage() {
		return myImage;
	}
}
