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
			mF = new mainFrame(true, this);
			mF.setVisible(true);
			return true;
		} catch (Exception e) {
			try {
				mF = new mainFrame(false, this);
				mF.setVisible(true);
				return true;
			} catch (Exception e1) {
				return false;
			}
		}
	}
	
	public void newDeck() {
		deck = new Deck();
	}
	
	public Deck getCurrentDeck() {
		return this.deck;
	}
	
	public void addImage(String pathToImage) {
		try {
			deck.addImage(pathToImage);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void deleteDeck() {
		this.deck = null;
	}
}
