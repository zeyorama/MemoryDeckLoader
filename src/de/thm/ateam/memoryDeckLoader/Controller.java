/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader
 * Controller.java
 * 14.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import de.thm.ateam.memoryDeckLoader.gui.mainFrame;
import de.thm.ateam.memoryDeckLoader.io.ImageLoader;

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
				System.setOut(System.out);
				System.out.println("error: Nimbus not found");
				System.out.println(e.getLocalizedMessage());
				mF = new mainFrame(false, this);
				mF.setVisible(true);
				return true;
			} catch (Exception e1) {
				return false;
			}
		}
	}
	
	public void newDeck(Component c) {
		try {
			File f = ImageLoader.getInstance().setNewFileWithUI(c);
			if (f != null)
				deck = new Deck(f.getAbsolutePath());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Deck getCurrentDeck() {
		return this.deck;
	}
	
	public void addImage(String pathToImage) {
		deck.add(pathToImage);
	}
	
	public void deleteDeck() {
		this.deck = null;
	}
}
