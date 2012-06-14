/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader
 * Controller.java
 * 14.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader;

import de.thm.ateam.memoryDeckLoader.gui.mainFrame;

/**
 * @author Frank Kevin Zey
 *
 */
public class Controller {

	public Controller() {}
	
	public void startGUI() {
		try {
			new mainFrame(true).setVisible(true);
		} catch (Exception e) {
			try {
				new mainFrame(false).setVisible(true);
			} catch (Exception e1) {
				System.exit(-1);
			}
		}
	}
	
}
