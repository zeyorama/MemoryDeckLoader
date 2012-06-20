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
	private ZipOutputStream zos;
	private ZipFile zip;
	public Deck(String path) throws IOException {
		zip = new ZipFile(new File(path));
		
		img = new ArrayList<String>();
		zos = new ZipOutputStream(new FileOutputStream(path));
	}
	
	public Deck() {
		img = new ArrayList<String>();
	}
	
	public void add(String s) {
		img.add(s);
	}
	
	public ZipFile genZipFile() throws IOException {
		
		for (String s : img) {
			ZipEntry entry = new ZipEntry(s);
			zos.putNextEntry(entry);
			zos.flush();
			zos.closeEntry();
		}
		
		zos.finish();
		return zip;
	}
	
	public void deleteImage(String s) {
		img.remove(s);
	}
	
	public void deleteByIndex(int index) {
		img.remove(index);
	}

}
