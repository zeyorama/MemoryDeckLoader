/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader
 * MemoryDeckLoader.java
 * 04.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader;

import de.thm.ateam.memoryDeckLoader.gui.mainFrame;

/**
 * @author Frank Kevin Zey
 *
 */
public class MemoryDeckLoader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
