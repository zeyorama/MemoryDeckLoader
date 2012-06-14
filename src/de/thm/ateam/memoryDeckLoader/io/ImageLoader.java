/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader.io
 * ImageLoader.java
 * 14.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader.io;

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
	public File getImageWithUI() {
		JFileChooser fc = new JFileChooser();
		File f = null;
		
		fc.setFileFilter(new FileFilter() {
			
			public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".jpeg") || f.isDirectory();
            }
            public String getDescription() {
                return "*.jpeg";
            }
		});
		fc.setAccessory(new ImagePreview(fc));
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			f = fc.getSelectedFile();
		
		return f;
	}

}
