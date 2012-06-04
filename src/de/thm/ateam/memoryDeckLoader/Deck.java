/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader
 * Deck.java
 * 04.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * @author Frank Kevin Zey
 *
 */
public class Deck {
	
	Image []img;
	
	public Deck(byte count) {
		img = new Image[count];
	}
	
	public void addImage(String path) {
		File f = new File(path);
		try {
			ZipFile zip = new ZipFile(new File("deck.zip"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
