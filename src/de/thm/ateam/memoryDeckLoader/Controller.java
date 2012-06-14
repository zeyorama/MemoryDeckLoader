/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader
 * Controller.java
 * 14.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader;

import java.io.IOException;

import de.thm.ateam.memoryDeckLoader.gui.mainFrame;

/**
 * @author Frank Kevin Zey
 *
 */
public class Controller {

	private mainFrame mF;
	private Deck deck;
	
	public Controller() {}
	
	public boolean startGUI() {
		try {
			mF = new mainFrame(true);
			mF.setVisible(true);
		} catch (Exception e) {
			try {
				mF = new mainFrame(false);
				mF.setVisible(true);
			} catch (Exception e1) {
				return false;
			}
		}
		
		return true;
	}
	
	public void newDeck(int imageCount) {
		deck = new Deck(imageCount);
	}
	
	public void addImage(String pathToImage) {
		try {
			deck.addImage(pathToImage);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
