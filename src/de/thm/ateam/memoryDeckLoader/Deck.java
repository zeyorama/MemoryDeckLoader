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
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @author Frank Kevin Zey
 *
 */
public class Deck {
	
	ArrayList<Image> img;
	private ZipOutputStream zos;
	
	public Deck(String path) throws IOException {
		File zip = new File(path);
		
		if (zip.createNewFile())
			System.out.println(path + " successfully created");
		
		img = new ArrayList<Image>();
		zos = new ZipOutputStream(new FileOutputStream(zip));
	}
	
	public Deck() {
		img = new ArrayList<Image>();
	}
	
	public ImageIcon addImage(String path) throws IOException {
	    BufferedImage i = ImageIO.read(new File(path));
		
		zos.putNextEntry(new ZipEntry(path));
	    System.out.println(path + " added to zip file");
	    
	    return new ImageIcon(i);
	}
	
	public void deleteImage(Image i) {
		img.remove(i);
	}
	
	public ZipFile genZipFile(String path) throws IOException {
		ZipFile zip = new ZipFile(path);
		
		return zip;
	}
	
	public void deleteByIndex(int index) {
		img.remove(index);
	}

}
