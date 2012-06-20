/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader.io
 * ImageLoader.java
 * 14.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader.io;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * ImageLoader class as singleton class to get image files
 * 
 * @author Frank Kevin Zey
 *
 */
public class ImageLoader {
	
	private static ImageLoader il = null;
	
	private ImageLoader() {}
	
	/**
	 * If no ImageLoader currently instantiated, a new will be created, otherwise the current will be returned.
	 * 
	 * @return ImageLoader Returns ImageLoader object
	 */
	public static ImageLoader getInstance() {
		if (il == null)
			il = new ImageLoader();
		
		return il;
	}
	
	/**
	 * Opens an open dialog to select a file
	 * 
	 * @return File Returns a selected file, otherwise null
	 */
	public File getImageWithUI(Component c) {
		JFileChooser fc = new JFileChooser();
		File f = null;
		
		/* setting file filter for *.jpeg files */
		fc.setFileFilter(new FileFilter() {
			
			public boolean accept(File f) {
                /* returning only jpeg, jpg or directories */
				return f.getName().toLowerCase().endsWith(".jpeg")
						|| f.isDirectory()
						|| f.getName().toLowerCase().endsWith(".jpg");
            }
			
            public String getDescription() {
            	/* description for jpeg files */
                return "JPEG Image File (*.jpeg, *.jpg)";
            }
		});
		fc.setAccessory(new ImagePreview(fc));
		if (fc.showOpenDialog(c) == JFileChooser.APPROVE_OPTION)
			f = fc.getSelectedFile();
		
		return f;
	}
	
	/**
	 * Opens an save dialog to select a file
	 * 
	 * @return File Returns a selected file, otherwise null
	 */
	public File setNewFileWithUI(Component c) {
		JFileChooser fc = new JFileChooser();
		File f = null;
		if (fc.showSaveDialog(c) == JFileChooser.APPROVE_OPTION)
			f = fc.getSelectedFile();
		
		return f;
	}

}
