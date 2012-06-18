/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader
 * MemoryDeckLoader.java
 * 04.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader;

import java.io.PrintStream;

/**
 * @author Frank Kevin Zey
 *
 */
public class MemoryDeckLoader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream stdout = System.out;
		Controller c = new Controller();
		
		if (c.startGUI()) {
			System.setOut(stdout);
			System.out.println("error: gui not drawable");
		}
		
	}

}
