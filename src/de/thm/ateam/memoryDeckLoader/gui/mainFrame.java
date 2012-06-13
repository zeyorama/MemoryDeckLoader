/**
 * MemoryDeckLoader
 * de.thm.ateam.memoryDeckLoader.gui
 * mainFrame.java
 * 13.06.2012
 * 
 * by Frank Kevin Zey
 */
package de.thm.ateam.memoryDeckLoader.gui;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * @author Frank Kevin Zey
 *
 */
public class mainFrame extends JFrame {

	private static final long serialVersionUID = 3434479642153869508L;
	
	private void initialize(boolean b) throws Exception {
		this.setSize(550, 450);
		this.setLayout(new FlowLayout());
		this.setTitle("Memory Deck Packer");
		
		if (b)
			/*
			 * set Nimbus LookAndFeel
			 */
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			    if ("Nimbus".equals(info.getName())) {
			       UIManager.setLookAndFeel(info.getClassName());
			       break;
			    }
			}
		
		
	}
	
	public mainFrame(boolean b) throws Exception {
		this.initialize(b);
	}

}
