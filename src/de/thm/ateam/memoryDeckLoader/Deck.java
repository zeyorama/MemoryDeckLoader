/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader
 * Deck.java
 * 04.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader;

import java.io.BufferedInputStream;
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
	private ZipOutputStream zos;
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
		try {
			if (path == null) {
				System.out.println("file is null");
				return null;
			}
			System.out.println(path.getAbsolutePath());
			zos  = new ZipOutputStream( new FileOutputStream(path) ) ;
		} catch(IOException ex) {
		   ex.printStackTrace();
		
		} finally {
			BufferedInputStream bis = null;
			try	{
				for (String s : img) {
					bis = new BufferedInputStream( new FileInputStream(s) );
					int avail = bis.available();
					byte[] buffer = new byte[avail];
					
					if ( avail>0 )
						bis.read(buffer, 0, avail);
	
					ZipEntry e = new ZipEntry(new File(s).getAbsolutePath());
					
					zos.putNextEntry(e);
					zos.write(buffer, 0, buffer.length);
					zos.closeEntry();
					
				}
			} catch(IOException ex) {
			   ex.printStackTrace();
			
			} finally {
			   try {
			      if(bis!=null)
			    	  bis.close();
			      
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   
			   }
			}
			
			try {
				if(zos!=null)
					zos.close();
			   
				return null;
			   
			} catch(Exception ex) {
				ex.printStackTrace();
			
			}
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
