/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader
 * MemoryDeckLoader.java
 * 04.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader;

/**
 * @author Frank Kevin Zey
 *
 */
public class MemoryDeckLoader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Controller c = new Controller();
		
		if (!c.startGUI())
			System.out.println("error: gui not drawable");
		
	}

}
