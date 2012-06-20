/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader
 * Deck.java
 * 04.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author Frank Kevin Zey
 *
 */
public class Deck {
	
	ArrayList<String> img;
	private File path = null;
	
	public Deck(File path) throws IOException {
		this.path = path;
		img = new ArrayList<String>();
	}
	
	public Deck() {
		img = new ArrayList<String>();
	}
	
	public void add(String s) {
		img.add(s);
	}
	
	public ZipFile genZipFile() throws IOException {
		// Create a buffer for reading the files
	    try {
	        // Create the ZIP file
	        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(path));
	    
	        // Compress the files
	        for (String s : img) {
	        	byte buf[] = new byte[(int) new File(s).length()];
	            FileInputStream in = new FileInputStream(s);
	    
	            // Add ZIP entry to output stream.
	            out.putNextEntry(new ZipEntry(s));
	    
	            // Transfer bytes from the file to the ZIP file
	            int len;
	            while ((len = in.read(buf)) > 0)
	                out.write(buf, 0, len);
	    
	            // Complete the entry
	            out.closeEntry();
	            in.close();
	        }
	    
	        // Complete the ZIP file
	        out.close();
	    } catch (IOException e) {
	    }
		
		File f = path;
		path = null;
		return new ZipFile(f, ZipFile.OPEN_READ);
	}
	
	public void deleteImage(String s) {
		img.remove(s);
	}
	
	public void deleteByIndex(int index) {
		img.remove(index);
	}
	
	public void reset() {
		this.img = new ArrayList<String>();
		this.path = null;
	}

}
