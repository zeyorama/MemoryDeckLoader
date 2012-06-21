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
		int c = 0;
		
		try {
	        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(path));
	    
	        for (String s : img) {
	        	if (s.equals("")) {
	        		c++;
	        		continue;
	        	}
	        	
	        	if ( c > 33 )
	        		break;
	        	
	        	byte buf[] = new byte[(int) new File(s).length()];
	            FileInputStream in = new FileInputStream(s);
	    
	            out.putNextEntry(new ZipEntry((new Integer(c++).toString() + ".jpg")));
	    
	            int len;
	            while ((len = in.read(buf)) > 0)
	                out.write(buf, 0, len);
	    
	            out.closeEntry();
	            in.close();
	        }
	    
	        out.close();
	    } catch (IOException e) {
	    	System.out.println(e.getMessage());
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
