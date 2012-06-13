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
	
	Image []img;
	private ZipOutputStream zos;
	
	public Deck(byte count, String path) throws IOException {
		File zip = new File(path);
		
		if (zip.createNewFile())
			System.out.println(path + " successfully created");
		
		img = new Image[count];
		zos = new ZipOutputStream(new FileOutputStream(zip));
	}
	
	public ImageIcon addImage(String path) throws IOException {
	    BufferedImage i = ImageIO.read(new File(path));
		
		zos.putNextEntry(new ZipEntry(path));
	    System.out.println(path + " added to zip file");
	    
	    return new ImageIcon(i);
	}
	
	public void deleteImage(Image i) {
		for (int j = 0; j < img.length; j++) {
			if (i.equals(img[j])) {
				img[j] = null;
				break;
			}
			
		}
		
		img = this.reorganizeArray();
	}
	
	public ZipFile genZipFile(String path) throws IOException {
		ZipFile zip = new ZipFile(path);
		
		return zip;
	}
	
	private Image[] reorganizeArray() {
		Image[] i = img;
		
		for (int index = 0; index < i.length; index++) {
			if (i[index] == null)
				if (index+1 < i.length) {
					i[index] = i[index+1];
					i[index+1] = null;
				}
		}
		
		return i;
	}

}
